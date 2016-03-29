<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty serviceMessage}">
    <div id="service_message">
        <p>${serviceMessage}</p>
    </div>
</c:if>
<div class="usersBox">
    <div class="usersTable">
        <table>
            <tr>
                <td>Логин</td>
                <td>Email</td>
                <td>Страна</td>
                <td>Активность</td>
                <td>Роль</td>
                <td colspan="3">Всего пользователей: ${users.size()}</td>
            </tr>
            <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.login}</td>
                <td>${user.email}</td>
                <td>${user.country.name}</td>
                <td>${user.enable}</td>
                <td>${user.role}</td>
                <c:choose>
                    <c:when test="${user.enable == 'true'}">
                        <td><a href="<c:url value='/user/deactivate/${user.id}' />">Deactivate</a></td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="<c:url value='/user/activate/${user.id}' />">Activate</a></td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <c:if test="${user.role != 'ROLE_ADMIN'}">
                        <a href="<c:url value='/user/do_admin/${user.id}' />">setAsAdmin</a>
                    </c:if>
                </td>
                <td><a href="<c:url value='/user/delete/${user.id}' />">Delete</a></td>
                </c:forEach>
            </tr>
        </table>
    </div>
</div>
