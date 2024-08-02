package hotel.dao;

import org.apache.ibatis.annotations.Param;

import hotel.model.vo.MemberVO;

public interface MemberDAO {
	
	MemberVO selectMember(@Param("mb")MemberVO mb);

	MemberVO loginMember(@Param("mb")MemberVO mb);

	boolean insertMember(@Param("mb")MemberVO mb);

	boolean updateMember(@Param("new")MemberVO newMember);

	boolean deleteMember(@Param("mb")MemberVO mb);


}
