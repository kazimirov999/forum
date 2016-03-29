<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="adminPanel">
        <div class="titleAdminPanel">Admin panel</div>
        <ul>
            <li><a href="<c:url value="/section/add"/>">Добавить секцию</a></li>
            <li><a href="<c:url value="/users/show"/>">Управление пользователями</a></li>
        </ul>
    </div>
</sec:authorize>