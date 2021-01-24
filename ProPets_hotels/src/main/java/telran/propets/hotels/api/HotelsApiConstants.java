package telran.propets.hotels.api;

public interface HotelsApiConstants {

	String GET_HOTEL_BY_ID = 	"/en/v1/id/{idPost}";		//GET
	String VIEW_HOTELS_PAGEABLE = "/en/v1/view/";				//GET
	
	String CREATE_HOTEL = 		"/en/v1/login/{login}";		//POST
	String GET_USER_DATA = 		"/en/v1/userdata";			//POST
	
	String UPDATE_HOTEL = 		"/en/v1/{id}";				//PUT
	String DELETE_HOTEL = 		"/en/v1/{id}";				//DELETE

}
