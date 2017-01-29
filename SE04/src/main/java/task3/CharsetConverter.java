package task3;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by iMac on 29/01/17.
 */
public class CharsetConverter {

    private File inputFile;
    private File outputFile;
    private String text;

    public CharsetConverter(String fileName) {
        this.inputFile = new File(fileName);
        outputFile = new File("utf16.txt");
    }

    public void convert() {
        try {
            text = loadFile(inputFile);
            System.out.println(text);
            writeFile(outputFile, text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String loadFile(File inputFile) throws IOException {
        StringBuilder out = new StringBuilder();
        if (inputFile != null && inputFile.exists() && inputFile.canRead()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), Charset.forName("utf8")))) {
                String temp = reader.readLine();
                while (temp != null) {
                    out.append(temp).append("\n");
                    temp = reader.readLine();
                }
            }
        }
        return out.toString();
    }

    private void writeFile(File outputFile, String text) throws IOException {
        if (text != null && !text.isEmpty()) {
            if (outputFile != null) {
                if (outputFile.exists()) {
                    if (outputFile.isFile() && outputFile.canWrite()) {
                        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), Charset.forName("utf16")))) {
                            writer.write(text);
                        }
                    } else {
                        throw new IOException("Can't write to file");
                    }
                } else {
                    if (outputFile.createNewFile()) {
                        writeFile(outputFile, text);
                    } else {
                        throw new IOException("Can't create file");
                    }
                }
            } else {
                throw new IOException("No filename");
            }
        }
    }
}
