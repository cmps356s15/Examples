<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <link href="css/styles.css" rel="stylesheet">
        <link rel="icon" href="logo/ims-logo1.ico" type="image/x-icon" /> 

        <meta charset="UTF-8">
        <title>Enter Grades</title>
      
    </head>
    <body onload='calculateTotal()'>
        
        
        
        <script src="js/script.js"></script>
        <jsp:include page="header.jsp" />
        <header>

                


        </header>


        <section>
            
            
            <form id="selectionForm" action="ExaminerGradeSheet" method="get" onchange="submitForm()">
                
                   <Label>Internships :</label>
                
                <select name="interships" id="interships">
                    <option value=""></option>

                    <c:forEach var="interships" items="${requestScope.interships}">
                       
                        <option value=${interships.interId} 
                                
                                ${interships.interId eq internshipID ? "selected" : "" }
                                
                                
                                > (Internship #${interships.interId}) ${interships.student.studentId} - ${interships.student.firstName} ${interships.student.lastName} @ ${interships.hostCompany.name}</option>
                    
                    </c:forEach>
                        
                </select>
                
                
                
                
            </form>
            
            
            <form action="ExaminerGradeSheet" method="post" onchange='calculateTotal()'>

             
                
                  <c:if test='${not empty message}'>
                  <p class='message'>${message} </p>
                  <c:remove var="message" scope="session" />
                  </c:if>
        
                <!--hidden field used to read the selected internship from the first form in the post method-->
                <input type="hidden" name="students" id="students" value="${internshipID}"/>
                
                
                <!--this loop is for JS to auto calculate the totals, simply getting rating list from controller and store it in an array-->
                <c:forEach var="rating" items="${ratingList}">
                    
                    <input type="hidden" name="ratingsScript" value="${rating.percentage}"/> 
                    
                </c:forEach>
                
                
                
                
               <!--If the examiner already entered a values, set them-->
                 <c:if test='${not empty contentlist}'>
                 
                     
                 
                
                        <table name="examiner-gradeTable" id="examiner-gradeTable">


                            <thead>

                            <tr>

                                <th> Evaluation Criteria</th>
                                <th>Select a rating?</th>
                                <th>Comment</th>

                            </tr>


                            </thead>

                            <tbody>

                                <c:forEach var="row" items="${requestScope.contentlist}">
                                    <tr>
                                        <td name="Criterias" value="${row.criteria.id}" >
                                            <h4>${row.criteria.title} -  ( out of ${row.criteria.weight} ) </h4>
                                            <br/>
                                            ${row.criteria.description}
                                        </td>


                                        <td>

                                            <select name="rating" id="rating${row.criteria.id}" >

                                                <c:forEach var="ratingList" items="${requestScope.ratingList}">

                                                    <option  value="${ratingList.id}" criteriaWeight="${row.criteria.weight}" criteriaType="${row.criteria.type}" 

                                                             ${ratingList.id eq row.rating.id ? "selected" : "" }
                                                             > 

                                                        ${ratingList.title} (Grade*${ratingList.percentage})

                                                    </option>

                                                </c:forEach>
                                            </select>
                                        </td>


                                        <td><textarea id="comment" name="comment">${row.comment}</textarea></td>
                                    </tr>
                                   </c:forEach>






                                  </tbody>


                                  </table>
                    </c:if>
                
                
               
                    <!--if the examiner didn't enter any grades before, load empty form-->
                    
                    <c:if test='${ empty contentlist}'>
                    
                    
                                <table name="examiner-gradeTable" id="examiner-gradeTable">


                                       <thead>

                                       <tr>

                                           <th> Evaluation Criteria</th>
                                           <th>Select a rating?</th>
                                           <th>Comment</th>

                                       </tr>


                                       </thead>

                                       <tbody>

                                           <c:forEach var="Criterias" items="${requestScope.Criterias}">
                                               <tr>
                                                   <td name="Criterias" value="${Criterias.id}" >
                                                       <h4>${Criterias.title} -  ( out of ${Criterias.weight} ) </h4>
                                                       <br/>
                                                       ${Criterias.description}
                                                   </td>


                                                   <td>

                                                       <select name="rating" id="rating${Criterias.id}" >

                                                           <c:forEach var="ratingList" items="${requestScope.ratingList}">

                                                               <option  value="${ratingList.id}" criteriaWeight="${Criterias.weight}" criteriaType="${Criterias.type}" > 

                                                                   ${ratingList.title} (Grade*${ratingList.percentage})

                                                               </option>

                                                           </c:forEach>
                                                       </select>
                                                   </td>


                                                   <td><textarea id="comment" name="comment"></textarea></td>
                                               </tr>
                                           </c:forEach>

                                           <tr>




                                       </tbody>


                           </table>


                    
                    
                    
                </c:if>
                
                
                
                <!--fields to show the totals-->
                
                <br/><br/>
                Total Report (out of 75 ) : <input type='text' id='reportTotal' disabled="" ></p>
                <br/>
                Total Presentation (out of 25 ) : <input type='text' id='presentationTotal' disabled="" ></p>
                <br/>
                Total (out of 100 ) : <input type='text' id='total' disabled="" ></p>
                <br/>



                <br/>
                <input type="submit" value="submit" >
                
                


            </form>

        </section>


    </body>
</html>