<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.heading}</title>
    </head>
    <body BGCOLOR="#FDF5E6">
    <center>
        <h1>${requestScope.heading}</h1>
        <h2>Information on Your Session:</h2>
        <table BORDER=1>
            <tr BGCOLOR="#FFAD00">
                <th>Info Type </th>
                <th>Value </th>
            </tr>
            <tr>
                <td>Session ID </td>
                <td>${pageContext.session.id}</td>
            </tr>
            <tr>
                <td>Creation Time</td>
                <!--td>${requestScope.sessionCreationDate}</td-->
                <td>${pageContext.session.creationTime}</td>
            </tr>
            <tr>
                <td>Time of Last Access</td>
                <!--td>${requestScope.sessionLastAccessedTime}</td-->
                <td>${pageContext.session.lastAccessedTime}</td>
            </tr>
            <tr>
                <td>Number of Previous Accesses</td>
                <td>${sessionScope.accessCount}</td>
            </tr>
        </table>
    </center>
    </body>
</html>
