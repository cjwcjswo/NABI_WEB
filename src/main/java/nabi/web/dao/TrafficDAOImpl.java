package nabi.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nabi.web.dto.BusBookDTO;
import nabi.web.dto.BusDTO;
import nabi.web.dto.SpendDTO;

@Repository
public class TrafficDAOImpl implements TrafficDAO{
	@Autowired
	SqlSession session;

	@Override
	public List<BusBookDTO> selectBookBus(String email) {
		return session.selectList("trafficMapper.selectBusBook", email);
	}

	@Override
	public int insertBookBus(BusBookDTO dto) {
		return session.insert("trafficMapper.insertBusBook", dto);
	}

	@Override
	public int deleteBookBus(BusBookDTO dto) {
		return session.insert("trafficMapper.deleteBusBook", dto);
	}

}
