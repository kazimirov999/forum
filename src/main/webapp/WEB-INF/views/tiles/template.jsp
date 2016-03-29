<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:importAttribute name="titleName"/>

<!DOCTYPE >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css" href="/resources/styles/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/css/forms.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/css/paginator.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/css/footer.css"/>
</head>

<body>

<div class="wrapper">

    <div class="header">
        <tiles:insertAttribute name="header"/>
    </div>

    <div class="middle">

        <div class="container">
            <div class="content">
                <tiles:insertAttribute name="body"/>
                <tiles:insertAttribute name="barInfo"/>
            </div>
        </div>
    </div>

    <div class="clear"></div>

    <div class="footer">
        <tiles:insertAttribute name="footer"/>
    </div>

</div>

</body>
</html>
