<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE html>
<html>
    <head>
        <title>Internship Management System</title>
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <c:if test='${not empty message}'>
            <p class='message'>${message}</p>
            <c:remove var="message" scope="session" />
        </c:if>
        <br>
        <form action="confirm" method="post">
            <input type="hidden" name="internshipId" value="${internship.id}" >
            <h3>
            Internship #${internship.id} for Student ${internship.student.studentId} - ${internship.student.name}
            </h3>
            <label>Select a company</label>
            <select name="company" id="company" required>
                <option value=""></option>
                <option value="0">Add new company</option>
                    <c:forEach var="company" items="${companies}">
                    <option value='${company.id}'>
                        ${company.name}
                    </option>
                </c:forEach>
            </select>             
            <br><br>
            <input type="submit" />
        </form>
    </body>
</html>


