<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<spring:message code="siteName" var="siteName"/>

<div class="headerTitle">
    <a style="color: inherit;text-decoration: none" href="<c:url value="/"/>">${siteName}</a>
</div>

<div class="authBlockHeader">
    <c:choose>
        <c:when test="${pageContext.request.userPrincipal.name != null}">
            <p>${pageContext.request.userPrincipal.name} |
                <a href="<c:url value="/logout" />">Выйти</a></p>
        </c:when>
        <c:otherwise>
            <p>
                <a href="<c:url value="/login" />">Ввойти</a> |
                <a href="<c:url value="/register" />">Регистрация</a>
            </p>
        </c:otherwise>
    </c:choose>
</div>
