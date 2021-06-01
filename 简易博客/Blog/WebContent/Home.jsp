<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>开发者博客 &ndash; Index</title>
    <script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
<script type="text/javascript">  
    console.log(window.navigator.userAgent)
    console.log("公网ip :" + returnCitySN["cip"]+','+returnCitySN["cname"])
</script>
    
    <link rel="stylesheet" href="css/pure-min.css" integrity="sha384-" crossorigin="anonymous">
        <link rel="stylesheet" href="css/grids-responsive-min.css">
            <link rel="stylesheet" href="css/blog.css">
</head>
<body>
<script src="js/GetIp.js"></script>






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
                <h1 class="content-subhead">公告</h1>

                <!-- A single blog post -->
                <section class="post">
                    <header class="post-header">

                        <h2 class="post-title">Title</h2>

                        <p class="post-meta">
                            By <a href="#" class="post-author">Administrator</a>
                        </p>
                    </header>

                    <div class="post-description">
                        <p>
                            这是一个基于pure制作的网页
                        </p>
                    </div>
                </section>
            </div>

            <div class="posts">
                <h1 class="content-subhead">Blog</h1>

               
				<jsp:include page="Blogs.jsp" />
            </div>

            <div class="footer">
                <div class="pure-menu pure-menu-horizontal">
                    <ul>
                        <li class="pure-menu-item"><a href="ServerInfo" class="pure-menu-link">About</a></li>
                        <li class="pure-menu-item"><a href="LogoutServlet" class="pure-menu-link">退出</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>




</body>
</html>
