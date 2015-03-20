<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <title>Add Contact</title>
        <link rel="stylesheet" href="css/styles.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="js/script.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <h3>Add Contact</h3>
        <form action="addcontact" method="post">
            <label>First Name 
                <input type="text" id = "firstName" name="firstName" required />
            </label>
            <label>Last Name
                <input type="text" id = "lastName" name="lastName" required/>
            </label> 
            <label>Phone
                <input type="text" id = "phone" name="phone" />
            </label>
            <label>Mobile
                <input type="text" id = "mobile" name="mobile" required />
            </label>
            <label>Email
                <input type="text" id = "email" name="email" required />
            </label>
            <label>Street
                <input type="text" id = "street" name="street" />
            </label>

            <label>Country
                <select id = "country" name="country" required onchange="onCountryChanged()">
                    <option value=''></option>
                    <c:forEach var="country" items="${countryList}">
                        <option value='${country}'>
                            ${country}
                        </option>
                    </c:forEach>
                </select>
            </label>
            <label>City
                <select id = "city" name="city" required >
                    <option value=""></option>
                    <c:forEach var="city" items="${cityList}">
                        <option value='${city}'>
                            ${city}
                        </option>
                    </c:forEach>
                </select>
            </label>
            <input type="submit" id="submit" value="Submit" />
        </form>
    </body>
</html>