<#include "../include/imports.ftl">

<div class="container">
  <div>
  <@hst.include ref="top-container"/>
  </div>

  <div>
  <@hst.include ref="three-column-container"/>
  </div>
  <div>
  <@hst.include ref="bottom-container"/>
  </div>
</div>


<@hst.headContribution category="changeLocation">
  <#assign mount=hstRequestContext.resolvedMount.mount.alias/>
  <#if mount=="site">
  <a href="<@hst.link siteMapItemRefId="events" mount="site_fr" />">
    <img width="10" class="location" location="us" src="<@hst.link path="/binaries/content/gallery/bostonjavameetup/icons/fr.png" />" style="width:32px;margin-top:6px"/>

  </a>
  <#else>
  <a href="<@hst.link siteMapItemRefId="events" mount="site" />">
    <img width="10" class="location" location="us" src="<@hst.link path="/binaries/content/gallery/bostonjavameetup/icons/us.png" />" style="width:32px;margin-top:6px"/>

  </a>
  </#if>
</@hst.headContribution>