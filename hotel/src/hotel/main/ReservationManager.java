package hotel.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import hotel.controller.MemberController;
import hotel.controller.RoomController;
import hotel.model.vo.MemberVO;
import hotel.model.vo.RoomVO;
import program.Program;

public class ReservationManager implements Program {

	private Scanner scan = new Scanner(System.in);
	private MemberController memberController = new MemberController(scan);
	private RoomController roomController = new RoomController(scan);
	
	
	@Override
	public void printMenu() {
		System.out.println("[메뉴]");
		System.out.println("1.로그인\n2.회원등록\n3.관리자모드\n4.종료");
		System.out.print("메뉴 선택: ");
	}

	@Override
	public void runMenu(int menu) throws Exception {
		switch(menu) {
			case 1:
				login();
			break;
			case 2:
				memberController.insertMember();
			break;
			case 3:
				adminOnly();
			break;
			case 4:
				System.out.println("프로그램이 종료되었습니다.");
			break;
			default: 
		}
	}

	private void adminOnly() {
		System.out.println("[관리자 메뉴키]");
		String isAdminKey = "admin1234"; //관리자 메뉴 접근 키
		System.out.print("관리자 메뉴 키를 입력하세요: ");
		String inputAdminKey = scan.next();
		
		if(isAdminKey.equals(inputAdminKey)) {
			System.out.println("[관리자 로그인]");
			adminLogin();
		} else {
			System.out.println("관리자키를 확인해주세요.");
			return;
		}
	} //end adminOnly
	
	private void adminLogin() { 
		MemberVO chkMb = memberController.inputMember();
		MemberVO mb = memberController.chkMember(chkMb);
		if(mb == null) {
			System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
			return;
		} 
		if(mb.getMb_is_admin() != 'Y') {
			System.out.println("관리자 권한이 없습니다.");
			return;
		}
		System.out.println(mb.getMb_name() + "님 관리자 모드로 접속했습니다.");
		adminMenu();
	}

	private void adminMenu() {
		System.out.println("관리자 메뉴입니다.");
		int menu;
		
		do {
			printAdminMenu();
			menu = nextInt();
			
			try {
				runAdminMenu(menu);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (menu != 4);
	} //end adminMenu

	private void printAdminMenu() {
		System.out.println("1.회원관리\n2.방관리\n3.예약관리\n4.로그아웃");
		System.out.print("메뉴 선택: ");
	} //end printAdminMenu
	
	private void runAdminMenu(int menu) {
		switch(menu) {
			case 1:
				System.out.println("회원관리");
			break;
			case 2:
				System.out.println("방관리");
			break;
			case 3:
				System.out.println("예약관리");
			break;
			case 4: 
				System.out.println("로그아웃 되었습니다.");
			break;
			default: 
		}
	} //end runAdminMenu

	private void login() {
		MemberVO chkMb = memberController.inputMember();
		MemberVO mb = memberController.chkMember(chkMb);
		if(mb == null) {
			System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
			return;
		} 
		System.out.println(mb.getMb_name() + " 회원님 어서오세요.");
		memberMenu();
	}

	private void memberMenu() {
		System.out.println("회원 메뉴입니다.");
		int menu;
		
		do {
			printMemberMenu();
			menu = nextInt();
			
			try {
				runMemberMenu(menu);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (menu != 4);
	}

	private void printMemberMenu() {
		System.out.println("1.정보수정\n2.예약하기\n3.예약확인하기\n4.로그아웃");
		System.out.print("메뉴 선택: ");
	}

	private void runMemberMenu(int menu) {
		switch(menu) {
			case 1:
				editMember();
			break;
			case 2:
				reservation();
			break;
			case 3:
				viewReservation();
			break;
			case 4: 
				System.out.println("로그아웃 되었습니다.");
			break;
			default: 
		}
	}

	private void editMember() {
		System.out.println("[내 정보 수정]");
		memberController.updateMember();
	}

	private void reservation() {
		System.out.println("[예약페이지 입니다]");
		RoomVO room = roomController.selectRoom();
		if(room == null) {
			System.out.println("예약 가능한 방이 존재하지 않습니다.");
			return;
		}
		System.out.println("[예약 가능한 방]");
		System.out.println(room);
		/*
		if(roomList.size() == 0) {
			
		}
		System.out.println("방목록");
		for(int i=0;i<roomList.size();i++) {
			
			System.out.println((i + 1) + "번 " + roomList.get(i));
			System.out.println("=================================");
		}
		
		System.out.print("예약할 번호 선택 : ");
		int roomNum = scan.nextInt() - 1;
		
		Room room = roomList.get(roomNum);
		
		System.out.println(room);*/
	}

	private void viewReservation() {
		System.out.println("[내 예약 확인]");
		
	}

//	private void findPassWord() {
//		MemberVO chkMb = memberController.inputMember();
//		MemberVO mb = memberController.chkMember(chkMb);
//		if(mb == null) {
//			System.out.println("일치하는 회원이 없거나 회원이 아닙니다.");
//			return;
//		} else {
//			System.out.println("회원님의 비밀번호는 " + mb.getMb_password() + "입니다.");
//		}
//	}

	@Override
	public void run() {
		int menu;
		do {
			printMenu();
			menu = nextInt();
			
			try {
				runMenu(menu);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (menu != 4);
	}

	private int nextInt() {
		try {
			return scan.nextInt();
		} catch(InputMismatchException e) {
			scan.nextLine();
			return Integer.MIN_VALUE;
		}
	}

}
