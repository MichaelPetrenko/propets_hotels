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
public class AdvancedFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();

		/** if ((path.matches("/en/v1/login/[^/]+")))
				{

			String xToken = request.getHeader("X-Token");

			if (xToken == null || xToken == "") 
			{
				response.sendError(401);
				return;
			}
			
			String postId = path.split("/")[3];
			System.out.println(postId);
			
			
			if(entity==null) {
				response.sendError(401);
				return;
			}
			String loginEntity = entity.getUserName();
			
			
			try {
				TokenValidationRequestor tvr = new TokenValidationRequestor();
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
				TokenValidationRequestor tvr = new TokenValidationRequestor();
				String newToken = tvr.validateToken(xToken);
				response.setHeader("X-Token", newToken);
			} catch (Exception e) {
				response.sendError(403);
				return;
			}
		}
		 */
		chain.doFilter(request, response);
	}
}