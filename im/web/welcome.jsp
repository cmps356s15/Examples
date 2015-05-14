<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head >
        <link href="css/styles.css" rel="stylesheet">
        <link rel="icon" href="logo/ims-logo1.ico" type="image/x-icon" />
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <header class="header">

            <img src="logo/ims-logo2.jpg">


            <span> Welcome " ${user.firstName}, ${user.lastName} " (<a href="login">logout?</a>)</span>


        </header>
        <div class="seperator">

        </div>


    </body>
</html>