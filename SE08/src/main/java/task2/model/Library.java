package task2.model;

import task2.dao.AuthorDao;
import task2.dao.BookDao;
import task2.dao.DaoFactory;
import task2.dao.H2DaoFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

/**
 * Спроектируйте БД, хранящую информацию, например, о домашней библиотеке.
 * Реализуйте функциональность добавления, поиска и удаления разнообразной информации из этой БД.
 * При реализации используйте (напишите) пул соединений и DAO.
 */
public class Library {

    private Connection connection;
    private AuthorDao authorDao;
    private BookDao bookDao;
    private final String SQL_PATH = "SE08/src/main/resources/";

    public Library() {
    }

    public void startApplication() {
        if (connection == null) {
            try (DaoFactory factory = new H2DaoFactory()) {
                connection = factory.getConnection();
                authorDao = factory.getAuthorDao(connection);
                bookDao = factory.getBookDao(connection);
                initDatabase();
                Scanner scanner = new Scanner(System.in);

                // Main loop
                while (true) {
                    System.out.println("Input command: show / add / del / exit: ");
                    switch (scanner.nextLine()) {
                        case "show":
                            for (Book book : bookDao.getAllBooks()) {
                                System.out.println(book.toString());
                            }
                            break;

                        case "add":
                            Author newAuthor = null;
                            System.out.println("Add new book.\nSelect author:");
                            System.out.println("0 - add new author");
                            for (Author author : authorDao.getAllAuthors()) {
                                System.out.println(author.getId() + " - " + author.toString());
                            }
                            try {
                                int authorId = Integer.parseInt(scanner.nextLine());
                                if (authorId > 0) {
                                    try {
                                        newAuthor = authorDao.readAuthor(authorId);
                                    } catch (SQLException e) {
                                        System.out.println("Wrong number!");
                                        break;
                                    }
                                } else if (authorId == 0) {
                                    System.out.println("Input first name: ");
                                    String firstName = scanner.nextLine();
                                    System.out.println("Input last name: ");
                                    String lastName = scanner.nextLine();
                                    newAuthor = authorDao.createAuthor(new Author(0, firstName, lastName));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Wrong input!");
                                break;
                            }
                            System.out.println("Input book title: ");
                            String bookTitle = scanner.nextLine();
                            System.out.println("Input year: ");
                            String bookYear = scanner.nextLine();
                            if (newAuthor != null) {
                                bookDao.createBook(new Book(0, newAuthor, bookTitle, bookYear));
                            } else {
                                System.out.println("Wrong author!");
                            }
                            System.out.println("Book added.");
                            break;

                        case "del":
                            for (Book book : bookDao.getAllBooks()) {
                                System.out.println(book.toString());
                            }
                            System.out.println("Input book id to delete: ");
                            int bookId;
                            try {
                                bookId = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Not a number!");
                                break;
                            }
                            if (bookId > 0) {
                                try {
                                    bookDao.deleteBook(bookId);
                                } catch (SQLException e) {
                                    System.out.println("Wrong id!");
                                    break;
                                }
                            }
                            System.out.println("Book deleted.");
                            break;

                        case "exit":
                            return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("App is already running...");
        }
    }


    private void initDatabase() {
        for (int i = 1; new File(SQL_PATH + i + ".sql").exists(); i++) {
            try {
                List<String> sql = Files.readAllLines(Paths.get(SQL_PATH + i + ".sql"));
                StringBuilder stringBuilder = new StringBuilder();
                for (String s : sql) {
                    stringBuilder.append(s);
                }
                try (Statement statement = connection.createStatement()) {
                    statement.execute(stringBuilder.toString());
                }
            } catch (IOException e) {
                System.out.println("Can't read the file!");
            } catch (SQLException e) {
                System.out.println("Can't initialize the database!");
            }
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.startApplication();
    }

}
