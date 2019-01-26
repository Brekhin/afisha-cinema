<#import "parts/common.ftl" as c>

<@c.page>
<form method = "post">
    <div class="card-columns">
            <div class="card my-3 .text-secondary">
                    <p class="lead">Фильм: ${ticket.getMovieName()}</p>
                    <p class="lead">Сеанс: ${ticket.getTime()}</p>
                    <p class="lead">Зал: ${ticket.getHallName()}</p>
                    <p class="lead">Место: ${ticket.getColumnHall()}</p>
                    <p class="lead">Ряд: ${ticket.getRowHall()}</p>
                    <p class="lead">Цена: ${ticket.getPrice()}</p>
            </div>
    </div>
    <button type="submit">Save</button>
</form>

</@c.page>