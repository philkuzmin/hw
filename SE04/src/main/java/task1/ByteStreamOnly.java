package task1;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iMac on 29/01/17.
 */
public class ByteStreamOnly {

    private File input;
    private File output;
    private String[] keywords =

            {
                    "abstract", "continue", "for", "new", "switch",
                    "assert", "default", "goto", "package", "synchronized",
                    "boolean", "do", "if", "private", "this",
                    "break", "double", "implements", "protected", "throw",
                    "byte", "else", "import", "public", "throws",
                    "case", "enum", "instanceof", "return", "transient",
                    "catch", "extends", "int", "short", "try",
                    "char", "final", "interface", "static", "void",
                    "class", "finally", "long", "strictfp", "volatile",
                    "const", "float", "native", "super", "while"
            };

    public ByteStreamOnly(String input) {
        this.input = new File(input);
        output = new File("output.txt");
        Arrays.sort(keywords);
    }

    /**
     * Основной управляющий метод
     */
    public void analyze() {
        try {
            byte[] sourceBytes = readFile();
            String resultString = parse(new String(sourceBytes, Charset.forName("utf8")));
            writeFile(resultString.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private byte[] readFile() throws IOException {
        byte[] buffer;
        if (input != null && input.isFile() && input.canRead()) {
            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(input))) {
                buffer = new byte[inputStream.available()];
                inputStream.read(buffer, 0, inputStream.available());
            }
        } else {
            throw new IOException("Can't read the file");
        }
        System.out.println(buffer.length + " bytes read");
        return buffer;
    }

    private void writeFile(byte[] source) throws IOException {
        if (source.length > 0) {
            if (output != null) {
                if (output.exists() && output.isFile()) {
                    if (output.canWrite()) {
                        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output))) {
                            outputStream.write(source, 0, source.length);
                            System.out.println(source.length + " bytes written");
                        }
                    } else {
                        throw new IOException("Can't write to the file");
                    }
                } else {
                    output.createNewFile();
                    writeFile(source);
                }
            } else {
                throw new IOException("Output file is not specified");
            }
        } else {
            throw new IOException("Nothing to write");
        }
    }

    private String parse(String text) {
        StringBuilder out = new StringBuilder();
        Matcher matcher = null;
        Pattern pattern = null;
        for (String keyword : keywords) {
            pattern = Pattern.compile("\\W" + keyword + "\\W");
            matcher = pattern.matcher(text);
            if (matcher.find()) {
                int count;
                for (count = 1; matcher.find(); count++) {
                    matcher.find();
                }
                out.append(keyword).append(": ").append(count).append("\n");
            }
        }
        return out.toString();
    }

}
