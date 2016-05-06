<#include "../include/imports.ftl">

<style>

<#--
  .col1, .col2 {
    width: 33%;
  }

  .col3 {
    width: auto;

  }
  .inline{
    display:inline-block;
  }
  -->
div .inline{

  width: 33.33%; /* as @passatgt mentioned in the comment, for the older browsers fallback */
  width: calc(99.3% / 3);
  display: inline-block;
  vertical-align: top;
  padding-left:4px;

}
</style>
<div class="col1 inline">
    <@hst.include ref="left-container"/>
</div>
<div class="col2 inline">
    <@hst.include ref="center-container"/>
</div>
<div class="col3 inline">
    <@hst.include ref="right-container"/>
</div>

