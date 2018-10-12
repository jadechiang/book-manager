/**
 *#Author : EalenXie
 *#CreateTime : 2017-04-24 14:51
 */
$(document).ready(function () {
    $("#modifyAddress").citySelect({nodata: "none", required: false});
    $("#addAddress").citySelect({nodata: "none", required: false});
    jQuery("#grid_user_data").jqGrid({
        rownumbers: true,
        url: "/user/all/users",
        width: $(".grid-body").width() - 17,
        colNames: ["id", "用户名", "性别", "出生年月", "电子邮箱", "电话", '地址', "权限", "操作"],
        pager: "#grid_user_data_pager",
        colModel: [{name: 'id', index: 'id', align: 'center', hidden: true}
            , {name: 'username', index: 'username', align: 'center'}
            , {name: 'gender', index: 'gender', align: 'center', nowrap: false}
            , {name: "birth", index: 'birth', align: 'center'}
            , {name: 'email', index: 'email', align: 'center'}
            , {name: 'telephone', index: 'telephone', align: 'center'}
            , {name: 'address', index: 'address', align: 'center'}
            , {
                name: 'role', index: 'role', align: 'center',
                formatter: "select", search: false,
                editoptions: {
                    value: "admin:<span class='label label-success'>管理员</span>;ordinary:<span class='label label-warning'>用户</span>"
                }
            },
            {name: 'operation', index: 'operation', align: 'center', sortable: false, search: false}
        ],
        multiselect: false,
        gridComplete: function () {
            var ids = jQuery("#grid_user_data").jqGrid("getDataIDs");
            for (var i = 0; i < ids.length; i++) {
                var id = ids[i];
                if (id != null || id != 0) {
                    jQuery("#grid_user_data").jqGrid("setRowData", id, {
                        operation: "<a onclick='modifyUser(" + id + ")' class='btn btn-info btn-xs' >修改</a> " +
                        " <a onclick='return deleteUser()' href='" + context + "/user/" + id + "/delete' class='btn btn-warning btn-xs' >删除</a>"
                    })
                }
            }
        }
    });
    jQuery("#grid_book_data").jqGrid({
        rownumbers: true,
        url: "/book/list/json",
        colNames: ["id", "书名", "作者", "单价", "数量", "ISBN", "操作"],
        pager: "#grid_book_data_pager",
        colModel: [{name: 'id', index: 'id', align: 'center', hidden: true}
            , {name: 'name', index: 'name', align: 'center'}
            , {name: 'author', index: 'author', align: 'center', nowrap: false}
            , {name: "price", index: 'price', align: 'center'}
            , {name: 'number', index: 'number', align: 'center'}
            , {name: 'isbn', index: 'isbn', align: 'center'}
            , {name: 'operation', index: 'operation', align: 'center', sortable: false, search: false}
        ],
        multiselect: false,
        gridComplete: function () {
            var ids = jQuery("#grid_book_data").jqGrid("getDataIDs");
            for (var i = 0; i < ids.length; i++) {
                var id = ids[i];
                if (id != null || id != 0) {
                    jQuery("#grid_book_data").jqGrid("setRowData", id, {
                        operation: "<a onclick='modifyBook(" + id + ")' class='btn btn-info btn-xs' >修改</a> " +
                        " <a onclick='return deleteBook()' href='" + context + "/book/" + id + "/delete' class='btn btn-warning btn-xs' >删除</a>"
                    })
                }
            }
        }
    });
    $(window).resize(function () {
        $("#grid_user_data").setGridWidth($(".grid-body").width());
        $("#grid_book_data").setGridWidth($(".grid-body").width());
    });

});
function addModal(data) {
    if (data == 'user') {
        $('#addUserModal').modal();
    } else if (data == 'addBook') {
        $('#addBookModal').modal();
    } else if (data == 'operation') {
        $('#operationModal').modal();
    }
}
function deleteBook() {
    return !!confirm("确定删除图书吗?");
}
function deleteUser() {
    return !!confirm("确定删除此用户吗?");
}
function modifyUser(id) {
    $('#userID').val(id);
    $('#UserModal').modal();
}
function modifyBook(id) {
    $('#bookID').val(id);
    $('#closeOperation').trigger("click");
    $('#modifyBookModal').modal();
}

function verify(data) {
    var regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    var regTelephone = /^[1][0-9]{10}$/;
    var email;
    var birth;
    var telephone;
    if (data == 'modify') {
        email = $('#modifyEmail').val();
        telephone = $('#modifyPhone').val();
        birth = $('#birthDay').val();

    } else if (data == 'add') {
        email = $('#addEmail').val();
        telephone = $('#addPhone').val();
        birth = $('#addBirth').val();
    }
    if (!regEmail.test(email)) {
        $('.errorEmail').text("  邮箱格式不正确!");
        return false;
    }
    if (!regTelephone.test(telephone) || email.trim().equals("")) {
        $('.errorPhone').text("  电话号码格式不正确!");
        return false;
    }
    var regBirth = birth.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
    if (regBirth == null) {
        $('.errorBirth').text("  生日格式不正确！");
        return false;
    }

}