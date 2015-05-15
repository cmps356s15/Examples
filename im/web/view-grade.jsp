<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <link href="css/styles.css" rel="stylesheet">
        <title>View Grades</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <br>
        <h3>
            Internship #${internship.id} for Student ${internship.student.studentId} - ${internship.student.name} 
            @ ${internship.hostCompany.name} 
            <br>
            Examiner: ${internship.examiner.name}
        </h3>
        <br>
        <table>
            <thead>
                <tr>
                    <th style="width: 60%">Criteria</th>
                    <th>Rating</th>
                    <th>Grade</th>
                    <th>Comments</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="grade" items="${internship.gradeItems}">
                    <tr>
                        <td>
                            <p style="font-weight: bold">
                                ${grade.criteria.title} - ( out of ${grade.criteria.grade} )
                            </p>
                            ${grade.criteria.description}
                        </td>
                        <td>${grade.rating.title}</td>
                        <td>${grade.subTotal}</td>
                        <td>${grade.comment} </td>
                    </tr>
                </c:forEach>
                <tr style="font-weight: bold">
                    <td>Total  (out of 100):  </td>
                    <td colspan="3"> ${internship.totalGrade} </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>