/**
 * Created by Camilla Velvin on 20.09.2017.
 */
$(document).ready(function () {
    $.ajax({
        url: 'rest/quiz',
        type: 'GET',
        data: JSON.stringify(quiz),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(result) {
            console.log(result);
        },
        error: function (e) {
            console.log(e);
        }
    });


    $(".clickable-row").click(function () {
        alert("hei");
    })
})