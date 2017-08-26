package nabi.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nabi.web.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertMember(MemberDTO dto) {
		return sqlSession.insert("memberMapper.insertMember",dto);
	}

	@Override
	public MemberDTO checkId(MemberDTO dto) {
		return sqlSession.selectOne("memberMapper.checkId", dto);
	}

	@Override
	public MemberDTO selectMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
}
