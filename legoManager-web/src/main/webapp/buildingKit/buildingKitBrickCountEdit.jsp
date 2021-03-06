<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="brick.count.edit">
    <s:layout-component name="body">

        <s:useActionBean beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean" var="actionBean"/>
        <h1 class="text-center"><f:message key="buildingkit.addbrick.editCount"/></h1>


        <s:form class="createForm form-horizontal" beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean">
            <s:param name="buildingKit.id" value="${actionBean.buildingKit.id}"/>
            <legend><f:message key="edit.edit.title"/></legend>

            <%--input element for setting brick count--%>
            <div class="form-group">
                <s:label for="b1" class="col-sm-2 control-label"><f:message key="bricks.form.create.count"/></s:label>
                <div class="col-sm-4">
                    <s:text class="form-control" id="b2" name="brickCount" value="${actionBean.brickCount}"/>
                </div>
            </div>

            <%--form buttons--%>
            <div class="form-group">

                    <%--save button--%>
                <div class="col-sm-offset-2 col-sm-1">
                    <s:hidden name="brick.id" value="${actionBean.brick.id}"/>
                    <s:submit class="btn" name="saveBrickCount"><f:message key="themeset.button.save"/></s:submit>
                </div>

                    <%--cancel button--%>
                <div class="col-sm-offset-1 col-sm-3">
                    <s:link class="btn anchor-to-button table-buttons" beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean" event="openManageBrickPage">
                        <s:param name="buildingKit.id" value="${actionBean.buildingKit.id}"/>
                        <f:message key="buildingkit.addbrick.cancel"/>
                    </s:link>
                </div>
            </div>
        </s:form>

    </s:layout-component>
</s:layout-render>