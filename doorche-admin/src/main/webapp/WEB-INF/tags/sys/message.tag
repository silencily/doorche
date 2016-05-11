<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容" %>
<%@ attribute name="type" type="java.lang.String" description="消息类型：info、success、warning、danger" %>
<c:if test="${not empty content}">
    <c:if test="${not empty type}">
        <c:set var="ctype" value="${type}"/>
    </c:if>
    <c:if test="${empty type}">
        <c:set var="ctype" value="${fn:indexOf(content,'失败') eq -1?'success':'error'}"/>
    </c:if>
    <div class="alert alert-${ctype} alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <i class="icon fa fa-info"></i>${content}
    </div>
</c:if>