package nabi.web.dao;

import nabi.web.dto.MemberDTO;

public interface MemberDAO {
	/**
	 * 회원 가입
	 * @param dto 멤버 dto
	 * @return 데이터베이스 결과 값
	 */
	int insertMember(MemberDTO dto);
	
	/**
	 * 아이디 중복 체크
	 * @param dto 멤버 dto
	 * @return 데이터베이스 결과 값
	 */
	MemberDTO checkId(MemberDTO dto);
	
	/**
	 * 이메일 인증
	 * @param dto 인증할 회원 dto
	 * @return
	 */
	int authMember(MemberDTO dto);
	
}
