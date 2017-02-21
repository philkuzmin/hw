package task2.dao;

/**
 * Created by Air on 20/02/2017.
 */
public class DaoManager {

    private final AuthorDao authorDao;
    private final BookDao bookDao;

    public DaoManager(AuthorDao authorDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }
}
