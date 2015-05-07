<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Completed Tasks</title>
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <body>

        <!--Navigation Bar-->
        <div class="navbar-fixed-top">
            <c:if test="${user.class.name == 'ims.entity.Faculty'}">
                <ul class="menu">
                    <li class="menu-item"><a class="menu-link logo">Internship Management System</a></li>
                    <li class="menu-item"><a class="menu-link" href="pendingInternships">Pending Internships</a></li>
                    <li class="menu-item"><a class="menu-link" href="confirmedInternships">Confirmed Internships</a></li>
                    <li class="menu-item"><a class="menu-link" href="logout">Logout</a></li>
                </ul>
            </c:if>
            <c:if test="${user.class.name == 'ims.entity.Student'}">
                <ul class="menu">
                    <li class="menu-item"><a class="menu-link logo">Internship Management System</a></li>
                    <li class="menu-item"><a class="menu-link" href="registerInternship">Update Internship</a></li>
                    <li class="menu-item"><a class="menu-link" href="viewGrades?internshipIndex=${internship.id}">View Internship Grade</a></li>
                    <li class="menu-item"><a class="menu-link" href="logout">Logout</a></li>
                </ul>
            </c:if>

        </div>

        <div>
            <h3>Welcome ${sessionScope.user.getFullName()}</h3>
        </div>

        <!--Completed List-->
        <div class="completed-section">

            <h1>View Grade</h1>

            <br><br>

            <div class="Field">
                <h2>Internship Information</h2>
                <br>
                <label>Internship ID: ${internship.id}</label>
                <br>
                <label>Student Name: ${students[internship.studentID].firstName}</label>
                <br>
                <label>Student ID: ${students[internship.studentID].studentId}</label>
                <br>
                <label>Company Name: ${companies[internship.companieID].name}</label>
                <br>
                <c:forEach var="examiner" items="${internship.examiners}" varStatus="loopIndex">
                    <label>Examiner ${loopIndex.count}: ${staffMembers[examiner.key].firstName}</label>
                    <br>
                </c:forEach>

            </div>

            <div class="Field">
                <h2>Internship Grade</h2>
                <br>

                <table class="Task-list">

                    <thead>
                        <tr class="Task-row"><th>Criterea</th><th>Rating</th><th>Comment</th></tr>
                    </thead>

                    <tbody>

                        <c:forEach var="category" items="${categories}">
                            <tr><td colspan="7"><h2>${category}</h2></td></tr>
                                        <c:forEach var="criterea" items="${critereas}" varStatus="loopIndex">
                                            <c:if test="${criterea.category == category}">
                                    <tr class="Task-row">
                                        <td class="critereaTD"><p class = "critereaTitleText">${loopIndex.count}. ${criterea.title} (out of ${criterea.grade})</p>${criterea.description}</td>
                                        <td>
                                            <c:forEach var="examiner" items="${internship.examiners}" varStatus="examinerNo">
                                                <c:if test="${internship.getExaminerGrade(examiner.key) != null}"> Examiner ${examinerNo.count}: <p>${ratings.get(internship.getExaminerGrade(examiner.key).getGradeForCriterea(criterea.id)-1).title}</p></c:if> 
                                            </c:forEach>

                                        </td>
                                        <td class = "commentTD" >
                                            <c:forEach var="examiner" items="${internship.examiners}" varStatus="examinerNo">
                                                <c:if test="${internship.getExaminerGrade(examiner.key) != null}">
                                                    <p>Examiner ${examinerNo.count}: ${internship.getExaminerGrade(examiner.key).getCommentForCriterea(criterea.id)}</p>
                                                </c:if> 
                                            </c:forEach>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </tbody>
                </table>
                <br>
                <br>
                <c:forEach var="category" items="${categories}">
                    <label> Total ${category} (Out of ${internshipGrade[category.concat("Total")]}): ${internshipGrade[category]}
                        <br>
                    </c:forEach>

                    <label>Total (Out of 100): ${internshipGrade["total"]}</label>

                    <br>

                    <label>Letter Grade: ${internshipLetterGrade}</label>
            </div>
        </div>
    </body>
</html>
