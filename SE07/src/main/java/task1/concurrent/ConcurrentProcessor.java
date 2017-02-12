package task1.concurrent;

import org.w3c.dom.Element;
import task1.Account;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Air on 12/02/2017.
 */
public class ConcurrentProcessor implements Runnable{

    private Queue<Element> elements;
    private List<Account> accounts;

    ConcurrentProcessor(Queue<Element> elements, List<Account> accounts) {
        this.elements = elements;
        this.accounts = accounts;
    }

    @Override
    public void run() {
        System.out.println("Concurrent processor started...");
        ExecutorService executorService = Executors.newCachedThreadPool();
        while (!Thread.currentThread().isInterrupted()) {
            if (!elements.isEmpty()) {
                executorService.execute(new SingleTransaction(accounts, elements.poll()));
            }
        }
        executorService.shutdown();
    }
}
