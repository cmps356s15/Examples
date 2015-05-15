<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head >
        <title>Internship Management System</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
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
            <p>  There are no ${selectedState eq 'all' ? " " : selectedState} internship.</p>
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
                        <th>Examiner</th>
                        <th>Presentation</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="internship" items="${internships}">
                        <tr>
                            <td> ${internship.id} </td>
                            <td> ${internship.student.studentId}</td>
                            <td> ${internship.student.firstName} ${internship.student.lastName}</td>
                            <td> ${internship.year} </td>
                            <td> ${internship.status} </td>
                            <td> ${internship.hostCompany.name} </td>
                            <td> ${internship.examiner.name} </td>
                            <td>
                                ${internship.presentationDate} ${internship.presentationTime} 
                                ${not empty internship.presentationLocation ? "@ " : ""}
                                ${internship.presentationLocation}
                            </td>
                            <c:if test='${internship.status eq "pending"}'>
                                <td>
                                    <a href="confirm?internshipId=${internship.id}">
                                        Confirm
                                    </a>               
                                </td>
                            </c:if>
                            <c:if test='${internship.status eq "confirmed"}'>
                                <td>    
                                    <a href="AssignExaminer?internshipId=${internship.id}">
                                        Assign Examiner
                                    </a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>