package hotel.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import hotel.controller.MemberController;
import hotel.model.vo.MemberVO;
import program.Program;

public class ReservationManager implements Program {

	private Scanner scan = new Scanner(System.in);
	private MemberController memberController = new MemberController(scan);
	
	
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
			System.out.println("[관리자 메뉴]");
		} else {
			System.out.println("관리자키를 확인해주세요.");
			return;
		}
	} //end adminOnly
	
	private void adminMenu() {
		System.out.println("관리자 메뉴입니다.");
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
	} //end adminMenu

	private void printAdminMenu() {
		System.out.println("1.정보수정\n2.예약하기\n3.예약확인하기\n4.로그아웃");
		System.out.print("메뉴 선택: ");
	} //end printAdminMenu

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
		/*roomList.add(new Room(101, 4, 5000, "스위트", true));
		roomList.add(new Room(102, 2, 2500, "더블", true));
		roomList.add(new Room(103, 1, 1500, "싱글", true));*/
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
