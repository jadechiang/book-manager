/**
 * #Author : EalenXie && #CreateTime : 2017-04-05.
 */
$(document).ready(function () {

    jQuery("#gridData").jqGrid({
        rownumbers: true,
        url: "/user/list/json",
        width: $(".grid-body").width() - 17,
        colNames: ["id", "书名", "作者", "单价", "ISBN", "简介", "操作"],
        pager: "#gridPager",
        colModel: [{name: 'id', index: 'id', align: 'center', hidden: true}
            , {name: 'name', index: 'name', align: 'center'}
            , {name: 'author', index: 'author', align: 'center'}
            , {name: "price", index: 'price', align: 'center'}
            , {name: 'isbn', index: 'isbn', align: 'center'}
            , {name: 'lookOver', index: 'check', sortable: false, align: "center", search: false},
            {name: 'operation', index: 'operation', sortable: false, align: 'center', search: false}
        ],
        multiselect: false,
        gridComplete: function () {
            var ids = jQuery("#gridData").jqGrid("getDataIDs");
            for (var i = 0; i < ids.length; i++) {
                var id = ids[i];
                if (id != null || id != 0) {
                    jQuery("#gridData").jqGrid("setRowData", id, {
                        lookOver: "<a href='javascript:void(0);'  data-remote='" + context + "/user/" + id + "/lookOver'" + "data-toggle='modal' data-target='#dialog-data-view' class='btn btn-success btn-xs'>查看</a>"
                    });
                    jQuery("#gridData").jqGrid("setRowData", id, {
                        operation: "<a href='" + context + "/user/" + id + "/giveBack' class='btn btn-default btn-xs' >归还</a>"
                    });
                }
            }
        }
    });
    jQuery("#gridData").jqGrid("navGrid", "#gridPager", {
        edit: false, add: false, del: false, search: false
    });

    $("#dialog-data-view").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });

    $('#dialog-data-view').on('shown.bs.modal', function () {
        SyntaxHighlighter.highlight();
    });

    $(window).resize(function () {
        var width = $(".grid-body").width();
        $("#gridData").setGridWidth(width);
    });

});