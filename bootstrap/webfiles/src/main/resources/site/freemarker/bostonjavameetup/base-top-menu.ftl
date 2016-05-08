<#include "../include/imports.ftl">
<@hst.setBundle basename="essentials.global"/>
<style>
  .left-inner-addon {
    position: relative;
  }

  .left-inner-addon input {
    padding-left: 30px;
  }

  .left-inner-addon i {
    position: absolute;
    padding: 10px 12px;
    pointer-events: none;
  }

  .right-inner-addon {
    position: relative;
    font-size: 20px;
    padding: 4px;
  }

  .right-inner-addon input {
    padding-right: 30px;
  }

  .right-inner-addon i {
    position: absolute;
    right: 0px;
    padding: 10px 12px;
    pointer-events: none;
  }
</style>

<#-- @ftlvariable name="menu" type="org.hippoecm.hst.core.sitemenu.HstSiteMenu" -->
<#-- @ftlvariable name="editMode" type="java.lang.Boolean"-->
<div style="background-color: #000;width:100%;padding-left:20px;padding-bottom:6px;padding-right:20px">
  <div class="container">
  <#if menu??>
      <#if menu.siteMenuItems??>
        <ul class="nav nav-pills" style="z-index:999999">

            <#list menu.siteMenuItems as item>
                <#if  item.selected || item.expanded>
                    <#if item.externalLink?? &&  item.externalLink?has_content>
                      <li class="active"><a href="${item.externalLink}" target="_new">${item.name?html}</a></li>
                    <#else>
                      <li class="active"><a href="<@hst.link link=item.hstLink/>">${item.name?html}</a></li>
                    </#if>

                <#else>
                    <#if item.externalLink?? &&  item.externalLink?has_content>
                      <li><a href="${item.externalLink}" target="_new">${item.name?html}</a></li>
                    <#else>
                      <li><a href="<@hst.link link=item.hstLink/>">${item.name?html}</a></li>
                    </#if>

                </#if>
            </#list>

          <li style="float:right;margin-top:4px;">

            <table>
              <tr>
                <td>
                  <form action="<@hst.link path="/"/>search" method="POST">
                    <div class="right-inner-addon right-addon">
                      <i class="glyphicon glyphicon-search"></i>
                      <input name="query" type="text" class="form-control" placeholder="<@fmt.message key="search" />" style="width:500px;height:30px;padding:4px;font-size:20px"/>
                    </div>
                  </form>
                </td>
                <td>
                    <#assign mountName = hstRequest.requestContext.resolvedMount.mount.name/>

                    <#if mountName == 'hst:root'>
                      <img class="location" location="us" src="<@hst.link path="/binaries/content/gallery/bostonjavameetup/icons/us.png" />" style="width:32px;margin-top:6px"/>
                    <#else>
                      <img class="location" location="${mountName}" src="<@hst.link path="/binaries/content/gallery/bostonjavameetup/icons/${mountName}.png" />" style="width:32px;margin-top:6px"/>
                    </#if>

                </td>
              </tr>
            </table>


          </li>

        </ul>
      </#if>
      <@hst.cmseditmenu menu=menu/>
  <#else>
      <#if editMode>
        <h5>[Menu Component]</h5>
        <sub>Click to edit Menu</sub>
      </#if>
  </#if>
  </div>
</div>
