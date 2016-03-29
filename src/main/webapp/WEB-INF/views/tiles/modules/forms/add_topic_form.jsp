<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<div class="addBranchForm">
    <form:form name="Добавление темы" method="post" action="/topic/add" commandName="topicBean">
        <table>
            <tr>
                <td>Название темы</td>
                <td>
                    <p class="error"><form:errors path="name" cssclass="error"></form:errors></p>
                    <form:textarea title="Описание" class="textArea" path="name"/></td>
            </tr>
            <tr>
                <td colspan="3" class="submit"><input class="submit" type="submit" value="Добавить"/></td>
            </tr>
        </table>
    </form:form>
</div>