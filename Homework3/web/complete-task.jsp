<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Hifz Tracker</title>
        <link href="css/styles.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="js/complete-task.js"></script>
    </head>

    <body>
        <jsp:include page="header.jsp" />
        <section>
            <h2>Complete Task</h2>
            <table>
                <thead>
                    <tr>
                        <th>Sura</th>
                        <th>Ayats</th>
                        <th>Due Date</th>
                        <th>Type</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${task.surah.name} - ${task.surah.englishName}</td>
                        <td>${task.fromAya} to ${task.toAya}</td>
                        <td>
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${task.dueDate}" /> 
                        </td>
                        <td>${task.type}</td>
                    </tr>
                </tbody>
            </table>
            <br />

            <form method="post" action="completetask">
                <input type="hidden" name="taskId" value="${task.id}" >
                <label for="">Hifz level</label>
                <label>
                    <input type="radio" name="level" value="Excellent"> Excellent
                </label>
                <label>
                    <input type="radio" name="level" value="Ok"> Ok
                </label>
                <label>
                    <input type="radio" name="level" value="Poor" required> Poor
                </label>
                <br />
                <fmt:formatDate value="${task.dueDate}"  
                    type="date" 
                    pattern="yyyy-MM-dd"
                    var="dueDate" />
                
                <label>Completed date
                    <input id="completedDate" 
                           name="completedDate"
                           data-dueDate="${dueDate}"
                           type="date" required />
                </label>
                <label>Comments
                    <textarea id="comment" name="comment"></textarea>
                </label>
                <input type="submit" value="Submit">
            </form>
        </section>
    </body>
</html>