package hotel;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import program.Program;

public class ReservationManager implements Program {

	
	private Scanner scan = new Scanner(System.in);
	private List<Member> memberList = new ArrayList<Member>();
	private List<Room> roomList = new ArrayList<Room>();
	
	@Override
	public void printMenu() {
		System.out.println("[메뉴]");
		System.out.println("1.로그인\n2.회원등록\n3.비밀번호 찾기\n4.종료");
		System.out.print("메뉴 선택: ");
	}

	@Override
	public void runMenu(int menu) throws Exception {
		switch(menu) {
			case 1:
				login();
			break;
			case 2:
				register();
			break;
			case 3:
				findPassWord();
			break;
			case 4:
				System.out.println("프로그램이 종료되었습니다.");
			break;
			default: 
		}
	}

	private void login() {
		System.out.print("아이디: ");
		String id = scan.next();
		System.out.print("비밀번호: ");
		String passWord = scan.next();
		List<Member> tmps = memberList.stream().filter(m->m.getId().equals(id)&&m.getPassWord().equals(passWord)).collect(Collectors.toList());
		if(tmps.size() == 0) {
			System.out.println("일치하는 회원이 없거나 아이디 혹은 비밀번호를 잘못 입력하였습니다.");
			return;
		} else {
			System.out.println(tmps.get(0).getName() + " 회원님 어서오세요.");
			memberMenu();
		}
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
		System.out.println("내 정보 수정");
		
	}

	private void reservation() {
		System.out.println("[예약페이지 입니다]");
		System.out.println("방목록");
		for(int i=0;i<roomList.size();i++) {
			System.out.println((i + 1) + "번 " + roomList.get(i));
			System.out.println("=================================");
		}
		
		System.out.print("예약할 방 번호 선택 : ");
		int roomNum = scan.nextInt();
	}

	private void viewReservation() {
		System.out.println("[내 예약 확인]");
		
	}

	private void register() {
		System.out.print("아이디: ");
		String id = scan.next();
		System.out.print("비밀번호: ");
		String passWord = scan.next();
		System.out.print("이름: ");
		String name = scan.next();
		System.out.print("이메일: ");
		String email = scan.next();
		Member tmp = new Member(id, name, null, null, false);
		if(memberList.contains(tmp)) {
			System.out.println("이미 등록된 아이디입니다.");
			return;
		} else {
			Member mb = new Member(id, name, passWord, email, false);
			memberList.add(mb);
			System.out.println("회원가입이 완료되었습니다.\n로그인 페이지로 이동합니다.");
			login();
		}
	}

	private void findPassWord() {
		System.out.print("아이디: ");
		String id = scan.next();
		System.out.print("이메일: ");
		String email = scan.next();
		
		List<Member> tmps = memberList.stream().filter(m->m.getId().equals(id)&&m.getEmail().equals(email)).collect(Collectors.toList());
		if(tmps.size() == 0) {
			System.out.println("일치하는 회원이 없거나 회원이 아닙니다.");
			return;
		} else {
			System.out.println("회원님의 비밀번호는 " + tmps.get(0).getPassWord() + "입니다.");
		}
	}

	@Override
	public void run() {
		int menu;
		roomList.add(new Room(101, 4, 5000, "스위트", true));
		roomList.add(new Room(102, 2, 2500, "더블", true));
		roomList.add(new Room(103, 1, 1500, "싱글", true));
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
