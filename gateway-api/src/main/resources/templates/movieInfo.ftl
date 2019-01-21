<#import "parts/common.ftl" as c>

<@c.page>
<form>
    <div class="card-columns">
            <div class="card my-3 .text-secondary">
                <#if movie.getName()??>
                    <p class="lead">${movie.getName()}</p>
                    <p class="lead">${movie.getGenre()}</p>
                    <p class="lead">${movie.getDuration()}</p>
                </#if>

        <#list movie.getSessions() as session>
            <#if session??>
            <a href="/gateway-api/api/movieservice-api/session/${session.getTimeOfSessionId()}">${session.getTimeOfSessionDate()}</a>
                <p class="lead"></p>
                <p class="lead"> ${session.getPrice()}</p>
            </#if>
        </#list>
        </div>
    </div>
</form>
</@c.page>