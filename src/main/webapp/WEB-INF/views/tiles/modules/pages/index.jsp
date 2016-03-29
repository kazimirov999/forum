<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<div class="indexPage">

    <div class="indexPageWrapper">
        <h1>Форум Java программистов</h1>

        <jsp:include page="../fragments/admin_panel.jsp"/>

        <c:forEach var="section" items="${sectionInfo.sections}">

            <div class="mainBoard">
                <table>
                    <tr>
                        <td class="title firstColumn" colspan="2">
                                ${section.name}
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a href="<c:url value="/section/delete/${section.id}"/>">Удалить секцию</a>
                            </sec:authorize>
                        </td>
                        <td class="rightColumn">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a href="<c:url value="/branch/add/${section.id}"/>">Добавить ветку</a>
                            </sec:authorize>
                        </td>
                    </tr>
                    <c:if test="${empty section.branches}">
                        <tr>
                            <td colspan="3" style="color: #030402">Веток нет.
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a href="<c:url value="/branch/add/${section.id}"/>">Добавить тему</a>
                                </sec:authorize>
                            </td>
                        </tr>
                    </c:if>
                    <c:forEach var="branch" items="${section.branches}">
                        <tr>
                            <td>
                                <div class="wrapInfo">
                                    <a href="<c:url value="/branch/${branch.id}/page/1"/>">${branch.name}</a>
                                </div>
                                <div class="desc">
                                        ${branch.description}
                                </div>
                                <div class="deleteBranch">
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="<c:url value="/branch/delete/${branch.id}"/>">Удалить ветку</a>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                                        <a href="<c:url value="/topic/add/${branch.id}"/>">Добавить тему</a>
                                    </sec:authorize>
                                </div>
                            </td>
                            <td class="rightColumn">
                                <div class="amount">
                                    <p>Темы:${sectionInfo.getCountTopicsInBranch(branch)}</p>

                                    <p>Сообщения:${sectionInfo.getCountMessagesInBranch(branch)}</p>
                                </div>
                            </td>

                            <c:set var="lastMessage" value="${sectionInfo.getLastMessageInBranch(branch)}"/>

                            <td class="rightColumn">
                                <c:if test="${not empty lastMessage}">
                                    <div class="lastPost">
                                        сообщение от<br>
                                        <a href="<c:url value="/topic/${lastMessage.topic.id}"/>">
                                                ${lastMessage.publicationDate.toString("yyyy-MM-dd MM : mm")}</a>

                                        <span class="author">автор</span>
                                        <a href="<c:url value="/user/${lastMessage.user.id}"/>">
                                                ${lastMessage.user.login}</a>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:forEach>
    </div>
</div>