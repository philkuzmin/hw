package task2;

import java.io.*;
import java.util.Arrays;
import task1.*;

/**
 * Created by iMac on 29/01/17.
 */
public class SymbolStreamsOnly {

        private File input;
        private File output;

        public SymbolStreamsOnly(String input) {
            this.input = new File(input);
            output = new File("output_writer.txt");
            Arrays.sort(task1.ByteStreamOnly.keywords);
        }

        /**
         * Основной управляющий метод
         */
        public void analyze() {
            try {
                String source = readFile();
                String resultString = ByteStreamOnly.parse(source);
                writeFile(resultString);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        private String readFile() throws IOException {
            StringBuilder result = new StringBuilder();
            if (input != null && input.isFile() && input.canRead()) {
                try (BufferedReader inputReader = new BufferedReader(new FileReader(input))) {
                    String temp = inputReader.readLine();
                    while (temp != null) {
                        result.append(temp);
                        temp = inputReader.readLine();
                    }
                }
            } else {
                throw new IOException("Can't read the file");
            }
            return result.toString();
        }

        private void writeFile(String source) throws IOException {
            if (source.length() > 0) {
                if (output != null) {
                    if (output.exists() && output.isFile()) {
                        if (output.canWrite()) {
                            try (BufferedWriter outputWriter = new BufferedWriter(new FileWriter(output))) {
                                outputWriter.write(source);
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
    }