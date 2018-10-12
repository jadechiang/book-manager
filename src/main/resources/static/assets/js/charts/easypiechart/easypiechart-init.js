$(document).ready(function () {
    var gridbordercolor = "#eee";
    var cpuchart = $('[data-toggle=cpuchart]');
    $.each(cpuchart, function () {
        $(this).easyPieChart({
            barColor: "#fff",
            trackColor: "rgba(255,255,255,0.1)",
            scaleColor: false,
            lineCap: "butt",
            lineWidth: 3,
            size: 47,
            animate: 500
        });
    });

    setInterval(function () {
        var cpuchart = $('[data-toggle=cpuchart]');
        var percent = parseInt(Math.random() * 100);
        $.each(cpuchart, function () {
            $(this).data('easyPieChart').update(percent);
            $(this).children(".font-90").html(percent + "%");
        });
    }, 2000)
});

