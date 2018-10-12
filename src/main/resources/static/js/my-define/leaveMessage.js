/**
 * #Created with IntelliJ IDEA
 * #Author : EalenXie
 * #CreateTime : 2017-05-15 09:41
 */
function leaveMessageForm() {
    $('#leaveMessageBox').html("<br><form method='post' role='form' action='"+context+"/book/addInformation'><textarea class='form-control' rows='3' placeholder='请输入你的留言信息' id='addInformation' name='addInformation'></textarea><br><input class='btn btn-default' type='submit' value='添加'>&nbsp;&nbsp;<button class='btn btn-default' onclick='cancelLeaveMessage()'>取消</button></form>");
}
function cancelLeaveMessage() {
    $('#leaveMessageBox').html("");
}