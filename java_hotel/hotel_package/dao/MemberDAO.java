package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.vo.CustomerVO;


public interface MemberDAO {

	CustomerVO selectMember(@Param("mb_id") String mb_id);

	boolean insertMember(@Param("mb_id") String mb_id, @Param("mb_password") String mb_password,
			@Param("mb_name") String mb_name, @Param("mb_email") String mb_email);

	boolean deleteMember(@Param("mb_id") String mb_id);

	boolean updateMember(@Param("mb_id_ori") String mb_id_ori, @Param("mb_password") String mb_password,
			@Param("mb_name") String mb_name, @Param("mb_email") String mb_email);

	CustomerVO searchuser(@Param("mb_no") int mb_no);

	List<CustomerVO> searchuser_admin(@Param("mb_id") String mb_id);

	CustomerVO selectadmin(@Param("mb_id") String adminId);

	CustomerVO getuser(@Param("mb_id") String mb_id);

}
