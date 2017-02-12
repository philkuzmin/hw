package task1.concurrent;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import task1.Account;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Air on 12/02/2017.
 */
public class SingleTransaction implements Runnable {

    private static Lock lock = new ReentrantLock();
    private List<Account> accounts;
    private Element element;

    public SingleTransaction(List<Account> accounts, Element element) {
        this.accounts = accounts;
        this.element = element;
    }

    @Override
    public void run() {
        NodeList fromElement = element.getElementsByTagName("from");
        NodeList toElement = element.getElementsByTagName("to");
        NodeList amountElement = element.getElementsByTagName("amount");
        try {
            int from = Integer.parseInt(fromElement.item(0).getTextContent().trim());
            int to = Integer.parseInt(toElement.item(0).getTextContent().trim());
            int amount = Integer.parseInt(amountElement.item(0).getTextContent().trim());
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
                lock.lock();
                fromAccount.decreaseBalance(amount);
                toAccount.increaseBalance(amount);
                System.out.println(Thread.currentThread().getName() + ": " + fromAccount.getId() + " - " + fromAccount.getBalance() + "; "
                        + toAccount.getId() + " - " + toAccount.getBalance());
            }
        } catch (NumberFormatException e) {
            System.out.println("Bad transaction!");
        } finally {
            lock.unlock();
        }
    }
}
