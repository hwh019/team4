package java_hotel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import program.Program;

public class Manager implements Program {
	private Scanner scan = new Scanner(System.in);

	public List<Membership> list = new ArrayList<Membership>();
	public List<Room> roomlist = new ArrayList<Room>();
	private String fileName = "src/customer/memberlist.txt";
	private String roomfileName = "src/customer/roomlist.txt";

	public void creatroom() {
		for (int i = 0; i < 10; i++) {
			roomlist.add(new Room(i + 1));
		}
	}

	@Override
	public void printMenu() {
		System.out.println("메뉴");
		System.out.println("0. 회원 가입");
		System.out.println("1. 예약");
		System.out.println("2. 예약확인");
		System.out.println("3. 종료");
		System.out.print("메뉴 선택 :");

	}

	@Override
	public void runMenu(int menu) throws Exception {
		switch (menu) {
		case 0:
			Membership m = create_account();
			break;
		case 1:
			reservation();
			break;
		case 2:
			check_reservation();
			break;
		case 3:
			exit();
			break;
		}

	}

	private Membership create_account() {
		System.out.print("아이디 :");
		String id = scan.next();
		System.out.print("비밀번호 :");
		String pw = scan.next();
		System.out.print("이름 :");
		String name = scan.next();
		System.out.print("전화번호 :");
		String p_number = scan.next();

		Membership m = new Membership(id, pw, name, p_number);
		return m;
	}

	private void reservation() { // 예약
		System.out.println("회원이시라면 1, 회원이 아니시라면 2를 입력해주세요");
		if (scan.nextInt() == 1) {
			System.out.print("아이디 :");
			String id = scan.next();
			System.out.print("비밀번호 :");
			String pw = scan.next();
		}
		else {
			System.out.println("이름 :");
			String name = scan.next();
			System.out.println("비밀번호 4자리를 생성(입력)해주세요 :");
			String pw = scan.next();
		}
		System.out.println("예약하실 날짜를 선택해주세요 :");
		String date = scan.next();
		printroom();
		System.out.print("원하시는 방 번호를 선택해 주세요 : ");
		int roomnumber = scan.nextInt();
		System.out.println(roomnumber + "로 예약 하겠습니다.");
		System.out.println("숙박 일수를 입력해주세요");
		int day = scan.nextInt();
		
	}

	private void printroom() {
		System.out.println(roomlist);

	}

	private void check_reservation() { // 예약 확인

	}

	private void exit() {
		System.out.println("프로그램을 종료합니다.");

	}

	@Override
	public void run() {
		load(fileName);
		loadRoom(roomfileName);
		int menu = 0;
		do {
			printMenu();
			menu = scan.nextInt();
			try {
				runMenu(menu);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (menu != 3);

		save(fileName);
		saveroom(roomfileName);
	}

	@SuppressWarnings("unchecked")
	private void loadRoom(String roomfileName) {
		try (FileInputStream fis = new FileInputStream(roomfileName)) {
			ObjectInputStream ois = new ObjectInputStream(fis);
			roomlist = (List<Room>) ois.readObject();
		} catch (Exception e) {
			creatroom();
		}

	}

	public void saveroom(String roomfileName) {
		try (FileOutputStream fos = new FileOutputStream(roomfileName)) {
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(roomlist);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void load(String fileName) {
		try (FileInputStream fis = new FileInputStream(fileName)) {
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (List<Membership>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save(String fileName) {
		try (FileOutputStream fos = new FileOutputStream(fileName)) {
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
