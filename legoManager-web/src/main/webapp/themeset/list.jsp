
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="themeset.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.PA165.action.ThemeSetActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="themeset.headline"/></h1>

        <s:form class="form-horizontal" id="themesetCreateForm" beanclass="cz.muni.fi.PA165.action.ThemeSetActionBean">
            <legend><f:message key="themeset.create.title"/></legend>
            <%@include file="form.jsp"%>

            <!--submit button, in bootstrap div class, see documentation for bootstrap forms-->
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4">
                    <s:submit class="btn" name="add"><f:message key="themeset.create.button"/></s:submit>
                </div>
            </div>
        </s:form>
        <table class="table">
            <tr>
                <th>id</th>
                <th><f:message key="table.themeset.name"/></th>
                <th><f:message key="table.themeset.price"/></th>
                <th><f:message key="table.themeset.description"/></th>
                <th><f:message key="table.themeset.category"/></th>
                <th><f:message key="table.options"/></th>
            </tr>

            <c:forEach items="${actionBean.themeSets}" var="themeSet">
                <tr>
                    <td>${themeSet.id}</td>
                    <td><c:out value="${themeSet.name}"/></td>
                    <td><c:out value="${themeSet.price}"/></td>
                    <td><c:out value="${themeSet.description}"/></td>
                    <td></td>
                    <td>
                        <span>
                            <!--table buttons-->
                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.ThemeSetActionBean">
                                <s:hidden name="dto.id" value="${dto.id}"/>
                                <s:submit class="btn" name="openEditPage"><f:message key="themeset.edit.button"/></s:submit>
                            </s:form>

                            <s:form class="table-buttons" beanclass="cz.muni.fi.PA165.action.ThemeSetActionBean">
                                <s:hidden name="dto.id" value="${dto.id}"/>
                                <s:submit class="btn" name="delete"><f:message key="themeset.delete.button"/></s:submit>
                            </s:form>
                        </span>
                    </td>
                    
                </tr>
            </c:forEach>
        </table>
            
            
        </s:layout-component>
</s:layout-render>