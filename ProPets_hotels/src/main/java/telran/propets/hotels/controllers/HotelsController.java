package telran.propets.hotels.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.propets.hotels.api.HotelDto;
import telran.propets.hotels.api.HotelsApiConstants;
import telran.propets.hotels.dao.HotelsRepository;
import telran.propets.hotels.service.interfaces.HotelsManagement;

@RestController
public class HotelsController {

	
	@Autowired
	HotelsRepository repo;
	
	@Autowired
	HotelsManagement hotels;
	
	@GetMapping(value = HotelsApiConstants.GET_HOTEL_BY_ID)
	HotelDto getHotelById(@PathVariable String idPost) {
		return hotels.getHotelById(idPost);
	}
	
	@PostMapping(value = HotelsApiConstants.CREATE_HOTEL)
	HotelDto createHotel(@RequestBody HotelDto dto, String userLogin, String xToken) {
		return null;
	}
	
	
}
