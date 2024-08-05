package java_hotel.main;

import java.util.Scanner;

import java_hotel.controller.MemberController;
import java_hotel.controller.ReservationController;
import java_hotel.controller.RoomController;
import java_hotel.model.vo.CustomerVO;

public class AdminManager {
	private Scanner scanner = new Scanner(System.in);
	private MemberController memberController = new MemberController(scanner);
	private ReservationController reservationController = new ReservationController(scanner);
	private RoomController roomController = new RoomController(scanner);

	private CustomerVO loginadmin;

	public void run() {
		// 방 샘플 데이터
//        rooms.add(new RoomVO("101"));
//        rooms.add(new RoomVO("102"));
//        rooms.add(new RoomVO("103"));
		// 샘플 데이터를 sql로 추가하는데 좋을거같아서 삭제
		adminLogin();
	}

	private void adminLogin() {
		System.out.print("관리자 아이디 : ");
		String adminId = scanner.nextLine();
		System.out.print("관리자 비밀번호 : ");
		String adminPw = scanner.nextLine();

		loginadmin = memberController.adminLogin(adminId, adminPw);
		if (loginadmin == null) {
			System.out.println("잘못된 아이디 또는 비밀번호입니다.");
			return;
		}
		adminMenu(loginadmin);
	}

	private void adminMenu(CustomerVO loginadmin) {
		while (true) {
			System.out.println("관리자 메뉴");
			System.out.println("1. 회원관리");
			System.out.println("2. 방 관리");
			System.out.println("3. 예약 관리");
			System.out.println("4. 로그아웃");
			System.out.print("입력 :");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				manageMembers();
				break;
			case 2:
				manageRooms();
				break;
			case 3:
				manageReservations();
				break;
			case 4:
				return;
			default:
				System.out.println("잘못된 선택입니다. 다시 시도하세요.");
			}
		}
	}

	private void manageMembers() {
		while (true) {
			System.out.println("회원관리");
			System.out.println("1. 회원 정보");
			System.out.println("2. 뒤로가기");
			System.out.print("입력 :");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				searchMemberInfo();
				break;
			case 2:
				return;
			default:
				System.out.println("잘못된 선택입니다. 다시 시도하세요.");
			}
		}
	}

	// 해당하는 입력값의 모든 회원들의 정보를 출력 admin빼고;
	private void searchMemberInfo() {
		memberController.searchmember_admin();
	}

	private void manageRooms() {
		while (true) {
			System.out.println("방 관리");
			System.out.println("1. 방 목록");
			System.out.println("2. 뒤로가기");
			System.out.print("입력 :");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				listRooms();
				break;
			case 2:
				return;
			default:
				System.out.println("잘못된 선택입니다. 다시 시도하세요.");
			}
		}
	}

	// 모든 방 출력
	private void listRooms() {
		roomController.AllRooms();
	}

	private void manageReservations() {
		while (true) {
			System.out.println("예약 관리");
			System.out.println("1. 예약");
			System.out.println("2. 체크인/아웃");
			System.out.println("3. 예약 확인");
			System.out.println("4. 예약 수정");
			System.out.println("5. 예약 취소");
			System.out.println("6. 뒤로가기");
			System.out.print("입력 :");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				makeReservation();
				break;
			case 2:
				checkInOut();
				break;
			case 3:
				confirmReservation();
				break;
			case 4:
				modifyReservation();
				break;
			case 5:
				cancelReservation();
				break;
			case 6:
				return;
			default:
				System.out.println("잘못된 선택입니다. 다시 시도하세요.");
			}
		}
	}

	private void makeReservation() {
		System.out.println("고객 정보 입력");
		CustomerVO loginmember = memberController.getUser_admin();
		if (reservationController.makeReservation(loginmember)) {
			// 성공
		} else {
			// 실패
		}
	}

	private void checkInOut() {
		System.out.print("예약 번호");
		int rv_id = scanner.nextInt();
		scanner.nextLine();
		System.out.print("고객 아이디: ");
		String customerId = scanner.nextLine();
		// 체크인은 예약시작일~예약종료일 전까지만 가능
		reservationController.checkInOut(rv_id, customerId);
	}

	private void confirmReservation() {
		System.out.println("고객 정보 입력");
		CustomerVO loginmember = memberController.getUser_admin();
		if (reservationController.confirmReservation(loginmember)) {
		}
	}

	private void modifyReservation() {
		System.out.println("고객 정보 입력");
		CustomerVO loginmember = memberController.getUser_admin();
		if (reservationController.modityReservation(loginmember)) {
		}
	}

	private void cancelReservation() {
		System.out.println("고객 정보 입력");
		CustomerVO loginmember = memberController.getUser_admin();
		if (reservationController.cancelReservation(loginmember)) {
		}
	}
}