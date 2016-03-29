<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
 Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
--%>
<div class="addBranchForm">
    <form:form title="Добавление ветки" method="post" action="/branch/add" commandName="branchBean">
        <table>
            <tr>
                <td>Название ветки</td>
                <td>
                    <p class="error"><form:errors path="name" cssclass="error">Введите имя</form:errors></p>
                    <form:input title="Название" class="name" path="name"/></td>
            </tr>
            <tr>
                <td>Описание ветки</td>
                <td>
                    <p class="error"><form:errors path="description" cssclass="error">Введите описание</form:errors></p>
                    <form:textarea title="Описание" class="textArea" path="description"/></td>

            </tr>
            <tr>
                <td colspan="3" class="submit"><input class="submit" type="submit" value="Добавить"/></td>

            </tr>
        </table>
    </form:form>
</div>