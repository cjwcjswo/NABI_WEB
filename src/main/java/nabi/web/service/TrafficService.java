package nabi.web.service;

import java.util.List;

import nabi.web.dto.BusDTO;

public interface TrafficService {
	List<BusDTO> searchStation(String stationId);
}
