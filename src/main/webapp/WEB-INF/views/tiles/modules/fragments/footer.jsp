<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>

<spring:message code="footerText" var="text"/>
<spring:message code="siteName" var="siteName"/>


<div class="footerContent">
    <span class="text">${text}</span>
    <span class="siteName">${siteName}</span>
</div>

