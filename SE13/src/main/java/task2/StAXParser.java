package task2;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Air on 01/03/2017.
 */
public class StAXParser {

    private ArrayList<Food> menu = new ArrayList<>();
    private Food food;
    private String file;

    public StAXParser(String file) {
        this.file = file;
    }

    public List<Food> parseFile() {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream inputStream = new FileInputStream(file)){
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            String element = null;
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {

                    case XMLStreamConstants.START_ELEMENT:
                        element = reader.getLocalName();
                        if (element.equals("food")) {
                            food = new Food();
                            food.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();
                        if (!text.isEmpty()) {
                            switch (element) {
                                case "name":
                                    food.setName(text);
                                    break;
                                case "price":
                                    food.setPrice(text);
                                    break;
                                case "description":
                                    food.setDescription(text);
                                    break;
                                case "calories":
                                    food.setCalories(Integer.parseInt(text));
                                    break;
                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        element = reader.getLocalName();
                        if (element.equals("food")) {
                            menu.add(food);
                            System.out.println(food.toString());
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (XMLStreamException e) {
            System.out.println("XML error!");
        } catch (IOException e) {
            System.out.println("File error!");
        }
        return menu;
    }
}
