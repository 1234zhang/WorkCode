<%--
  Created by IntelliJ IDEA.
  User: Brandon
  Date: 2018/12/16
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>课表</title>
</head>
<body>
    <%
        String myschedule = (String) request.getAttribute("schedule");
        response.getWriter().print(myschedule);
    %>
</body>
</html>
