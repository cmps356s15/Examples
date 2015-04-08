<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

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
        <h2>Completed Tasks</h2>
        <table>
            <thead>
                <tr>
                    <th>Sura</th>
                    <th>Ayats</th>
                    <th>Completed Date</th>
                    <th>Type</th>
                    <th>Hifz level</th>
                    <th>Comment</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="task" items="${requestScope.completedTaskList}">
                <tr>
                    <td >(${task.surah.name})${task.surah.englishName}</td>
                    <td>${task.fromAya} to ${task.toAya}</td>
                    <td>${task.completedDate}</td>
                    <td>${task.type}</td>
                    <td>${task.level}</td>
                    <td>${task.comment}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>

</body>
</html>
