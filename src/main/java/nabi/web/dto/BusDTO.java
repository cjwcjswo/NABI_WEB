package nabi.web.dto;

public class BusDTO {
	private String locationNoOne;
	private String locationNoTwo;
	private String plateNoOne;
	private String plateNoTwo;
	private String predictTimeOne;
	private String predictTimeTwo;
	private String routeId;
	private String staOrder;
	private String stationId;
	private String busName;
	private String busType;
	private String firstStation;
	private String lastStation;
	private String upFirstTime;
	private String upLastTime;
	private String downFirstTime;
	private String downLastTime;
	private String peekAlloc;
	private String nPeekAlloc;
	private boolean isBook;
	
	
	
	@Override
	public String toString() {
		return "BusDTO [" + (locationNoOne != null ? "locationNoOne=" + locationNoOne + ", " : "")
				+ (locationNoTwo != null ? "locationNoTwo=" + locationNoTwo + ", " : "")
				+ (plateNoOne != null ? "plateNoOne=" + plateNoOne + ", " : "")
				+ (plateNoTwo != null ? "plateNoTwo=" + plateNoTwo + ", " : "")
				+ (predictTimeOne != null ? "predictTimeOne=" + predictTimeOne + ", " : "")
				+ (predictTimeTwo != null ? "predictTimeTwo=" + predictTimeTwo + ", " : "")
				+ (routeId != null ? "routeId=" + routeId + ", " : "")
				+ (staOrder != null ? "staOrder=" + staOrder + ", " : "")
				+ (stationId != null ? "stationId=" + stationId + ", " : "")
				+ (busName != null ? "busName=" + busName + ", " : "") + (busType != null ? "busType=" + busType : "")
				+ "]";
	}

	
	
	public boolean isBook() {
		return isBook;
	}



	public void setBook(boolean isBook) {
		this.isBook = isBook;
	}



	public String getUpFirstTime() {
		return upFirstTime;
	}



	public void setUpFirstTime(String upFirstTime) {
		this.upFirstTime = upFirstTime;
	}



	public String getUpLastTime() {
		return upLastTime;
	}



	public void setUpLastTime(String upLastTime) {
		this.upLastTime = upLastTime;
	}



	public String getDownFirstTime() {
		return downFirstTime;
	}



	public void setDownFirstTime(String downFirstTime) {
		this.downFirstTime = downFirstTime;
	}



	public String getDownLastTime() {
		return downLastTime;
	}



	public void setDownLastTime(String downLastTime) {
		this.downLastTime = downLastTime;
	}



	public String getPeekAlloc() {
		return peekAlloc;
	}



	public void setPeekAlloc(String peekAlloc) {
		this.peekAlloc = peekAlloc;
	}



	public String getnPeekAlloc() {
		return nPeekAlloc;
	}



	public void setnPeekAlloc(String nPeekAlloc) {
		this.nPeekAlloc = nPeekAlloc;
	}



	public String getFirstStation() {
		return firstStation;
	}



	public void setFirstStation(String firstStation) {
		this.firstStation = firstStation;
	}



	public String getLastStation() {
		return lastStation;
	}



	public void setLastStation(String lastStation) {
		this.lastStation = lastStation;
	}



	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getPlateNoOne() {
		return plateNoOne;
	}
	public void setPlateNoOne(String plateNoOne) {
		this.plateNoOne = plateNoOne;
	}
	public String getPlateNoTwo() {
		return plateNoTwo;
	}
	public void setPlateNoTwo(String plateNoTwo) {
		this.plateNoTwo = plateNoTwo;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}String getStationId() {
		return stationId;
	}
	public String getLocationNoOne() {
		return locationNoOne;
	}
	public void setLocationNoOne(String locationNoOne) {
		this.locationNoOne = locationNoOne;
	}
	public String getLocationNoTwo() {
		return locationNoTwo;
	}
	public void setLocationNoTwo(String locationNoTwo) {
		this.locationNoTwo = locationNoTwo;
	}
	public String getPredictTimeOne() {
		return predictTimeOne;
	}
	public void setPredictTimeOne(String predictTimeOne) {
		this.predictTimeOne = predictTimeOne;
	}
	public String getPredictTimeTwo() {
		return predictTimeTwo;
	}
	public void setPredictTimeTwo(String predictTimeTwo) {
		this.predictTimeTwo = predictTimeTwo;
	}
	public String getStaOrder() {
		return staOrder;
	}
	public void setStaOrder(String staOrder) {
		this.staOrder = staOrder;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
	

}
