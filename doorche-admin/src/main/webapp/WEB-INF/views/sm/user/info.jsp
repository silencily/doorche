<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doorche-Admin | 用户详情</title>
    <meta name="decorator" content="default"/>
</head>

<body>

<div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/sm/user">列表</a></li>
        <c:if test="${not empty user.id}">
            <li class="active"><a href="${ctx}/sm/user/edit?id=${user.id}">用户修改</a></li>
        </c:if>
        <c:if test="${empty user.id}">
            <li class="active"><a href="${ctx}/sm/user/new">用户新增</a></li>
        </c:if>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active">
            <sys:message content="${message}" type="info"/>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">用户详情</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->
                <form id="infoForm" class="form-horizontal" method="post" action="${ctx}/sm/user/save">
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="hidden" name="version" value="${user.version}"/>
                    <input type="hidden" name="password" value="${user.password}"/>

                    <div class="box-body">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">用户名</label>

                            <div class="col-sm-4">
                                <input id="name" class="form-control" type="text" name="name"
                                       value="${user.name}" placeholder="用户名"/>
                            </div>
                            <label for="no" class="col-sm-2 control-label">用户编号</label>

                            <div class="col-sm-4">
                                <input id="no" class="form-control" type="text" name="no"
                                       value="${user.no}" placeholder="用户编号"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="loginName" class="col-sm-2 control-label">登录名</label>

                            <div class="col-sm-4">
                                <input id="loginName" class="form-control" type="text" name="loginName"
                                       value="${user.loginName}" placeholder="登录名"/>
                            </div>
                            <label for="mobile" class="col-sm-2 control-label">手机号</label>

                            <div class="col-sm-4">
                                <input id="mobile" class="form-control" type="text" name="mobile"
                                       value="${user.mobile}" placeholder="手机号"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">邮箱</label>

                            <div class="col-sm-4">
                                <input id="email" class="form-control" type="text" name="email"
                                       value="${user.email}" placeholder="邮箱"/>
                            </div>
                            <label for="isDisable" class="col-sm-2 control-label">是否禁用</label>

                            <div class="col-sm-4">
                                <sys:select id="isDisable" name="isDisable" typeCode="USER_ISDISABLE"
                                            value="${user.isDisable}"/>
                            </div>
                        </div>
                        <table id="code-table" class="table table-bordered table-striped table-hover">
                            <thead>
                            <tr>
                                <th>角色名称</th>
                                <th>角色编码</th>
                                <th>数据范围</th>
                                <th>是否禁用</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="role" items="${user.tsmRoles}">
                                <tr>
                                    <td>${role.name}</td>
                                    <td>${role.code}</td>
                                    <td>${fns:getDictValue('ROLE_DATASCOPE',role.dataScope)}</td>
                                    <td>${fns:getDictValue('ROLE_ISDISABLE',role.isDisable)}</td>
                                    <td>
                                        <security:hasPermission name="sys:user:deleteRole">
                                            <a href="${ctx}/sm/user/deleteRole?id=${role.id}">删除</a>
                                        </security:hasPermission>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer" style="text-align: center;">
                        <button type="button" onclick="CurrentPage.addRole();" class="btn btn-info">添加角色</button>
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
        $.formUtils.post($("#infoForm"), "${ctx}/sm/user");
    }
    CurrentPage.addRole = function(){

    }
</script>
</body>
</html>
