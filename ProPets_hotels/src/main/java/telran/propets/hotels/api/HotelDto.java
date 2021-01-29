package telran.propets.hotels.api;

import java.util.Arrays;

import telran.propets.hotels.entities.HotelEntity;

public class HotelDto {

	public String id;
	public String userLogin;
	public String userName;
	public String avatar;
	public String post_header;
	public String text;
	public String phone;
	public double min_price;
	public double max_price;
	public Address address;
	public LocationDto location;
	public String[] images;
	
	public HotelDto() {}

	public HotelDto(String id, String userLogin, String userName, String avatar, String post_header, String text, String phone, double min_price,
			double max_price, Address address, LocationDto location, String[] images) {
		super();
		this.id = id;
		this.userLogin = userLogin;
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
		this.id = ent.getId();
		this.userLogin = ent.getUserLogin();
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

	public HotelDto(String userLogin, String userName, String avatar, String post_header, String text, String phone,
			double min_price, double max_price, Address address, LocationDto location, String[] images) {
		super();
		this.id = null;
		this.userLogin = userLogin;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + Arrays.hashCode(images);
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		long temp;
		temp = Double.doubleToLongBits(max_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(min_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((post_header == null) ? 0 : post_header.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((userLogin == null) ? 0 : userLogin.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HotelDto other = (HotelDto) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (!Arrays.equals(images, other.images))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (Double.doubleToLongBits(max_price) != Double.doubleToLongBits(other.max_price))
			return false;
		if (Double.doubleToLongBits(min_price) != Double.doubleToLongBits(other.min_price))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (post_header == null) {
			if (other.post_header != null)
				return false;
		} else if (!post_header.equals(other.post_header))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (userLogin == null) {
			if (other.userLogin != null)
				return false;
		} else if (!userLogin.equals(other.userLogin))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HotelDto [id = " + id + "]";
	}
	
	
	
	
	
}
