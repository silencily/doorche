<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doorche-Admin | 角色列表</title>
    <meta name="decorator" content="dialog"/>
</head>

<body>


<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">查询条件</h3>

        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                    class="fa fa-minus"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
    <form id="searchForm" class="form-horizontal" method="post" action="${ctx}/sm/user/selectRoles">
        <div class="box-body">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">角色名</label>

                <div class="col-sm-4">
                    <search:text id="name" name="name" oper="like" type="java.lang.String"
                                 placeholder="角色名"/>
                </div>
                <label for="code" class="col-sm-2 control-label">角色编码</label>

                <div class="col-sm-4">
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
                <th style="width: 20px;"><input type="checkbox" id="ckb-all"/></th>
                <th>角色名称</th>
                <th>角色编码</th>
                <th>数据范围</th>
                <th>是否禁用</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="role" items="${list}">
                <tr>
                    <td><input type="checkbox" value="${role.id}"/></td>
                    <td>${role.name}</td>
                    <td>${role.code}</td>
                    <td>${fns:getDictValue('ROLE_DATASCOPE',role.dataScope)}</td>
                    <td>${fns:getDictValue('ROLE_ISDISABLE',role.isDisable)}</td>
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
<script type="text/javascript">
    if (CurrentPage == null) {
        var CurrentPage = {};
    }
    CurrentPage.query = function () {
        $.formUtils.post($("#searchForm"));
    };
    top.$.windowUtils.handleData = function () {
        var checkedIds = [];
        $(".table tbody :checkbox").each(function (idx, element) {
            var $element= $(element);
            if($element.is(':checked')){
                var id = $(element).val();
                checkedIds.push(id);
            }
        });
        return checkedIds;
    };
    $(function () {
        $("#ckb-all").change(function () {
            var $ckbAll = $(this);
            if ($ckbAll.is(':checked')) {
                $(".table tbody :checkbox").prop("checked", true);
            } else {
                $(".table tbody :checkbox").prop("checked", false);
            }
        });
        $(".table tbody :checkbox").change(function () {
            if ($(this).is(':checked')) {
                var allChecked = true;
                $(".table tbody :checkbox").each(function (idx, element) {
                    if (!$(element).is(':checked')) {
                        allChecked = false;
                        return false;
                    }
                });
                if (allChecked) {
                    $("#ckb-all").prop("checked", true);
                }
            } else {
                $("#ckb-all").prop("checked", false);
            }

        });
    });
</script>
</body>
</html>
