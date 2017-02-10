package task1;

/**
 * Created by Air on 10/02/2017.
 */
public class Account {

    private int id;
    private volatile int balance;

    public Account(int id, int balance) {
        this.balance = balance;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void increaseBalance(int sum) {
        balance += sum;
    }

    public void decreaseBalance(int sum) {
        balance -= sum;
    }
}
