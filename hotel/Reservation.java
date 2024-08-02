package hotel;

import java.time.LocalDate;

public class Reservation {
    private String customerId;
    private String roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean CheckedIn;

    public Reservation(String customerId, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        this.customerId = customerId;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.CheckedIn = false;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public boolean CheckedIn() {
        return CheckedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        CheckedIn = checkedIn;
    }
    
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return  customerId + "님" + 
                " 방번호 = " + roomNumber + "호" +
                ", 체크인 날짜 = " + checkInDate +
                ", 체크아웃 날짜 = " + checkOutDate +
                ", 체크인 =" + CheckedIn;
    }
}
