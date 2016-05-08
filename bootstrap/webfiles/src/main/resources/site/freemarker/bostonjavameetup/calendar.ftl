<#include "../../include/imports.ftl">
<#assign isPreview=hstRequest.requestContext.preview/>
<@hst.headContribution category="localScripts">
<script>


     var props_${now?c} = {
            "width": ${info.width}, //width of the calendar dialog
            "height":${info.height}, //height of the calendar dialog
            "max_years": ${info.maxYears}, //max. number of years shown in the year selector
            "future_years": ${info.futureYears}, //number of future years shown in the year selector.
            "background_color": "${info.backgroundColor}", //background color of the calendar dialog.
            "color": "${info.color}", //font color of elements within calendar
            "date_field_class": "${info.calendarClassName}", //Class name of input field that is will invoke the calendar dialog.
            "padding":"${info.padding}px",
<#if isPreview>
  "rest_endpoint":"<@core.url value="/rest/preview/${info.restEndpoint}"/>"//event_calendar_rest_endpoint
<#else>
    "rest_endpoint":"<@hst.link path="/rest/"+info.restEndpoint/>"//event_calendar_rest_endpoint
</#if>

        };
        new Calendar(props_${now?c});





</script>
</@hst.headContribution>
<div style="text-align:center"><b>${info.title}</b></div>
<div class="${info.calendarClassName}"></div>