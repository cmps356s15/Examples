<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Hifz Tracker</title>
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <section>
            <h2>Pending Tasks</h2>
            <c:if test='${not empty message}'>
                <p class='message'>${message}</p>
                <c:remove var="message" scope="session" />
            </c:if>
            <table>
                <thead>
                    <tr>
                        <th>Sura</th>
                        <th>Ayats</th>
                        <th>Due Date</th>
                        <th>Type</th>
                        <th colspan="3"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="task" items="${requestScope.taskList}">
                        <tr>
                            <td>${task.surah.name} - ${task.surah.englishName}</td>
                            <td>${task.fromAya} to ${task.toAya}</td>
                            <td>
                                <fmt:formatDate pattern="dd/MM/yyyy" value="${task.dueDate}" /> 
                            </td>
                            <td>${task.type}</td>
                            <td><a href="updatetask?id=${task.id}">Update</a></td>
                            <td><a href="completetask?id=${task.id}">Complete</a></td>
                            <td><a href="deletetask?id=${task.id}" onclick="return confirm('Do you really want to delete this task?');">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </body>
</html>
