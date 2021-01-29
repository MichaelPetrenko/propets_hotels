package telran.propets.hotels;

import static org.junit.Assert.*;

import org.junit.jupiter.api.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.propets.hotels.api.Address;
import telran.propets.hotels.api.HotelDto;
import telran.propets.hotels.api.LocationDto;
import telran.propets.hotels.service.impl.HotelsManagementMongo;
import telran.propets.hotels.service.interfaces.HotelsManagement;

@SpringBootApplication
class ProPetsHotelsApplicationTests {
	
	final static HotelDto HOTEL_DTO = new HotelDto(
			"reizmp4@gmail.com", "Michael Petrenko", 
			"https://www.gravatar.com/avatar/0?d=mp", "New Zoo Hotel in Rehovot", 
			"Text", "88005553535", 
			100, 1000, 
			new Address("Israel", "Petah Tikva", "David Wolfson", "13"), 
			new LocationDto(0.0, 0.0), 
			new String[] {"https://img.jpg","https://img.jpg"}
					);
	
	final static HotelDto HOTEL_DTO_CHANGED = new HotelDto(
			"reizmp4@gmail.com", "Michael Petrenko", 
			"https://www.gravatar.com/avatar/0?d=mp", "Changed Header", 
			"Changed Text", "88005553535", 
			100, 1000, 
			new Address("Israel", "Petah Tikva", "David Wolfson", "13"), 
			new LocationDto(0.0, 0.0), 
			new String[] {"https://img.jpg","https://img.jpg"}
					);
	
	final static String USER_LOGIN = "reizmp4@gmail.com";
	final static String X_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJsb2dpbiI6InJlaXptcDRAZ21haWwuY29tIiwicGFzc3dvcmQiOiIkMmEkMTAkejFvMXdBVnpzdXlvWlpPM0VXU1BrZVkxaEpEVk91N2VHT21FRHV1T1RNelhCNU9hZUpvYU8iLCJ0aW1lc3RhbXAiOjE2MTQ1MTkyMzM2MTIsInJvbGUiOlsiQURNSU4iLCJVU0VSIl19.ggmpxVRyfK2hqCKhzYHAtbr08TFDGx0ygyrUojbLE78";
	static String POST_ID = null;
	static HotelsManagement hotels = null;
	
	@BeforeAll
	static void beforeAll() {
		ConfigurableApplicationContext cac = SpringApplication.run(ProPetsHotelsApplicationTests.class);
		hotels = cac.getBean(HotelsManagementMongo.class);
		
	}
	
	@Test
	void creatingHotelTest() {
		HotelDto createdHotel = hotels.createHotel(HOTEL_DTO, USER_LOGIN, X_TOKEN);
		System.out.println(createdHotel.toString());
		POST_ID = createdHotel.id;
		assertNotNull(POST_ID);
	}
	
	@Test
	void getByIdTest() {
		HotelDto newHotel = hotels.getHotelById(POST_ID);
		assertEquals(HOTEL_DTO.post_header, newHotel.post_header);
		assertEquals(HOTEL_DTO.text, newHotel.text);
	}
	
	@Test
	void updatePostTest() {
		HotelDto updatedHotel = hotels.updateHotel(HOTEL_DTO_CHANGED, POST_ID);
		assertEquals(HOTEL_DTO_CHANGED.post_header, updatedHotel.post_header);
		assertEquals(HOTEL_DTO_CHANGED.text, updatedHotel.text);
	}
	
	@AfterAll
	static void deleteHotel() {
		HotelDto deletedHotel = hotels.deleteHotel(POST_ID, USER_LOGIN, X_TOKEN);
		assertEquals(POST_ID, deletedHotel.id);
	}

}
