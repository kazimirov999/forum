<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set value="${holderTopics.pageList}" var="topics"/>

<div class="indexPage">

    <div class="indexPageWrapper">
        <h1><a href="<c:url value="/"/>">Форум</a>
            / ${currentBranch.name}</h1>

        <c:if test="${pager.totalPageCount > 1}">
            <jsp:include page="../fragments/paginator.jsp"/>
        </c:if>

        <div class="mainBoard">
            <table>
                <tr>
                    <td class="title firstColumn" colspan="3">Тема</td>
                </tr>
                <c:if test="${empty topics}">
                    <tr>
                        <td colspan="2" style="color: #030402">Тем нет.</td>
                    </tr>
                </c:if>
                <c:forEach var="topic" items="${topics}">
                    <tr>
                        <td>
                            <div class="wrapInfo">
                                <a href="<c:url value="/topic/${topic.id}"/>">${topic.name}</a>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a href="<c:url value="/topic/delete/${topic.id}"/>">Удалить тему</a>
                                </sec:authorize>
                            </div>
                            <div class="desc">
                                от <a href="<c:url value="/user/${topic.user.id}"/>">
                                    ${topic.user.login}</a>
                            </div>
                        </td>
                        <td class="rightColumn">
                            <div class="amount">
                                Сообщений:${topic.messages.size()}
                            </div>
                        </td>
                        <td class="rightColumn">
                            <c:if test="${not empty topic.messages}">
                                <div class="lastPost">
                                    сообщение от
                                    <a href="<c:url value="/topic/${topic.id}"/>">
                                            ${topic.messages.get(0).publicationDate.toString("yyyy-MM-dd MM : mm")}</a>

                                    <span class="author">автор</span>
                                    <a href="<c:url value="/user/${topic.messages.get(0).user.id}"/>">
                                            ${topic.messages.get(0).user.login}</a>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>