<%--
  Created by IntelliJ IDEA.
  User: Brandon
  Date: 2018/12/10
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<form method="get" action = "Login.do" >
    <table width="300" height = "180" border="5" bordercolor="#A0A0A0">
        <tr>
            <th>账  户：</th>
            <td><input type="text" name="name"  value = "请输入用户名" maxlength = "16" onfocus = "if(this.value == '请输入用户名') this.value =''"></td>
        </tr>
        <tr>
            <th>密  码：</th>
            <td><input type="password" name="pwd" maxlength = "20"></td>
        </tr>
        <tr>
            <td colspan = "2" align = "center">
                <input type="submit" name="submit" value="登       录">
                <input type="button" value="返       回"
                       onclick="window.location.href('/index')">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
