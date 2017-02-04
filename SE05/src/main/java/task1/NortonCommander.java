package task1;

import java.io.*;
import java.util.Scanner;

/**
 * Разработать приложение, позволяющее просматривать файлы и каталоги файловой системы, а также создавать и
 * удалять текстовые файлы. Для работы с текстовыми файлами необходимо реализовать функциональность записи
 * (дозаписи) в файл. Требуется определить исключения для каждого слоя приложения и корректно их обработать.
 */
public class NortonCommander {

    private File path = new File(".");

    public void startApp() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Current directory: " + path.getAbsolutePath());
                System.out.println("Input command (cd, ls, new, open, quit): ");
                String[] input = scanner.nextLine().trim().split(" ", 1);
                switch (input[0]) {

                    case "cd":
                        try {
                            System.out.println("Input directory: ");
                            String dir = scanner.nextLine();
                            changeDirectory(dir.trim());
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                        break;

                    case "ls":
                        try {
                            showFiles(path);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "new":
                        try {
                            System.out.println("Input file name: ");
                            String fileName = scanner.nextLine();
                            File file;
                            if (fileName.startsWith("/")) {
                                file = new File(fileName);
                            } else {
                                file = new File(path.getCanonicalPath() + "/" + fileName);
                            }
                            newFile(file);
                            System.out.println("Input text: ");
                            String text = scanner.nextLine();
                            addText(file, text);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "open":
                        try {
                            System.out.println("Input file name: ");
                            String fileName = scanner.nextLine();
                            File file;
                            if (fileName.startsWith("/")) {
                                file = new File(fileName);
                            } else {
                                file = new File(path.getCanonicalPath() + "/" + fileName);
                            }
                            openFile(file);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "quit":
                        return;
                }
            }
        }
    }

    private void showFiles(File path) throws IOException {
        File[] list;
        if (path.isDirectory() && path.canRead()) {
            list = path.listFiles();
            for (int i = 0; i < list.length; i++) {
                if (!list[i].getName().startsWith(".")) {
                    System.out.println(i + "\t" + list[i].getName());
                }
            }
        } else {
            throw new IOException("No such directory or you don't have permission to read!");
        }
    }

    private void changeDirectory(String newDirectory) throws IOException {
        if (!newDirectory.startsWith("/")) {
            newDirectory = path.getCanonicalPath() + "/" + newDirectory;
        }
        File tempDir = new File(newDirectory);
        if (tempDir.exists() && tempDir.isDirectory()) {
            if (tempDir.canRead()) {
                showFiles(path = tempDir);
            } else {
                throw new IOException("Can't read the directory!");
            }
        } else {
            throw new IOException("No such directory!");
        }
    }

    private void newFile(File newFile) throws IOException {
        if (newFile != null) {
            if (!newFile.exists()) {
                    newFile.createNewFile();
            } else {
                throw new IOException("File already exists!");
            }
        } else {
            throw new IOException("File can't be null!");
        }
    }

    private void addText(File file, String text) throws IOException {
        if (file != null) {
            if (file.exists() && file.canWrite()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.append(text);
                }
            } else {
                throw new IOException("Can't write to the file!");
            }
        } else {
            throw new IOException("File can't be null!");
        }
    }

    private void openFile(File file) throws IOException {
        if (file != null) {
            if (file.exists() && file.isFile()) {
                if (file.canRead()) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line = reader.readLine();
                        while (line != null) {
                            System.out.println(line);
                            line = reader.readLine();
                        }
                    }
                } else {
                    throw new IOException("Can't read the file!");
                }
            } else {
                throw new IOException("File doesn't exist!");
            }
        } else {
            throw new IOException("File can't be null!");
        }
    }

    public static void main(String[] args) throws IOException {
        NortonCommander nc = new NortonCommander();
        nc.startApp();
    }

}
