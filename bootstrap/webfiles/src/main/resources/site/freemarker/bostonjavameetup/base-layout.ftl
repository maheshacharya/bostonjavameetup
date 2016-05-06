<!doctype html>
<#include "../include/imports.ftl">
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="<@hst.webfile  path="/css/bootstrap.css"/>" type="text/css"/>
    <@hst.defineObjects/>
    <#if hstRequest.requestContext.cmsRequest>
      <link rel="stylesheet" href="<@hst.webfile  path="/css/cms-request.css"/>" type="text/css"/>
    </#if>
<@hst.headContributions categoryExcludes="htmlBodyEnd, scripts" xhtml=true/>
  <style>
    .top-menu-container{
      background-color: #101010! important;
    }
  </style>
</head>
<body>
<div class="container">
    <div class="top-menu-container">
        <div class="col-md-6 col-md-offset-3">
       <#-- <@hst.include ref="top"/> -->
        <@hst.include ref="menu"/>
        </div>
    </div>
    <div class="rowx">
        <@hst.include ref="main"/>
    </div>
    <div class="rowx">
        <@hst.include ref="footer"/>
    </div>
</div>
<@hst.headContributions categoryIncludes="htmlBodyEnd, scripts" xhtml=true/>
</body>
</html>