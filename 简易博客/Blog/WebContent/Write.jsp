<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>开发者博客 &ndash; Write</title>
    
    <link rel="stylesheet" href="css/pure-min.css" integrity="sha384-" crossorigin="anonymous">
        <link rel="stylesheet" href="css/grids-responsive-min.css">
            <link rel="stylesheet" href="css/blog.css">
        <script src="js/jquery.min.js"></script>
        <script src="trumbowyg/trumbowyg.js"></script>
        <script src="trumbowyg/langs/zh_cn.js"></script>
        <script src="trumbowyg/plugins/upload/trumbowyg.upload.js"></script>
        <script src="trumbowyg/plugins/base64/trumbowyg.base64.js"></script>
        <link rel="stylesheet" href="trumbowyg/design/css/trumbowyg.css">
        
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
                        <a class="pure-button" href="wirte.html">写博客</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

    <div class="content pure-u-1 pure-u-md-3-4">
        <div>
        <form action="WriteServlet" method="post">
            <!-- A wrapper for all the blog posts -->
            <div class="posts">
                <h1 class="content-subhead">写Blog</h1>
                
				
                <!-- A single blog post -->
                <section class="post">
                    <input type="text" name="title" id="title" placeholder="Title" style="width:100%; "/>
                </section>
            </div>
                <section class="post">
                    
                            <textarea id="form-content" class="editor" cols="30" rows="10" name="string">
                                    <p>Textarea</p>
                                </textarea>
                    			<input class="pure-button" type="submit" value="提交" >
                    </section>
        </form>
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



<script>
        /** Default editor configuration **/
        $('#simple-editor').trumbowyg();



        /********************************************************
         * Customized button pane + buttons groups + dropdowns
         * Use upload plugin
         *******************************************************/

        /*
         * Add your own groups of button
         */
        $.trumbowyg.btnsGrps.test = ['bold', 'link'];

        /* Add new words for customs btnsDef just below */
        $.extend(true, $.trumbowyg.langs, {
            zh_cn: {
                align: 'Alignement',
                image: 'Image'
            }
        });


        /** Simple customization with current options **/
        $('#form-content').trumbowyg({
            lang: 'zh_cn',
            closable: false,
            mobile: true,
            fixedBtnPane: true,
            fixedFullWidth: true,
            semantic: true,
            resetCss: true,
            autoAjustHeight: true,
            autogrow: true
        });




        $('.editor').on('dblclick', function(e){
            $(this).trumbowyg({
                lang: 'zh_cn',
                closable: false,
                fixedBtnPane: true
            });
        });
        
    </script>
</body>
</html>
