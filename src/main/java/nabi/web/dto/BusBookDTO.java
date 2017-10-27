package nabi.web.dto;

public class BusBookDTO {
	private String email;
	private String routeId;
	private String stationId;
	private int isBook;
	
public BusBookDTO(){}


	public String getStationId() {
	return stationId;
}


public void setStationId(String stationId) {
	this.stationId = stationId;
}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public int getIsBook() {
		return isBook;
	}
	public void setIsBook(int isBook) {
		this.isBook = isBook;
	}


	@Override
	public String toString() {
		return "BusBookDTO [email=" + email + ", routeId=" + routeId + ", stationId=" + stationId + ", isBook=" + isBook
				+ "]";
	}

	

	
	
}
