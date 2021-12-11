const mealAjaxUrl = "ui/meals/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealAjaxUrl
};

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        })
    );
});

function updateFilter(){
        $.ajax({
        url: ctx.ajaxUrl + "filter",
        type: "GET",
        data: $("#filter").serialize()
    }).done(function (data){
    ctx.datatableApi.clear().rows.add(data).draw();
    successNoty("Filtered")
    });
}

function clearFilter(){
    $("#filter")[0].reset();
    updateTable();
}