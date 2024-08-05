package java_hotel.controller;

import java.util.Scanner;

import java_hotel.model.vo.CustomerVO;
import java_hotel.service.MemberService;
import java_hotel.service.MemberServiceImp;

public class MemberController {

	private MemberService memberService = new MemberServiceImp();
	private Scanner scanner;

	public MemberController(Scanner scan) {
		this.scanner = scan;
	}

	public boolean userRegister() {

		System.out.print("아이디: ");
		String mb_id = scanner.nextLine();
		System.out.print("비밀번호: ");
		String mb_password = scanner.nextLine();
		System.out.print("이름: ");
		String mb_name = scanner.nextLine();
		System.out.print("이메일: ");
		String mb_email = scanner.nextLine();
		return memberService.Register(mb_id, mb_password, mb_name, mb_email);
	}

	public CustomerVO userLogin() {
		System.out.print("아이디: ");
		String mb_id = scanner.nextLine();
		System.out.print("비밀번호: ");
		String mb_password = scanner.nextLine();
		CustomerVO user = memberService.loginmember(mb_id, mb_password);
		return user;
	}

	public boolean updatemember(CustomerVO loginmember) {

		System.out.print("비밀번호: ");
		String mb_password = scanner.nextLine();
		System.out.print("이름: ");
		String mb_name = scanner.nextLine();
		System.out.print("이메일: ");
		String mb_email = scanner.nextLine();
		return memberService.update(mb_password, mb_name, mb_email, loginmember.getMb_id());
	}

	public void deleteMember(CustomerVO loginmember) {
		System.out.println("정말로 탈퇴하시겠습니까? (Y | N)");
		char input = scanner.next().charAt(0);
		if (input == 'y' || input == 'Y') {
			if (memberService.deleteMember(loginmember)) {
				System.out.println("회원탈퇴를 완료했습니다.");
				return;
			}
			System.out.println("탈퇴 실패");
		}
	}

	public void searchuser(CustomerVO loginmember) {
		CustomerVO user = memberService.searchuser(loginmember.getMb_no());
		System.out.println(user);
	}

	public CustomerVO getUser(CustomerVO loginmember) {
		return memberService.getUser(loginmember.getMb_no());
	}

	public CustomerVO adminLogin(String adminId, String adminPw) {
		if (memberService.check_is_admin(adminId)) {
			// 어드민임
			return memberService.loginmember(adminId, adminPw);
		} else {

			return null;
		}
	}

	// 해당하는 입력값의 모든 회원들의 정보를 출력 admin빼고;
	public void searchmember_admin() {
		System.out.print("아이디: ");
		String mb_id = scanner.nextLine();
		if (memberService.printuser_admin(mb_id)) {
			// 성공
		} else {
			// 실패
			System.out.println("해당하는 회원이 없습니다.");

		}
	}

	public CustomerVO getUser_admin() {
		System.out.print("아이디: ");
		String mb_id = scanner.nextLine();
		CustomerVO user = memberService.getuser_admin(mb_id);
		return user;
	}

}
