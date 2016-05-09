/*
 *
 * @author Mahesh R. Acharya - maheshacharya@hotmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 *
 */

function Calendar(props) {
    //default day of week labels.
    var dayofweek = ["Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"];
    //default month labels.
    var months = ["January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"];
    var el = null;
    var current_component = null;
    var month = null;
    var year = null;

    $(document).ready(function () {
        if (props === undefined) {
            props = {
                "width": 500, //width of the calendar display
                "height": 300, //height of the calendar display
                "max_years": 100, //max. number of years shown in the year selector
                "future_years": 10, //number of future years shown in the year selector.
                "background_color": "rgba(255,255,255,0.96)", //background color of the calendar display.
                "color": "rgba(0,0,0,1.0)", //font color of elements within calendar
                "date_field_class": "event-calendar", //class name of input field that is will invoke the calendar display.

            };

        }

        el = document.createElement("div");
        $(el).attr("id", "calendar");
        $(el).css("background-color", props.background_color);
        $(el).width(props.width);
        $(el).height(props.height);
        if (props.dayofweek !== undefined) {
            dayofweek = props.dayofweek;
        }
        if (props.months !== undefined) {
            months = props.months;
        }
        $("." + props.date_field_class).append(el);
        layoutCalendar(el, new Date(), true);
        var endpoint = props.rest_endpoint + "?start="
            + (month + 1) + "-" + start + "-"
            + year + "&end=" + (month + 1)
            + "-" + end + "-" + year;
        updateEventsView($(el), endpoint);


    });


    /**
     * Add L&F colors, effects, etc..
     * @returns {undefined}
     */
    function _addCalendarStyle() {
        //Calendar main diaglog
        $(el).css({
            "font-family": "arial",
            // "box-shadow": "0 0 6px #666666",
            "-webkit-touch-callout": "none",
            "-webkit-user-select": "none",
            "-khtml-user-select": "none",
            "-moz-user-select": "none",
            "-ms-user-select:": "none",
            "user-select": "none"
        });
        //calendar Navigation Controls, especially the arrows
        //for navigating trhough months/years
        $(".calendar-control").css({
            "font-weight": "bold",
            "padding-left": "10px",
            "padding-right": "10px",
            "cursor": "pointer",
            "color": "#666666"
        });
        //Calendar control hover effects
        $(".calendar-control:hover").css({
            "color": "#ffffff",
            "text-shadow": "0 0 6px #000000"
        });

        //Calendar Day Cell
        $(".calendar-day-td").css({
            "cursor": "pointer",
            "border": "1px solid rgba(0,0,0,0.02)",
            "padding": "4px"
        });
        //Quick Pick Buttons at bottom, such a TODAY, YESTERDAY, TOMORROW etc
        $(".calendar-quick-button").css({
            "padding": "4px",
            "background-color": "#333333",
            "color": "white",
            "margin": "2px",
            "cursor": "pointer",
            "float": "right",
            "font-size": "10px"
        });

    }

    /**
     *
     * local
     * get number of days in a specific month for a given year
     * @param {type} year
     * @param {type} month
     * @returns {Number}
     */
    this.getDays = function (year, month) {
        return _getDays(year, month);
    };
    /**
     * public
     * get number of days in a specific month for a given year
     * @param {type} year
     * @param {type} month
     * @returns {Number}
     */
    function _getDays(year, month) {

        return new Date(year, month, 0).getDate();

    }

    /**
     * public function for gettng a day of week  number
     * @param {type} date
     * @returns {Number}
     */
    this.getDayOfWeek = function (date) {
        return _getDayForDate(date);
    };

    /**
     * public function for getting day of week literal
     * for a given date
     * @param {type} date
     * @returns {String}
     */
    this.getDayOfWeekName = function (date) {
        var dow = _getDayForDate(date);
        return dayofweek[dow];
    };

    /**
     * get Current Day (Numeric Value) as
     * highlighted on the calendar
     * @param {type} date
     * @returns {Number}
     */
    function _getDayForDate(date) {
        var d = new Date(date);
        return d.getDay();
    }

    /**
     * Calendar Navigation Bar(TOP)
     * @param {type} table
     * @param {type} date
     * @returns {undefined}
     */
    function buildNavigation(table, date) {

        var h = props.width * .08;
        var tr = $(table).prepend("<tr style='font-size:" + h
            + "px' class='calendar-heading'><tr>").find("tr.calendar-heading");
        $(tr).append("<td colspan ='7' style='text-align:center;padding-bottom:10px;background-color:rgba(0,0,0,0.02);height:" + h + "px;'>"
            + "<span style='display:inline;' class='year-control calendar-control' " +
            "name='calendar-control' title='Previous Year'>&#171;</span>"
            + "<span style='display:inline;' class='month-control calendar-control' " +
            "name='calendar-control' title='Previous Month'>&lsaquo;</span>"
            + "<span><select class='calendar-month-selector'" +
            " name='calendar-control' style='display:inline;height:" + h + "px;font-size:"
            + h / 2 + "px;border:none;background-color:rgba(0,0,0,0.0)'></select></span>"
            + "<span><select class='calendar-year-selector' " +
            "name='calendar-control' style='display:inline;height:" + h + "px;font-size:"
            + h / 2 + "px;border:none;background-color:rgba(0,0,0,0.0)'></select></span>"
            + "<span style='display:inline;' class='month-control calendar-control' " +
            "name='calendar-control' title='Next Month'>&rsaquo;</span>"
            + "<span style='display:inline;' class='year-control calendar-control' " +
            "name='calendar-control' title='Next Year'> &#187;</span>"
            + "</td>");

        $.each(months, function (i, m) {
            var selected = "";
            if (months[date.getMonth()] === m) {
                selected = "selected";
            }

            $(el).find(".calendar-month-selector").append("<option " + selected + ">" + m + "</option>");
        });

        var yearend = date.getFullYear() + props.future_years;
        var yearstart = yearend - props.max_years;
        if (yearstart < 0) {
            yearstart = yearstart * -1;
        }

        for (var y = yearstart; y <= yearend; y++) {
            var selected = "";
            if (date.getFullYear() === y) {
                selected = "selected";
            }


            $(el).find(".calendar-year-selector").append("<option " + selected + ">" + y + "</option>");
        }
        $(".calendar-control").on("click", function (e) {
            e.stopPropagation();
            e.preventDefault();
            var month = date.getMonth();
            var year = date.getFullYear();
            var control = $(this).attr("title");

            if (control === 'Previous Year') {
                year--;
            } else if (control === 'Next Year') {
                year++;

            } else if (control === 'Previous Month') {
                if (month > 0) {
                    month--;
                } else {
                    month = 11;
                    year--;
                }

            } else if (control === 'Next Month') {
                if (month === 11) {
                    year++;
                    month = 0;
                } else {
                    month++;
                }
            }

            layoutCalendar(el, new Date(month + 1 + "/" + _getSelectedDay() + "/" + year), false);
            var endpoint = props.rest_endpoint + "?start=" + (month + 1)
                + "-" + start + "-" + year + "&end="
                + (month + 1) + "-" + end + "-" + year;

            updateEventsView($(el), endpoint);
        });

        $(".calendar-month-selector, .calendar-year-selector").change(function () {

            layoutCalendar(el, _getSelectedDate(), false);
            var endpoint = props.rest_endpoint + "?start="
                + (month + 1) + "-" + start + "-"
                + year + "&end=" + (month + 1) + "-"
                + end + "-" + year;

            updateEventsView($(el), endpoint);
        });


    }

    //get selected date
    function _getSelectedDate() {
        var month = $(".calendar-month-selector").val();
        var year = $(".calendar-year-selector").val();
        var date = _getSelectedDay();
        var mon = -111;
        for (var i = 0; i < months.length; i++) {
            if (months[i] === month) {
                mon = i + 1;
            }
        }

        return new Date(mon + "/" + date + "/" + year);
    }

    //get selected day count
    function _getSelectedDay() {
        var day = 1;
        $.each($(".calendar-day-td"), function (i, td) {
            if ($(td).attr("day") === "today") {
                day = $(td).text();
                return;

            }
        });
        return day;
    }

    /**
     * Layout The Calendar Details..
     * @param {type} date
     * @param {type} today
     * @returns {undefined}
     */
    function layoutCalendar(elx, date, today) {

        var days = _getDays(date.getFullYear(), date.getMonth() + 1);
        var thisday = new Date();
        var dd = date.getDate();
        if (today) {
            dd = thisday.getDate();
        }


        var table = $(elx).html("<center><table cellpadding='0' cellspacing='0' class='calendar-table'></table></center>").find("table.calendar-table");
        buildNavigation(table, date);
        var day = 0;

        var rows = Math.ceil(days / 7);
        //each cell width
        var cw = (props.width - props.width * .05) / 7;
        //cell height
        var ch = (props.height - props.height * .60) / rows;
        var fsize = props.height * .04;//Math.ceil(ch / 2) + 4;
        if (fsize < 10) {
            fsize = 10;
        }
        //day of week
        var d = (date.getMonth() + 1) + "/1/" + date.getFullYear();
        year = date.getFullYear();
        month = date.getMonth();
        var dow = _getDayForDate(d);
        var labels = $(table).append("<tr class='calendar-week-labels'></tr>").find("tr.calendar-week-labels");
        $.each(dayofweek, function (i, dow) {
            $(labels).append("<td style='font-size:" + fsize
                + "px;text-align:left;border-bottom:1px solid #cccccc;background-color:rgba(0,0,0,0.2);padding:4px'>"
                + dow.substring(0, 3) + "</td>");
        });

        start = 1;

        for (var row = 0; row <= rows; row++) {
            try {
                var tr = $(table).append("<tr class='calendar-row-tr-" + row + "'></tr>").find("tr.calendar-row-tr-" + row);
                for (var col = 0; col < dayofweek.length; col++) {
                    try {
                        if (row === 0 && col < dow) {

                        } else {
                            day++;
                        }
                        var label = day;
                        end = day;
                        if (label <= days) {
                            if (day < 10) {
                                label = "0" + label;
                            }
                            var css = "";
                            if (label === "00") {
                                label = "&nbsp;&nbsp;";
                                css += "color:#cccccc;background-color:rgba(0,0,0,0.02)";
                            }
                            var dday = "";
                            if (parseInt(dd) === parseInt(day)) {
                                dday = "today";
                                css += ";background-color:rgba(0,0,0,0.8);color:#fff;";
                            }
                            var id = guid();
                            var m = (month + 1);
                            if ((month + 1) < 10) {
                                m = "0" + m;

                            }
                            var d = day;
                            if (day < 10) {
                                d = "0" + day;
                            }
                            var td = $(tr).append("<td day='" + label + "' id='"
                                + id + "' class='calendar-day-td " + m + "-"
                                + d + "-" + year + "' style='font-size:"
                                + fsize + "px;position:relative;text-align:left"
                                + css + "' click='' day='" + dday + "'>"
                                + label + "</td>").find("td#" + id);

                            $(td).css("width", cw + "px");
                            $(td).css("height", ch + "px");


                            if (day > 0) {
                                $(td).attr("click", "allowed");

                            }

                        } else {
                            css += "color:#cccccc;background-color:rgba(0,0,0,0.02)";
                            label = "&nbsp;&nbsp;";
                            $(tr).append("<td class='calendar-day-td' style='font-size:"
                                + fsize + "px;" + css + ";width:"
                                + cw + "px;height:" + ch + "px;'> "
                                + label + " </td>");
                        }
                    } catch (e) {
                    }

                }

            } catch (e) {
            }


        }
        //table cell hover effects
        $(el).find(".calendar-day-td").hover(function () {
            if ($(this).attr("click") === 'allowed') {
                $(this).css("box-shadow", "inset 0 0 6px #333333");
                $(this).css("font-weight", "bold");
            }
            var el = $(this).find(".event-details");
            if (el != null) {
                $(el).fadeIn();
            }
        }, function () {
            $(this).css("box-shadow", "none");
            $(this).css("font-weight", "normal");
            var el = $(this).find(".event-details");
            if (el != null) {
                $(el).fadeOut();
            }
        });
        //table cell click event
        $(el).find(".calendar-day-td").on("click", function () {
            var day = $(this).text();
            // $(el).fadeOut();
            var month = $(".calendar-month-selector").val();
            var year = $(".calendar-year-selector").val();
            var monthnum = 0;
            $.each(months, function (i, m) {
                if (month === m) {
                    monthnum = i + 1;
                }
            });
            var now = (monthnum) + "/" + day + "/" + year;
            var format = $(current_component).attr("format");
            if (format === undefined) {
                format = "mm/dd/yyyy";
            }
            var time = "";
            if (format.indexOf("hh:") > 0) {
                if (props.time_value === undefined) {
                    time = DateFormat(format.substring(format.indexOf("hh:")), new Date());
                } else {
                    time = props.time_value;
                }

            }


            $(current_component).val(now + " " + time);


        });

        //build time selector
        buildTimeSelector(date, table);
        //add styles
        _addCalendarStyle();
    }

    //build time selector
    function buildTimeSelector(date, table) {
        var tr = $(table).append("<tr class='calendar-time-selector'></tr>").find("tr.calendar-time-selector");
        $(tr).css("height", "40px");

        setInterval(function () {
            $(".calendar-time").html(DateFormat("hh:mm:ss", new Date()));
        }, 1000);


    }


}

