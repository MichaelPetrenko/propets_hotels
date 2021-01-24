package telran.propets.hotels.api;

import java.util.List;

public class ResponcePageableDto {
	
	public int itemsOnPage;
	public int currentPage;
	public int itemsTotal;
	public List<HotelDto> hotels;
	
	public ResponcePageableDto(int itemsOnPage, int currentPage, int itemsTotal, List<HotelDto> hotels) {
		super();
		this.itemsOnPage = itemsOnPage;
		this.currentPage = currentPage;
		this.itemsTotal = itemsTotal;
		this.hotels = hotels;
	}
	
	public ResponcePageableDto() {
	}
}
