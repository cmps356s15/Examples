<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Hifz Tracker</title>
        <link href="css/styles.css" rel="stylesheet">
    </head>

    <body>
        <header>
            <div class="header">
                <img src="img/logo.png" alt="Hifz Tracker Logo">
                <h1>Hifz Tracker</h1>
            </div>
        </header>
        <c:if test='${not empty message}'>
            <p class='message'>${message}</p>
            <c:remove var="message" scope="session" />
        </c:if>
        <section id="loginForm">
            <h2>Login</h2>
            <div>
                <form method="post" action="login">
                    <p>
                        <input type="text" name="username" id="username" maxlength="15" minlength="2" placeholder="Username" required />
                    </p>
                    <p>
                        <input type="password" name="password" id="password" maxlength="10" minlength="3" placeholder="Password" required />
                    </p>
                    <input type="submit" value="Login">
                </form>
            </div>
        </section>
    </body>
</html>
