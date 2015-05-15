$(document).ready(function () {
    $('#presentationDate').on("input", validatePresentationDate);

    $('#companies').change(function () {
        this.setCustomValidity('');
        if ($(this).val().length > 4) {
            this.setCustomValidity('You can only select up to 4 companies!');
        }
    });

    $('select#internshipId').change(function () {
        var internshipId = $(this).val();
        console.log("internshipId = " + internshipId);
        window.location = 'http://localhost:8080/im/grading?internshipId=' + internshipId;
    });
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