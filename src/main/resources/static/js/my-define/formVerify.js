/**
 *#Created with IntelliJ IDEA
 *#Author : EalenXie
 *#CreateTime : 2017-04-10 15:05
 */

jQuery(document).ready(function () {
    $("#birthDate").datetimepicker({
        language: 'zh-CN',
        minView: "month",//设置只显示到月份
        format: "yyyy-mm-dd",//日期格式
        autoclose: true,//选中关闭
        todayBtn: true//今日按钮
    });
    $("#setAddress").citySelect({nodata: "none", required: false});
});
function verify() {
    var regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    var email = $('#setEmail').val();
    var regTelephone = /^[1][0-9]{10}$/;
    var telephone = $('#setTelephone').val();
    var birth = $('#birthDate').val();
    var regBirth = birth.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
    if (regBirth == null) {
        $('#errBirth').text("  生日格式不正确！");
        return false;
    }
    if (!regEmail.test(email)) {
        $('#errEmail').text("  邮箱格式不正确!");
        return false;
    }
    if (!regTelephone.test(telephone)) {
        $('#errTelephone').text("  电话号码格式不正确");
        return false;
    }
}
