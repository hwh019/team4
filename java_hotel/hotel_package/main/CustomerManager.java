package main;

import java.util.Scanner;

import controller.MemberController;
import controller.ReservationController;
import model.vo.CustomerVO;


public class CustomerManager {
	private Scanner scanner = new Scanner(System.in);

	private MemberController memberController = new MemberController(scanner);
	private ReservationController reservationController = new ReservationController(scanner);

	private CustomerVO loginmember;

	public void run() {
		userMenu();
	}

	private void userMenu() {
		while (true) {
			System.out.println("[사용자 메뉴]");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 뒤로가기");
			System.out.print("입력: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				loginmember = memberController.userLogin();
				if (loginmember == null) {
					System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
				} else {
					userLoggedInMenu();
				}
				break;
			case 2:
				userRegister();
				break;
			case 3:
				return;
			default:
				System.out.println("잘못된 선택입니다. 다시 시도하세요.");
			}
		}
	}

	private void userRegister() {
		System.out.print("아이디: ");
		String mb_id = scanner.nextLine();
		
		if (memberController.userRegister(mb_id)) {
			System.out.println(mb_id + "님 회원가입을 축하합니다.");
		} else {
			System.out.println("이미 등록된 회원입니다.");
		}

	}

	private void userLoggedInMenu() {
		System.out.println(loginmember.getMb_name() + "님 환영합니다.");
		while (true) {
			System.out.println("[사용자 메뉴]");
			System.out.println("1. 예약");
			System.out.println("2. 회원정보");
			System.out.println("3. 로그아웃");
			System.out.print("입력: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				userManageReservations();
				break;
			case 2:
				userManageInfo();
				break;
			case 3:
				return;
			default:
				System.out.println("잘못된 선택입니다. 다시 시도하세요.");
			}
		}
	}

	private void userManageReservations() {
		while (true) {
			System.out.println("[예약 메뉴]");
			System.out.println("1. 예약");
			System.out.println("2. 예약확인");
			System.out.println("3. 예약수정");
			System.out.println("4. 예약취소");
			System.out.println("5. 뒤로가기");
			System.out.print("입력: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				makeReservation();
				break;
			case 2:
				confirmReservation();
				break;
			case 3:
				modifyReservation();
				break;
			case 4:
				cancelReservation();
				break;
			case 5:
				return;
			default:
				System.out.println("잘못된 선택입니다. 다시 시도하세요.");
			}
		}
	}

	private void userManageInfo() {
		while (true) {
			System.out.println("[회원정보]");
			System.out.println("1. 회원정보");
			System.out.println("2. 회원정보 수정");
			System.out.println("3. 회원탈퇴");
			System.out.println("4. 뒤로가기");
			System.out.print("입력: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				searchUserInfo();
				break;
			case 2:
				loginmember = modifyUserInfo();
				break;
			case 3:
				deleteUserInfo();
				break;
			case 4:
				return;
			default:
				System.out.println("잘못된 선택입니다. 다시 시도하세요.");
			}
		}
	}

	private void searchUserInfo() {
		memberController.searchuser(loginmember);
		System.out.println("넘어가려면 엔터를 눌러주세요.");
		scanner.nextLine();
	}

	private CustomerVO modifyUserInfo() {
		if (memberController.updatemember(loginmember)) {
			System.out.println("회원님의 정보가 수정되었습니다.");
			return memberController.getUser(loginmember);
		}
		System.out.println("회원님의 정보를 수정할 수 없습니다.");
		return loginmember;
	}

	private void deleteUserInfo() {
		memberController.deleteMember(loginmember);
		System.out.println("프로그램을 종료합니다.");
		// 실행 종료
		System.exit(0);
	}

	private void makeReservation() {
		if (reservationController.makeReservation(loginmember)) {

		}
	}

	private void confirmReservation() {
		// false이면 예약 없음
		if (reservationController.confirmReservation(loginmember)) {
		}
	}

	private void modifyReservation() {
		// 예약 수정
		if (reservationController.modityReservation(loginmember)) {
		}
	}

	private void cancelReservation() {
		// 예약 취소 로직 추가
		if (reservationController.cancelReservation(loginmember)) {

		}
	}
}
