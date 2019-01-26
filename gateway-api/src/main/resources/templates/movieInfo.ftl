<#import "parts/common.ftl" as c>

<@c.page>
<form>
    <div class="card-columns">
            <div class="card my-3 .text-secondary">
                <#if movie.getName()??>
                    <p class="lead">Фильм: ${movie.getName()}</p>
                    <p class="lead">Жанр: ${movie.getGenre()}</p>
                    <p class="lead">Длительность: ${movie.getDuration()}</p>
                </#if>

        <#list movie.getSessions() as session>
            <#if session??>
            <div class="card my-3 .text-secondary">
            <a href="/gateway-api/api/ticket-api/${session.getTimeOfSessionId()}/selectseat">Время: ${session.getTimeOfSession()}</a>
                <p class="lead">Цена: ${session.getPrice()}</p>
            </#if>
            </div>
        </#list>
        <input value="Добавить сеанс" type="button" onclick="location.href='/gateway-api/api/movieservice-api/${movie.getMovieId()}/session'" />
        </div>
    </div>
</form>
</@c.page>