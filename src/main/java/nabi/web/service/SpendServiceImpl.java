package nabi.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nabi.web.dao.SpendDAO;
import nabi.web.dto.SpendDTO;

@Service
@Transactional
public class SpendServiceImpl implements SpendService{
	@Autowired
	SpendDAO spendDAO;

	@Override
	public int insertSpending(SpendDTO dto) {
		return spendDAO.insertSpending(dto);
	}

	@Override
	public List<SpendDTO> selectSpending(SpendDTO dto) {
		return spendDAO.selectSpending(dto);
	}

	@Override
	public int insertGoal(SpendDTO dto) {
		if(spendDAO.selectGoal(dto) == null){
			return spendDAO.insertGoal(dto);
		}
		else{
			return spendDAO.updateGoal(dto);
		}
	}

	@Override
	public SpendDTO selectGoal(SpendDTO dto) {
		return spendDAO.selectGoal(dto);
	}

	@Override
	public int deleteSpending(SpendDTO dto) {
		return spendDAO.deleteSpending(dto);
	}
}
