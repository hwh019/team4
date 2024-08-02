package java_hotel.main;


import java.util.Scanner;

public class hotel_main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CustomerManager cm = new CustomerManager();
        AdminManager am = new AdminManager();

        while (true) {
            System.out.println("당신은 직원? 손님?");
            System.out.println("1. 직원");
            System.out.println("2. 손님");
            System.out.println("3. 종료");
            int userType = scanner.nextInt();
            scanner.nextLine(); 

            if (userType == 1) {
                am.run();
            } else if (userType == 2) {
                cm.run();
            } else if (userType == 3) {
            	System.out.println("종료합니다.");
                  break;
            } else {
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }
}
