package telran.propets.hotels.api;

public class Address {
	
	public String country;
	public String city;
	public String street;
	public String building;
	
	public Address(String country, String city, String street, String building) {
		super();
		this.country = country;
		this.city = city;
		this.street = street;
		this.building = building;
	}

	public Address() {
		super();
	}

	@Override
	public String toString() {
		return street.replace(" ", "+")+","+building.replace(" ", "+")+","+city.replace(" ", "+")+","+country.replace(" ", "+");
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getBuilding() {
		return building;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setBuilding(String building) {
		this.building = building;
	}
	
	

}
