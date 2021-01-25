package telran.propets.hotels.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		if (dto == null) {
			throw new NoContentException("DTO not exists!");
		}
		if (userLogin == null) {
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
		return new HotelDto(entity);
	}

	@Override
	public HotelDto updateHotel(HotelDto dto, String id) {
		if (dto == null || id == null) {
			throw new NoContentException();
		}

		HotelEntity entity = repo.findById(id).orElse(null);
		if (entity == null) {
			throw new NotExistsException();
		}

		entity.setAddress(dto.address);
		entity.setAvatar(dto.avatar);
		entity.setImages(dto.images);
		double[] res = new double[2];
		res[0] = dto.location.longitude;
		res[1] = dto.location.latitude;
		entity.setLocation(res);
		entity.setMax_price(dto.max_price);
		entity.setMin_price(dto.min_price);
		entity.setPhone(dto.phone);
		entity.setPost_header(dto.post_header);
		entity.setText(dto.text);
		entity.setUserName(dto.userName);
		repo.save(entity);
		return new HotelDto(entity);
	}

	@Override
	public HotelDto deleteHotel(String id, String userLogin, String xToken) {
		if (id == null || xToken == null) {
			throw new NoContentException();
		}
		HotelEntity hotel = repo.findById(id).orElse(null);
		if (hotel == null) {
			throw new NotExistsException();
		}
		//
		try {
			removePostFromActivites(userLogin, id, xToken);
		} catch (Exception e) {
			e.getStackTrace();
			if (e instanceof Forbidden) {
				throw new ForbiddenException();
			} else if (e instanceof Unauthorized) {
				throw new BadTokenException();
			} else if (e instanceof BadRequest) {
				throw new BadRequestException();
			} else
				throw new NotExistsException();
		}
		repo.deleteById(id);
		return new HotelDto(hotel);
	}

	@Override
	public HotelDto getHotelById(String id) {
		if (id == null) {
			throw new NotExistsException();
		}
		HotelEntity ent = repo.findById(id).orElse(null);
		if (ent == null) {
			throw new NotExistsException();
		}

		return new HotelDto(ent);
	}

	@Override
	public ResponcePageableDto viewHotelsPageable(int items, int currentPage) {
		Pageable pageable = PageRequest.of(currentPage, items);
//		repo.findAll(pageable);
		int itemsTotal = repo.findAll(pageable).getNumberOfElements();
		List<HotelEntity> hotelsList = repo.findAll(pageable).toList();

		List<HotelDto> res = hotelsList.stream().map(hotel -> new HotelDto(hotel)).collect(Collectors.toList());

		ResponcePageableDto pDto = new ResponcePageableDto(items, currentPage, itemsTotal, res);
		return pDto;
	}

	@Override
	public Object[] getUserData(String[] listID) {
		List<HotelDto> list = new ArrayList<>();
		for (int i = 0; i < listID.length; i++) {
			HotelEntity entity = repo.findById(listID[i]).orElse(null);
			if (entity != null) {
				HotelDto dto = new HotelDto(entity);
				list.add(dto);
			}
		}
		return list.toArray();
	}

	// CUSTOM

	private void addPostToActivites(String userLogin, String xToken, String hotelID) {
		String endpointAddActivity = "https://propets-me.herokuapp.com/" + "en/v1/" + userLogin + "/activity/"
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

	private void removePostFromActivites(String userLogin, String id, String xToken) {
		String endpointRemoveActivity = "https://propets-me.herokuapp.com/" + "en/v1/" + userLogin + "/activity/" + id;

		URI uri;
		try {
			uri = new URI(endpointRemoveActivity);
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
		ResponseEntity<Void> responceFromAddUserActivity = restTemplate.exchange(uri, HttpMethod.DELETE, request,
				Void.class);

	}

}
