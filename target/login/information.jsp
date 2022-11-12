<%@ page import="com.cheng.util.Constants" %><%--
  个人信息页
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body aglin="center">
用户名:<%=request.getSession().getAttribute(Constants.USER_NAME)%><br/>
生日:<%=request.getSession().getAttribute(Constants.USER_BIRTHDAY)%><br/>
电话:<%=request.getSession().getAttribute(Constants.USER_PHONE)%><br/>
<button><a href="update.jsp">修改</a></button>


</body>
</html>
