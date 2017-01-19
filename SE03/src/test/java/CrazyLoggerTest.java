import org.junit.Test;

import java.util.Date;

/**
 * Created by Air on 19/01/2017.
 */
public class CrazyLoggerTest {

    CrazyLogger logger = new CrazyLogger();

    @Test
    public void name() throws Exception {
        logger.addMessage(new Date(764721685453l), "Something good happened");
        logger.addMessage("Hello");
        logger.addMessage("World");
        logger.addMessage("Hello again");
        logger.addMessage(new Date(945766674643l), "Again good");
        logger.printLog();
        System.out.println(logger.find("27-3-1994"));
        System.out.println(logger.find("Hell"));
        System.out.println(logger.find("good"));
    }
}
