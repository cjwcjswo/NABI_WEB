package nabi.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nabi.web.dto.SpendDTO;

@Repository
public class SpendDAOImpl implements SpendDAO{
	@Autowired
	SqlSession session;
	
	@Override
	public int insertSpending(SpendDTO dto) {
		return session.insert("spendMapper.insertSpending", dto); 
	}

	@Override
	public List<SpendDTO> selectSpending(SpendDTO dto) {
		return session.selectList("spendMapper.selectSpending", dto);
	}

	@Override
	public int insertGoal(SpendDTO dto) {
		return session.insert("spendMapper.insertGoal", dto);
	}

	@Override
	public int updateGoal(SpendDTO dto) {
		return session.update("spendMapper.updateGoal", dto);
	}

	@Override
	public SpendDTO selectGoal(SpendDTO dto) {
		return session.selectOne("spendMapper.selectGoal", dto);
	}

	@Override
	public int deleteSpending(SpendDTO dto) {
		return session.update("spendMapper.deleteSpending", dto);
	}

}
