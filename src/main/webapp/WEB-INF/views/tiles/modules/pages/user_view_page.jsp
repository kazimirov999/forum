<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<div class="userViewWrapper">
    <div class="userView">

        <div class="photo">
            <c:choose>
                <c:when test="${user.photo != null}">
                    <img src="/user/photo/${user.id}" alt="${user.login}" width="100" height="100"/>
                </c:when>
                <c:otherwise>
                    <img src="/resources/styles/images/avatar.jpg" alt="${user.login}" width="100" height="100"/>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="userInf">
            <table>
                <tr>
                    <td>Login</td>
                    <td>${user.login}</td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <td>Role</td>
                    <td>${user.role}</td>
                </tr>
                <tr>
                    <td>Date register</td>
                    <td>${user.dateRegister.toString("yyyy-MM-dd MM:mm")}</td>
                </tr>
            </table>
        </div>
        <div class="backToIndexPage"></div>
    </div>
</div>
