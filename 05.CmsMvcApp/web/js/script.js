function emptyDropDown(dropdown) {
    $(dropdown).empty();
    $(dropdown).append("<option value=''></option>");
}

function onCountryChanged() {
    var selectedCountry = $('#country').val();
    var cityDropdown = $('#city');
    if (selectedCountry === '') {
        emptyDropDown(cityDropdown);
        return;
    }

    url = '/cms/cities?country=' + selectedCountry;
    $.getJSON(url, function (data) {
        emptyDropDown(cityDropdown);

        // Fill drop down list with new data
        $.each(data, function (index, item) { // Iterates through a collection
            cityDropdown.append(
                    $("<option></option>").text(item).val(item)
                    );
        });
    });
}


