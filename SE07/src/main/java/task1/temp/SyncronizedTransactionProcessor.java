package task1.temp;

import task1.Account;

import java.util.List;

/**
 * Created by Air on 10/02/2017.
 */
public class SyncronizedTransactionProcessor {

    private List<Transaction> transactions;
    private List<Account> accounts;

    public SyncronizedTransactionProcessor(List<Transaction> transactions, List<Account> accounts) {
        this.transactions = transactions;
        this.accounts = accounts;
    }

    public void processTransactions() {
        for (Transaction transaction : transactions) {
            int from = transaction.getFromAccount();
            int to = transaction.getToAccount();
            Account fromAccount = null;
            Account toAccount = null;
            for (Account account : accounts) {
                if (from == account.getId()) {
                    fromAccount = account;
                }
                if (to == account.getId()) {
                    toAccount = account;
                }
            }
            if (fromAccount != null && toAccount != null) {
                Thread t = new Thread(new SingleTransaction(transaction.getAmount(), fromAccount, toAccount));
                t.start();
            }

        }
    }

    class SingleTransaction implements Runnable {

        private int amount;
        private Account from;
        private Account to;

        public SingleTransaction(int amount, Account from, Account to) {
            this.amount = amount;
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            synchronized (from) {
                System.out.println(Thread.currentThread().getName() + " start");
                try {
                    Thread.sleep((long) amount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                from.decreaseBalance(amount);
                to.increaseBalance(amount);
                System.out.println(Thread.currentThread().getName() + ": " + from.getId() + " - " + from.getBalance() + "; "
                        + to.getId() + " - " + to.getBalance());
            }
        }
    }
}
