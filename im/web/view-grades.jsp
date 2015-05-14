<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <link href="css/styles.css" rel="stylesheet">
        <link rel="icon" href="logo/ims-logo1.ico" type="image/x-icon" />
        <meta charset="UTF-8">
        <title>View Grades</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <header>
            <nav>
                <ul>
                    <li>
                        <a href="studentHome">
                            Home
                        </a>
                    </li>
                    <li>
                        <a href="UpdateInternship">
                             Update Details
                        </a>
                    </li>
                </ul>
            </nav>
        </header>
        <table name="student-gradeTable" id="student-gradeTable">
            <thead>
                <tr> 
                </tr>
                <tr>
                    <th>Evaluation Criteria</th>
                    <th>Faculty: <br>${examiner1.firstName} ${examiner1.lastName} </th>
                    <th>Faculty: <br>${examiner2.firstName} ${examiner2.lastName} </th>
                    <th>Comments</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="gradesList" items="${requestScope.gradesList}">
                    <tr>
                        <td>
                            <h4>${gradesList.criteria.title} - ( out of ${gradesList.criteria.weight} )</h4>
                            <br/>
                            ${gradesList.criteria.description}</td>
                        <td>${gradesList.grade1}</td>
                        <td>${gradesList.grade2}</td>
                        <td>${gradesList.comment} </td>
                    </tr>
                </c:forEach>
                <tr>
                <tr>
                    <td >Report Total  (out of 75):</td>
                    <td>${reportTotal1}</td>
                    <td>${reportTotal2}</td>
                </tr>
                <tr>
                   <td >Presentation Total  (out of 25):</td>
                    <td> ${presentationTotal1} </td>
                    <td>${presentationTotal2} </td>
                </tr>
                <tr>
                    <td >- Total  (out of 100):  </td>
                    <td colspan="2"> ${total} </td>
                </tr>
                <tr>
                    <td>- Letter Grade:   </td>  
                    <td colspan="2"> ${letterGrade} </td>
                </tr>  
            </tbody>
        </table>
    </body>
</html>