/* ----------------------------------------------------------
 *  Field        | Full Form          | Short Form
 *  -------------|--------------------|-----------------------
 *  Year         | yyyy (4 digits)    | yy (2 digits)
 *  Month        | MMM (abbr.)        | MM (2 digits)
 | NNN (name)         |
 *  Day of Month | dd (2 digits)      |
 *  Day of Week  | EE (name)          | E (abbr)
 *  Hour (1-12)  | hh (2 digits)      |
 *  Minute       | mm (2 digits)      |
 *  Second       | ss (2 digits)      |
 *  ----------------------------------------------------------
 */
function DateFormat(formatString, date) {
    if (typeof date === 'undefined') {
        var DateToFormat = new Date();
    }
    else {
        var DateToFormat = date;
    }
    var DAY = DateToFormat.getDate();
    var DAYidx = DateToFormat.getDay();
    var MONTH = DateToFormat.getMonth() + 1;
    var MONTHidx = DateToFormat.getMonth();
    var YEAR = DateToFormat.getYear();
    var FULL_YEAR = DateToFormat.getFullYear();
    var HOUR = DateToFormat.getHours();
    var MINUTES = DateToFormat.getMinutes();
    var SECONDS = DateToFormat.getSeconds();
    var strMONTH;
    var strDAY;
    var strHOUR;
    var strMINUTES;
    var strSECONDS;
    var Separator;

    if (parseInt(MONTH) < 10 && MONTH.toString().length < 2)
        strMONTH = "0" + MONTH;
    else
        strMONTH = MONTH;
    if (parseInt(DAY) < 10 && DAY.toString().length < 2)
        strDAY = "0" + DAY;
    else
        strDAY = DAY;
    if (parseInt(HOUR) < 10 && HOUR.toString().length < 2)
        strHOUR = "0" + HOUR;
    else
        strHOUR = HOUR;
    if (parseInt(MINUTES) < 10 && MINUTES.toString().length < 2)
        strMINUTES = "0" + MINUTES;
    else
        strMINUTES = MINUTES;
    if (parseInt(SECONDS) < 10 && SECONDS.toString().length < 2)
        strSECONDS = "0" + SECONDS;
    else
        strSECONDS = SECONDS;

    switch (formatString) {
        case "hh:mm:ss":
            return strHOUR + ':' + strMINUTES + ':' + strSECONDS;
            break;
        case "hh:mm":
            return strHOUR + ':' + strMINUTES;
            break;
        case "hh":
            return strHOUR;
            break;
        //More cases to meet your requirements.
    }
}
/**
 *
 * @param dateString
 * @returns {boolean}
 */
