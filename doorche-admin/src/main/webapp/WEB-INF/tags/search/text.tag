<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" type="java.lang.String" required="false" description="查询条件的value的id"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="查询条件的持久化类的属性"%>
<%@ attribute name="oper" type="java.lang.String" required="true" description="查询操作符"%>
<%@ attribute name="type" type="java.lang.String" required="true" description="持久化类属性类型"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="placeholder" type="java.lang.String" required="false" description="placeholder"%>

<c:set var="key" value="${name}-${oper}"/>
<input type="text" style="${cssStyle}" name="conditions['${key}'].value" class="form-control" id="${id}" placeholder="${placeholder}" value="${conditions.conditions[key].value}">
<input type="hidden" name="conditions['${key}'].name" value="${name}">
<input type="hidden" name="conditions['${key}'].operator" value="${oper}">
<input type="hidden" name="conditions['${key}'].type" value="${type}">