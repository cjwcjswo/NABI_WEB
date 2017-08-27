package nabi.web.dao;

import nabi.web.dto.MemberDTO;

public interface MemberDAO {
	/**
	 * ȸ�� ����
	 * @param dto ��� dto
	 * @return �����ͺ��̽� ��� ��
	 */
	int insertMember(MemberDTO dto);
	
	/**
	 * ���̵� �ߺ� üũ
	 * @param dto ��� dto
	 * @return �����ͺ��̽� ��� ��
	 */
	MemberDTO checkId(MemberDTO dto);
	
	/**
	 * �̸��� ����
	 * @param dto ������ ȸ�� dto
	 * @return
	 */
	int authMember(MemberDTO dto);
	
	/**
	 * �α���
	 * @param dto ��� dto
	 * @return �����ͺ��̽� ��� ����
	 */
	MemberDTO selectMember(MemberDTO dto);
}
