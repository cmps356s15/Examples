$(document).ready(function () {
    fillCountryDropDown();

    $('#country').change(
            function () {
                if ($(this).val() === '') {
                    $('#city').html('');
                }
                else {
                    getCities($(this).val());
                }
            });

    $(document).ajaxStart(function () {
        $("#loading").show();
    });

    $(document).ajaxStop(function () {
        $("#loading").hide();
    });
});


function fillCountryDropDown() {
    var request = $.ajax({
        method: "GET",
        url: "countries.json",
        dataType: "json"
    });

    request.done(function (data) {
        console.log(data);
        var templateSource = $("#country-template").html();
        var template = Handlebars.compile(templateSource);
        var html = template(data);
        $("#country").html(html);
    });

    request.fail(function (jqXHR, status) {
        alert("Request failed: " + status);
    });
}

function getCities(countryCode) {
    var url = 'api/contacts/cities/' + countryCode;
    var request = $.ajax({
        method: "GET",
        url: url,
        dataType: "json"
    });

    request.done(function (data) {
        console.log(data);
        var templateSource = $('#city-template').html();
        var template = Handlebars.compile(templateSource);
        var html = template(data);
        $("#city").html(html);
    });

    request.fail(function (jqXHR, status) {
        alert("Request failed: " + status);
    });
}