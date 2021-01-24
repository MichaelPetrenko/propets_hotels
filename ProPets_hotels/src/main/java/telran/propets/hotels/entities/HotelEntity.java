package telran.propets.hotels.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import telran.propets.hotels.api.Address;
import telran.propets.hotels.api.HotelDto;

@Document(collection = "hotels")
public class HotelEntity {
	
	@Id
	String id;
	String userName;
	String avatar;
	String post_header;
	String text;
	String phone;
	double min_price;
	double max_price;
	Address address;
	@GeoSpatialIndexed(name = "Location")
	double[] location;
	String images;
	
	public HotelEntity() {}
	
	public HotelEntity(HotelDto dto) {
		this.userName = dto.userName;
		this.avatar = dto.avatar;
		this.post_header = dto.post_header;
		this.text = dto.text;
		this.phone = dto.phone;
		this.min_price = dto.min_price;
		this.max_price = dto.max_price;
		this.address = dto.address;
		this.images = dto.images;
		double[] res = new double[2];
		res[0] = dto.location.longitude;
		res[1] = dto.location.latitude;
		this.location = res;
	}

	public HotelEntity(String userName, String avatar, String post_header, String text, String phone, double min_price,
			double max_price, Address address, double[] location, String images) {
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

	public String getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getPost_header() {
		return post_header;
	}

	public String getText() {
		return text;
	}

	public String getPhone() {
		return phone;
	}

	public double getMin_price() {
		return min_price;
	}

	public double getMax_price() {
		return max_price;
	}

	public Address getAddress() {
		return address;
	}

	public double[] getLocation() {
		return location;
	}

	public String getImages() {
		return images;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setPost_header(String post_header) {
		this.post_header = post_header;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setMin_price(double min_price) {
		this.min_price = min_price;
	}

	public void setMax_price(double max_price) {
		this.max_price = max_price;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setLocation(double[] location) {
		this.location = location;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
}
