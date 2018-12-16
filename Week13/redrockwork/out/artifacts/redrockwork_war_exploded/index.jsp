<%--
  Created by IntelliJ IDEA.
  User: Brandon
  Date: 2018/12/9
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>注册以及登录以及课表查询</title>
  </head>
  <body>
  <table width = "200" border ="1" bordercolor = "#00F">
    <tr>
      <td><input type = "button" value = "登      陆" onclick = "window.open('login.jsp')"></td>
      <td><input type = "button" value = "注      册" onclick = "window.open('signup.jsp')"></td>
    </tr>
    <tr>
      <td>
        <form method="Post" action="schedule.do">
          <input type="text" name="stuId" value="请输入查询的学号">
          <input type="submit" value="提 交">
        </form>
      </td>
    </tr>
  </table>
  </body>
</html>
