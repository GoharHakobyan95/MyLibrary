package servlet;


import manager.AuthorManager;
import model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(urlPatterns = "/authors/add")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1mb
        maxFileSize = 1024 * 1024 * 10, // 10mb
        maxRequestSize = 1024 * 1024 * 100 //100mb
)
public class AddAuthorServlet extends HttpServlet {
    private AuthorManager authorManager = new AuthorManager();
    private static final String IMAGE_PATH = "\\C:\\Users\\Noname\\IdeaProjects\\MyLibrary\\projectImages\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addAuthor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        Double age = Double.valueOf(req.getParameter("age"));

        Part profilePicPart = req.getPart("profilePic");
        String fileName = null;
        if (profilePicPart != null) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + profilePicPart.getSubmittedFileName();
            profilePicPart.write(IMAGE_PATH + fileName);

            Author author = Author.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .age(age)
                    .profilePic(fileName)
                    .build();
            authorManager.addAuthor(author);
            resp.sendRedirect("/authors");

        }
    }
}