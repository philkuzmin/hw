package task1;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Класс запускает в параллельном потоке обработчик очереди транзакций, парсит xml и добавляет транзакции в очередь.
 */
public class XMLParser {

    protected File file;
    protected List<Account> accounts;
    protected Queue<Element> elements;

    public XMLParser(File file, List<Account> accounts) {
        this.file = file;
        this.accounts = accounts;
        elements = new LinkedList<>();
    }

    protected void startProcessor() {
        Thread processor = new Thread(new Processor(elements, accounts));
        processor.start();
    }

    public void process() throws IOException, SAXException {
        startProcessor();
        DOMParser parser = new DOMParser();
        try (FileInputStream stream = new FileInputStream(file)) {
            parser.parse(new InputSource(stream));
            Document document = parser.getDocument();
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("transaction");
            for (int i = 0; i < nodeList.getLength(); i++) {
                elements.add((Element) nodeList.item(i));
                System.out.println("New element added...");
            }
        }

    }
}
