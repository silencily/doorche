<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doorche-Admin | 权限详情</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/include/treeview.jspf" %>
</head>

<body>

<div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/sm/permission">列表</a></li>
        <c:if test="${not empty permission.id}">
            <li class="active"><a href="${ctx}/sm/permission/edit?id=${permission.id}">权限修改</a></li>
        </c:if>
        <c:if test="${empty permission.id}">
            <li class="active"><a href="${ctx}/sm/permission/new">权限新增</a></li>
        </c:if>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">权限详情</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->
                <form id="infoForm" class="form-horizontal" method="post" action="${ctx}/sm/permission/save">
                    <input type="hidden" name="id" value="${permission.id}"/>
                    <input type="hidden" name="version" value="${permission.version}"/>

                    <div class="box-body">
                        <div class="form-group">
                            <label for="parentPermission" class="col-sm-2 control-label">上级节点</label>

                            <div class="col-sm-4">
                                <tree:select id="parentPermission" title="请选择" name="parentId"
                                             value="${permission.parent.id}" showName="parentName"
                                             showValue="${permission.parent.name}" url="${ctx}/sm/permission/tree"
                                             placeholder="请选择" excludeId="${permission.id}"/>
                            </div>
                            <label for="type" class="col-sm-2 control-label">权限类型</label>

                            <div class="col-sm-4">
                                <input id="type" class="form-control" type="text" name="type"
                                       value="${permission.type}" placeholder="权限类型"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">权限名称</label>

                            <div class="col-sm-4">
                                <input id="name" class="form-control" type="text" name="name"
                                       value="${permission.name}" placeholder="权限名称"/>
                            </div>
                            <label for="sort" class="col-sm-2 control-label">排序</label>

                            <div class="col-sm-4">
                                <input id="sort" class="form-control" type="text" name="sort"
                                       value="${permission.sort}" placeholder="排序"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="href" class="col-sm-2 control-label">链接</label>

                            <div class="col-sm-10">
                                <input id="href" class="form-control" type="text" name="href"
                                       value="${permission.href}" placeholder="链接"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="icon" class="col-sm-2 control-label">权限图标</label>

                            <div class="col-sm-4">
                                <input id="icon" class="form-control" type="text" name="icon"
                                       value="${permission.icon}" placeholder="权限图标"/>
                            </div>
                            <label for="permission" class="col-sm-2 control-label">权限标识</label>

                            <div class="col-sm-4">
                                <input id="permission" class="form-control" type="text" name="permission"
                                       value="${permission.permission}" placeholder="权限标识"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="isShow" class="col-sm-2 control-label">是否显示</label>

                            <div class="col-sm-4">
                                <input id="isShow" class="form-control" type="text" name="isShow"
                                       value="${permission.isShow}" placeholder="是否显示"/>
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
        $.formUtils.post($("#infoForm"), "${ctx}/sm/permission");
    }
</script>
</body>
</html>
