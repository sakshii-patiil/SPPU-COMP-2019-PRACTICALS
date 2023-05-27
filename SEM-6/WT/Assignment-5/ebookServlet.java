import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ebookServlet")
public class ebookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Register the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish the database connection
            String jdbcUrl = "jdbc:mysql://localhost:3306/WT";
            String username = "sakshii";
            String password = "12345678";
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Execute the SQL query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ebookshop");

            // Display the table content
            out.println("<html><body>");
            out.println("<h2>Table Content</h2>");
            out.println("<table>");
            out.println("<tr><th>Book ID</th><th>Title</th><th>Author</th><th>Price</th><th>Quantity</th></tr>");

            while (resultSet.next()) {
                int bookId = resultSet.getInt("book_id");
                String bookTitle = resultSet.getString("book_title");
                String bookAuthor = resultSet.getString("book_author");
                double bookPrice = resultSet.getDouble("book_price");
                int quantity = resultSet.getInt("quantity");

                out.println("<tr>");
                out.println("<td>" + bookId + "</td>");
                out.println("<td>" + bookTitle + "</td>");
                out.println("<td>" + bookAuthor + "</td>");
                out.println("<td>" + bookPrice + "</td>");
                out.println("<td>" + quantity + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            // Close the database connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
