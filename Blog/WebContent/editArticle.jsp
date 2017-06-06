<%@ page language="java" import="java.util.*,java.text.*,com.dragon.blog.entity.*"
	contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	//接受文章id
	int id = (Integer)request.getAttribute("articleId");
	Article art = (Article)request.getAttribute("article");
	List<Tag> tList = (List<Tag>)request.getAttribute("tList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Change the world</title>
<link rel="stylesheet" type="text/css" href="./css/main.css">
<link rel="stylesheet" type="text/css" href="./css/editArticle.css">
<link rel="stylesheet" type="text/css" href="./css/font-awesome.min.css">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script>
	$(document).ready(function() {
		//login window handle
		$("#login-trigger").on("click", function(event) {
			event.preventDefault();
			$("#login-frame").addClass("login-visiable");
		});
		$("#login-frame").on("click", function(event) {
			if ($(event.target).is("#login-frame")) {
				event.preventDefault();
				$(this).removeClass("login-visiable");
			}
		});

		//search submit
		$("#search_button").click(function() {
			$(this).parent().submit();
		});
		$("#select-tag").on("change", function() {
			this.parentNode.nextSibling.value = this.options[this.value].text;
		});
		$("#submit-btn").click(function() {
			var title = $("#title-input").val();
			var tag = $("#tag").val();
			var content = CKEDITOR.instances.editor.getData();
			if(title.length > 0 && tag.length > 0 && content.length > 0) {
				$("#article-form").submit();
			} else {
				$(".warning").remove();
				$(this).after("<span class='warning' style='color: red; margin-left: 16px;'>请将文章内容补充完整!</span>");
			}
		});
	});
	var inter = null;
	function formSubmit() {
		$("#progress").show();
		inter = window.setInterval("callback();", 100);
		document.getElementById('dis').innerHTML = "初始化数据...";
		document.getElementById('p_in').style.width = "0%";
		$(".file-form").submit();
	}
	function callback() {
		$.ajax({
			url: "<%=basePath%>/HandleUploadStatusServlet.action",
			type : "POST",
			async : false,
			data : {},
			error : function(errorMsg) {
			},
			success : function(data) {
				document.getElementById('dis').innerHTML = '已上传：' + data;
				document.getElementById('p_in').style.width = data;
				if (data.indexOf("100%") != -1) {
					uploadSuccess();
				}
			}
		});
	}
	function uploadSuccess() {
		clearInterval(inter);
		document.getElementById('dis').innerHTML = "上传成功!";
		document.getElementById('p_in').style.width = "100%";
	}
</script>
</head>
<body>
	<div class="main">
		<div class="headband"></div>
		<div class="middle">
			<div class="left-menu">
				<div class="left-menu-head">
					<a href="#" class="left-menu-home"> <span class="home-title">Change
							the world</span>
					</a>
					<p class="head-title">Keep hungry keep foolish!</p>
				</div>
				<nav class="menu-nav">
					<ul id="menu" class="menu">
						<li class="menu-item menu-home"><a class="menu-button"
							href="index.html"> <span class="fa fa-home fa-fw"></span> 首页<br>
						</a></li>
						<li class="menu-item menu-about"><a class="menu-button"
							href="#"> <span class="fa fa-user fa-fw"></span> 关于<br>
						</a></li>
						<li class="menu-item menu-file"><a class="menu-button"
							href="GetAllTagNameServlet.action"> <span
								class="fa fa-archive fa-fw"></span> 标签<br>
						</a></li>
						<li class="menu-item menu-classification"><a
							class="menu-button" href="#"> <span class="fa fa-th fa-fw"></span>
								Links<br>
						</a></li>
					</ul>
				</nav>
				<div class="search">
					<form name="search-form" action="GetSearchServlet.action" method="get">
						<input type="text" name="searchText" class="search_text" placeholder="Search" />
						 <input type="hidden" name="isTagSearch" value=0 /> <input type="hidden" name="currentPage" value=1 /> 
						 <input type="button" name="search-button" class="search_button" id="search_button">
					</form>
				</div>
			</div>
			<div class="content-wrap">
			<span class="label">上传附件</span><br>
					<hr>
					<form action="<%=basePath%>/HandleUploadServlet.action" method="post" enctype="multipart/form-data" target="progress_iframe" name="form" class="file-form">
						<input class="label" type="file" name="file">
						<input type="button" onclick="formSubmit();" value="上传">
					</form>
					<iframe  id="progress_iframe" class="progress_iframe" name="progress_iframe" src="javascript:void(0)">
					</iframe>
					<div id="progress">
						<div id="p_out">
							<div id="p_in"></div>
						</div>
						<div id="dis"></div>
					</div>
				<form id="article-form" action="HandleEditArticleServlet.action"
					method="post" style="position: relative;">
					<input type="hidden" value="<%=id %>" id="article-id" name="article-id">
					<span class="label">文章标题</span><br>
					<hr>
					<input type="text" id="title-input" class="title-input"
						placeholder="Title" name="title" value="<%=art==null?"":art.getTitle()%>"><br> <span
						class="label">标签</span><br>
					<hr>
					<span style="margin-left: 126px; width: 18px; overflow: hedden;">
						<select class="select-tag" id="select-tag" style="width: 120px; margin-left: -110px">
						<option value="-1">--请选择--</option>
						<% for(int i = 0; i < tList.size(); i++) { %>
							<option value=<%=i+1 %>><%=tList.get(i).getTagName() %></option>
						<% } %>
					</select>
					</span><input type="text" name="tag" id="tag" style="width: 100px; position: absolute; left: 16px;" value = <%=art==null?"":art.getTag().getTagName() %>><br>
				
					<span class="label">文章内容</span><br>
					<hr>
					<textarea id="editor" class="editor" name="editor">
					<%=art==null?"":art.getContent() %>
					</textarea>
				</form>
				<button id="submit-btn" class="submit-btn">提交博文</button><br>
					
			</div>
		</div>
		<div class="footer">
			<div class="footer-inner">
				<div class="copyright">
					© 2015 - 2016 <i class="fa fa-heart"></i> x.m.Xin
				</div>
				<div class="powered-by">
					Copy from Hexo | Theme-Diy <a href="javascript:0;" id="login-trigger">Manage</a>
				</div>
				<div class="theme-info"></div>
			</div>
		</div>
	</div>
	<!-- login -->
	<div class="login-frame" id="login-frame">
		<div class="login-main">
			<h1>Login</h1>
			<form class="login-form" method="Post"
				action="HandleLoginServlet.action">
				<ul>
					<li class="login-li-text"><input type="text" name="username"
						placeholder="Username" /></li>
					<li class="login-li-text"><input type="password" name="password" placeholder="Password" /></li>
					<input type="hidden" name="referPage" value="index.jsp">
					<li class="login-li-submit"><input type="submit" value="Login" class="login-submit" /></li>
				</ul>
			</form>
		</div>
	</div>

	<!-- after login, show message -->
	<%
		if (request.getAttribute("loginMessage") != null) {
			out.print("<script>alert('" + request.getAttribute("loginMessage") + "')</script>");
		}
	%>

</body>
<script>
	CKEDITOR.replace("editor");
	CKEDITOR.config.height = 700;
</script>
</html>