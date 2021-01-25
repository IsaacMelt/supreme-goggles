import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import JavaClasses.ProgramBean;

@WebServlet("/Main")
public class MainServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        ArrayList<ProgramBean> programs;

        try {
            String url = "jdbc:mysql://localhost:3306/animation_programs?useSSL=false&useJDBCComplaintTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            String username = "root";
            String password = "1111";

            try (Connection connection = DriverManager.getConnection(url, username, password)){
                // Для Резалт Сетов этого Statement'а задал свойство для скролла указателя вниз-вверх. По умолчанию курсор двигается только вниз.
                // Каждый стейтмент может держать открытым только один ResultSet
                Statement statementForProgramsSet = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet programsResultSet = statementForProgramsSet.executeQuery("SELECT * FROM program");

                // создаётся ArrayList размером с количество записей. Чтобы получить число записей используются манипуляции с курсором резалт сета
                programsResultSet.last();
                int programsNumber = programsResultSet.getRow();
                programs = new ArrayList<ProgramBean>(programsNumber);
                programsResultSet.beforeFirst(); // курсор обратно перед первой строкой

                // Заполнение ArrayList'а Bean'ами ProgramBean

                while (programsResultSet.next()) {

                    // добавление заполненной записи в ArrayList
                    programs.add(
                            new ProgramBean(programsResultSet.getString("Program_ID"), programsResultSet.getString("Program_name"),
                                    programsResultSet.getString("Language"), programsResultSet.getString("Author"),
                                    programsResultSet.getString("Release_year"), programsResultSet.getString("Subscription_price")));
                }
            }

            request.setAttribute("programs", programs);
            ServletContext servletContext = getServletContext();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");//ТУТ
            if (dispatcher != null) {
                dispatcher.forward(request, response);
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