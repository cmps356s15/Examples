<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>

<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Internship Management System</title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>

<!--Navigation Bar-->
<div class="navbar-fixed-top">
    <ul class="menu">
        <li class="menu-item"><a class="menu-link logo">Internship Management System</a></li>
        <c:if test="${internship == null || internship.IsConfirmed() == false}">
            <li class="menu-item"><a class="menu-link" href="registerInternship">Register Internship</a></li>
        </c:if>    
  
        <c:if test="${internship != null && internship.IsConfirmed() == true}">
            <li class="menu-item"><a class="menu-link" href="registerInternship">Update Internship</a></li>
            <li class="menu-item"><a class="menu-link" href="viewGrades?internshipIndex=${internship.id}">View Internship Grade</a></li>
        </c:if>
            <li class="menu-item"><a class="menu-link" href="logout">Logout</a></li>
    </ul>
</div>

<div>
    <h3>Welcome ${sessionScope.user.getFullName()}</h3>
</div>
<!--Completed List-->
<div class="completed-section">
    <c:if test="${user.getCompletedCHs() < 90}">
        <script>
            window.alert("You need to complete more than 90 CH in order to register an internship")
            window.location.href="logout";
        </script>"
    </c:if>
    <c:if test="${internship == null}">
        <h1>Register For Internship</h1>
    </c:if>
        
    <c:if test="${internship != null && internship.IsConfirmed() == false}">
        <h1>Internship Status: Pending Coordinator Approval</h1>
    </c:if>
        
    <c:if test="${internship != null && internship.IsConfirmed() == true}">
        <h1>Update Internship Information</h1>
    </c:if>
    <br><br>
    
        <div class="Field">
            <h2>Student Information</h2>
            <br>
            <label>Name: ${user.firstName}</label>
            <br>
            <label>Student ID: ${user.studentId}</label>
            <br>
            <label>Completed Hours: ${user.completedCHs}</label>
            <br>
            <label>GPA: ${user.GPA}</label>
        </div>
        <c:if test="${internship == null}">
            <form action="registerInternship" method="post">
                <div class="Field">
                    <h2>Company Selection</h2>
                    <br>
                    <label>Available Companies</label>
   
                   
                    <div style="padding:20px">

                    <select id="companies" multiple>
                             <c:forEach var ="company" items="${companies}">
                                <option value="${company.value.name}">${company.value.name}<br>
                            </c:forEach>
                    </select>
                    </div>
                 
                </div>
  
                <div class="formSubmit">
                    <input type="submit" value="Register">
                </div>
            </form>
        </c:if>
        
        <c:if test="${internship != null && internship.IsConfirmed() == true}">
            <form action="updateInternship" method="post">
                <div class="Field">
                    <h2>Mentor Information</h2>
                    <br>
                    <label>First Name</label>
                    <br>
                    <input type="text" name="firstName" value="${internship.mentor.firstName}">
                    <br>

                    <br>
                    <label>Last Name</label>
                    <br>
                    <input type="text" name="lastName" value="${internship.mentor.lastName}">
                    <br>


                    <br>
                    <label>Office Phone</label>
                    <br>
                    <input type="text" name="officePhone" value="${internship.mentor.officePhone}">
                    <br>

                    <br>
                    <label>Mobile</label>
                    <br>
                    <input type="text" name="mobile" value="${internship.mentor.mobile}">
                    <br>


                    <br>
                    <label>Email</label>
                    <br>
                    <input type="text" name="email" value="${internship.mentor.email}">
                    <br>

                </div>

                <div class="Field">
                    <h2>Internship Information</h2>
                    <br>
                    <label>Location</label>
                    <br>
                    <input type="text" placeholder="LONGITUDE" name="longitude" value="${internship.loc.long}">
                    <input type="text" placeholder="LATITUDE" name="latitude" value="${internship.loc.lat}">

                    <br><br>
                    <label>Abstract Upload</label>
                    <input type="file" name="abstract" value="${internship.abstract}">
                </div>


                <div class="formSubmit">
                    <input type="submit" value="Update Internship Info">
                </div>
        </c:if>
    </form>
</div>
        
<script>
    $(document).ready(function() {

      var last_valid_selection = null;

      $('#companies').change(function(event) {

        if ($(this).val().length > 4) {
          alert('You can only choose 4!');
          $(this).val(last_valid_selection);
        } else {
          last_valid_selection = $(this).val();
        }
      });
  });
</script>

</body>
</html>
