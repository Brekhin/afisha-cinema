<#import "parts/common.ftl" as c>

<@c.page>
<form method="post" >
        <div class="col-sm-6">
            <input type="text" name="movieName" class="form-control"
                   placeholder="input name"/>
        </div>


        <div class="col-sm-6">
            <input type="text" name="genre" class="form-control"
                   placeholder="input genre"/>
            </div>


        <div class="col-sm-6">
            <input type="number" name="duration" class="form-control"
                   placeholder="input duration"/>
        </div>

    <button class="btn btn-primary" type="submit">Add</button>
</form>
</@c.page>