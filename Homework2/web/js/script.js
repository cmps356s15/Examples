

//updating max of toAya and fromAya by getting the aya count of the selected surah
function updateMax(sur) {
    var maxAya = $('option:selected', sur).attr('data-ayaCount');
    console.log(maxAya);
    document.getElementById('fromAya').setAttribute('max', maxAya);
    document.getElementById("fromAya").value = '1';
    document.getElementById("selectedFromAya").value = '1';
    document.getElementById('toAya').setAttribute('max', maxAya);
    document.getElementById("toAya").value = '1';
    document.getElementById("selectedToAya").value = '1';
}

//forcing toAya slider to always be larger than from aya
$(document).ready(function () {
    $("#fromAya").on('change', function (e) {
        $("#toAya").attr('min', +$("#fromAya").val() + 1);
        $("#toAya").val(+$("#fromAya").val() + 1);
        $("#selectedToAya").text(+$("#fromAya").val() + 1);
    });

    var today = new Date();
    document.getElementById('dueDate').setAttribute('min', today.toISOString().substring(0, 10));

});

function setDueDate() {
    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    document.getElementById('dueDate').valueAsDate = tomorrow;
}







