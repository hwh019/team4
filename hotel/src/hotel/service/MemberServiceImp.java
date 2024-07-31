package hotel.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import hotel.dao.MemberDAO;
import hotel.model.vo.MemberVO;

public class MemberServiceImp implements MemberService {
	
	private MemberDAO memberDao;
	
	public MemberServiceImp(){
		String resource = "hotel/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			memberDao = session.getMapper(MemberDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} //end MemberServiceImp
	
	public boolean insertMember(MemberVO mb) {
		if(mb == null) {
			return false;
		}
		
		MemberVO dbMem = memberDao.selectMember(mb);
		if(dbMem != null) {
			return false;
		}

		return memberDao.insertMember(mb);
	} //end boolean insertMember
	
	public boolean contains(MemberVO mb) {
		if(mb == null) {
			return false;
		}
		MemberVO dbMem = memberDao.selectMember(mb);
		return dbMem !=  null;
	} //end boolean contains
	
	public boolean updateMember(MemberVO mb, MemberVO newMember) {
		if(mb == null || newMember == null) {
			return false;
		}
		mb = memberDao.selectMember(mb);
		
		MemberVO dbMem = memberDao.selectMember(newMember);
		if(dbMem != null && !mb.equals(dbMem)) {
			return false;
		}
		newMember.setMb_no(mb.getMb_no());
		return memberDao.updateMember(newMember);
	} //end boolean updateMember
	
	public boolean deleteMember(MemberVO mb) {
		if(mb == null) {
			return false;
		}
		return memberDao.deleteMember(mb);
	} //end boolean deleteMember
	
	public MemberVO selectMember(MemberVO mb) {
		return memberDao.selectMember(mb);
	} //end MemberVO selectMember
}
