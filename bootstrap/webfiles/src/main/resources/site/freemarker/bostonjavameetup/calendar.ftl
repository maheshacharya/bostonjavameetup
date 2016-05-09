<#include "../include/imports.ftl">
<#-- @ftlvariable name="info" type="org.meetup.bostonjava.components.CalendarComponentInfo" -->

<#-- Calener resource bundle containing the language specific labels.-->
<@hst.setBundle basename="calendar"/>

<#-- determine wheather or not current view mode is preview -->
<#assign isPreview=hstRequest.requestContext.preview/>


<#-- reference to calendar javascript resource -->
<@hst.headContribution category="scripts">
<script type="text/javascript" src="<@hst.webfile path="/js/macharya-calendar.js"/>"></script>
</@hst.headContribution>

<#-- define calender properties and assign property values -->
<@hst.headContribution  category="scripts">
<script>
  var context = "<@hst.link path="/"/>";

    <#if isPreview>

    <#--  rest end point context for preview mode in Channel Manager -->
   var  restendpoint= "<@core.url value="/rest/preview/${info.restEndpoint}"/>";
    <#else>

    <#--  rest end point for site contexts -->
    var restendpoint = "<@hst.link path="/rest/"+info.restEndpoint/>";
    </#if>

  var props_${now?c} = {
    "width": ${info.width}, //width of the calendar display
    "height":${info.height}, //height of the calendar display
    "max_years": ${info.maxYears}, //max. number of years shown in the year selector
    "future_years": ${info.futureYears}, //number of future years shown in the year selector.
    "background_color": "${info.backgroundColor}", //background color of the calendar display.
    "color": "${info.color}", //font color of elements within calendar
    "date_field_class": "${info.calendarClassName}", //Class name of input field that is will invoke the calendar display.
    "rest_endpoint":restendpoint, //Rest service endpoint.
    "padding": "${info.padding}px",
    "dayofweek": [
      "<@fmt.message key="sunday"/>",
      "<@fmt.message key="monday"/>",
      "<@fmt.message key="tuesday"/>",
      "<@fmt.message key="wednesday"/>",
      "<@fmt.message key="thursday"/>",
      "<@fmt.message key="friday"/>",
      "<@fmt.message key="saturday"/>"
    ],
    "months": [
      "<@fmt.message key="january"/>",
      "<@fmt.message key="february"/>",
      "<@fmt.message key="march"/>",
      "<@fmt.message key="april"/>",
      "<@fmt.message key="may"/>",
      "<@fmt.message key="june"/>",
      "<@fmt.message key="july"/>",
      "<@fmt.message key="august"/>",
      "<@fmt.message key="september"/>",
      "<@fmt.message key="october"/>",
      "<@fmt.message key="november"/>",
      "<@fmt.message key="december"/>"
    ]

  };

  <#-- Initialize calendar object -->
  new Calendar(props_${now?c});
</script>
</@hst.headContribution>

<#-- Celendar title-->
<div style="text-align:center"><b>${info.title}</b></div>

<#-- Calendar element-->
 <center>
  <div class="${info.calendarClassName}" style="min-width:${info.width}px;min-height:${info.height}px"></div>
 </center>

<@hst.headContribution category="changeLocation">
    <#assign mount=hstRequestContext.resolvedMount.mount.alias/>
    <#if mount=="site">
    <a href="<@hst.link siteMapItemRefId="calendar" mount="site_fr" />">
      <img width="10" class="location" location="us" src="<@hst.link path="/binaries/content/gallery/bostonjavameetup/icons/fr.png" />" style="width:32px;margin-top:6px"/>

    </a>
    <#else>
    <a href="<@hst.link siteMapItemRefId="calendar" mount="site" />">
      <img width="10" class="location" location="us" src="<@hst.link path="/binaries/content/gallery/bostonjavameetup/icons/us.png" />" style="width:32px;margin-top:6px"/>

    </a>
    </#if>
</@hst.headContribution>


