<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>开发者博客 &ndash; my</title>
    
    <link rel="stylesheet" href="css/pure-min.css" integrity="sha384-" crossorigin="anonymous">
        <link rel="stylesheet" href="css/grids-responsive-min.css">
            <link rel="stylesheet" href="css/blog.css">
</head>
<body>







<div id="layout" class="pure-g">
    <div class="sidebar pure-u-1 pure-u-md-1-4">
        <div class="header">
            <h1 class="brand-title">开发者博客</h1>
            <h2 class="brand-tagline">Developer Blog</h2>

            <nav class="nav">
                <ul class="nav-list">
                    <li class="nav-item">
                        <a class="pure-button" href="HomeServlet">主页</a>
                    </li>
                    <li class="nav-item">
                        <a class="pure-button" href="MyServlet">我的</a>
                    </li>
                    <li class="nav-item">
                        <a class="pure-button" href="Write.jsp">写博客</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

    <div class="content pure-u-1 pure-u-md-3-4">
        <div>
            <!-- A wrapper for all the blog posts -->
            

            <div class="posts">
                <h1 class="content-subhead">我的博客</h1>

               
				<jsp:include page="myblogs.jsp" />
            </div>

            <div class="footer">
                <div class="pure-menu pure-menu-horizontal">
                    <ul>
                        <li class="pure-menu-item"><a href="#" class="pure-menu-link">About</a></li>
                        <li class="pure-menu-item"><a href="#" class="pure-menu-link">Server status</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>




</body>
</html>
