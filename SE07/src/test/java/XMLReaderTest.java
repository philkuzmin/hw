import org.junit.Test;
import task1.Account;
import task1.XMLParser;
import task1.temp.SyncronizedTransactionProcessor;
import task1.temp.Transaction;
import task1.temp.XMLReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Air on 10/02/2017.
 */
public class XMLReaderTest {

    @Test
    public void XMLReaderWorks() throws Exception {

        Account acc1 = new Account(1111, 100);
        Account acc2 = new Account(2222, 100);
        List<Account> accounts = new ArrayList<>();
        accounts.add(acc1);
        accounts.add(acc2);

        XMLParser parser = new XMLParser(new File("src/test/resources/accounts.xml"), accounts);
        parser.process();
    }
}
