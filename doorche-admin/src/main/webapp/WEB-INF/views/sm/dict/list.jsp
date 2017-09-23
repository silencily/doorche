<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doorche-Admin | 系统字典编码列表</title>
    <meta name="decorator" content="default"/>
</head>

<body>

<div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/sm/dict">列表</a></li>
        <li><a href="${ctx}/sm/dict/new">字典新增</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active">
            <sys:message content="${message}" type="info"/>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">查询条件</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->
                <form id="searchForm" class="form-horizontal" method="post" action="${ctx}/sm/dict">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="typeName" class="col-sm-2 control-label">类型名称</label>

                            <div class="col-sm-4">
                                <search:text id="typeName" name="typeName" oper="like" type="java.lang.String"
                                             placeholder="类型名称"/>
                            </div>
                            <label for="typeCode" class="col-sm-2 control-label">类型编码</label>

                            <div class="col-sm-4">
                                <search:text id="typeCode" name="typeCode" oper="like" type="java.lang.String"
                                             placeholder="类型编码"/>
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
                    <h3 class="box-title">字典列表</h3>

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
                            <th>类型名称</th>
                            <th>类型编码</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="dict" items="${list}">
                            <tr>
                                <td>${dict.typeName}</td>
                                <td>${dict.typeCode}</td>
                                <td>
                                    <security:hasPermission name="sys:dict:edit">
                                        <a href="${ctx}/sm/dict/edit?id=${dict.id}">修改</a>
                                    </security:hasPermission>
                                    <security:hasPermission name="sys:dict:delete">
                                        <a href="${ctx}/sm/dict/delete?id=${dict.id}">删除</a>
                                    </security:hasPermission>
                                </td>
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
