<#import "parts/common.ftl" as c>

<@c.page>
<form method="post">
        ${movieInfo.getHallName()}
        <input name="row" type="text" id="row" class="form-control"/>
        <input name="col" type="text" id="col" class="form-control"/>
        <button type="submit">Save</button>
</form>

</@c.page>