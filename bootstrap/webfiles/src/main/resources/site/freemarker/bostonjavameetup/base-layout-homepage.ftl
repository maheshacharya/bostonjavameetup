<!doctype html>
<#include "../include/imports.ftl">
<html lang="en">
<head>
  <meta charset="utf-8"/>
  <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="<@hst.webfile  path="/css/bootstrap.css"/>" type="text/css"/>
<@hst.defineObjects/>
<#if hstRequest.requestContext.cmsRequest>
  <link rel="stylesheet" href="<@hst.webfile  path="/css/cms-request.css"/>" type="text/css"/>
</#if>
<@hst.headContributions categoryExcludes="htmlBodyEnd, scripts" xhtml=true/>
  <style>
    .top-menu-container {
      background-color: #101010 ! important;
    }
  </style>
</head>
<body>
<div>
<@hst.include ref="menu"/>
</div>

<div class="row">
<@hst.include ref="main"/>
</div>
<div class="container">

<@hst.include ref="footer"/>
</div>

<@hst.headContributions categoryIncludes="htmlBodyEnd, scripts" xhtml=true/>
</body>
</html>