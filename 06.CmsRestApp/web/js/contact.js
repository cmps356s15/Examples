$(document).ready(function () {
    getContacts();

    $("#dataTable tbody").on("click", ".deleteLink", function (event) {
        //avoid navigating to the link
        event.preventDefault();
        //The delete link that was clicked
        var $deleteLink = $(this);
        onDeleteContact($deleteLink);
    });
});

function getContacts() {
    var request = $.ajax({
        method: "GET",
        url: "api/contacts",
        dataType: "json"
    });

    request.done(function (data) {
        console.log(data);
        var templateSource = $("#contact-template").html();
        var template = Handlebars.compile(templateSource);
        var html = template(data);
        $("#dataTable tbody").html(html);
    });

    request.fail(function (jqXHR, status) {
        alert("Request failed: " + status);
    });
}

function onDeleteContact($deleteLink) {
    var id = $deleteLink.attr('data-id');
    console.log(id);

    var request = $.ajax({
        method: "DELETE",
        url: "api/contacts/" + id
    });

    request.done(function (data) {
        console.log(data);
        $deleteLink.closest('tr').remove();
    });

    request.fail(function (jqXHR, status) {
        alert("Request failed: " + status);
    });
}