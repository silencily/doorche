<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" type="java.lang.String" required="false" description="id"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择标题"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="showName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="showValue" type="java.lang.String" required="true" description="输入框值（Name）"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="树结构数据地址"%>
<%@ attribute name="excludeId" type="java.lang.String" required="false" description="排除掉的节点（不能选择的节点）"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="placeholder" type="java.lang.String" required="false" description="placeholder"%>

<div class="input-group">
    <input id="${id}" type="hidden" name="${name}" value="${value}">
    <input type="text" style="${cssStyle}" name="${showName}" class="form-control" id="show-${id}" placeholder="${placeholder}" value="${showValue}" readonly>
    <span class="input-group-btn">
        <button id="btn-select-${id}" class="btn btn-default" type="button" data-toggle="modal" data-target="#modal-${id}">
            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
        </button>
      </span>
</div>
<div id="modal-${id}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modal-title-${id}">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="modal-title-${id}">${title}</h4>
            </div>
            <div class="modal-body">
                ...
            </div>
        </div>
    </div>
</div>