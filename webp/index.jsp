<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="JavaClasses.*" %>
<%@ page import ="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>GamesTableHtml</title>
    <script src="script.js"></script>
</head>

<body>
    <section>
        <div>
            <div>
                <div>
                    <form action="InsertNewProgram" method="POST">

                        <fieldset>
                            <p> Название программы </p>

                            <input type="text" class="form-control" name="Program_name" id="name">

                            <p> Язык программирования </p>

                            <input type="text" class="form-control raz" name="Language" id="language">

                            <p> Создатель </p>

                            <input type="text" class="form-control raz" name="Author" id="author">

                            <p> Год выпуска </p>

                            <input type="number" class="form-control raz" name="Release_year" id="release_year">

                            <p> Цена подписки </p>

                            <input type="number" class="form-control raz" name="Subscription_price" id="price">
    
                            <br>
                            <button type="submit" id="create">Add</button>
                        </fieldset>
                    </form>
                    <button type="button" id="delete" onclick="deleteRow()" >Delete</button>
                </div>
            </div>
        </div>
    </section>
    <br>
    <div></div>
    <br>
    <section>
        <div>
            <div>
                <div>

                    <br>
                    <table id="programs">
                        <thead>
                            <tr>
                                <th>ID программы</th>
                                <th>Название программы</th>
                                <th>Язык программирования</th>
                                <th>Создатель</th>
                                <th>Год выпуска</th>
                                <th>Цена подписки</th>
                            </tr>
                        </thead>
                        <tbody>
                            
                            <% if (request.getAttribute("programs") != null) {

                                ArrayList<ProgramBean> list = (ArrayList<ProgramBean>) request.getAttribute("programs");
                            
                                for (ProgramBean element : list) {
                                    out.println("<tr>");
                                    out.println("<td>" + element.getProgram_ID() + "</td>");
                                    out.println("<td>" + element.getProgram_name() + "</td>");
                                    out.println("<td>" + element.getLanguage() + "</td>");
                                    out.println("<td>" + element.getAuthor() + "</td>");
                                    out.println("<td>" + element.getRelease_year() + "</td>");
                                    out.println("<td>" + element.getSubscription_price() + "</td>");
                                    out.println("</tr>");
                                }
                              }
                            %>

                            <%-- <c:forEach var="program" items="${programs}">
                                <tr>
                                    <th>${program.get(0).getProgram_ID()}</th>
                                    <td>${program.Program_name}</td>
                                    <td>${program.Language}</td>
                                    <td>${program.Author}</td>
                                    <td>${program.Release_year}</td>
                                    <td>${program.Subscription_price}</td>
                                </tr>
                            </c:forEach> --%>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>


</body>
</html>