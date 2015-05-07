<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
    <head lang="en">
        <meta charset="UTF-8">
        <title>Internship Management System</title>
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <style>
        .absolute-center {
            margin: auto;
            position: absolute;
            top: 0; left: 0; bottom: 0; right: 0;
            width: 50%; 
            height: 50%;
            min-width: 200px;
            max-width: 400px;
            padding: 40px;
        }
    </style>

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