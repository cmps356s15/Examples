 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
 <html>
 <head >
     <link href="css/styles.css" rel="stylesheet">
     <meta charset="UTF-8">
     <link rel="icon" href="logo/ims-logo1.ico" type="image/x-icon" />
     <title>Update Internship Details</title>
 </head>
    <body>
     <jsp:include page="header.jsp" />
    <br>

    <header>

        <nav>

              <ul>

                     <li>
                      <a href="studentHome">


                           Home


                      </a>
                     </li>

                      <li>
                      <a href="studentViewGrade">


                           View Grade


                      </a>
                     </li>




              </ul>


          </nav>


    </header>

    <h3>Update Internship Details</h3>


    <div>
        <form action="UpdateInternship" method="post">
                       <fieldset>
                           <legend>Mentor</legend>

                       <label for="mentorFirstName">
                           First Name:
                           <input id="mentorFirstName" name="mentorFirstName" type="text" value="${mentor.firstName}">
                       </label>
                       <br>
                       <label for="mentorLastName">Last name:
                           <input id="mentorLastName" name="mentorLastName" type="test" value="${mentor.lastName}">
                       </label>
                       <br>
                       <label for="email">Email:
                           <input id="email" name="email" type="test" value="${mentor.email}">
                       </label>
                        <br>

                      <label for="phone">Phone:
                           <input id="phone" name="phone" type="test" value="${mentor.officePhone}">
                       </label>
                           <br>

                      <label for="mobile">Mobile:
                           <input id="mobile" name="mobile" type="test" value="${mentor.mobilePhone}">
                       </label>


            </fieldset>

            <br><br>

                       <fieldset>
                           <legend>Internship</legend>
                       <label for="street">
                           Street Name:
                           <input id="street" name="street" type="text" value="${hostCompany.street}">
                       </label>
                       <br>
                       <label for="city">

                         City:
                           <input id="city" name="city" type="text" value="${hostCompany.city}">
                       </label>
                       <br>
                        <label for="gpsCoordinates">

                          GPS Coordinates:
                          <input id="gpsCoordinates" name="gpsCoordinates" type="text" value="${hostCompany.gpsCoordinates}">
                       </label>



                       </fieldset>
               <br><br>

              <label for="abstract">

               Abstract:
               <textarea id="abstract" name="abstract" >${internshipAbstract}</textarea>
              </label>
              <br>

            <input type="submit" value="submit">

        </form>
    </body>
  </html>