$(document).ready(function () {
    console.log("DOCUMENT READY!");
    if ($('#dueDate').val() === "") {
        //Set the due date to today + 1
        var tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        document.getElementById('dueDate').valueAsDate = tomorrow;
    }
    
    $('#dueDate').on("input", validateDueDate);
    
    //When the Sura changes change the max-value of the Aya sliders
    $('#sura').on("change", function () {
        var ayaCount = $('option:selected', this).attr('data-ayaCount');
        console.log('ayaCount = ' + ayaCount);
        updateAyaSlider(ayaCount);
    });
    
    //Make sure toAya is never less than fromAya
    $("#fromAya").on('change', onFromAyaChange);
    
    //Make sure toAya is never less than fromAya
    $("#toAya").on('change', onToAyaChange);
    
});

//updating max of toAya and fromAya by getting the aya count of the selected surah
function updateAyaSlider(ayaCount) {
    $('#fromAya').attr('max', ayaCount);
    $("#fromAya").val(1);
    $("#selectedFromAya").html(1);
    $("#fromMaxAya").html(ayaCount);
    
    $('#toAya').attr('max', ayaCount);
    $("#toAya").val(1);
    $("#selectedToAya").html(1);
    $("#toMaxAya").html(ayaCount);
}

function onFromAyaChange() {
    var fromAya = parseInt($("#fromAya").val());
    var toAya = parseInt($('#toAya').val());
    console.log("test");
    if (toAya < fromAya) {
        var ayaCount = parseInt($('#fromAya').attr('max'));
        var toAya = fromAya === ayaCount ? ayaCount : fromAya + 1; 

        $("#toAya").val(toAya);
        $("#selectedToAya").html(toAya);
    }
}

function onToAyaChange() {
    var fromAya = parseInt($("#fromAya").val());
    var toAya = parseInt($('#toAya').val());
    if (toAya < fromAya) {
       $('#toAya').val(fromAya);
       $("#selectedToAya").html(fromAya);
    }
}

function validateDueDate(){
    var dueDate = new Date( this.value );
    dueDate.setHours(0, 0, 0, 0);
    console.log("dueDate: " + dueDate);
    
    var today = new Date();
    today.setHours(0, 0, 0, 0);
    console.log("today: " + today);
    
    if( dueDate < today ){
        this.setCustomValidity('Due date should be >= today');
    } else {
        this.setCustomValidity('');
    }
}