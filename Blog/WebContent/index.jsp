<%@ page language="java" import="java.util.*,java.text.*, com.dragon.blog.entity.*" contentType="text/html;charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Change the world</title>
	<link rel="stylesheet" type="text/css" href="./css/main.css">
	<link rel="stylesheet" type="text/css" href="./css/main-content.css">
	<link rel="stylesheet" type="text/css" href="./css/font-awesome.min.css">
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script>
		$(document).ready(function() {
			
			//login window handle
			$("#login-trigger").on("click", function(event) {
				event.preventDefault();
				$("#login-frame").addClass("login-visiable");
			});
			$("#login-frame").on("click", function(event) {
				if($(event.target).is("#login-frame")) {
					event.preventDefault();
					$(this).removeClass("login-visiable");
				}
			});
			
			//search submit
			$("#search_button").click(function() {
				$(this).parent().submit();
			});
			
		});
	</script>
</head>
<body>
<div class="main">
		<div class="headband"></div>
		<div class="middle">
			<div class="left-menu">
				<div class="left-menu-head">
					<a href="#" class="left-menu-home">
						<span class="home-title">
						<%
							if(request.getAttribute("systemInfo") != null) {
								out.print(((SystemInfo)request.getAttribute("systemInfo")).getBlogName());
							}
						%>
						</span>
					</a>
					<p class="head-title">Keep hungry keep foolish!</p>
				</div>
				<nav class="menu-nav">
					<ul id="menu" class="menu">
						<li class="menu-item menu-home">
							<a class="menu-button" href="index.html">
								<span class="fa fa-home fa-fw"></span>
								首页<br>
							</a>
						</li>
						<li class="menu-item menu-about">
							<a class="menu-button" href="#">
								<span class="fa fa-user fa-fw"></span>
								关于<br>
							</a>
						</li>
						<li class="menu-item menu-file">
							<a class="menu-button" href="GetAllTagNameServlet.action">
								<span class="fa fa-archive fa-fw"></span>
								标签<br>
							</a>
						</li>
						<li class="menu-item menu-classification">
							<a class="menu-button" href="#">
								<span class="fa fa-th fa-fw"></span>
								Links<br>
							</a>
						</li>
					</ul>
				</nav>
				<div class="search">
					<form name="search-form" action="GetSearchServlet.action" method="get">
						<input type="text" name="searchText" class="search_text" placeholder="Search"/>
						<input type="hidden" name="isTagSearch" value=0 />
						<input type="hidden" name="currentPage" value=1 />
						<input type="button" name="search-button" class="search_button" id="search_button"></a>
					</form>
				</div>
			</div>
			<div class="content-wrap">
			<% if(request.getSession().getAttribute("Login") != null) { %>
			<a class="add-btn" href="GetEditArticleServlet.action?articleId=-1">add</a>
			<% } %>
			<%
				if(request.getAttribute("articleList") != null) {
					for(Article item: (List<Article>)request.getAttribute("articleList")) {
			%>
				<p class="title"><a href="GetArticleContentServlet.action?articleId=<%= item.getId() %>"><%= item.getTitle() %></a></p>
				<p class="time">发表于<%= item.getDatetime() %> | 分类于<%= item.getTag().getTagName() %></p>
				<div class="content-wrap-content">
					<%= item.getSimpleContent() %>
					<a class="content-wrap-a" href="GetArticleContentServlet.action?articleId=<%= item.getId() %>">阅读全文</a></p>
				</div>
				<hr>
			<%
					}
				}
			%>
			</div>
		</div>
		<div class="footer">
			<div class="footer-inner">
				<div class="copyright">
					 ©  2015 - 2016
					 <i class="fa fa-heart"></i>
					 x.m.Xin 
				</div>
				<div class="powered-by">
					Copy from Hexo | Theme-Diy PageView: 
					<%
						if(request.getAttribute("systemInfo") != null) {
							out.print(((SystemInfo)request.getAttribute("systemInfo")).getPageView());
						}
					%>
					 <a href="javascript:0;" id="login-trigger">Manage</a>
				</div>
				<div class="theme-info"></div>
			</div>
		</div>
</div>
<!-- login -->
<div class="login-frame" id="login-frame">
	<div class="login-main">
    	<h1>Login</h1>
        <form class = "login-form" method="Post" action="HandleLoginServlet.action">
        	<ul>
            	<li class="login-li-text">
            		<input type="text" name="username" placeholder="Username" />
                </li>
            	<li class="login-li-text">
                	<input type="password" name="password" placeholder="Password" />
                </li>
                <input type="hidden" name="referPage" value="index.html" />
                <li class="login-li-submit">
                	<input type="submit" value="Login" class="login-submit" />
                </li>
        	</ul>
        </form>
	</div>
</div>

<!-- after login, show message -->
<%
	if(request.getAttribute("loginMessage") != null) {
		out.print("<script>alert('" + request.getAttribute("loginMessage") + "')</script>");
	}
%>

</body>
</html>
