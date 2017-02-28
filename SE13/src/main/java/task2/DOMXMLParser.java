package task2;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Air on 01/03/2017.
 */
public class DOMXMLParser {

    private List<Food> menu = new ArrayList<>();
    private String file;
    private Food food;

    public DOMXMLParser(String file) {
        this.file = file;
    }

    public List<Food> parseFile() {
        DOMParser parser = new DOMParser();
        try (FileInputStream stream = new FileInputStream(file)) {
            parser.parse(new InputSource(stream));
            Document document = parser.getDocument();
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("food");
            for (int i = 0; i < nodeList.getLength(); i++) {
                processItem((Element) nodeList.item(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("File error!");
        } catch (SAXException e) {
            System.out.println("XML error!");
        }
        return menu;
    }

    private void processItem(Element item) {
        food = new Food();
        food.setId(Integer.parseInt(item.getAttribute("id")));
        NodeList name = item.getElementsByTagName("name");
        NodeList price = item.getElementsByTagName("price");
        NodeList description = item.getElementsByTagName("description");
        NodeList calories = item.getElementsByTagName("calories");
        food.setName(name.item(0).getTextContent().trim());
        food.setPrice(price.item(0).getTextContent().trim());
        food.setDescription(description.item(0).getTextContent().trim());
        food.setCalories(Integer.parseInt(calories.item(0).getTextContent()));
        menu.add(food);
        System.out.println(food.toString());
    }
}
