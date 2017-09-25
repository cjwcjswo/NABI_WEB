package nabi.web.dao;

import java.util.List;

import nabi.web.dto.SpendDTO;

public interface SpendDAO {
	/**
	 * 지출내역 삽입
	 * @param dto
	 * @return
	 */
	int insertSpending(SpendDTO dto);
	
	/**
	 * 지출내역 불러오기
	 * @param dto 회원, 달
	 * @return
	 */
	List<SpendDTO> selectSpending(SpendDTO dto);
	
	/**
	 * 지출내역 삭제
	 * @param dto
	 * @return
	 */
	int deleteSpending(SpendDTO dto);
	
	/**
	 * 지출목표 삽입
	 * @param dto
	 * @return
	 */
	int insertGoal(SpendDTO dto);
	
	/**
	 * 지출목표 수정
	 * @param dto
	 * @return
	 */
	int updateGoal(SpendDTO dto);
	
	/**
	 * 지출목표 불러오기
	 * @param dto
	 * @return
	 */
	SpendDTO selectGoal(SpendDTO dto);
}
