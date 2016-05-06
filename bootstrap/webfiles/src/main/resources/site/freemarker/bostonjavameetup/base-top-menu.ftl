<#include "../include/imports.ftl">
<style>
  .top-menu a{
    //color:#fff;
    text-decoration:none;

  }
  .top-menu a:hover{
   // color:#000;
  }

</style>
<#if menu??>
    <#if menu.siteMenuItems??>

    <ul class="nav nav-pills top-menu">

        <#list menu.siteMenuItems as item>
            <#if  item.selected || item.expanded>
              <li class="active"><a href="<@hst.link link=item.hstLink/>">${item.name?html}</a></li>

            <#else>

              <li><a href="<@hst.link link=item.hstLink/>">${item.name?html}</a></li>

            </#if>

        </#list>


    </ul>
    </#if>
    <@hst.cmseditmenu menu=menu/>
<#else>
    <#if editMode>
    <h5>[Menu Component]</h5>
    <sub>Click to edit Menu</sub>
    </#if>
</#if>