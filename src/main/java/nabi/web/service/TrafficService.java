package nabi.web.service;

import java.util.List;

import nabi.web.dto.BusBookDTO;
import nabi.web.dto.BusDTO;
import nabi.web.dto.StationDTO;

public interface TrafficService {
	List<BusDTO> searchStation(String stationId, String email);
	List<StationDTO> searchBus(String routeId);
	List<BusDTO> selectBookBus(String email);
	int insertBookBus(BusBookDTO dto);
	int deleteBookBus(BusBookDTO dto);
	void fileSetup();
}
