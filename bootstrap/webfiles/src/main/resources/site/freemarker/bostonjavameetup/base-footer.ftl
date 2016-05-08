<#include "../include/imports.ftl">
<style>
  .title{
    font-size:40px;
    text-shadow:0 0 3px #fff;
  }
  .subtitle{
    font-size:20px;
    color:rgba(255,255,255,.60);
    text-shadow: 0 0 6px #000;
    margin-top:-10px;
  }
</style>
<@hst.setBundle basename="essentials.global"/>
<@hst.include ref="container"/>
<div style="width:100%;min-height:200px;background-color: #999">
  <div style="padding:20px;">
    <div class="title"><@fmt.message key="footer.title"/></div>

    <div class="subtitle"><@fmt.message key="footer.subtitle"/></div>
  </div>
</div>
<hr/>
<div class="text-center">
  <sub><@fmt.message key="footer.text" var="footer"/>${footer?html}</sub>
</div>
