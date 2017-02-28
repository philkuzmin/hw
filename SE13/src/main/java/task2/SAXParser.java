package task2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Air on 28/02/2017.
 */
public class SAXParser extends DefaultHandler {

    private List<Food> menu = new ArrayList<>();
    private Food food;
    private StringBuilder element;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Parsing started...");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Parsing finished...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        element = new StringBuilder();
        if (qName.equals("food")) {
            food = new Food();
            food.setId(Integer.parseInt(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "name":
                food.setName(element.toString());
                break;
            case "price":
                food.setPrice(element.toString());
                break;
            case "description":
                food.setDescription(element.toString().trim());
                break;
            case "calories":
                food.setCalories(Integer.parseInt(element.toString()));
                break;
            case "food":
                menu.add(food);
                System.out.println(food.toString());
                food = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        element.append(ch, start, length);
    }
}
