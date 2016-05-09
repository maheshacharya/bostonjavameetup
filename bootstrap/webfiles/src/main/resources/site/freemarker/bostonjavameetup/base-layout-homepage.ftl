<!doctype html>
<#include "../include/imports.ftl">
<html lang="en">
<head>
  <meta charset="utf-8"/>
  <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="<@hst.webfile  path="/css/bootstrap.css"/>" type="text/css"/>
  <link rel="stylesheet" href="<@hst.webfile  path="/css/style.css"/>" type="text/css"/>
<@hst.defineObjects/>
<#if hstRequest.requestContext.cmsRequest>
  <link rel="stylesheet" href="<@hst.webfile  path="/css/cms-request.css"/>" type="text/css"/>
</#if>
<@hst.headContributions categoryExcludes="htmlBodyEnd,changeLocation, scripts" xhtml=true/>
  <script src="<@hst.webfile path="/js/jquery-2.1.0.min.js"/>" type="text/javascript"></script>
  <script src="<@hst.webfile path="/js/bootstrap.min.js"/>" type="text/javascript"></script>
  <style>
    body{
      font-family: 'Lato', sans-serif;
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
  <div class="change-location">
    Change Location To:
  <@hst.headContributions categoryIncludes="changeLocation" xhtml=true/>
  </div>
<@hst.include ref="footer"/>
</div>

<@hst.headContributions categoryIncludes="htmlBodyEnd, scripts" xhtml=true/>
<script>
  $(document).ready(function () {
    $(".change-location-container").append($(".change-location"));
  });
</script>
</body>
</html>