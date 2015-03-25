<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Welcome!</h1>
        
        <c:if test='${not empty requestScope.errorMessage}' >
            <h1 style="color: red">${requestScope.errorMessage}</h1>
        </c:if>
    
        <form id='login' action='login' method='post' accept-charset='UTF-8'>
            <fieldset >
            <legend>Login</legend>
            <h3>(Login User Names you can use: ali or mariam and Password is password.</h3>
            <table>
                <tr>
                    <td> <label for='userName' >User Name*:</label> </td>
                    <td> <input type='text' name='userName' id='userName'  maxlength="50" /> </td>
                </tr>
                <tr>
                    <td> <label for='password' >Password*:</label> </td>
                    <td> <input type='password' name='password' id='password' maxlength="50" /> </td>
                </tr>
                <tr>
                    <td colspan="2"> <input type="checkbox" name='rememberMe' >Remember me</input> </td>
                </tr>
                <tr>
                    <td colspan="2"> <input type='submit' name='Submit' value='Submit' /> </td>
                </tr>
            </table>
            </fieldset>
            </form>
    </body>
</html>
