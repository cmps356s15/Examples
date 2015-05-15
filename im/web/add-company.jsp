<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Internship Management System</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <h3>Add Company</h3>
        <br>
        <c:if test='${not empty message}'>
            <p class='message'>${message} </p>
            <c:remove var="message" scope="session" />
        </c:if>

        <form action="company" method="post">
            <input type="hidden" name="internshipId" value="${internshipId}">
            <label for="name">Name:</label>
            <input id="name" name="name" type="text" required><br>

            <label for="email">Email:</label>
            <input id="email" name="email" type="email" required><br>

            <label for="phone">Phone number:</label>
            <input id="phone" name="phone" type="text" required><br>

            <label for="website">Website Url:</label>
            <input id="website" name="website" type="url" required><br>

            <label for="street">Street:</label>
            <input id="street" name="street" type="text" required><br>
            
            <label for="city">City:</label>
            <input id="city" name="city" type="text" required><br>

            <input type="submit" value="Submit">
        </form>
    </body>
</html>