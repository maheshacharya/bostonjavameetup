<#include "../../include/imports.ftl">

<#-- @ftlvariable name="document" type="org.meetup.bostonjava.beans.Video" -->
<#-- @ftlvariable name="cparam" type="org.onehippo.cms7.essentials.components.info.EssentialsVideoComponentInfo"--%> -->
<#if document??>
  <iframe width="${cparam.width}" height="${cparam.height}" src="${document.link?html}?autoplay=1" frameborder="0" allowfullscreen></iframe>
<#-- @ftlvariable name="editMode" type="java.lang.Boolean"-->
<#elseif editMode>
<img src="<@hst.link path="/images/essentials/catalog-component-icons/video.png" />"> Click to edit Video
</#if>
