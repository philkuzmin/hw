package task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Создать “универсальный” класс, позволяющий получить значение из любого properties-файла.
 * Физическое чтение файла должно происходить только один раз. Учтите ситуацию, когда несколько потоков
 * одновременно обращаются к одному и тому же файлу.
 */
public class PropertiesReader {

    private File file;
    private Map<String, String> properties;

    public PropertiesReader(File file) {
        this.file = file;
        properties = new ConcurrentHashMap<>();
    }

    private void readFile(File file) throws IOException {
        if (file != null && file.exists() && file.isFile()) {
            synchronized (file) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line = reader.readLine();
                    while (line != null) {
                        String[] pair = line.split("=");
                        if (pair.length == 2) {
                            properties.put(pair[0].trim(), pair[1].trim());
                            line = reader.readLine();
                        }
                    }
                }
            }
        } else {
            throw new IOException("Properties file not found");
        }
    }

    public String getProperty(String key) throws IOException {
        try {
            readFile(file);
        } catch (IOException e) {
            throw new IOException("Can't read file: " + e.getMessage());
        }
        String value = properties.get(key);
        if (value != null) {
            return value;
        } else {
            throw new IOException("No such key");
        }
    }
}
