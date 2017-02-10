package task1.temp;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Air on 10/02/2017.
 */
public class XMLReader {

    public static List<Transaction> getTransactions(File file) throws IOException, SAXException {
        DOMParser parser = new DOMParser();
        try (FileInputStream stream = new FileInputStream(file)) {
            parser.parse(new InputSource(stream));
            Document document = parser.getDocument();
            Element root = document.getDocumentElement();
            ArrayList<Transaction> transactions = new ArrayList<>();
            NodeList nodeList = root.getElementsByTagName("transaction");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                NodeList fromElement = element.getElementsByTagName("from");
                NodeList toElement = element.getElementsByTagName("to");
                NodeList amountElement = element.getElementsByTagName("amount");
                try {
                    int from = Integer.parseInt(fromElement.item(0).getTextContent().trim());
                    int to = Integer.parseInt(toElement.item(0).getTextContent().trim());
                    int amount = Integer.parseInt(amountElement.item(0).getTextContent().trim());
                    transactions.add(new Transaction(from, to, amount));
                } catch (NumberFormatException e) {
                    System.out.println("Bad XML file!");
                }
            }
            return transactions;
        }
    }
}
