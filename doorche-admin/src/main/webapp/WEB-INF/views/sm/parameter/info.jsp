<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doorche-Admin | 系统参数详情</title>
    <meta name="decorator" content="default"/>
</head>

<body>

<div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/sm/parameter">列表</a></li>
        <c:if test="${not empty parameter.id}">
            <li class="active"><a href="${ctx}/sm/parameter/edit?id=${parameter.id}">参数修改</a></li>
        </c:if>
        <c:if test="${empty parameter.id}">
            <li class="active"><a href="${ctx}/sm/parameter/new">参数新增</a></li>
        </c:if>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">参数详情</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->
                <form id="infoForm" class="form-horizontal" method="post" action="${ctx}/sm/parameter/save">
                    <input type="hidden" name="id" value="${parameter.id}"/>
                    <input type="hidden" name="version" value="${parameter.version}"/>

                    <div class="box-body">
                        <div class="form-group">
                            <label for="paramType" class="col-sm-2 control-label">参数类型</label>

                            <div class="col-sm-4">
                                <sys:select id="paramType" name="paramType" typeCode="PARAM_TYPE"
                                            value="${parameter.paramType}"/>
                            </div>
                            <label for="paramName" class="col-sm-2 control-label">参数名称</label>

                            <div class="col-sm-4">
                                <input id="paramName" class="form-control" type="text" name="paramName"
                                       value="${parameter.paramName}" placeholder="参数名称"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="paramKey" class="col-sm-2 control-label">参数编码</label>

                            <div class="col-sm-4">
                                <input id="paramKey" class="form-control" type="text" name="paramKey"
                                       value="${parameter.paramKey}" placeholder="参数编码"/>
                            </div>
                            <label for="paramValue" class="col-sm-2 control-label">参数值</label>

                            <div class="col-sm-4">
                                <input id="paramValue" class="form-control" type="text" name="paramValue"
                                       value="${parameter.paramValue}" placeholder="参数值"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="remarks" class="col-sm-2 control-label">说明</label>

                            <div class="col-sm-10">
                                <input id="remarks" class="form-control" type="text" name="remarks"
                                       value="${parameter.remarks}" placeholder="说明"/>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer" style="text-align: center;">
                        <button type="button" onclick="CurrentPage.save();" class="btn btn-info">保 存</button>
                        <button type="button" onclick="CurrentPage.back();" class="btn btn-info">返 回</button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.tab-pane -->
    </div>
    <!-- /.tab-content -->
</div>
<script type="text/javascript">
    if (CurrentPage == null) {
        var CurrentPage = {};
    }
    CurrentPage.save = function () {
        $.formUtils.post($("#infoForm"));
    }
    CurrentPage.back = function () {
        $.formUtils.post($("#infoForm"),"${ctx}/sm/parameter");
    }
</script>
</body>
</html>