function isDate(dateString) {
    // First check for the pattern
    if (!/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(dateString))
        return false;

    // Parse the date parts to integers
    var parts = dateString.split("/");
    var day = parseInt(parts[1], 10);
    var month = parseInt(parts[0], 10);
    var year = parseInt(parts[2], 10);

    // Check the ranges of month and year
    if (year < 1000 || year > 3000 || month === 0 || month > 12)
        return false;

    var monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    // Adjust for leap years
    if (year % 400 === 0 || (year % 100 !== 0 && year % 4 === 0))
        monthLength[1] = 29;

    // Check the range of the day
    return day > 0 && day <= monthLength[month - 1];
}
;

var guid = (function () {
    function s4() {
        return Math.floor((1 + Math.random()) * 0x10000)
            .toString(16)
            .substring(1);
    }

    return function () {
        return s4() + '' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
    };
})();

/**
 *
 * @param cal
 * @param endpoint
 */
function updateEventsView(cal, endpoint) {

    var port = window.location.port;
    if (port === undefined || port != "") {
        port = ":" + port;
    }
    var x = "" + window.location.protocol + "//" + window.location.hostname + "" + port
    $.ajax({
        url: endpoint
    }).done(function (data) {
        if (data.code === 200) {

            $(data.data.eventInfo).each(function (i, event) {

                var el = $(cal).find("." + event.startDate);
                $(el).css("background-color", "rgba(250,216,22,.9)");
                var eventlink = x + context + "/" + event.link;
                if (event.link.startsWith("http")) {
                    eventlink = event.link;
                }
                $(el).append("<div class='event-details' " +
                    "style='display:none;padding:10px;position:absolute;background:rgba(250,216,22,.9);z-index:99999;border:1px solid #333'>"
                    + "<div>" + event.startTime + "</div>"
                    + "<div style='font-weight:bold'><a href='" + eventlink + "'>" + event.title + "</a></div>"
                    + "</div>");
            });
        }
    });
}
