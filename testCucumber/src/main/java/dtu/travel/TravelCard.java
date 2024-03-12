package dtu.travel;

public class TravelCard {
    private int balance;
    private boolean checkinStatus;

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setCheckinStatus(boolean status) {
        this.checkinStatus=status;
    }

    public boolean isCheckedin() {
        return checkinStatus;
    }

    public int getBalance() {
        return this.balance;
    }
}
