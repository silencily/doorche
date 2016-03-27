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
        <li class="active"><a href="#tab_1" data-toggle="tab">列表</a></li>
        <li><a href="#tab_2" data-toggle="tab">添加用户</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="tab_1">

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">用户列表</h3>
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
                            <th>状态</th>
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
                                <td>${user.isDisable}</td>
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
        <div class="tab-pane" id="tab_2">
            The European languages are members of the same family. Their separate existence is a myth.
            For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ
            in their grammar, their pronunciation and their most common words. Everyone realizes why a
            new common language would be desirable: one could refuse to pay expensive translators. To
            achieve this, it would be necessary to have uniform grammar, pronunciation and more common
            words. If several languages coalesce, the grammar of the resulting language is more simple
            and regular than that of the individual languages.
        </div>
        <!-- /.tab-pane -->
    </div>
    <!-- /.tab-content -->
</div>
</body>
</html>
