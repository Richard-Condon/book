<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>尚硅谷会员注册页面</title>
		<%--静态包含 base标签 css样式 jQuery样式--%>
	<%@include file="/pages/common/head.jsp"%>


    <script type="text/javascript">
      //页面加载完成后
      $(function (){
		  $("#username").blur(function (){
			  var  username=this.value;
			  $.getJSON("us","action=ajaxExistsUsername&username="+username,function (data){
				  if (data.existsUsername){
					  $(".errorMsg").text("用户名重复不可用");
				  }else {
					  $(".errorMsg").text("用户名可用");
				  }
			  });
		  });
		  //给验证码的图片绑定单击刷新事件
		  $("#code_img").click(function (){
			  //this为当前在响应事件对应的标签 即 <img>
			  //?d="+new Date() 的作用是让每次请求的参数都不一样，从而跳过缓存，达到多次刷新的目的
			  //如果每次请求参数都一样，浏览器会自动读取缓存用来替代。
			 this.src="${basePath}/kaptcha.jpg?d="+new Date();

		  });



        //给注册绑定单击事件
        $("#sub_btn").click(function (){
          //验证账户名是否合法
          var usernametext = $("#username").val();
          //创建正则表达式对象
          var usernamePatt=/^\w{5,12}$/;

          if (!usernamePatt.test(usernametext)){
            $(".errorMsg").text("用户信息不合法");
            //不合法禁止跳转
            return false;
          }


          //验证账户密码是否合法
          var passwordtext = $("#password").val();
          //创建正则表达式对象
          var passwordPatt=/^\w{5,12}$/;

          if (!passwordPatt.test(passwordtext)){
            $(".errorMsg").text("密码不合法");
            //不合法禁止跳转
            return false;
          }

          //验证确认密码是否与密码相同
          var repwd=$("#repwd").val();
          if (repwd!=passwordtext){
            $(".errorMsg").text("两次输入的密码不相同");
            return  false;
          }



          //验证邮箱
          var email = $("#email").val();
          var emailPatt=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
          if (!emailPatt.test(email)){
            $(".errorMsg").text("邮箱不符合格式");
            return false;
          }

          //验证码
          var codetext = $("#code").val();
          //去掉前后空格
          codetext= $.trim(codetext);
          if (codetext==""||code==" "){
            $(".errorMsg").text("请输入验证码");
            return false;
          }
		 });
	  });


    </script>

	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
	</head>

	<body>
	<table>
		<tr>
			<td>
				<div id="login_header">
					<img class="logo_img" alt="" src="pages/static/img/logo.gif" >
				</div></td>
			<td>
				<a href="pages/user/login.jsp">账号密码登录</a>
			</td>
		</tr>
	</table>



			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>

								<span class="errorMsg" ID="erro">
									<%--<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="us" method="post">
									<input type="hidden" name="action" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username" value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" value="${requestScope.password}"/>
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" value="<${requestScope.password}" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email" value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 130px;" id="code" name="code"/>
									<%--生成验证码的步骤  1.导入kaptcha的JAR包   2.去webx.xml文件中配置用于生产验证码的文件（已经写好只需要配置路径就可）
									3.在表单中使用img标签去显示验证码并使用它（如下）   4.在服务器获取谷歌生成的验证码和客户端发过来的验证码比较使用--%>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px;width: 100px;height: 38px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/foot.jsp"%>
	</body>
</html>
