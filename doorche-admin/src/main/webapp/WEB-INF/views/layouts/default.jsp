<%@page pageEncoding="utf-8" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
    <title><sitemesh:title/> - Powered By Doorche</title>
    <%@include file="/WEB-INF/include/head.jspf" %>
    <sitemesh:head/>
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <header class="main-header">

        <!-- Logo -->
        <a href="/" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>D</b>A</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>Doorche</b> Admin</span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">

                    <!-- User Account Menu -->
                    <li class="dropdown user user-menu">
                        <!-- Menu Toggle Button -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <!-- The user image in the navbar-->
                            <img src="${ctxStatic}/assets/admin/img/user2-160x160.jpg" class="user-image"
                                 alt="User Image">
                            <!-- hidden-xs hides the username on small devices so only the image appears. -->
                            <span class="hidden-xs">${currentUser.chName}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- The user image in the menu -->
                            <li class="user-header">
                                <img src="${ctxStatic}/assets/admin/img/user2-160x160.jpg" class="img-circle"
                                     alt="User Image">

                                <p>
                                    ${currentUser.username} - ${currentUser.no}
                                    <small>${currentUser.email}</small>
                                </p>
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="#" class="btn btn-default btn-flat">Profile</a>
                                </div>
                                <div class="pull-right">
                                    <a href="${ctx}/logout" class="btn btn-default btn-flat">Sign out</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

            <!-- Sidebar user panel (optional) -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${ctxStatic}/assets/admin/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>${currentUser.username}</p>
                    <!-- Status -->
                    <a href="#"><i class="fa fa-circle text-success"></i> ${currentUser.email}</a>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <ul class="sidebar-menu">
                <li class="header">主菜单</li>
                <!-- Optionally, you can add icons to the links -->
                <c:forEach items="${menus}" var="menu">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-laptop"></i> <span>${menu.name}</span> <i
                                class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu" data-name="${menu.name}">
                            <c:forEach items="${menu.children}" var="child">
                                <li><a href="${child.href}" data-href="${child.href}" data-name="${child.name}">
                                    <i class="fa fa-circle-o"></i> ${child.name}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1 id="content-title">
                主页
            </h1>

            <ol class="breadcrumb">
                <li><i class="fa fa-dashboard"></i> 主页</li>
                <li id="nav-content-title-fst">系统管理</li>
                <li id="nav-content-title-scd" class="active">用户管理</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <sitemesh:body/>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
            Anything you want
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2015 <a href="#">Company</a>.</strong> All rights reserved.
    </footer>
</div>
<!-- ./wrapper -->


<script type="text/javascript">
    $(function () {
        //获取当前路径地址
        var currentHref = location.href;
        $(".treeview-menu li").each(function (idx, element) {
            var menuHref = $(element).find("a").data("href");
            if (currentHref.indexOf(menuHref) > 0) {
                $(element).addClass("active");
                $(element).parent().parent().addClass("active");
                var menuName = $(element).find("a").data("name");
                var parentMenuName = $(element).parent().data("name");
                $("#content-title").text(menuName);
                $("#nav-content-title-scd").text(menuName);
                $("#nav-content-title-fst").text(parentMenuName);
            }
        });
    });
</script>

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>
