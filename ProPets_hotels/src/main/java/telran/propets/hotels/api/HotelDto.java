package telran.propets.hotels.api;

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

	public LocationDto getLocation() {
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

	public void setLocation(LocationDto location) {
		this.location = location;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
}
