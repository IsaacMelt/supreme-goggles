import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/InsertNewProgram")
public class InsertNewProgramServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        String program_name = request.getParameter("Program_name");
        String language = request.getParameter("Language");
        String author = request.getParameter("Author");
        String release_year = request.getParameter("Release_year");
        String subscription_price = request.getParameter("Subscription_price");

        try {
            String url = "jdbc:mysql://localhost:3306/animation_programs?useSSL=false&useJDBCComplaintTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            String username = "root";
            String password = "1111";

            try (Connection connection = DriverManager.getConnection(url, username, password)){
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT MAX(Program_ID) FROM program");
                resultSet.next();
                int nextProgramID = resultSet.getInt(1) + 1;

                statement.executeUpdate("INSERT INTO program (Program_name, Language, Author, Release_year, Subscription_price) VALUES ("+program_name+", '"+language+"', '"+author+"', "+release_year+", "+subscription_price+")");

                writer.println("<!DOCTYPE html>"+
                        "<html lang=\"ru\">"+

                        "<head>"+
                        "<meta charset=\"UTF-8\">"+
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
                        "<title>Ответ от сервера</title>"+
                        "</head>"+
                        "<body>"+
                        "<p class=\"lead\">"+
                        "Запись добавлена!"+
                        "</p>"+
                        "<a href=\"Main\">"+
                        "<button type = \"button\">Вернуться к списку</button>"+
                        "</a>"+
                        "</body>"+
                        "</html>");
            }
        }
        catch(Exception e){
            writer.println("<p>Database connection failed...</p>");
            writer.println("<p>"+e.getMessage()+"</p>");
            writer.println("<a href=\"Main\"><button>Вернуться к списку</button></a>");
            e.printStackTrace();
        }
        finally {
            writer.close();
        }
    }
}