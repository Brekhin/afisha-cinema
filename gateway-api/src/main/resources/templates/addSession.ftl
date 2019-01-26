<#import "parts/common.ftl" as c>

<@c.page>
<form method="post">
        <div class="col-sm-6">
            <input type="text" id="timeOfSession" name="timeOfSession" class="form-control"
                   placeholder="введите время сеанса"/>
        </div>

        <input type="hidden" id="movieId" name="movieId" value="${movieId}" />

        <div class="col-sm-6">
            <input type="number" id="price" name="price" class="form-control"
                   placeholder="введите стоимость билета на сеанс"/>
        </div>

        <div class="col-sm-6">
            <#list cinemaHalls as cinemaHall>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="hallId" name="hallId" value="${cinemaHall.getHallId()}">
                    <p class="lead">${cinemaHall.getName()}</p>
            </#list>
        </div>

        <div class="col-sm-6">
            <button class="btn btn-primary" type="submit">Добавить</button>
        </div>
</form>
</@c.page>