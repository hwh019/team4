package java_hotel.service;

import java_hotel.model.vo.CustomerVO;

public interface MemberService {

	CustomerVO loginmember(String mb_id, String mb_password);

	boolean Register(String mb_id, String mb_password, String mb_name, String mb_email);

	boolean deleteMember(CustomerVO loginmember);

	boolean update(String mb_id, String mb_password, String mb_name, String mb_email);

	CustomerVO searchuser(int mb_no);

	CustomerVO getUser(int mb_no);

	boolean printuser_admin(String mb_id);

	boolean check_is_admin(String adminId);

	CustomerVO getuser_admin(String mb_id);

}
