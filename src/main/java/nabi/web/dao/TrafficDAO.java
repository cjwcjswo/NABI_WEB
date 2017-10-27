package nabi.web.dao;

import java.util.List;

import nabi.web.dto.BusBookDTO;
import nabi.web.dto.BusDTO;

public interface TrafficDAO {
	List<BusBookDTO> selectBookBus(String email);
	int insertBookBus(BusBookDTO dto);
	int deleteBookBus(BusBookDTO dto);
}
