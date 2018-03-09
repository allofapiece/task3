//ОТРИЦАТЕЛЬНЫЕ ЧИСЛА ДЛЯ КОЛИЧЕСТВА
//ЕСЛИ КОЛИЧЕСТВО ЗАПИСЕЙ БОЛЬШЕ 500000 ПРЕДУПРЕЖДЕНИЕ

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