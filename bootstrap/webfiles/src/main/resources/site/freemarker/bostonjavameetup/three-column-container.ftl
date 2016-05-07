<#include "../include/imports.ftl">

<style>


div .inline{

  width: 33.33%;
  width: calc(99.3% / 3);
  display: inline-block;
  vertical-align: top;
  padding-left:4px;


}

div .inline:nth-child(3){
  padding-left:10px;
  background-color: rgba(0,0,0,0.1);
}
  div .inline:last-child{
    border:none;
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

