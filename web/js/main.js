//ОТРИЦАТЕЛЬНЫЕ ЧИСЛА ДЛЯ КОЛИЧЕСТВА
//ЕСЛИ КОЛИЧЕСТВО ЗАПИСЕЙ БОЛЬШЕ 500000 ПРЕДУПРЕЖДЕНИЕ

$(document).ready(function(){

    $('form #form-signin').submit(function(e) {
        if($("#record-amount").val() < 1 || $("#mistakes-amount").val() < 1){
            $("#negative-amount-alert").removeClass("hide");
            return false;
        }
    });
});
$(document).on("change", "#record-amount, #mistakes-amount", function () {
    checkRecordAmountValue();
});

$(document).on("keydown", "#record-amount, #mistakes-amount",function(){ //отлавливаем нажатие клавиш//если нажали Enter, то true
    checkRecordAmountValue();
});


function checkRecordAmountValue() {
    if($("#record-amount").val() < 1 || $("#mistakes-amount").val() < 1){
        $("#negative-amount-alert").removeClass("hide");
        $("#submit").prop('disabled',true);
    } else{
        $("#negative-amount-alert").addClass("hide");
        $("#submit").prop('disabled',false);
    }
    if($("#record-amount").val() > 10000){
        $("#great-amount-alert").removeClass("hide");
    } else {
        $("#great-amount-alert").addClass("hide");
    }
}

var isMouseDown = false;

$(document).on("mousedown","#mistake-percent",function () {
    isMouseDown = true;
});
$(document).on("mouseup","#mistake-percent",function () {
    isMouseDown = false;
});
$(document).on("mousemove","#mistake-percent",function () {
    if (isMouseDown){
        $("#valueOfRange").text($("#mistake-percent").val() + "%");
    }
});