<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Silencily Portal Publisher</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${ctxStatic}/assets/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${ctxStatic}/assets/Font-Awesome-4.4.0/css/font-awesome.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${ctxStatic}/assets/ionicons-2.0.1/css/ionicons.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${ctxStatic}/css/base.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
    <link rel="stylesheet" href="${ctxStatic}/css/skins/skin-blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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
        <a href="index2.html" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>P</b>P</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>Portal</b> Publisher</span>
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
                            <img src="${ctxStatic}/img/user2-160x160.jpg" class="user-image" alt="User Image">
                            <!-- hidden-xs hides the username on small devices so only the image appears. -->
                            <span class="hidden-xs">${currentUser.chName}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- The user image in the menu -->
                            <li class="user-header">
                                <img src="${ctxStatic}/img/user2-160x160.jpg" class="img-circle" alt="User Image">

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
                    <img src="${ctxStatic}/img/user2-160x160.jpg" class="img-circle" alt="User Image">
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
                        <ul class="treeview-menu">
                            <c:forEach items="${menu.children}" var="child">
                                <li><a href="javascript:void(0);" data-href="${child.href}"><i
                                        class="fa fa-circle-o"></i> ${child.name}</a></li>
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

            <ol class="breadcrumb">
                <li><span><i class="fa fa-dashboard"></i> <span id="breadcrumb-catalog"></span></span></li>
                <li class="active"><span id="breadcrumb-menu"></span></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Your Page Content Here -->
            <iframe id="content-frame" src=""
                    style="width: 100%;border: none; min-height: 800px;"></iframe>

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

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.1.4 -->
<script src="${ctxStatic}/assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="${ctxStatic}/assets/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${ctxStatic}/js/app.js"></script>
<script type="text/javascript">
    $(function () {
        $(".treeview-menu li a").click(function () {
            $(".treeview-menu li").removeClass("active");
            $(this).parent().addClass("active");
            var ctx = global.ctx;
            var href = $(this).data("href");
            var menuName = $(this).text();
            var catalogName = $(this).parents(".treeview").find("span").first().text();
            $("#breadcrumb-catalog").text(catalogName);
            $("#breadcrumb-menu").text(menuName);
            $("#content-frame").attr("src", ctx + href);
        });
    });
</script>

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>
