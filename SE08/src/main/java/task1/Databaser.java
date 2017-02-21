package task1;

import java.sql.*;

/**
 * Создайте таблицу в БД и с помощью JDBC выполните следующие действия
 * извлеките информацию из таблицы с помощью подготовленного запроса;
 * обновите несколько записей в таблице;
 * выберите конкретную запись в таблице;
 * вставьте новую запись в таблицу;
 * удалите таблицу.
 */
public class Databaser implements AutoCloseable {

    private Connection connection = null;

    private final String createTable = "CREATE TABLE Users (\n" +
            "  id INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "  first_name VARCHAR(32),\n" +
            "  last_name VARCHAR(32),\n" +
            "  age INT\n" +
            ");";

    private final String insertStatement =
            "INSERT INTO Users (first_name, last_name, age) VALUES (?, ?, ?)";

    private final String updateStatement =
            "UPDATE Users SET first_name = ?, last_name = ?, age = ? WHERE id = ?";

    private final String renameUsersStatement =
            "UPDATE Users SET last_name = ? WHERE first_name = ?";

    private final String selectByIdStatement =
            "SELECT first_name, last_name, age FROM Users WHERE id = ?";

    private final String selectAllStatement = "SELECT * FROM Users";

    private final String dropTableStatement = "DROP TABLE Users";

    public void getConnection() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:test");
            System.out.println("Connected with database.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initDatabase() {
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(createTable);
                insertQuery("John", "Smith", 33);
                insertQuery("Mark", "Brown", 13);
                insertQuery("Chris", "Black", 53);
                System.out.println("Database ready!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            getConnection();
            initDatabase();
        }
    }

    public int insertQuery(String firstName, String lastName, int age) {
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(insertStatement)) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setInt(3, age);
                return statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public int updateQuery(int id, String firstName, String lastName, int age) {
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(updateStatement)) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setInt(3, age);
                statement.setInt(4, id);
                return statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public int renameUserQuery(String firstName, String lastName) {
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(renameUsersStatement)) {
                statement.setString(1, lastName);
                statement.setString(2, firstName);
                return statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public void selectById(int id) {
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(selectByIdStatement)) {
                statement.setInt(1, id);
                statement.executeQuery();
                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("first_name") + " " +
                                resultSet.getString("last_name") + ", " +
                                resultSet.getInt("age"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void selectAll() {
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(selectAllStatement)) {
                statement.executeQuery();
                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt("id") + ": " +
                                resultSet.getString("first_name") + " " +
                                resultSet.getString("last_name") + ", " +
                                resultSet.getInt("age"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropTable() {
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(dropTableStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
        System.out.println("Connection closed.");
    }
}
