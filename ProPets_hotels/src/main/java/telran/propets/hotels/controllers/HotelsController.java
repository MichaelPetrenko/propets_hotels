package telran.propets.hotels.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.propets.hotels.api.HotelDto;
import telran.propets.hotels.api.HotelsApiConstants;
import telran.propets.hotels.api.ResponcePageableDto;
import telran.propets.hotels.dao.HotelsRepository;
import telran.propets.hotels.service.TokenValidationRequestor;
import telran.propets.hotels.service.interfaces.HotelsManagement;

@RestController
public class HotelsController {

	@Autowired
	HotelsRepository repo;
	
	@Autowired
	HotelsManagement hotels;
	
//	@Autowired
//	TokenValidationRequestor tvr;
	TokenValidationRequestor tvr = new TokenValidationRequestor();
	
	@GetMapping(value = HotelsApiConstants.GET_HOTEL_BY_ID)
	HotelDto getHotelById(@PathVariable String idPost) {
		return hotels.getHotelById(idPost);
	}
	
	@PostMapping(value = HotelsApiConstants.CREATE_HOTEL)
	HotelDto createHotel(@RequestBody HotelDto dto, @PathVariable("login") String userLogin, HttpServletRequest request) {
		String xToken = request.getHeader("X-Token");
		return hotels.createHotel(dto, userLogin, xToken);
	}
	
	@PutMapping(value = HotelsApiConstants.UPDATE_HOTEL)
	HotelDto updateHotel(@RequestBody HotelDto dto, @PathVariable("id") String id) {
		return hotels.updateHotel(dto, id);
	}
	
	@DeleteMapping(value = HotelsApiConstants.DELETE_HOTEL)
	HotelDto deleteHotel(@PathVariable("id") String id, HttpServletRequest request) {
		String xToken = request.getHeader("X-Token");
		String userLogin = tvr.decompileToken(xToken)[0];
		return hotels.deleteHotel(id, userLogin, xToken);
	}
	
	@GetMapping(value = HotelsApiConstants.UPDATE_HOTEL)
	ResponcePageableDto viewHotelsPageable(@RequestParam("itemsOnPage") int items, @RequestParam("currentPage") int currentPage) {
		return hotels.viewHotelsPageable(items, currentPage);
	}

	@PostMapping(value = HotelsApiConstants.GET_USER_DATA)
	Object[] getUserData(@RequestBody String[] listID) {
		return hotels.getUserData(listID);
	}

	
}
