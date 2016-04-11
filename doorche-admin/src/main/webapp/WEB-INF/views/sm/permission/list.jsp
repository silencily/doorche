<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doorche-Admin | 权限列表</title>
    <meta name="decorator" content="default"/>
</head>

<body>

<div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/sm/permission">列表</a></li>
        <li><a href="${ctx}/sm/permission/add">添加权限</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">查询条件</h3>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->
                <form id="searchForm" class="form-horizontal" method="post" action="${ctx}/sm/permission">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="name" class="col-sm-1 control-label">权限名称</label>
                            <div class="col-sm-3">
                                <search:text id="name" name="name" oper="like" type="java.lang.String" placeholder="权限名称"/>
                            </div>
                            <label for="type" class="col-sm-1 control-label">权限类型</label>

                            <div class="col-sm-3">
                                <search:text id="type" name="type" oper="=" type="java.lang.String" placeholder="权限类型"/>
                            </div>
                            <label for="permission" class="col-sm-1 control-label">权限标识</label>

                            <div class="col-sm-3">
                                <search:text id="permission" name="permission" oper="like" type="java.lang.String" placeholder="权限标识"/>
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
                    <h3 class="box-title">权限列表</h3>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body no-padding">
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                        <tr>
                            <th>权限名称</th>
                            <th>权限类型</th>
                            <th>排序</th>
                            <th>链接</th>
                            <th>图标</th>
                            <th>标识</th>
                            <th>是否显示</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="permission" items="${list}">
                            <tr>
                                <td>${permission.name}</td>
                                <td>${permission.type}</td>
                                <td>${permission.sort}</td>
                                <td>${permission.href}</td>
                                <td>${permission.icon}</td>
                                <td>${permission.permission}</td>
                                <td>${permission.isShow}</td>
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
    if(CurrentPage==null){
        var CurrentPage = {};
    }
    CurrentPage.query = function(){
        $.formUtils.post($("#searchForm"));
    }
</script>
</body>
</html>
