
//When document is fully loaded in the browser
$(document).ready(function () {
    //Change the background color based on the selected product category
    var backgroundColor = getBackgroundColor($("#productCategory").val());
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
    var vegCount = 0;
    var fruitCount = 0;

    for (var i = 0; i < qtyList.length; i++) {
        var qty = qtyList[i].value;
        console.log('Qty ' + qty);
        var price = priceList[i].getAttribute('data-price');
        var productCategory = priceList[i].getAttribute('data-category');
        
        console.log('Price ' + price);
        
        if (isInteger(qty)) {
            total += (qty * price);

            if (productCategory === 'fruit') {
                fruitCount++;
            } else {
                vegCount++;
            }
        }
    }
    var totalHtml = "<br> Total: QR" + total 
            + "<br> Fruits (" + fruitCount + ")" + " & Vegetables (" + vegCount + ")";
        
    $('#total').html(totalHtml);
    //Same as the two lines below
    //var totalDiv = document.getElementById('total');
    //totalDiv.innerHTML = totalHtml;
}

function isInteger(n) {
    return !isNaN(parseInt(n)) && isFinite(n);
}

function getBackgroundColor(productCategory) {
    switch (productCategory) {
        case "vegetable":
            return 'DarkSeaGreen';
        case "fruit":
            return 'PapayaWhip';
        default:
            return 'WhiteSmoke';
    }
}