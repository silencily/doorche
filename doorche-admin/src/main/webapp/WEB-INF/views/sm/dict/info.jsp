<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doorche-Admin | 系统字典详情</title>
    <meta name="decorator" content="default"/>
</head>

<body>

<div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/sm/dict">列表</a></li>
        <c:if test="${not empty dict.id}">
            <li class="active"><a href="${ctx}/sm/dict/edit?id=${dict.id}">字典修改</a></li>
        </c:if>
        <c:if test="${empty dict.id}">
            <li class="active"><a href="${ctx}/sm/dict/new">字典新增</a></li>
        </c:if>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active">
            <sys:message content="${message}" type="info"/>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">字典详情</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->


                <div class="box-body">
                    <form id="infoForm" class="form-horizontal" method="post" action="${ctx}/sm/dict/save">
                        <input type="hidden" id="parentId" name="id" value="${dict.id}"/>
                        <input type="hidden" name="version" value="${dict.version}"/>

                        <div class="form-group">
                            <label for="typeName" class="col-sm-2 control-label">类型名称</label>

                            <div class="col-sm-4">
                                <input id="typeName" class="form-control" type="text" name="typeName"
                                       value="${dict.typeName}" placeholder="类型名称" required/>
                            </div>
                            <label for="typeCode" class="col-sm-2 control-label">类型编码</label>

                            <div class="col-sm-4">
                                <input id="typeCode" class="form-control" type="text" name="typeCode"
                                       value="${dict.typeCode}" placeholder="类型编码" required/>
                            </div>
                        </div>
                    </form>
                    <table id="code-table" class="table table-bordered table-striped table-hover">
                        <thead>
                        <tr>
                            <th>编码名称</th>
                            <th>编码值</th>
                            <th>排序</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="child" items="${children}" varStatus="status">
                            <tr>
                                <input type="hidden" name="id" value="${child.id}"/>
                                <input type="hidden" name="version" value="${child.version}"/>
                                <input type="hidden" name="parentId" value="${dict.id}"/>
                                <td>
                                    <input class="form-control input-sm" type="text"
                                           name="name" value="${child.name}"/>
                                </td>
                                <td>
                                    <input class="form-control input-sm" type="text" name="code"
                                           value="${child.code}"/>
                                </td>
                                <td>
                                    <input class="form-control input-sm" type="text" name="sort"
                                           value="${child.sort}"/>
                                </td>
                                <td style="padding: 13px 5px;">
                                    <a href="javascript:void(0);" style="padding-right: 5px;"
                                       onclick="CurrentPage.deleteCode(this)">删除</a>
                                    <a href="javascript:void(0);" onclick="CurrentPage.saveCode(this)">保存</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->

                <div class="box-footer" style="text-align: center;">
                    <c:if test="${not empty dict.id}">
                        <button type="button" onclick="CurrentPage.addCode();" class="btn btn-info">新增编码</button>
                    </c:if>
                    <button type="button" onclick="CurrentPage.save();" class="btn btn-info">保 存</button>
                    <button type="button" onclick="CurrentPage.back();" class="btn btn-info">返 回</button>
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
    CurrentPage.save = function () {
        $.formUtils.post($("#infoForm"));
    };
    CurrentPage.back = function () {
        $.formUtils.post($("#infoForm"), "${ctx}/sm/dict");
    };
    CurrentPage.addCode = function () {
        var trLastIndex = $("#code-table tbody").children("tr").length;
        var parentId = $("#parentId").val();
        var rowHtml =
                "<tr>" +
                "<input type='hidden' name='parentId' value='" + parentId + "'/>" +
                "<td>" +
                "<input class='form-control input-sm' type='text' name='name'/>" +
                "</td>" +
                "<td>" +
                "<input class='form-control input-sm' type='text' name='code'/>" +
                "</td>" +
                "<td>" +
                "<input class='form-control input-sm' type='text' name='sort'/>" +
                "</td>" +
                "<td style='padding: 13px 5px;'>" +
                "<a href='javascript:void(0);' style='padding-right: 5px;' onclick='CurrentPage.deleteCode(this)'>删除</a>" +
                "<a href='javascript:void(0);' onclick='CurrentPage.saveCode(this)'>保存</a>" +
                "</td>" +
                "</tr>";

        $("#code-table tbody").append(rowHtml);
    }
    CurrentPage.deleteCode = function (obj) {
        var id = $(obj).parents("tr").find("[name='id']").val() || null;
        if (id == null) {
            alert("id is null");
        }
        $(obj).parents("tr").remove();
    }
    CurrentPage.saveCode = function (obj) {
        var url = "${ctx}/sm/dict/save";
        var fields = [];
        $(obj).parents("tr").find("input").each(function(){
            fields.push({name:this.name,value:this.value});
        });
        $.formUtils.dynamicPost(url, fields);
    }

</script>
</body>
</html>
