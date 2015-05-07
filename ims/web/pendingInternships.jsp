<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Completed Tasks</title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>

<!--Navigation Bar-->
<div class="navbar-fixed-top">
    <ul class="menu">
        <li class="menu-item"><a class="menu-link logo">Internship Management System</a></li>
        <li class="menu-item"><a class="menu-link" href="pendingInternships">Pending Internships</a></li>
        <li class="menu-item"><a class="menu-link" href="confirmedInternships">Confirmed Internships</a></li>
        <li class="menu-item"><a class="menu-link" href="logout">Logout</a></li>
    </ul>
</div>

<div>
    <h3>Welcome ${sessionScope.user.getFullName()}</h3>
</div>
<!--Completed List-->
<div class="completed-section">

    <h1>Pending Internships</h1>

    <table class="Task-list">

        <thead>
            <tr class="Task-row"><th>Internship ID<th>Student Name</th><th>Student ID</th><th>Completed Hours</th><th>GPA</th><th></th></tr>
        </thead>

        <tbody>
            <c:forEach var="internship" items="${internships}">
                <c:if test="${internship.value.IsConfirmed() == false}">
                            <tr class="Task-row">
                                <td>${internship.value.id}</td>
                                <td>${users[internship.value.studentID].firstName}</td>
                                <td>${users[internship.value.studentID].studentId}</td>
                                <td>${users[internship.value.studentID].completedCHs}</td>
                                <td>${users[internship.value.studentID].GPA}</td>
                                <td><a href="confirmInternship?internshipID=${internship.value.id}">Confirm</a></td>
                            </tr>
                </c:if>
            </c:forEach>
    </table>
</div>

</body>
</html>
