<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fns' uri="/WEB-INF/tlds/fns.tld" %>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="id" type="java.lang.String" required="false" description="查询条件的value的id" %>
<%@ attribute name="name" type="java.lang.String" required="true" description="查询条件的持久化类的属性" %>
<%@ attribute name="oper" type="java.lang.String" required="true" description="查询操作符" %>
<%@ attribute name="type" type="java.lang.String" required="true" description="持久化类属性类型" %>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式" %>
<%@ attribute name="typeCode" type="java.lang.String" required="true" description="字典类型编码" %>
<%@ attribute name="headName" type="java.lang.String" required="false" description="头显示名称" %>

<c:set var="key" value="${name}-${oper}"/>
<select style="${cssStyle}" class="form-control" id="${id}" name="conditions['${key}'].value"">
<option value="">${headName ne null?headName:'请选择'}</option>
<c:forEach var="item" items="${fns:getDict(typeCode)}">
    <c:if test="${item.key eq conditions.conditions[key].value}">
        <option selected value="${item.key}">${item.value}</option>
    </c:if>
    <c:if test="${item.key ne conditions.conditions[key].value}">
        <option value="${item.key}">${item.value}</option>
    </c:if>
</c:forEach>
</select>
<input type="hidden" name="conditions['${key}'].name" value="${name}">
<input type="hidden" name="conditions['${key}'].operator" value="${oper}">
<input type="hidden" name="conditions['${key}'].type" value="${type}">

