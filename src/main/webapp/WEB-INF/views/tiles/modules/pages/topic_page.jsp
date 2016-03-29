<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="indexPage">

    <div class="indexPageWrapper">
        <h1><a href="<c:url value="/"/>">Форум / </a>
            <a href="<c:url value="/branch/${topic.branch.id}/page/1"/>">${topic.branch.name}</a>
            / ${topic.name}</h1>

        <c:forEach var="message" items="${topic.messages}">

            <div class="messageWrapper">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="deleteMessage">
                        <a href="<c:url value="/message/delete/${message.id}" />">Удалить вопрос</a>
                    </div>
                </sec:authorize>
                <div class="userView">
                    <div class="img">
                        <c:choose>
                            <c:when test="${message.user.photo != null}">
                                <a href="<c:url value="/user/${message.user.id}" />">
                                    <img src="/user/photo/${message.user.id}" alt="${message.user.login}" width="100"
                                         height="100"/></a>
                            </c:when>
                            <c:otherwise>
                                <a href="<c:url value="/user/${message.user.id}" />">
                                    <img src="/resources/styles/images/avatar.jpg" alt="${message.user.login}"
                                         width="100" height="100"/></a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="evaluating"></div>
                </div>
                <div class="innerWrapper">
                    <div class="titleMessage">
                        <table>
                            <tr>
                                <td><p>${message.user.login}</p></td>
                                <td class="rightColumn">${message.publicationDate.toString("yyyy-MM-dd MM : mm")}</td>
                            </tr>
                        </table>
                    </div>
                    <div class="textMessage">${message.text}</div>
                    <div class="comment">
                        <ul class="commentList">
                            <c:forEach var="comment" items="${message.comments}">
                                <li>
                                    <ul>
                                        <li class="userComment">
                                                ${comment.user.login}, ${comment.publicationDate.toString("yyyy-MM-dd MM : mm")}

                                            <c:if test="${userDetails != null}">
                                                <c:if test="${userDetails.id == comment.user.id || userDetails.role == 'ROLE_ADMIN'}">
                                                    <form:form method="post" action="/comment/delete"
                                                               commandName="commentFormBean">
                                                        <form:input type="hidden" cssClass="hidden_id_input"
                                                                    path="comment"
                                                                    value="${comment.id}"/>
                                                        <input id="submitDelete" type="submit" value="Удалить"/>
                                                    </form:form>
                                                </c:if>
                                                <c:if test="${userDetails.id == comment.user.id}">
                                                    <form:form method="get" action="/comment/edit/show"
                                                               commandName="commentFormBean">
                                                        <form:input type="hidden" cssClass="hidden_id_input"
                                                                    path="comment"
                                                                    value="${comment.id}"/>
                                                        <input id="submitDelete" type="submit" value="Редактировать"/>
                                                    </form:form>
                                                </c:if>
                                            </c:if>
                                        </li>
                                        <li class="commentText">${comment.text}</li>
                                    </ul>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                        <div class="inputComment">
                            <c:choose>
                                <c:when test="${edit != true}">
                                    <form:form method="post" action="/comment/add" commandName="commentBean">

                                        <table>
                                            <tr>
                                                <td><form:input title="Комментарий" class="text" path="text"/></td>
                                            </tr>
                                            <tr>
                                                <td class="submit"><input class="submit" type="submit"
                                                                          value="Комментировать"/>
                                                </td>
                                                    <%--зробити узера  --%>

                                                <form:input type="hidden" cssClass="hidden_id_input" path="user"
                                                            value="1"/>
                                                <form:input type="hidden" cssClass="hidden_id_input" path="message"
                                                            value="${message.id}"/>
                                            </tr>
                                        </table>
                                    </form:form>
                                </c:when>
                                <c:otherwise>
                                    <form:form method="post" action="/comment/edit" commandName="commentEditBean">
                                        <table>
                                            <tr>
                                                <td><form:input title="Комментарий" class="text" path="text"/></td>
                                            </tr>
                                            <tr>
                                                <td class="submit"><input class="submit" type="submit"
                                                                          value="Комментировать"/>
                                                </td>
                                                    <%--зробити узера  --%>
                                                <form:input type="hidden" cssClass="hidden_id_input" path="user"
                                                            value="1"/>
                                                <form:input type="hidden" cssClass="hidden_id_input" path="message"
                                                            value="${message.id}"/>
                                                <form:input type="hidden" cssClass="hidden_id_input" path="id"
                                                            value="${commentBean.id}"/>
                                            </tr>
                                        </table>
                                    </form:form>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </sec:authorize>
                </div>
            </div>
        </c:forEach>

        <div class="addMessage">
            <jsp:include page="../forms/add_message_form.jsp"/>
        </div>
    </div>

</div>
</div>