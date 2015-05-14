<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head >
        <link href="css/styles.css" rel="stylesheet">
        <link rel="icon" href="logo/ims-logo1.ico" type="image/x-icon" /> 
        <meta charset="UTF-8">
        <title>Assign Examiners</title>
    </head>
    <body  >
        <jsp:include page="header.jsp" />
        <h3>Assign Examiners</h3>
        <br/>

        <div>
            <form action="AssignExaminers" method="post" >
                <input type="hidden" name="internshipId" value="${internship.id}" >
                <h3>
                    Internship #${internship.id} for Student ${internship.student.studentId} - ${internship.student.name}
                </h3>
                <br/>

                <label for="examiners">Examiners:</label>

                <select id="examiners" name="examiners" multiple >
                    <c:forEach var="examiner" items="${requestScope.examinerList}">
                        <option  value="${examiner.staffNo}">${examiner.firstName} ${examiner.lastName} </option>
                    </c:forEach>
                </select>

                <div>
                    <label for="pLocation">Presentation Location:</label>
                    <input id="pLocation" name="pLocation" type="text" required/>
                    <br/><br/>

                    <label >Presentation Date:</label>
                    <input id="pDate" name="pDate" type="date" required />

                    <br/><br/>

                    <label >Presentation Time:</label>
                    <input id="pTime" name="pTime" type="time" required/>

                    <br/><br/>
                    <br/>
                </div>
                <input type="submit" value="submit">
            </form>
        </div>
    </body>
</html>