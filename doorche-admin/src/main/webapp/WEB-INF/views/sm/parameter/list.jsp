<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doorche-Admin | 系统参数列表</title>
    <meta name="decorator" content="default"/>
</head>

<body>

<div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/sm/parameter">列表</a></li>
        <li><a href="${ctx}/sm/parameter/new">添加参数</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">查询条件</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->
                <form id="searchForm" class="form-horizontal" method="post" action="${ctx}/sm/user">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="paramType" class="col-sm-1 control-label">参数类型</label>

                            <div class="col-sm-3">
                                <search:text id="paramType" name="paramType" oper="like" type="java.lang.String"
                                             placeholder="参数类型"/>
                            </div>
                            <label for="paramName" class="col-sm-1 control-label">参数名称</label>

                            <div class="col-sm-3">
                                <search:text id="paramName" name="paramName" oper="like" type="java.lang.String"
                                             placeholder="参数名称"/>
                            </div>
                            <label for="paramKey" class="col-sm-1 control-label">参数编码</label>

                            <div class="col-sm-3">
                                <search:text id="paramKey" name="paramKey" oper="like" type="java.lang.String"
                                             placeholder="参数编码"/>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer" style="text-align: center;">
                        <button type="reset" class="btn btn-info">重 置</button>
                        <button type="button" onclick="CurrentPage.query();" class="btn btn-info">查 询</button>
                    </div>
                </form>
            </div>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">参数列表</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body no-padding">
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                        <tr>
                            <th>参数类型</th>
                            <th>参数名称</th>
                            <th>参数编码</th>
                            <th>参数值</th>
                            <th>说明</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="parameter" items="${list}">
                            <tr>
                                <td>${fns:getDictValue('PARAM_TYPE',parameter.paramType)}</td>
                                <td>${parameter.paramName}</td>
                                <td>${parameter.paramKey}</td>
                                <td>${parameter.paramValue}</td>
                                <td>${parameter.remarks}</td>
                                <td><a href="#">修改</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
                <div class="box-footer clearfix">
                    <%@include file="/WEB-INF/include/paginater.jspf" %>
                </div>
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
    CurrentPage.query = function () {
        $.formUtils.post($("#searchForm"));
    }
</script>
</body>
</html>
