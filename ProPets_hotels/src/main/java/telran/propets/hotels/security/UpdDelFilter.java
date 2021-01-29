package telran.propets.hotels.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.propets.hotels.dao.HotelsRepository;
import telran.propets.hotels.entities.HotelEntity;
import telran.propets.hotels.service.TokenValidationRequestor;

@Service
public class UpdDelFilter implements Filter {
	
	@Autowired
	HotelsRepository repo;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();

		if (
			(path.matches("/en/v1/[^/]+") && request.getMethod().equalsIgnoreCase("DELETE") ||
			path.matches("/en/v1/[^/]+") && request.getMethod().equalsIgnoreCase("PUT"))
		 ) {
				TokenValidationRequestor tvr = new TokenValidationRequestor();

				String xToken = request.getHeader("X-Token");
				if(xToken==null || xToken=="") {
					response.sendError(401);
					return;
				}
				
				String postId = request.getServletPath().split("/")[3];
				HotelEntity entity = repo.findById(postId).orElse(null);
				if(entity==null) {
					response.sendError(404);
					return;
				}
				String loginEntity = entity.getUserLogin();
				
				
				try {
					String[] cred = tvr.decompileToken(xToken);
					if(!cred[0].equalsIgnoreCase(loginEntity)) {
						response.sendError(400);
						return;
					}
				} catch(Exception e) {
					response.sendError(400);
					return;
				}
							
				try {
					String newToken = tvr.validateToken(xToken);
					response.setHeader("X-Token", newToken);
				} catch (Exception e) {
					response.sendError(403);
					return;
				}
				
		}
		chain.doFilter(request, response);
	}
}
