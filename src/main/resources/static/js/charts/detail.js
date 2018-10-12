$(document).ready(function () {
    //为jQuery类添加类方法，可以理解为添加静态方法
    $.extend({
        getResponseData: function () {
            var data = [];
            $.sendRequest({
                async: false,
                url: context + "/charts/response.do",
                callback: function (result) {
                    data = result;
                }
            });
            return data;
        },
        getRequestData: function () {
            var data = [];
            $.sendRequest({
                async: false,
                url: context + "/charts/request.do",
                callback: function (result) {
                    data = result;
                }
            });
            return data;
        },
        getQueueMgrData: function () {
            $.sendRequest({
                async: true,
                dataType: "html",
                url: context + "/queuemgr/detail.html",
                callback: function (html) {
                    $("#detail_queue_mgr").html(html);
                }
            });
        }
    });


    var gridbordercolor = "#eee";
    var lineoptions = {
        series: {
            lines: {
                show: true
            },
            points: {
                show: true
            }
        },
        legend: {
            noColumns: 4
        },
        xaxis: {
            mode: "time",
            timeformat: "%m-%d"
        },
        yaxis: {
            min: 0,
            color: gridbordercolor
        },
        selection: {
            mode: "x"
        },
        grid: {
            hoverable: true,
            clickable: true,
            borderWidth: 0,
            aboveData: false
        },
        tooltip: true,
        tooltipOpts: {
            defaultTheme: false,
            content: "<b>%s</b> :  <span>%y</span>",
        },
        crosshair: {
            mode: "x"
        }
    };
    $.plot("#selectable-chart", $.getResponseData(), lineoptions);


    var barOptions = {
        series: {
            bars: {
                show: true,
                lineWidth: 5,
                fillColor: {
                    colors: [{
                        opacity: 0.4
                    }, {
                        opacity: 1
                    }]
                }
            },
            points: {
                show: true
            }
        },
        legend: {
            noColumns: 4
        },
        xaxis: {
            mode: "time",
            timeformat: "%m-%d"
        },
        yaxis: {
            min: 0,
            tickDecimals: 0,
            tickSize: 1,
            color: gridbordercolor
        },
        grid: {
            hoverable: true,
            clickable: true,
            borderWidth: 0,
            aboveData: false
        },
        tooltip: true,
        tooltipOpts: {
            defaultTheme: false,
            content: "<b>合计</b> :  <span>%y</span>",
        }
    };
    $.plot("#bar-chart", $.getRequestData(), barOptions);

    $.getQueueMgrData();
});