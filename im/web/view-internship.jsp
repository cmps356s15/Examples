<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">
        <title>Internship Management System</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <nav>
            <ul>
                <c:forEach var="item" items="${menuItems}">
                    <li>
                        <a href="${item.controllerName}">
                            ${item.name}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
        <section>
            <c:if test='${not empty message}'>
                <p class='message'>${message}</p>
                <c:remove var="message" scope="session" />
            </c:if>

            <h1> Internship Details</h1>
            </br>
            <table>
                <thead>
                    <tr> 
                        <th>Internship Id</th>
                        <th>Status</th>
                        <th>Host Company</th>
                        <th>Preferred Companies</th>
                    </tr>
                </thead>
                <tbody>
                    <tr> 
                        <td>${internship.id}</td>
                        <td>${internship.status}</td>
                        <td>${internship.hostCompany.name}</td>
                        <td>
                            <c:forEach var="company" items="${internship.preferredCompanies}">
                                - ${company.name} <br> 
                            </c:forEach>
                        </td>
                    </tr>
                </tbody>
            </table>
        </section>
    </body>
</html>
