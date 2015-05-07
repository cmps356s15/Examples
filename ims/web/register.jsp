<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>

<html>
    <head lang="en">
        <meta charset="UTF-8">
        <title>Internship Management System</title>
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <link href="css/styles.css" rel="stylesheet">

        <script>
            $(document).ready(function () {
                $('#companies').change(function () {
                    this.setCustomValidity('');
                    if ($(this).val().length > 4) {
                        this.setCustomValidity('You can only select up to 4 companies!');
                    }
                });
            });
        </script>
    </head>
    <body>

        <jsp:include page="header.jsp" />

        <c:if test="${user.getCompletedCHs() < 90}">
            <br>
            <div class="alert alert-warning">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                You need to complete at least 90 CH to register an internship
            </div>
        </c:if>
        <c:if test="${user.getCompletedCHs() >= 90}">
            <h1>Register an Internship</h1>
            <label>Student Id:</label>${user.studentId}
            <br>
            <label>Name:</label>${user.fullName}
            <br>
            <label>Completed Hours:</label>${user.completedCHs}
            <br>
            <label>GPA:</label>${user.gpa}
            <form action="registerInternship" method="post">
                <input type="hidden" name="studentid" value="${user.studentId}">
                <label for="companies">Select 4 Preferred Companies:</label>
                <div>
                    <select id="companies" name="companies" multiple autofocus size="8">
                        <c:forEach var ="company" items="${companies}">
                            <option value="${company.id}">${company.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <br>
                <input type="submit" value="Register" class="btn btn-primary">
            </form>
        </div>
    </c:if>
</body>
</html>
