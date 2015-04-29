$(document).ready(function () {
   //Set the completed date to today's date
   document.getElementById("completedDate").valueAsDate = new Date();
   $('#completedDate').on("input", validateCompletedDate);
});

function validateCompletedDate(){
    var completedDate = new Date( this.value );
    completedDate.setHours(0, 0, 0, 0); //remove time
    console.log("completedDate: " + completedDate);
    
    var dueDate = new Date(this.getAttribute('data-dueDate'));
    dueDate.setHours(0, 0, 0, 0); //remove time
    console.log("dueDate: " + dueDate);
    
    if(completedDate < dueDate ){
        this.setCustomValidity('Completed date should be >= due date');
    } else {
        this.setCustomValidity('');
    }
}