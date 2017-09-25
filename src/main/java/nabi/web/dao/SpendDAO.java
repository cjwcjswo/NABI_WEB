package nabi.web.dao;

import java.util.List;

import nabi.web.dto.SpendDTO;

public interface SpendDAO {
	/**
	 * ���⳻�� ����
	 * @param dto
	 * @return
	 */
	int insertSpending(SpendDTO dto);
	
	/**
	 * ���⳻�� �ҷ�����
	 * @param dto ȸ��, ��
	 * @return
	 */
	List<SpendDTO> selectSpending(SpendDTO dto);
	
	/**
	 * ���⳻�� ����
	 * @param dto
	 * @return
	 */
	int deleteSpending(SpendDTO dto);
	
	/**
	 * �����ǥ ����
	 * @param dto
	 * @return
	 */
	int insertGoal(SpendDTO dto);
	
	/**
	 * �����ǥ ����
	 * @param dto
	 * @return
	 */
	int updateGoal(SpendDTO dto);
	
	/**
	 * �����ǥ �ҷ�����
	 * @param dto
	 * @return
	 */
	SpendDTO selectGoal(SpendDTO dto);
}
