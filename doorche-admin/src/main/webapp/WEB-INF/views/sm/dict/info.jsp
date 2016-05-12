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
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">字典详情</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->
                <form id="infoForm" class="form-horizontal" method="post" action="${ctx}/sm/dict/save">
                    <input type="hidden" name="id" value="${dict.id}"/>
                    <input type="hidden" name="version" value="${dict.version}"/>

                    <div class="box-body">
                        <div class="form-group">
                            <label for="typeName" class="col-sm-2 control-label">类型名称</label>

                            <div class="col-sm-4">
                                <input id="typeName" class="form-control" type="text" name="typeName"
                                       value="${dict.typeName}" placeholder="类型名称"/>
                            </div>
                            <label for="typeCode" class="col-sm-2 control-label">类型编码</label>

                            <div class="col-sm-4">
                                <input id="typeCode" class="form-control" type="text" name="typeCode"
                                       value="${dict.typeCode}" placeholder="类型编码"/>
                            </div>
                        </div>
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
                            <c:forEach var="child" items="${children}">
                                <tr>
                                    <td>
                                        <input class="form-control input-sm" type="text" value="${child.typeName}"/>
                                    </td>
                                    <td>
                                        <input class="form-control input-sm" type="text" value="${child.typeCode}"/>
                                    </td>
                                    <td>
                                        <input class="form-control input-sm" type="text" value="${child.sort}"/>
                                    </td>
                                    <td style="padding: 13px 5px;">
                                        <a href="">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer" style="text-align: center;">
                        <button type="button" onclick="CurrentPage.addCode();" class="btn btn-info">新增编码</button>
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
    };
    CurrentPage.back = function () {
        $.formUtils.post($("#infoForm"), "${ctx}/sm/dict");
    };
    CurrentPage.addCode = function () {
        var rowHtml = "<tr>"+
                        "<td>"+
                             "<input class='form-control input-sm' type='text'/>"+
                        "</td>"+
                        "<td>"+
                             "<input class='form-control input-sm' type='text'/>"+
                        "</td>"+
                        "<td>"+
                             "<input class='form-control input-sm' type='text'/>"+
                        "</td>"+
                        "<td style='padding: 13px 5px;'>"+
                             "<a href=''>删除</a>"+
                        "</td>"+
                      "</tr>";
        $("#code-table tbody").append(rowHtml);
    }
    CurrentPage.deleteCode = function(){

    }
</script>
</body>
</html>
