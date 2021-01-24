package telran.propets.hotels.service;

import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import telran.propets.hotels.api.codes.BadTokenException;
import telran.propets.hotels.api.codes.NoContentException;

@Service
public class TokenValidationRequestor {
	
	public String validateToken(String token) {
		
		String endPoint = "http://propets-token.herokuapp.com/validation/en/v1/validate";
		RestTemplate restTemplate = new RestTemplate();

		URI uri;
		try {
			uri = new URI(endPoint);
		} catch (Exception e) {
			throw new NoContentException();
		}

		ResponseEntity<String> responceFromValidateToken;
		try {
			RequestEntity<String> requestToValidateToken = RequestEntity.post(uri)
					.accept(MediaType.APPLICATION_JSON)
					.body(token);
			responceFromValidateToken = restTemplate.exchange
					(uri, HttpMethod.POST, requestToValidateToken, String.class);
		} catch (Exception e) {
			throw new BadTokenException();
		}

		return responceFromValidateToken.getBody().toString();
	}
	
	public String[] decompileToken(String token) {
		String endPoint = "http://propets-token.herokuapp.com/validation/en/v1/decompile";
		RestTemplate restTemplate = new RestTemplate();

		URI uri;
		try {
			uri = new URI(endPoint);
		} catch (Exception e) {
			throw new NoContentException();
		}

		ResponseEntity<String[]> responceFromDecompile;
		try {
			RequestEntity<String> requestToDecompile = RequestEntity.post(uri).accept(MediaType.APPLICATION_JSON)
					.body(token);
			responceFromDecompile = restTemplate.exchange(uri, HttpMethod.POST, 
					requestToDecompile, String[].class);
		} catch (Exception e) {
			throw new BadTokenException();
		}
		
		return responceFromDecompile.getBody();
	}
}
