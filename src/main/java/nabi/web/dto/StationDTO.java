package nabi.web.dto;

public class StationDTO {
	String routeId;
	String stationId;
	String stationName;
	String plateNo;
	int order;
	boolean isBus;
	
	public StationDTO(){}
	
	
	public StationDTO(String routeId, String stationId, String stationName, String plateNo, int order, boolean isBus) {
		super();
		this.routeId = routeId;
		this.stationId = stationId;
		this.stationName = stationName;
		this.plateNo = plateNo;
		this.order = order;
		this.isBus = isBus;
	}


	public String getPlateNo() {
		return plateNo;
	}


	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}


	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public boolean isBus() {
		return isBus;
	}
	public void setBus(boolean isBus) {
		this.isBus = isBus;
	}


	@Override
	public String toString() {
		return "StationDTO [" + (routeId != null ? "routeId=" + routeId + ", " : "")
				+ (stationId != null ? "stationId=" + stationId + ", " : "")
				+ (stationName != null ? "stationName=" + stationName + ", " : "")
				+ (plateNo != null ? "plateNo=" + plateNo + ", " : "") + "order=" + order + ", isBus=" + isBus + "]";
	}


	
	
	
	
}
