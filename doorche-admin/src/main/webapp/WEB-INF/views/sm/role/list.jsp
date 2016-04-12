<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doorche-Admin | 角色列表</title>
    <meta name="decorator" content="default"/>
</head>

<body>

<div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/sm/role">列表</a></li>
        <li><a href="${ctx}/sm/role/add">添加角色</a></li>
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
                <form id="searchForm" class="form-horizontal" method="post" action="${ctx}/sm/role">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="name" class="col-sm-1 control-label">角色名</label>

                            <div class="col-sm-3">
                                <search:text id="name" name="name" oper="like" type="java.lang.String"
                                             placeholder="角色名"/>
                            </div>
                            <label for="code" class="col-sm-1 control-label">角色编码</label>

                            <div class="col-sm-3">
                                <search:text id="code" name="code" oper="like" type="java.lang.String"
                                             placeholder="角色编码"/>
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
                    <h3 class="box-title">角色列表</h3>

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
                            <th>角色名称</th>
                            <th>角色编码</th>
                            <th>数据范围</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="role" items="${list}">
                            <tr>
                                <td>${role.name}</td>
                                <td>${role.code}</td>
                                <td>${fns:getDictValue('ROLE_DATASCOPE',role.dataScope)}</td>
                                <td>${fns:getDictValue('ROLE_ISDISABLE',role.isDisable)}</td>
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
