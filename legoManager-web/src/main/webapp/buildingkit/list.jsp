<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="trol">

    <s:layout-component name="body">

        <s:form beanclass="cz.muni.fi.PA165.action.BuildingKitActionBean">
            <fieldset><legend><f:message key="book.list.newbook"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add">Vytvořit novou stavebnici</s:submit>
            </fieldset>
        </s:form>




    </s:layout-component>
</s:layout-render>