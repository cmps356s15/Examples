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
    
    $("#addContact").on("click", function (event) {

        
         var $theForm = $(this).closest('form');
        //Some browsers don't implement checkValidity
        if (( typeof($theForm[0].checkValidity) == "function" ) && !$theForm[0].checkValidity()) {
           return;
        }
        //avoid form submission
        event.preventDefault();
        
        //The delete link that was clicked
        var contact = {
            firstName: $('#firstName').val(),
            lastName: $('#lastName').val(),
            email: $('#email').val(),
            phone: $('#phone').val(),
            mobile: $('#mobile').val(),
            address: {
                street: $('#street').val(),
                city: $('#city').val(),
                country: $('#country').val()
            }          
        };
        onAddContact(JSON.stringify(contact));
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

function onAddContact(contact) {
    console.log(contact);

    var request = $.ajax({
        method: "POST",
        url: "api/contacts/",
        data : contact,
        contentType: 'application/json'
    });

    request.done(function (data) {
        console.log(data);
        //window.location = window.location.host + '/cmsapp/contacts.html';
        window.location.href = 'contacts.html';
    });

    request.fail(function (jqXHR, status) {
        alert("Request failed: " + status);
    });
}