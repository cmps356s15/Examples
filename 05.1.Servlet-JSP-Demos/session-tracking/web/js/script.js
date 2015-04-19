
//When document is fully loaded in the browser
$(document).ready(function () {
    //Change the background color based on the selected category
    var backgroundColor = $("#productCategory").val() === "vegetable" ? 'PaleGreen' : 'LightYellow';
    $("body").css("background-color", backgroundColor);
        
    //Listen to the qty change event and recompute the total
    $(".qty").on("change", function () {
        getTotal();
    });

    //Listen to productCategory change event and referesh the page using the selected category
    $("#productCategory").on("change", function () {
        window.location.href = "?category=" + this.value;
    });
});

function getTotal() {
    var qtyList = $('.qty'); //get all elements with the class qty
    var priceList = $('.price'); //get all elements with the class price
    var total = 0;

    for (var i = 0; i < qtyList.length; i++) {
        var qty = qtyList[i].value;
        console.log('Qty ' + qty);
        var price = priceList[i].getAttribute('data-price');
        console.log('Price ' + price);
        if (isNumeric(qty)) {
            total += (qty * price);
        }
    }

    $('#total').html("<br> Total: QR" + total);
    //Same as the two lines below
    //var totalDiv = document.getElementById('total');
    //totalDiv.innerHTML = "<br> Total: QR" + total;
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}