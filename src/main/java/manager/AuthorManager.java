package manager;

import db.DBConnectionProvider;
import model.Author;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AuthorManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addAuthor(Author author) {
        String sql = "Insert into author (`name`,surname,email,age) Values(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setDouble(4, author.getAge());
            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                author.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Author> getAllAuthors() {
        String sql = "SELECT * FROM author";
        List<Author> authors = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                authors.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public Author getById(int id) {
        String sql = "SELECT * FROM author  WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        return Author.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .age(resultSet.getDouble("age"))
                .build();

    }

    public void removeAuthorById(int authorId) {
        String sql = "DELETE  FROM  author WHERE id = " + authorId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editAuthor(Author author) {
        String sql = "UPDATE author set `name`= ?,surname = ?,email =?,age = ?  where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setDouble(4, author.getAge());
            ps.setInt(5, author.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



