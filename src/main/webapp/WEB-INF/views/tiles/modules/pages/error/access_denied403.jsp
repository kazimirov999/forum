<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="backToMainPage" var="backToMain"/>


<html>
<head>

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" type="text/css" href="/resources/styles/css/error.css"/>


  <title>ACCESS DENIED </title>

</head>

<body>

<div class="wrapperError">
  <div class="TitleError">Access denied</div>

  <p class="messageError">Защищенный ресурс</p>

  <p><a href="<c:url value='/' />">${backToMain}</a></p>
</div>

</body>

