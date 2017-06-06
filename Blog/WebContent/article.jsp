<%@ page language="java" import="java.util.*,java.text.*, com.dragon.blog.entity.*" contentType="text/html;charset=utf-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Article Page</title>
		<link rel="stylesheet" type="text/css" href="./css/main.css">
        <link rel="stylesheet" type="text/css" href="./css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="./css/article.css">
        <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
        <script type="text/javascript" src="js/ajax.js"></script>
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
	        
	        function touristSubmit() {
	        	$.getJSON("HandleReCommentServlet.action" ,{touristName:$(".touristName").val(),email:$(".email").val(),review:$(".review").val()} ,function(data) {
	        		if(data.touristStatus == 1) {
                        alert(data.message);
                    } else {
                    	$("#comments").before("<div class='comment'>"+
					                        "<p class='comment-tourist'>"+data.touristName+"</p>"+
					                        "<p class='comment-content'>"+data.comment+"</p>"+
					                        "<p class='comment-date'>"+data.time+"</p>"+
					                  "</div>");
                    	$(".touristName").val("");
                    	$(".email").val("");
                    	$(".review").val("");
                    }
                    
                });
            }
        </script>
	</head>
	<body>
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
					      <span class="fa fa-home fa-fw"></span> 首页<br>
					   </a>
					</li>
					<li class="menu-item menu-about">
					   <a class="menu-button" href="#">
					      <span class="fa fa-user fa-fw"></span> 关于<br>
					   </a>
					</li>
					<li class="menu-item menu-file">
					   <a class="menu-button" href="GetAllTagNameServlet.action">
					      <span class="fa fa-archive fa-fw"></span> 标签<br>
					   </a>
					</li>
					<li class="menu-item menu-classification">
					   <a class="menu-button" href="#">
					      <span class="fa fa-th fa-fw"></span> Links<br>
					   </a>
					</li>
				</ul>
			</nav>
			<div class="search">
				<form name="search-form" action="GetSearchServlet.action" method="get">
					<input type="text" name="searchText" class="search_text" placeholder="Search" />
					<input type="hidden" name="isTagSearch" value=0 />
					<input type="hidden" name="currentPage" value=1 />
					<input type="button" name="search-button" class="search_button" id="search_button">
				 </form>
			</div>
		   </div>
		   <div class="content-wrap">
		   <%
		      Article article = null;
		      if((article = (Article)request.getAttribute("article")) != null) {
		    	  out.print("<div class='blog-title'>"+
		    			    "<h1>"+article.getTitle()+"</h1>"+
		    			    "</div>");
		    	  out.print("<div class='blog-date'>"+
		    			    "<p>date:"+article.getDatetime()+"|kind:"+article.getTag().getTagName()+"</p>"+
		    			    "</div>");
		    	  out.print("<div class='blog-content'>"+article.getContent()+"</div>");
		      }
		   %>
			   <div class="edit-comment">
	              <form id="tourist-form">
	                  <ul>
	                      <li>
	                          <div class="tourist-info">
		                          <ul>
	                                  <li>
	                                      <input type="text" class="touristName" placeholder="Nickname"/>
	                                      <p>*</p>
	                                  </li>
	                                  <li>
	                                      <input type="text" class="email" placeholder="Email"/>
	                                      <p>*</p>
	                                  </li>
	                              </ul>
	                          </div>
	                      </li>
	                      <li>
	                          <textarea class="review" name="comment" placeholder="Comment"></textarea>
	                      </li>
	                      <li>
	                          <input type="button" value="Publish" class="comment-submit" onclick="touristSubmit()"/>
	                      </li>
	                  </ul>
	              </form>
	           </div>
	           <div id="comments">
	           <%
	              List<Comment> comments = (List<Comment>) request.getAttribute("comments");
	              if(comments != null) {
	            	  for(Comment comment: comments) {
	            		  String date = comment.getDatetime();
		            	  out.print("<div class='comment'>"+
		                                 "<p class='comment-tourist'>"+comment.getUsername()+ (session.getAttribute("Login")==null?"":"<a class='del-comment' href='HandleDelCommentServlet.action?commentId=" + comment.getId() + "&referPage=index.html" + "'>del</a>") +"</p>"+
		                                 "<p class='comment-content'>"+comment.getContent()+"</p>"+
		                                 "<p class='comment-date'>"+date.substring(0, date.lastIndexOf('.'))+"</p>"+
		                            "</div>");
	            	  }
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
		<%
		  if(request.getAttribute("articleMessage") != null) {
			  out.print("<script>alert('" + request.getAttribute("articleMessage") + "')</script>");
		  }
		  if(request.getAttribute("touristMessage") != null) {
			  out.print("<script>alert('" + request.getAttribute("touristMessage") + "')</script>");
		  }
		  if(request.getAttribute("loginMessage") != null) {
		      out.print("<script>alert('" + request.getAttribute("loginMessage") + "')</script>");
		  }
		%>
	</body>
</html>