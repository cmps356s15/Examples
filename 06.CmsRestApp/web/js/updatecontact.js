$(document).ready(function () {
    // Getting contactId from the URL
    var contactId = getParameterByName('id');
    console.log('contactId : ' + contactId);

    getContact(contactId);

    $(document).ajaxStart(function () {
        $("#loading").show();
    });

    $(document).ajaxStop(function () {
        $("#loading").hide();
    });

});

function getContact(contactId) {
    var request = $.ajax({
        method: "GET",
        url: "api/contacts/" + contactId,
        dataType: "json"
    });

    request.done(function (data) {
        console.log(data);
        var templateSource = $("#contact-template").html();
        var template = Handlebars.compile(templateSource);
        var html = template(data);
        $("#contactFormDiv").html(html);

        console.log($("#country"));

        fillCountryDropDown(data.address.country, data.address.city);
        
        $('#country').change(
                function () {
                    if ($(this).val() === '') {
                        $('#city').html('');
                    }
                    else {
                        getCities($(this).val());
                    }
                });
        
        $("#updateContact").on("click", function (event) {
            var $theForm = $(this).closest('form');
            //Some browsers don't implement checkValidity
            if ((typeof ($theForm[0].checkValidity) == "function") && !$theForm[0].checkValidity()) {
                return;
            }
            //avoid form submission
            event.preventDefault();

            //The delete link that was clicked
            var contact = {
                id: $('#id').val(),
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
            onUpdateContact(JSON.stringify(contact));
        });
    });

    request.fail(function (jqXHR, status) {
        alert("Request failed: " + status);
    });
}

function fillCountryDropDown(selectedCountry, selectedCity) {
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
        
        //Auto select the country
        $('#country').val(selectedCountry);
        getCities(selectedCountry, selectedCity);
    });

    request.fail(function (jqXHR, status) {
        alert("Request failed: " + status);
    });
}

function getCities(countryCode, selectedCity) {
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
        
        $('#city').val(selectedCity);
    });

    request.fail(function (jqXHR, status) {
        alert("Request failed: " + status);
    });
}

function onUpdateContact(contact) {
    console.log(contact);

    var request = $.ajax({
        method: "PUT",
        url: "api/contacts/",
        data: contact,
        contentType: 'application/json'
    });

    request.done(function (data) {
        console.log(data);
        window.location.href = 'contacts.html';
    });

    request.fail(function (jqXHR, status) {
        alert("Request failed: " + status);
    });
}


var ModeString = getParameterByName('Mode');

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
    var regexS = "[\\?&]" + name + "=([^&#]*)",
            regex = new RegExp(regexS),
            results = regex.exec(window.location.href);
    if (results == null) {
        return "";
    } else {
        return decodeURIComponent(results[1].replace(/\+/g, " "));
    }
}
