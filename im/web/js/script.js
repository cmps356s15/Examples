

/**
 * Created by Mooza Al-nisf on 3/31/15.
 */

function submitCoordinatorForm() {
    // read selected state
    var list = document.getElementById("state");
    var selectedState = list.options[list.selectedIndex].value;
    // set it to the hidden field 

    document.getElementById("selectedState").value = selectedState;

    // submit the form 
    document.getElementById("selectionForm").submit();

}



function submitForm() {


    // read selected internship
    var list = document.getElementById("interships");
    var selectedInternshipID = list.options[list.selectedIndex].value;
    // set it to the hidden field 

    document.getElementById("students").value = selectedInternshipID;

    // submit the form 
    document.getElementById("selectionForm").submit();



}


function validateDate() {

    var date = document.getElementById("pDate").value; // in the format yyyy-mm-dd
    var todayDate = this.getTodayYear() + "-" + this.getTodayMonth() + "-" + this.getTodayDay();


    if (date < todayDate) {
        alert("Presentation's date should be greater or equal to today's date");
        return false;
    }

    return true;
}





// check that coordinator select either from preferred companies or from all companies
function checkConfirmedCompany() {

    var prefferedCompanies = document.getElementById("prefferedCompanies");
    var allCompanies = document.getElementById("allCompanies");



    if (prefferedCompanies.selectedIndex != 0 && allCompanies.selectedIndex != 0)
    {
        alert("please choose a country either from preffered or all");
        return false;

    } else if (prefferedCompanies.selectedIndex == 0 && allCompanies.selectedIndex == 0) {

        alert("please choose a country");
        return false;


    }

    return true;

}



//checks if at least 1 and max 4 comapnies are selected 
function checkSelectedCompanies() {

    // check that at least 1 is selected
    var list = document.getElementById("companys");
    var selected = list.options[list.selectedIndex];
    //if nothing has been selected(in this case a company)
    if (selected == null) {
        alert("Please select a company");
        return false;

    }

    // check that max 4 are selected
    var options = document.getElementById("companys");
    var count = 0;
    for (var i = 0; i < options.length; i++) {
        if (options[i].selected)
            count++;
        if (count > 4)
            break;
    }
    if (count > 4) {
        alert("please select from 1-4 companies.");
        return false;



    }
    return true;
}



function checkSelectedExaminers() {
    var options = document.getElementById("examiners");
    var count = 0;

    for (var i = 0; i < options.length; i++) {
        if (options[i].selected)
            count++;
        if (count > 2)
            break;
    }
    if (count != 2) {
        alert("please select 2 examiners.");
        return false;


    }

    return true;
}


function calculateTotal() {

    var totalReport = 0;
    var totalPresentation = 0;
    var total = 0;

    // get the list of rating from the page
    var ratingList = document.getElementsByName("ratingsScript");

    // define an array to store the percentage values
    var percentage = new Array();

    // assign the list percentage in the array
    for (var i = 0; i < ratingList.length; i++) {

        percentage[i + 1] = ratingList[i].value;
    }





    // loop over ratings and calculate the totals



    for (var i = 1; i <= 7; i++) {

        var index = "rating" + i;
        var list = document.getElementById(index);
        var selected = list.options[list.selectedIndex];
        var percent = percentage[selected.value];
        var criteriaWeight = selected.getAttribute("criteriaWeight");
        var criteriaType = selected.getAttribute("criteriaType");

        total = total + (percent * criteriaWeight);


        if (criteriaType == "presentation") // it is a presentation
        {
            var index = "rating" + i;
            var list = document.getElementById(index);
            var selected = list.options[list.selectedIndex];
            var percent = percentage[selected.value];
            var criteriaWeight = selected.getAttribute("criteriaWeight");
            totalPresentation = totalPresentation + (percent * criteriaWeight);
        }
        else if (criteriaType == "report") { // it is a report
            var index = "rating" + i;
            var list = document.getElementById(index);
            var selected = list.options[list.selectedIndex];
            var percent = percentage[selected.value];
            var criteriaWeight = selected.getAttribute("criteriaWeight");
            totalReport = totalReport + (percent * criteriaWeight);


        }

    }


    // set the tect fields to the results

    document.getElementById("reportTotal").value = totalReport;
    document.getElementById("presentationTotal").value = totalPresentation;
    document.getElementById("total").value = total;




}



//these functions are used in validating the date 

function getTodayDay() {
    var currentDate = new Date();
    var day = currentDate.getDate();
    if (day < 10)
        day = "0" + day;
    return day;
}



function getTodayMonth() {
    var currentDate = new Date();
    var month = currentDate.getMonth() + 1;
    if (month < 10)
        month = "0" + month;
    return month;
}


function getTodayYear() {
    var currentDate = new Date();
    return currentDate.getFullYear();
}

// used to check that file extenstion is pdf
function checkFile() {

    var uplodedFile1 = document.getElementById('cvAttachment');
    var uplodedFile2 = document.getElementById('idAttachment');

    var fileName1 = uplodedFile1.value;
    var fileName2 = uplodedFile2.value;

    var ext1 = fileName1.substring(fileName1.lastIndexOf('.') + 1);
    var ext2 = fileName2.substring(fileName2.lastIndexOf('.') + 1);

    if (ext1 === "pdf" && ext2 === "pdf")
    {
        return true;
    }
    else
    {
        alert("Upload pdf files only");
        return false;
    }

}
//
//function addNewCompany(){
//    
//    var option= document.getElementById("companys").value;
//    
//    if (option == "add-company")
//        window.location.href=this.form.URL.options[this.form.URL.selectedIndex].value;
//     
//}
