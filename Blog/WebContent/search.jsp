<%@ page language="java" import="java.util.*,java.text.*, com.dragon.blog.entity.*" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Change the world</title>
	<link rel="stylesheet" type="text/css" href="./css/search.css">
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
						<span class="home-title">Change the world</span>
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
				<!-- search result -->
				<p class="content-wrap-title">Search Result</p>
				<ul>
					
					<%
						if(request.getAttribute("errorMessage") == null) {
							for(Article item: (List<Article>)request.getAttribute("articleList")) {
					%>
								<li>
									<a href="GetArticleContentServlet.action?articleId=<%= item.getId() %>" title=<%= item.getTitle() %>><%= item.getTitle() %></a>
									<span class="information_time">
									<% if(request.getSession().getAttribute("Login") != null) { %>
									<a class="edit-btn" href="GetEditArticleServlet.action?articleId=<%=item.getId() %>">Edit</a>
									<a class="delete-btn" href="HandleDeleteArticleServlet.action?articleId=<%= item.getId() %>&referPage=<%=basePath %>">Del</a>
									<% } %>
									【<%= item.getDatetime() %>】</span>
								</li>
					<%	
							}
						}
					
						//show errorMessage
						else {
							out.println("<script>alert('" + request.getAttribute("errorMessage") + "')</script>");
						}
					
					%>
				</ul>
				<div class="content-wrap-pageNumber">
				<%
				if(request.getAttribute("errorMessage") == null) {
					int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
					String isTagSearch = request.getAttribute("isTagSearch").toString();
					String searchText = request.getAttribute("searchText").toString();
					int totalPage = (Integer.parseInt(request.getAttribute("totalCount").toString()) - 1) / 10 + 1;
				%>

					当前页: [<%= currentPage %>/<%= totalPage %>]
					<%
						if(currentPage > 1) {
					%>
						<a href="GetSearchServlet.action?
						searchText=<%= searchText %>
						&isTagSearch=<%= isTagSearch %>
						&currentPage=1">首页</a>
						<a href="GetSearchServlet.action?
						searchText=<%= searchText %>
						&isTagSearch=<%= isTagSearch %>
						&currentPage=<%= currentPage - 1 %>">上一页</a>
					<%
						}
						if(currentPage < totalPage) {
					%>
						<a href="GetSearchServlet.action?
						searchText=<%= searchText %>
						&isTagSearch=<%= isTagSearch %>
						&currentPage=<%= currentPage + 1 %>">下一页</a>
						<a href="GetSearchServlet.action?
						searchText=<%= searchText %>
						&isTagSearch=<%= isTagSearch %>
						&currentPage=<%= totalPage %>">尾页</a>
					<%
						}
					%>
				<%
				}

				%>

				</div>
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
					Copy from Hexo | Theme-Diy
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
                <input type="hidden" name="referPage" value="index.jsp" />
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
