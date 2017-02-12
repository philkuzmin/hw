import org.junit.Test;
import task1.Account;
import task1.concurrent.XMLConcurentParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Air on 12/02/2017.
 */
public class XMLConcurrentParserTest {

    @Test
    public void XMLConcurentParserTest() throws Exception {

        Account acc1 = new Account(1111, 100);
        Account acc2 = new Account(2222, 100);
        List<Account> accounts = new ArrayList<>();
        accounts.add(acc1);
        accounts.add(acc2);

        XMLConcurentParser parser = new XMLConcurentParser(new File("src/test/resources/accounts.xml"), accounts);
        parser.process();

    }
}
