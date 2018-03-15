$.notify.addStyle("metro", {
    html:
        "<div>" +
            "<div class='image' data-notify-html='image'/>" +
            "<div class='text-wrapper'>" +
                "<div class='title' data-notify-html='title'/>" +
                "<div class='text' data-notify-html='text'/>" +
            "</div>" +
        "</div>",
    classes: {
        error: {
            "color": "#fafafa !important",
            "background-color": "#E15554",
            "border": "1px solid #FF0026"
        },
        success: {
            "background-color": "#68C39F",
            "border": "1px solid #4DB149"
        },
        info: {
            "color": "#fafafa !important",
            "background-color": "#65BBD6",
            "border": "1px solid #1E90FF"
        },
        warning: {
            "background-color": "#FFC052",
            "border": "1px solid #EEEE45"
        },
        black: {
            "color": "#fafafa !important",
            "background-color": "#4A525F",
            "border": "1px solid #000"
        },
        white: {
            "background-color": "#ABB7B7",
            "border": "1px solid #ddd"
        }
    }
});