package hotel;

public class Customer {
    private String userId;
    private String userPw;
    private String name;
    private String phoneNumber;

    public Customer(String userId, String userPw, String name, String phoneNumber) {
        this.userId = userId;
        this.userPw = userPw;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
