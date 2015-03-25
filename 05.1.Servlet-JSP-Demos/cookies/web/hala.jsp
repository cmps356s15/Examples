
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.heading}</title>
    </head>
    
    <body>
      
      <!--For the first 3 visits display a welcome message.
          After the 3rd visit display an advice message
      -->
      <c:choose>
        <c:when test='${empty cookie.accessCount or cookie.accessCount.value <= 3}' >
            <div style="text-align: center;">
                <h1>السلام عليكم ورحمة الله وبركاته</h1>
                <h2>يا هلا و مرحبا بك</h2>
                <h3>نورت المنتدى بأول زيارة لك</h3>
                <h3>Number of visists: ${empty cookie.accessCount ? 1 : cookie.accessCount.value} </h3>
                <img src="img/Hala.gif" />
            </div>
        </c:when>
    
        <c:otherwise >
            <div style="text-align: center;">
                <h1>السلام عليكم ورحمة الله وبركاته</h1>
                <h1>يا هلا و مرحبا بك</h1>
                <h2 style="color: red">!نورت المنتدى بزياراتك المتكررة. يا مضيع أوقاته</h2>
                <h3>Number of visists: ${cookie.accessCount.value} </h3>
                <table style="width: 100%">
                    <tr>
                        <td style="width: 30%"><img src="img/Wa9t.jpg" style="width:100%;height:100%;" ></td>
                        <td style="width: 40%"><img src="img/Wa9t2.jpg" style="width:100%;height:100%;" ></td>
                        <td style="width: 30%"><img src="img/فاز.jpg" style="width:100%;height:100%;" ></td>
                    </tr>
                </table>
            </div>
        </c:otherwise>
      </c:choose>
    </body>
</html>
