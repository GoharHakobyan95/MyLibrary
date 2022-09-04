package servlet;


import manager.AuthorManager;
import manager.BookManager;
import model.Author;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/books/edit")
public class EditBookServlet extends HttpServlet {
    BookManager bookManager = new BookManager();
    AuthorManager authorManager= new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> allAuthors = authorManager.getAllAuthors();
        req.setAttribute("authors", allAuthors);
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        Book book = bookManager.getById(bookId);
        req.setAttribute("book", book);
        req.getRequestDispatcher("/WEB-INF/editBook.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Double price = Double.valueOf(req.getParameter("price"));
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        Book book = Book.builder()
                .id(bookId)
                .title(title)
                .description(description)
                .price(price)
                .author(authorManager.getById(authorId))
                .build();
        bookManager.editBook(book);
        resp.sendRedirect("/books");
    }
}
