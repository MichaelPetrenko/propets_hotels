package telran.propets.hotels.api;

import telran.propets.hotels.entities.HotelEntity;

public class HotelDto {

	public String userName;
	public String avatar;
	public String post_header;
	public String text;
	public String phone;
	public double min_price;
	public double max_price;
	public Address address;
	public LocationDto location;
	public String images;
	
	public HotelDto() {}

	public HotelDto(String userName, String avatar, String post_header, String text, String phone, double min_price,
			double max_price, Address address, LocationDto location, String images) {
		super();
		this.userName = userName;
		this.avatar = avatar;
		this.post_header = post_header;
		this.text = text;
		this.phone = phone;
		this.min_price = min_price;
		this.max_price = max_price;
		this.address = address;
		this.location = location;
		this.images = images;
	}
	
	public HotelDto(HotelEntity ent) {
		this.userName = ent.getUserName();
		this.avatar = ent.getAvatar();
		this.post_header = ent.getPost_header();
		this.text = ent.getText();
		this.phone = ent.getPhone();
		this.min_price = ent.getMin_price();
		this.max_price = ent.getMax_price();
		this.address = ent.getAddress();
		this.images = ent.getImages();
		
		double[] location_array = ent.getLocation();
		LocationDto locationDto = new LocationDto(location_array[0], location_array[1]);
		this.location = locationDto;
	}
	
}
