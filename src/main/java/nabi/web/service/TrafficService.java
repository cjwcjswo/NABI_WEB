package nabi.web.service;

import java.util.List;

import nabi.web.dto.BusDTO;
import nabi.web.dto.StationDTO;

public interface TrafficService {
	List<BusDTO> searchStation(String stationId);
	List<StationDTO> searchBus(String routeId);
	void fileSetup();
}
