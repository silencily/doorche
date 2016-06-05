<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doorche-Admin | 用户列表</title>
    <meta name="decorator" content="default"/>
</head>

<body>

<div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/sm/user">列表</a></li>
        <li><a href="${ctx}/sm/user/add">用户新增</a></li>
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
                <form id="searchForm" class="form-horizontal" method="post" action="${ctx}/sm/user">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="name" class="col-sm-1 control-label">用户名</label>
                            <div class="col-sm-3">
                                <search:text id="name" name="name" oper="like" type="java.lang.String" placeholder="用户名"/>
                            </div>
                            <label for="no" class="col-sm-1 control-label">用户编号</label>

                            <div class="col-sm-3">
                                <search:text id="no" name="no" oper="like" type="java.lang.String" placeholder="用户编号"/>
                            </div>
                            <label for="loginName" class="col-sm-1 control-label">登录名</label>

                            <div class="col-sm-3">
                                <search:text id="loginName" name="loginName" oper="like" type="java.lang.String" placeholder="登录名"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-1 control-label">邮箱</label>

                            <div class="col-sm-3">
                                <search:text id="email" name="email" oper="like" type="java.lang.String" placeholder="邮箱"/>
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
                    <h3 class="box-title">用户列表</h3>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body no-padding">
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                        <tr>
                            <th>用户名</th>
                            <th>登录名</th>
                            <th>用户编号</th>
                            <th>邮箱</th>
                            <th>手机号</th>
                            <th>是否禁用</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${list}">
                            <tr>
                                <td>${user.name}</td>
                                <td>${user.loginName}</td>
                                <td>${user.no}</td>
                                <td>${user.email}</td>
                                <td>${user.mobile}</td>
                                <td>${fns:getDictValue('USER_ISDISABLE',user.isDisable)}</td>
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
