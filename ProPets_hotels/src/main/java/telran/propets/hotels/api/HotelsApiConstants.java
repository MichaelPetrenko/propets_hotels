package telran.propets.hotels.api;

public interface HotelsApiConstants {

	String GET_POST_BY_ID = 	"/en/v1/id/{idPost}";		//GET
	String VIEW_POST_PAGEABLE = "/en/v1/view/";				//GET
	
	String CREATE_POST = 		"/en/v1/login/{login}";		//POST
	String GET_USER_DATA = 		"/en/v1/userdata";			//POST
	
	String UPDATE_POST = 		"/en/v1/{id}";				//PUT
	String DELETE_POST = 		"/en/v1/{id}";				//DELETE

}
