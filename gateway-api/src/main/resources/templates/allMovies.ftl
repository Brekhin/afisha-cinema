<#import "parts/common.ftl" as c>

<@c.page>
<form method="get" action="/all">
    <div class="card-columns">
        <#list movies as movie>
            <div class="card my-3 .text-secondary">
                <#if movie.getName()??>
                    <p class="lead">${movie.getName()}</p>
                    <p class="lead">${movie.getGenre()}</p>
                    <p class="lead">${movie.getDuration()}</p>
                </#if>
                <div class="card-footer text-muted">
                    <a href="/${movie.getMovieId()}">Show detail info</a>
                </div>
            </div>
        </#list>
    </div>
    <div class="card-footer text-muted">
        <a href="/newmovie">Add movie</a>
    </div>
</form>
</@c.page>