package telran.propets.hotels.service.impl;

import java.net.URI;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import telran.propets.hotels.api.HotelDto;
import telran.propets.hotels.api.ResponcePageableDto;
import telran.propets.hotels.api.codes.BadRequestException;
import telran.propets.hotels.api.codes.BadTokenException;
import telran.propets.hotels.api.codes.BadURIException;
import telran.propets.hotels.api.codes.ForbiddenException;
import telran.propets.hotels.api.codes.NoContentException;
import telran.propets.hotels.api.codes.NotExistsException;
import telran.propets.hotels.dao.HotelsRepository;
import telran.propets.hotels.entities.HotelEntity;
import telran.propets.hotels.service.interfaces.HotelsManagement;

@Service
public class HotelsManagementMongo implements HotelsManagement {

	@Autowired
	HotelsRepository repo;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Override
	public HotelDto createHotel(HotelDto dto, String userLogin, String xToken) {
		if(dto == null) {
			throw new NoContentException("DTO not exists!");
		}
		if(userLogin==null) {
			throw new NoContentException("User login not exists!");
		}
		
		HotelEntity entity = new HotelEntity(dto);
		repo.save(entity);
		
		String id = entity.getId();

		try {
			addPostToActivites(userLogin, xToken, id);
		} catch (Exception e) {
			if (e instanceof Forbidden) {
				throw new ForbiddenException();
			} else if (e instanceof Unauthorized) {
				throw new BadTokenException();
			} else if (e instanceof BadRequest) {
				throw new BadRequestException();
			} else
				throw new NotExistsException();
		}
		return dto;
	}

	@Override
	public HotelDto updateHotel(HotelDto dto, String id) {
		if(dto == null || id == null) {
			throw new NoContentException();
		}
		
		return null;
	}

	@Override
	public HotelDto deleteHotel(String id, String xToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelDto getHotelById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponcePageableDto viewPostPageable(int items, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getUserData(String[] listID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void addPostToActivites(String userLogin, String xToken, String hotelID) {
		String endpointAddActivity = 
				"https://propets-me.herokuapp.com/" 
				+ "en/v1/" 
				+ userLogin 
				+ "/activity/"
				+ hotelID;

		URI uri;
		try {
			uri = new URI(endpointAddActivity);
		} catch (Exception e) {
			System.out.println("Error URI");
			throw new BadURIException();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("X-Token", xToken);
		headers.set("X-ServiceName", "hotels");

		HttpEntity<Void> request = new HttpEntity<>(headers);
		@SuppressWarnings("unused")
		ResponseEntity<Void> responceFromAddUserActivity = restTemplate.exchange(uri, HttpMethod.PUT, request,
				Void.class);

	}

}
