package manager;

import db.DBConnectionProvider;

import model.Author;
import model.Book;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BookManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private AuthorManager authorManager = new AuthorManager();

    public void addBook(Book book) {
        String sql = "Insert into book (title,description,price,author_id) Values(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getAuthor().getId());
            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                book.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM  book";
        List<Book> books = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                books.add(getBookFromResulSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return books;
    }

    public Book getById(int id) {
        String sql = "SELECT * FROM book  WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getBookFromResulSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Book getBookFromResulSet(ResultSet resultSet) throws SQLException {
        int authorId = resultSet.getInt("author_id");
        return Book.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .price(resultSet.getDouble("price"))
                .author(authorManager.getById(authorId))
                .build();

    }

    public void removeBookById(int bookId) {
        String sql = "DELETE  FROM  book WHERE id = " + bookId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editBook(Book book) {
        String sql = "UPDATE  book set title = ?,description = ?,price = ?,author_id = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getAuthor().getId());
            ps.setInt(5, book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


