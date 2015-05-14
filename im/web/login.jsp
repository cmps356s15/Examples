<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Internship Management System</title>
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="absolute-center">
            <div class="col-sm-12 col-md-10 col-md-offset-1">
                <h2>Welcome to IMS</h2>
                <c:if test='${not empty message}'>
                    <div class="alert alert-danger">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        ${message}
                    </div>
                    <c:remove var="message" scope="session" />
                </c:if>
                <form action="login" method="post">
                    <div class="form-group input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input class="form-control" type="text" name='username' placeholder="username"/>          
                    </div>
                    <div class="form-group input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input class="form-control" type="password" name='password' placeholder="password"/>     
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-def btn-block" value="Login" />
                    </div>
                </form>
            </div>  
        </div>    
    </div>
</html>