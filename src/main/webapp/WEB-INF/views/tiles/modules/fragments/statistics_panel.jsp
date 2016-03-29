<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>

<div class="statisticPanelWrapper">
    <div class="statisticPanel">
        <ul>
            <li>Наши пользователи оставили сообщений: <span class="decor"> ${statisticHolder.allMessagesNumber}</span>
            </li>
            <li>Всего зарегистрированных пользователей: <span
                    class="decor">${statisticHolder.registerUsersNumber}</span></li>
            <br>
            <li>Сейчас посетителей на форуме: <span class="decor">${statisticHolder.activeVisitorsNumber}</span>,
                из них зарегистрированных:<span class="decor">${statisticHolder.activeUsersNumber}</span>,
                гостей:<span
                        class="decor"> ${statisticHolder.activeVisitorsNumber - statisticHolder.activeUsersNumber}</span>
            </li>
            <li>Зарегистрированные пользователи:
                <c:forEach var="login" items="${statisticHolder.loginsOfActiveUsers}">
                    <a href="<c:url value="/user/name/${login}"/>">${login}</a>,
                </c:forEach>
            </li>
        </ul>
    </div>
</div>
