<#include "../include/imports.ftl">

<#-- @ftlvariable name="query" type="java.lang.String" -->
<#-- @ftlvariable name="pageable" type="org.onehippo.cms7.essentials.components.paging.Pageable" -->
<#if pageable??>
    <#if pageable.total == 0>
    <h3>No results for: ${query?html}</h3>
    <#else>
        <#list pageable.items as item>
            <#if item.title??>
                <#assign linkName=item.title/>
            <#else>
                <#assign linkName=item.localizedName/>
            </#if>

        <article>
            <@hst.cmseditlink hippobean=item/>
            <@hst.link var="link" hippobean=item />
          <h3><a href="${link}">${linkName?html}</a></h3>
        </article>
        </#list>
        <#if cparam.showPagination>
            <#include "../include/pagination.ftl">

        </#if>
    </#if>
<#else>
<h3>Please fill in a search term.</h3>
</#if>

<@hst.headContribution category="changeLocation">
    <#assign mount=hstRequestContext.resolvedMount.mount.alias/>
    <#if mount=="site">
    <a href="<@hst.link path="/" mount="site_fr" />">
      <img width="10" class="location" location="us" src="<@hst.link path="/binaries/content/gallery/bostonjavameetup/icons/fr.png" />" style="width:32px;margin-top:6px"/>

    </a>
    <#else>
    <a href="<@hst.link path="/" mount="site" />">
      <img width="10" class="location" location="us" src="<@hst.link path="/binaries/content/gallery/bostonjavameetup/icons/us.png" />" style="width:32px;margin-top:6px"/>

    </a>
    </#if>
</@hst.headContribution>