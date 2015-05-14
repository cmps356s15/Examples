<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head >
        <title>Internship Management System</title>
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <script src="js/script.js"></script>
        <jsp:include page="header.jsp" />

        <br>
        <form action="internships" method="post">
            <label for="state">Status</label>
            <select id ="state" name="state" onchange='this.form.submit()'>
                <c:forEach var="state" items="${states}">
                    <option value="${state}"
                        ${selectedState eq state ? "selected" : "" }>
                        ${state}
                    </option>
                </c:forEach>
            </select>
        </form>
        <br>
        <c:if test='${not empty message}'>
            <p class='message'>${message}</p>
            <c:remove var="message" scope="session" />
        </c:if>

        <c:if test="${empty internships}">
            <p>  There are no ${selectedState eq 'all' ? " " : selectedState} internships.</p>
        </c:if>
        <c:if test="${not empty internships}">
            <table id="internshipListTable" name="internshipListTable" >
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Student Id</th>
                        <th>Student Name</th>
                        <th>Year</th>
                        <th>status</th>
                        <th>Host Company</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="internships" items="${requestScope.internships}">
                        <tr>
                            <td> ${internships.id} </td>
                            <td> ${internships.student.studentId}</td>
                            <td> ${internships.student.firstName} ${internships.student.lastName}</td>
                            <td> ${internships.year} </td>
                            <td> ${internships.status} </td>
                            <td> ${internships.hostCompany.name} </td>
                            <c:if test='${internships.status eq "pending"}'>
                                <td>
                                    <a href="confirm?internshipId=${internships.id}">
                                        Confirm
                                    </a>               
                                </td>
                            </c:if>
                            <c:if test='${internships.status eq "confirmed"}'>
                                <td>    <a href="AssignExaminers?internshipId=${internships.id}">Assign Faculty</a>       </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>