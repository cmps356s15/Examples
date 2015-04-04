$(document).ready(function () {
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
});
