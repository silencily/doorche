<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fns' uri="/WEB-INF/tlds/fns.tld" %>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="id" type="java.lang.String" required="false" description="id" %>
<%@ attribute name="name" type="java.lang.String" required="true" description="属性名称" %>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式" %>
<%@ attribute name="typeCode" type="java.lang.String" required="true" description="字典类型编码" %>
<%@ attribute name="headName" type="java.lang.String" required="false" description="头显示名称" %>
<%@ attribute name="value" type="java.lang.String" required="false" description="值" %>

<select id="${id}" style="${cssStyle}" class="form-control" name="${name}">
    <option value="">${headName ne null?headName:'请选择'}</option>
    <c:forEach var="item" items="${fns:getDict(typeCode)}">
        <c:if test="${item.key eq value}">
            <option selected value="${item.key}">${item.value}</option>
        </c:if>
        <c:if test="${item.key ne value}">
            <option value="${item.key}">${item.value}</option>
        </c:if>
    </c:forEach>
</select>

