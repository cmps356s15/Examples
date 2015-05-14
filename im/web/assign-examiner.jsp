<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head >
        <link href="css/styles.css" rel="stylesheet">
        <title>Assign Examiner</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
            });
        </script>
    </head>
    <body  >
        <jsp:include page="header.jsp" />
        <h3>Assign Examiner</h3>
        <br/>

        <div>
            <form action="AssignExaminer" method="post" >
                <input type="hidden" name="internshipId" value="${internship.id}" >
                <h3>
                    Internship #${internship.id} for Student ${internship.student.studentId} - ${internship.student.name}
                </h3>
                <br/>

                <label for="examiners">Examiner:</label>
                <select id="examiner" name="examiner" required>
                    <option value = ""></option>
                    <c:forEach var="examiner" items="${examiners}">
                        <c:if test="${examiner.isCoordinator() == false}">
                            <option value = "${examiner.staffNo}"
                                    ${examiner.staffNo eq internship.examiner.staffNo ? "selected" : ""}>
                                ${examiner.name}
                            </option>
                        </c:if>
                    </c:forEach>
                </select>
                <br><br>

                <div>
                    <label for="pLocation">Presentation Location:</label>
                    <input id="pLocation" name="pLocation" 
                           value="${internship.presentationLocation}"
                           type="text" required/>
                    <br/><br/>

                    <label >Presentation Date:</label>
                    <input id="pDate" name="pDate"
                           value="${internship.presentationDate}"
                           type="date" required />

                    <br/><br/>

                    <label >Presentation Time:</label>
                    <input id="pTime" name="pTime" 
                           value="${internship.presentationTime}"
                           type="time" required/>

                    <br/><br/>
                    <br/>
                </div>
                <input type="submit" value="submit">
            </form>
        </div>
    </body>
</html>