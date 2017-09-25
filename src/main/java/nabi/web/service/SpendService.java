package nabi.web.service;

import java.util.List;

import nabi.web.dto.SpendDTO;

public interface SpendService {
	int insertSpending(SpendDTO dto);
	List<SpendDTO> selectSpending(SpendDTO dto);
	/**
	 * �����ǥ ����
	 * @param dto
	 * @return
	 */
	int insertGoal(SpendDTO dto);

	/**
	 * �����ǥ �ҷ�����
	 * @param dto
	 * @return
	 */
	SpendDTO selectGoal(SpendDTO dto);
	
	/**
	 * ���⳻�� ����
	 * @param dto
	 * @return
	 */
	int deleteSpending(SpendDTO dto);
	
}
