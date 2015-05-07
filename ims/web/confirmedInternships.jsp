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

    <h1>Confirmed Internships</h1>

    <table class="Task-list">

        <thead>
            <tr class="Task-row"><th>Internship ID</th><th>Student Name</th><th>Student ID</th><th>Company</th><th>Examiner One</th><th>Examiner Two</th><th>Action</th></tr>
        </thead>

        <tbody>

            <c:forEach var="internship" items="${internships}">
                <c:if test="${internship.value.IsConfirmed() == true}">
                    <tr class="Task-row">
                            <td>${internship.value.id}</td>
                            <td>${students[internship.value.studentID].firstName}</td>
                            <td>${internship.value.studentID}</td>
                            <td>${companies[internship.value.companieID].name}</td>
                            
                    <c:if test="${internship.value.examiners.size() == 0}">
                        <td>NOT ASSIGNED</td> <td>NOT ASSIGNED</td>
                        <td><a href="assignExaminers?internshipID=${internship.value.id}">Assign Examiners</a></td>
                    </c:if>
                        
                    <c:if test="${internship.value.examiners.size() != 0}">  
                        
                        <c:forEach var="examinerID" items="${internship.value.examiners}">
                                    <td>
                                        ${faculty[examinerID.key].firstName}
                                    </td>
                        </c:forEach>
                                    <td><a href="viewGrades?internshipIndex=${internship.value.id}">View Grades</a></td>
                    </c:if>
                               
                            </tr>
                        
                    
                </c:if>
            </c:forEach>
                            

    </table>
</div>

</body>
</html>
