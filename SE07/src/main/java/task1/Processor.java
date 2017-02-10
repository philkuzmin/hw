package task1;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.List;
import java.util.Queue;

/**
 * Класс отслеживает появление новых транзакций в очереди и обрабатывает их.
 */
public class Processor implements Runnable {

    private Queue<Element> elements;
    private List<Account> accounts;

    public Processor(Queue<Element> elements, List<Account> accounts) {
        this.elements = elements;
        this.accounts = accounts;
    }

    @Override
    public void run() {
        System.out.println("Processor started...");
        while (!Thread.currentThread().isInterrupted()) {
            if (!elements.isEmpty()) {
                Element element = null;
                synchronized (elements) {
                    if (!elements.isEmpty()) {
                        element = elements.poll();
                        System.out.println("New element received...");
                    }
                }
                if (element != null) {
                    processTransaction(element);
                }
            }
        }
        System.out.println("Processor stopped...");
    }

    private void processTransaction(Element element) {
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
                synchronized (fromAccount) {
                    fromAccount.decreaseBalance(amount);
                    toAccount.increaseBalance(amount);
                }
                System.out.println(Thread.currentThread().getName() + ": " + fromAccount.getId() + " - " + fromAccount.getBalance() + "; "
                        + toAccount.getId() + " - " + toAccount.getBalance());
            }
        } catch (NumberFormatException e) {
            System.out.println("Bad transaction!");
        }
    }
}

