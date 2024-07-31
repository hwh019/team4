package hotel.controller;

import java.util.Scanner;

import hotel.model.vo.MemberVO;
import hotel.service.MemberServiceImp;

public class MemberController {
	
	private MemberServiceImp memberService = new MemberServiceImp();
	private Scanner scan;
	
	public MemberController(Scanner scan) {
		this.scan = scan;
	} //end MemberController
	
	public void insertMember() {
		MemberVO mb = inputMemberExpand();

		if(memberService.insertMember(mb)) {
			System.out.println(mb.getMb_name() + "님 회원 가입을 축하합니다.");
		}
		else {
			System.out.println("이미 등록된 회원입니다.");
		}
	} //end insertMember

	public MemberVO inputMemberExpand() {
		MemberVO mb = inputMember();
		
		System.out.print("이름: ");
		String name = scan.next();
		System.out.print("이메일: ");
		String email = scan.next();
		
		mb.setMb_name(name);
		mb.setMb_email(email);
		return mb;
	} //end MemberVO inputMemberExpand

	public MemberVO inputMember() {
		System.out.print("아이디: ");
		String id = scan.next();
		System.out.print("비밀번호: ");
		String passWord = scan.next();
		return new MemberVO(id, passWord);
	} //end MemberVO inputMember
	
	public boolean chkMember(MemberVO mb) {
		if(!memberService.contains(mb)) {
			return false;
		} else {
			return true;
		}
	} //end boolean chkMember
	
	public void updateMember() {
		MemberVO mb = inputMember();
		if(chkMember(mb) == false) {
			System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
			return;
		}
		
		MemberVO newMember = inputMemberExpand();
		if(memberService.updateMember(mb, newMember)) {
			System.out.println("회원정보를 수정했습니다.");
			return;
		}
		System.out.println("해당 정보를 수정하지 못했습니다.");
	} //end updateMember
	
	public void deleteMember() {
		MemberVO mb = inputMember();
		
		if(memberService.deleteMember(mb)) {
			System.out.println("회원을 삭제했습니다.");
			return;
		}
		
		System.out.println("해당 회원은 존재하지 않습니다.");
	} //end deleteMember
	
	public void selectMember() {
		MemberVO mb = inputMember();
		MemberVO dbMem = memberService.selectMember(mb);
		if(dbMem == null) {
			System.out.println("일치하는 회원이 없습니다.");
			return;
		}
		
		System.out.println(dbMem);
	}
}
