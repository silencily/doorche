<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doorche-Admin | 角色详情</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/include/treetable.jspf" %>
</head>

<body>

<div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/sm/role">列表</a></li>
        <c:if test="${not empty role.id}">
            <li class="active"><a href="${ctx}/sm/role/edit?id=${role.id}">角色修改</a></li>
        </c:if>
        <c:if test="${empty role.id}">
            <li class="active"><a href="${ctx}/sm/role/new">角色新增</a></li>
        </c:if>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active">
            <sys:message content="${message}" type="info"/>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">角色详情</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->
                <form id="infoForm" class="form-horizontal" method="post" action="${ctx}/sm/role/save">
                    <input type="hidden" name="id" value="${role.id}"/>
                    <input type="hidden" name="version" value="${role.version}"/>
                    <input type="hidden" id="permissionIds" name="permissionIds" />

                    <div class="box-body">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">角色名称</label>

                            <div class="col-sm-4">
                                <input id="name" class="form-control" type="text" name="name"
                                       value="${role.name}" placeholder="角色名称"/>
                            </div>
                            <label for="code" class="col-sm-2 control-label">角色编码</label>

                            <div class="col-sm-4">
                                <input id="code" class="form-control" type="text" name="code"
                                       value="${role.code}" placeholder="角色编码"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dataScope" class="col-sm-2 control-label">数据范围</label>

                            <div class="col-sm-4">
                                <input id="dataScope" class="form-control" type="text" name="dataScope"
                                       value="${role.dataScope}" placeholder="数据范围"/>
                            </div>
                            <label for="isDisable" class="col-sm-2 control-label">是否禁用</label>

                            <div class="col-sm-4">
                                <input id="isDisable" class="form-control" type="text" name="isDisable"
                                       value="${role.isDisable}" placeholder="是否禁用"/>
                            </div>
                        </div>
                        <table id="treetable" class="treetable table table-bordered table-striped table-hover">
                            <thead>
                            <tr>
                                <th>权限名称</th>
                                <th>权限类型</th>
                                <th>排序</th>
                                <th>链接</th>
                                <th>图标</th>
                                <th>标识</th>
                                <th>是否显示</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="permission" items="${permissionList}">
                                <tr data-tt-id="${permission.node.id}"
                                    data-tt-parent-id="${empty permission.node.parent.id?'0':permission.node.parent.id}"
                                    data-tt-checked="${permission.checked}">
                                    <td>${permission.node.name}</td>
                                    <td>${fns:getDictValue('PERMISSION_TYPE',permission.node.type)}</td>
                                    <td>${permission.node.sort}</td>
                                    <td>${permission.node.href}</td>
                                    <td>${permission.node.icon}</td>
                                    <td>${permission.node.permission}</td>
                                    <td>${fns:getDictValue('PERMISSION_ISSHOW',permission.node.isShow)}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
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
    var treetable = $("#treetable").treetable({ expandable: true, initialState: "expanded", multiSelectable: true});//树形列表

    if (CurrentPage == null) {
        var CurrentPage = {};
    }
    CurrentPage.save = function () {
        var checkedIds = treetable.treetable("obtainChecked");
        $('#permissionIds').val(checkedIds);
        $.formUtils.post($("#infoForm"));
    }
    CurrentPage.back = function () {
        $.formUtils.post($("#infoForm"), "${ctx}/sm/role");
    }
</script>
</body>
</html>
