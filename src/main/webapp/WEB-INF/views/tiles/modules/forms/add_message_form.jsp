<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<div class="addMessageForm">
    <form:form method="post" action="/message/add" commandName="messageBean">
        <table>
            <tr>
                <td><form:textarea title="Вопрос" class="textArea" path="text"/></td>
            </tr>
            <tr>
                <td class="submit"><input class="submit" type="submit" value="Задать вопрос"/></td>
                <form:input type="hidden" cssClass="hidden_id_input" path="user"
                            value="1"/>
                <form:input type="hidden" cssClass="hidden_id_input" path="topic"
                            value="${topic.id}"/>
            </tr>
        </table>
    </form:form>
</div>
