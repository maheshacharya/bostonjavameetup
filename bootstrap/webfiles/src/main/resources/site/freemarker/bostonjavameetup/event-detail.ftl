<#include "../include/imports.ftl">
<@hst.setBundle basename="event.detail"/>
<style>
  .left-container {
    width: 70%;
  }

  .right-container {
    width: 29%;
    background-color: rgba(0, 0, 0, .1);
    padding: 10px;
  }

  .inline-block {
    display: inline-block;
    vertical-align: top;

  }

  .event-header-ul {
    list-style: none;
    margin: 0;
    padding: 10px;
    //background-color:rgba(0,0,0,.1) ;
  }

  .event-header-ul li {
    display: inline-block;
    padding-right: 20px;
    vertical-align: top;;

  }
</style>

<div>
<@hst.include ref="top-container"/>
  <div class="left-container inline-block">

    <h2>${document.title}</h2>
    <ul class="event-header-ul">
      <li>
       <@fmt.message key="start.date.time"/> : <b><@fmt.formatDate value=document.startDate.time type="Date" pattern="MMMM d, yyyy h:mm a" />
      </b>
        -  <@fmt.message key="end.time"/> :
        <b><@fmt.formatDate value=document.endDate.time type="Date" pattern="h:mm a" /></b>
        <br/>
       <@fmt.message key="location"/>: <b><span class="glyphicon glyphicon-map-marker"></span> ${document.venue.name}</b>

      <#list document.venue.address as address>
      ${address}
      </#list>
      ${document.venue.city}, ${document.venue.state} ${document.venue.zip}

      </li>
      <li>

      <@fmt.message key="speaker"/>:
      <#list document.speaker as speaker>
        <b><a href="<@hst.link hippobean=speaker/>">${speaker.name}</a></b>
        <br/>
      ${speaker.title}, ${speaker.company}
      </#list>
      </li>
    </ul>


    <hr/>
    <div>
    <@hst.html hippohtml=document.description/>
    </div>
  </div>
  <div class="right-container inline-block">
  <@hst.include ref="right-container"/>
  </div>
</div>


<@hst.headContribution category="changeLocation">
  <#assign mount=hstRequestContext.resolvedMount.mount.alias/>
  <#if mount=="site">
  <a href="<@hst.link hippobean=document.availableTranslations.getTranslation("fr")  mount="site_fr" />">
    <img width="10" class="location" location="us" src="<@hst.link path="/binaries/content/gallery/bostonjavameetup/icons/fr.png" />" style="width:32px;margin-top:6px"/>

  </a>
  <#else>
  <a href="<@hst.link hippobean=document.availableTranslations.getTranslation("en")  mount="site" />">
    <img width="10" class="location" location="us" src="<@hst.link path="/binaries/content/gallery/bostonjavameetup/icons/us.png" />" style="width:32px;margin-top:6px"/>

  </a>
  </#if>
</@hst.headContribution>