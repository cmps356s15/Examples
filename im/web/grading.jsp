<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Internship Management System</title>
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <h1>Internship Grading</h1>
        <br>
        <form action="grading" method="post">
            <label for="internshipId">Internships</label>
            <select name="internshipId" id="internshipId" >
                <c:forEach var="internship" items="${internships}">
                    <option value="${internship.id}"
                            ${internship.id eq selectedId ? "selected" : ""}>
                        ${internship.student.studentId} - ${internship.student.name} @ ${internship.hostCompany.name} 
                    </option>
                </c:forEach>
            </select>

            <br><br>

            <table>
                <thead>
                    <tr>
                        <th>Criteria</th>
                        <th>Rating</th>
                        <th>Comment</th>
                    </tr>
                </thead>

                <tbody>
                  <c:forEach var="criterion" items="${criteria}" varStatus="loopIndex">
                  <tr>
                    <input type="hidden" name="criteriaId" value="${criterion.id}">
                    <td class="criterea" data-grade="${criterion.grade}">
                        <p style="font-weight: bold">
                            ${loopIndex.count}. ${criterion.title} (out of ${criterion.grade})
                        </p>
                        ${criterion.description}
                    </td>
                    <td>
                        <!--${rating.id eq ratings[criterion.id] ? "selected" : ""}-->
                        <select name="rating" class="rating" required>
                            <!--<option value=""></option> -->
                            <c:forEach var="rating" items="${ratings}">
                                <option value="${rating.id}" data-percentage="${rating.percentage}">
                                    ${rating.title}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <textarea name="comment">
                        </textarea>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
            <br>
            <br>
            <input type="submit" value="submit">
        </form>
    </body>
</html>