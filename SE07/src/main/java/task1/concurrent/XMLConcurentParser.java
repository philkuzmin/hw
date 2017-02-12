package task1.concurrent;

import task1.Account;
import task1.XMLParser;

import java.io.File;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Air on 12/02/2017.
 */
public class XMLConcurentParser extends XMLParser{

    public XMLConcurentParser(File file, List<Account> accounts) {
        super(file, accounts);
        super.elements = new LinkedBlockingQueue<>();
    }

    @Override
    protected void startProcessor() {
        Thread processor = new Thread(new ConcurrentProcessor(elements, accounts));
        processor.start();
    }
}
