package nabi.web.service;

import java.util.List;

import nabi.web.dto.SpendDTO;

public interface SpendService {
	int insertSpending(SpendDTO dto);
	List<SpendDTO> selectSpending(SpendDTO dto);
	/**
	 * 지출목표 삽입
	 * @param dto
	 * @return
	 */
	int insertGoal(SpendDTO dto);

	/**
	 * 지출목표 불러오기
	 * @param dto
	 * @return
	 */
	SpendDTO selectGoal(SpendDTO dto);
	
	/**
	 * 지출내역 삭제
	 * @param dto
	 * @return
	 */
	int deleteSpending(SpendDTO dto);
	
}
