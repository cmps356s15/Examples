<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head lang="en">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <meta charset="UTF-8">
    <title>Internship Management System</title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">   
</head>
<body>

<!--Navigation Bar-->
<div class="navbar-fixed-top">
    <ul class="menu">
        <li class="menu-item"><a class="menu-link logo">Internship Management System</a></li>
        <li class="menu-item"><a class="menu-link" href="pendingInternships">Pending Internships</a></li>
        <li class="menu-item"><a class="menu-link" href="confirmedInternships">Confirmed Internships</a></li>
        <li class="menu-item"><a class="menu-link" href="logout">Logout</a></li>
    </ul>
</div>

<div>
    <h3>Welcome ${sessionScope.user.getFullName()}</h3>
</div>
<!--Completed List-->
<div class="completed-section">

    <h1>Confirm Internship</h1>
    <br><br>
    <form action="confirmInternship" method="post">
        <input type='hidden' value ='${internship.id}' name='internshipIndex'>
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

        <div class="Field">
            <h2>Company Selection</h2>
            <br>

            <label>Select from: </label>
            <br>
            <input type="radio" id = "existingCompany" name = "selectFrom" value="existing" checked="checked" >
            <label>Existing companies</label>
            <br>
            <input type="radio" id = "newCompany" name = "selectFrom" value="new" >
            <label>Add company</label>
            <br><br>

            <div id="availableCompanies"  >
                <label>Available Companies</label>
                <br>
                <select name='selectedCompany' id='selectedCompany'>
                    <c:forEach var="company" items="${companies}">
                        <option value="${company.value.id}">${company.value.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div id="addCompany" style="display: none">
                <h2>Company Information</h2>
                <br>
                <label>Company Name</label>
                <br>
                <input name = "companyName" id="companyName" type="text" required>
                <br>

                <br>
                <label>Street</label>
                <br>
                <input name = "street" id="street" type="text" required>
                <br>


                <br>
                <label>City</label>
                <br>
                <input name = "city" id="city" type="text" required>
                <br>

                <br>
                <label>Phone</label>
                <br>
                <input name = "phone" id="phone" type="tel" required>
                <br>


                <br>
                <label>Website</label>
                <br>
                <input name = "website" id="website" type="url" required>
                <br>


                <br>
                <label>Email</label>
                <br>
                <input name = "email" id="email" type="email" required>
                <br>



            </div>

            <br><br>
        </div>




        <div class="formSubmit">
            <input type="submit" value="Confirm">
        </div>
    </form>
</div>

         <script type='text/javascript'>
             $(document).ready(function(){
               $("#addCompany > input").removeAttr("required");
                $("#existingCompany").prop("checked", true);
             });
                    $("#existingCompany").click(function(){
                        $("#addCompany > input").removeAttr("required");
                        $('#availableCompanies').css('display',"block");
                        $('#addCompany').css('display','none'); 
                    });


                    $("#newCompany").click(function(){


                        $('#availableCompanies').css('display',"none")
                        $('#addCompany').css('display','block'); 
                        $('#addCompany > input').attr('required', 'true');
                    });
           
                
            
        </script>
</body>
</html>
