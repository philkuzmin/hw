package task3;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Air on 25/01/2017.
 */
public class TextAnalyzer {

    private Path file;
    private ArrayList<String> strings;
    private String pattern = "[A-ZА-Я]([^\\.\\?!]*?(\\([Рр]ис\\. \\d+.*?\\)))+?[^\\.\\?!]*?[\\.\\?!]\\s";  // не совсем корректно выбирает целые предложения со ссылками на рисунки
    private String patternShort = "(\\([Рр]ис\\. (\\d+).*?\\))|([Рр]исун[а-я]{2,3} (\\d+))";                // выделяет только ссылки на рисунки

    public TextAnalyzer(String fileName) {
        file = Paths.get(fileName);
        strings = new ArrayList<>();
    }

    /**
     * Ищет подходящие предожения и складывает их в коллекцию
     * @return
     * @throws IOException
     */
    public int findStrings() throws IOException {
        StringBuilder buffer = loadFile();
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(buffer);
        int i = 0;
        while (matcher.find()) {
            strings.add(matcher.group());
            i++;
        }
        return i;
    }

    /**
     * Анализирует последовательность ссылок на рисунки
     * @return
     * @throws IOException
     */
    public boolean analyzeRefs() throws IOException {
        StringBuilder buffer = loadFile();
        Pattern p = Pattern.compile(patternShort);
        Matcher matcher = p.matcher(buffer);
        int val = 0;
        while (matcher.find()) {
            String next = matcher.group(2);
            if (next == null) {
                next = matcher.group(4);
            }
            if (next != null) {
                System.out.println(next);
                try {
                    if (val <= Integer.parseInt(next)) {
                        val = Integer.parseInt(next);
                    } else {
                        return false;
                    }
                } catch (NumberFormatException ex) {
                    continue;
                }
            }
        }
        return true;
    }

    public void printStrings() {
        for (String s : strings) {
            System.out.println(s);
        }
    }

    private StringBuilder loadFile() throws IOException {
        List<String> list = Files.readAllLines(file, Charset.forName("cp1251"));
        StringBuilder buffer = new StringBuilder();
        for (String s : list) {
            buffer.append(s);
        }
        return buffer;
    }
}
