$(document).ready(function () {
    $('#presentationDate').on("input", validatePresentationDate);

    $('#companies').on("change", function () {
        this.setCustomValidity('');
        if ($(this).val().length > 4) {
            this.setCustomValidity('You can only select up to 4 companies!');
        }
    });

    $('select#internshipId').on("change", function () {
        var internshipId = $(this).val();
        var url = 'http://' + $(location).attr('host') 
                    + $(location).attr('pathname') 
                    + '?internshipId=' + internshipId;
        console.log("url = " + url);
        window.location = url;
    });
    
    //For grading page listen to the ratingPercentage change event and recompute the total grade
    $('#gradingForm').find('.rating').on("change", function () {
        getTotalGrade();
    });
    
    if($("#gradingForm").length > 0){
        getTotalGrade();
    }
});

function validatePresentationDate() {
    var presentationDate = new Date(this.value);
    presentationDate.setHours(0, 0, 0, 0);
    console.log("presentationDate: " + presentationDate);

    var today = new Date();
    today.setHours(0, 0, 0, 0);
    console.log("today: " + today);

    if (presentationDate < today) {
        this.setCustomValidity('Presentation Date should be >= today');
    } else {
        this.setCustomValidity('');
    }
}

function getTotalGrade() {
    var ratingList = $('.rating'); //get all elements with the class ratingPercentage
    var total = 0;

    $('.criteria').each( function( index, element ){
         var grade = $(this).data('grade'); //get the grade associated with each criteria
         var ratingPercentage = $(ratingList[index]).children('option:selected').data('percentage');
         console.log('Grade: ' + grade + '  Rating Percentage: ' + ratingPercentage);
         if (isInteger(ratingPercentage)) {
               total += (ratingPercentage * grade);
         }
    });
    var totalHtml = "Total Grade: " + Math.round(total * 100) / 100;
    console.log(totalHtml);
    $('#totalGrade').html(totalHtml);
}

function isInteger(n) {
    return !isNaN(parseInt(n)) && isFinite(n);
}