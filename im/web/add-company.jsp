<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
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
            <label for="companyName"> Name:</label>
            <input id="companyName" name="companyName" type="text" required><br>

            <label for="companyEmail"> Email:</label>
            <input id="companyEmail" name="companyEmail" type="text" required><br>

            <label for="companyPhone"> Phone number:</label>
            <input id="companyPhone" name="companyPhone" type="text" required><br>

            <label for="companyURL"> Website Url:</label>
            <input id="companyURL" name="companyURL" type="text" required><br>

            <label for="companyURL">Street:</label>
            <input id="companyStreet" name="companyStreet" type="text" required><br>
            
            <label for="companyURL"> City:</label>
            <input id="companyCity" name="companyCity" type="text" required><br>

            <input type="hidden" name="internshipId" value="${internshipId}">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>