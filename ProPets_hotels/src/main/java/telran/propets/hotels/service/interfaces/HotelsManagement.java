package telran.propets.hotels.service.interfaces;

import telran.propets.hotels.api.HotelDto;
import telran.propets.hotels.api.ResponcePageableDto;

public interface HotelsManagement {
	
	HotelDto createHotel(HotelDto dto, String userLogin, String xToken);	//get ret x-token
	HotelDto updateHotel(HotelDto dto, String id); 							//get ret x-token
	HotelDto deleteHotel(String id, String xToken); 						//get ret x-token
	HotelDto getHotelById(String id); 										//get ret x-token;
	ResponcePageableDto viewPostPageable(int items, int currentPage); 		//get ret x-token;
	Object[] getUserData(String[] listID);									//no xtoken

}
