package java_hotel.main;

import java.util.Scanner;

public class AdminManager {
    private static Scanner scanner = new Scanner(System.in);

    public void run() {
        adminLogin();
    }

    private void adminLogin() {
        System.out.print("관리자 아이디 : ");
        String adminId = scanner.nextLine();
        System.out.print("관리자 비밀번호 : ");
        String adminPw = scanner.nextLine();

        if ("admin".equals(adminId) && "admin123".equals(adminPw)) {
            adminMenu();
        } else {
            System.out.println("잘못된 아이디 또는 비밀번호입니다.");
        }
    }

    private void adminMenu() {
        while (true) {
            System.out.println("관리자 메뉴");
            System.out.println("1. 회원관리");
            System.out.println("2. 방 관리");
            System.out.println("3. 예약 관리");
            System.out.println("4. 로그아웃");
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
            System.out.println("2. 회원 정보 수정");
            System.out.println("3. 회원 정보 삭제");
            System.out.println("4. 뒤로가기");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchMemberInfo();
                    break;
                case 2:
                    modifyMemberInfo();
                    break;
                case 3:
                    deleteMemberInfo();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    private void searchMemberInfo() {
        System.out.print("검색 (아이디 or 이름 or 전화번호): ");
        String searchQuery = scanner.nextLine();
        // 검색 로직 추가
        System.out.println("검색 결과:");
        // 결과 출력 로직 추가
    }

    private void modifyMemberInfo() {
        System.out.print("검색 (아이디 or 이름 or 전화번호): ");
        String searchQuery = scanner.nextLine();
        // 검색 로직 추가
        System.out.println("수정할 회원 선택:");
        // 회원 목록 출력 로직 추가
        int memberId = scanner.nextInt();
        scanner.nextLine(); 
        // 수정 로직 추가
    }

    private void deleteMemberInfo() {
        System.out.print("검색 (아이디 or 이름 or 전화번호): ");
        String searchQuery = scanner.nextLine();
        // 검색 로직 추가
        System.out.println("삭제할 회원 선택:");
        // 회원 목록 출력 로직 추가
        int memberId = scanner.nextInt();
        scanner.nextLine();
        // 삭제 로직 추가
    }

    private void manageRooms() {
        // 방 관리 로직 추가
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
        // 예약 로직 추가
    }

    private void checkInOut() {
        // 체크인/아웃 로직 추가
    }

    private void confirmReservation() {
        // 예약 확인 로직 추가
    }

    private void modifyReservation() {
        // 예약 수정 로직 추가
    }

    private void cancelReservation() {
        // 예약 취소 로직 추가
    }
}
