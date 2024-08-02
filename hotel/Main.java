package hotel;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CustomerManager cm = new CustomerManager();
        AdminManager am = new AdminManager();

        while (true) {
            System.out.println("당신은 직원? 손님?");
            System.out.println("1. 직원");
            System.out.println("2. 손님");
            int userType = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (userType == 1) {
                am.run();
            } else if (userType == 2) {
                cm.run();
            } else {
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }
}
