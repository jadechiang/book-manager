/**
 * #Author : EalenXie && #CreateTime : 2017-04-05.
 */

$(document).ready(function () {
    $.extend({
        refresh: function () {
            var name = $("#name").select2("val");
            var author = $("#author").select2("val");
            var isbn = $("#isbn").val();
            var status = $("#status").val();
            jQuery("#grid_data").jqGrid('setGridParam', {
                postData: {
                    'name': name,
                    'author': author,
                    'isbn': isbn,
                    'status': status
                },
                datatype: "json"
            }).trigger("reloadGrid");
        },
        reset: function () {
            $("#name").select2("val", "");
            $("#author").select2("val", "");
            $("#isbn").val("");
            $("#status").val("");
        }
    });

    $("#name").select2({
        placeholder: "请选择书名",
        allowClear: true,
        multiple: false,
        formatResult: function (item) {
            return item.text;
        },
        formatNoMatches: function (term) {
            return "未找到匹配项";
        },
        ajax: {
            dataType: 'json',
            type: 'post',
            url: context + "/book/names",
            data: function (term, page) {
                return {
                    name: term
                };
            },
            results: function (data) {
                return {
                    results: data
                };
            }
        }
    });

    $("#author").select2({
        placeholder: "请选择作者",
        allowClear: true,
        multiple: false,
        formatResult: function (item) {
            return item.text;
        },
        formatNoMatches: function (term) {
            return "未找到匹配项";
        },
        ajax: {
            dataType: 'json',
            type: 'post',
            url: context + "/book/authors",
            data: function (term, page) {
                return {
                    name: term
                };
            },
            results: function (data) {
                return {
                    results: data
                };
            }
        }
    });

    jQuery("#grid_data").jqGrid({
        rownumbers: true,
        postData: {
            'id': $("#name").select2("val"),
            'author': $("#author").select2("val"),
            'isbn': $("#isbn").val(),
            'status': $("#status").val()
        },
        url: "/book/list/json",
        width: $(".grid-body").width() - 17,
        colNames: ["id", "书名", "作者", "单价", "数量", 'ISBN', "状态", "操作"],
        pager: "#grid_data_pager",
        colModel: [{name: 'id', index: 'id', align: 'center', hidden: true}
            , {name: 'name', index: 'name', align: 'center'}
            , {name: 'author', index: 'author', align: 'center', nowrap: false}
            , {name: "price", index: 'price', align: 'center'}
            , {name: 'number', index: 'number', align: 'center'}
            , {name: 'isbn', index: 'isbn', align: 'center'}
            , {
                name: 'status', index: 'status', align: 'center', edittype: "select", stype: "select",
                formatter: "select", search: false,
                editoptions: {
                    value: "F:<span class='label label-success'>未借阅</span>;T:<span class='label label-warning'>已借阅</span>"
                }
            },
            {name: 'borrow', index: 'borrow', align: 'center', sortable: false, search: false}
        ],
        multiselect: false,
        gridComplete: function () {
            var ids = jQuery("#grid_data").jqGrid("getDataIDs");
            for (var i = 0; i < ids.length; i++) {
                var id = ids[i];
                if (id != null || id != 0) {
                    jQuery("#grid_data").jqGrid("setRowData", id, {
                        borrow: "<a href='" + context + "/user/" + id + "/borrow' class='btn btn-default btn-xs' >借阅</a>"
                    })
                }
            }
        }
    });

    $("#search").click(function () {
        $.refresh();
    });
    $("#clear").click(function () {
        $.reset();
        $.refresh();
    });
    jQuery("#grid_data").jqGrid("navGrid", "#grid_data_pager", {
        edit: false, add: false, del: false, search: false
    });
    $.reset();
    $(window).resize(function () {
        var width = $(".grid-body").width();
        $("#grid_data").setGridWidth(width);
    });

});
function showPopover() {
    $('#leaveMessageContent').popover();
}