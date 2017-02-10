package task1.temp;

/**
 * Created by Air on 10/02/2017.
 */
public class Transaction {

    private int fromAccount;
    private int toAccount;
    private int amount;

    public Transaction(int fromAccount, int toAccount, int amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public int getAmount() {
        return amount;
    }
}
