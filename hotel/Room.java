package hotel;

import java.time.LocalDate;

public class Room {
    private String roomNumber;
    private boolean Available;
    private boolean CheckedIn;
    private String reserved;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
        this.Available = true;
        this.CheckedIn = false;
        this.reserved = null;
        this.checkInDate = null;
        this.checkOutDate = null;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public boolean Available() {
        return Available;
    }

    public void setAvailable(boolean available) {
        Available = available;
    }

    public boolean CheckedIn() {
        return CheckedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        CheckedIn = checkedIn;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public LocalDate getcheckInDate() {
        return checkInDate;
    }

    public void setcheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    
    public LocalDate getcheckOutDate() {
        return checkOutDate;
    }

    public void setcheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        String roomInfo = 
                "방번호 = " + roomNumber + 
                ", 예약가능 여부 = " + Available +
                ", 체크인 상황 = " + CheckedIn;

        if (!Available) {
            roomInfo += ", 예약인 = " + reserved + 
                        ", 예약 날짜 =" + checkInDate +"~"+ checkOutDate;
        }

        
        return roomInfo;
    }
}
