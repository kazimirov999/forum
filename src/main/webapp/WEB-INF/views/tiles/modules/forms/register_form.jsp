<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="register">

    <form:form class="form-container order_form" method="post" action="/register" commandName="userBean" enctype="multipart/form-data">

        <div class="form-title"><h2>Регистрация</h2></div>

        <div class="form-title">
            Логин
            <form:errors path="login" class="error"></form:errors>
        </div>
        <form:input class="form-field name" path="login"/>

        <div class="form-title">
            Email
            <form:errors path="email" class="error"></form:errors>
        </div>
        <form:input class="form-field" path="email"/>

        <div class="form-title">
            Пароль
            <form:errors path="passwordTmp" class="error"></form:errors>
        </div>
        <form:input class="form-field" path="passwordTmp"/>

        <div class="form-title">
            Повторить пароль
            <form:errors path="passwordCheck" class="error"></form:errors>
        </div>
        <form:input class="form-field" path="passwordCheck"/>

        <div class="form-title">
            Страна</div>
        <form:select class="form-field address" path="country" items="${countries}"
                     itemValue="id"
                     itemLabel="name"/>
        <tr>
            <td><p class="error"><form:errors path="tmpImg" cssclass="error"></form:errors></p>
                <input id="file" type="file" path="tmpImg" name="tmpImg"
                       value="Photo" id="file"/></td>
        </tr>

        <div class="submit-container submit">
            <input class="submit-button" type="submit" value="Регистрация"/>
        </div>
    </form:form>
</div>

