<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="authenticationDiv">

    <c:url value="/j_spring_security_check" var="loginUrl"/>

    <form class="form-container " method="post" action="${loginUrl}">

        <div class="form-title"><h2>Аутентификация</h2></div>

        <c:if test="${not empty errorAuth}">
            <div id="service_message">
                <p class="error">${errorAuth}</p>
            </div>
        </c:if>

        <div class="form-title">
            Логин
        </div>
        <input class="form-field" type='text' name='username'/>

        <div class="form-title">
            Пароль
        </div>
        <input class="form-field" type='password' name='password'>

        <div class="submit-container submit">
            <input class="submit-button" name="submit" type="submit"
                   value="Ввойти">
            <input class="submit-button" type=button
                   onClick="location.href='/register'"
                   value='Регистрация'>
        </div>
    </form>
</div>



