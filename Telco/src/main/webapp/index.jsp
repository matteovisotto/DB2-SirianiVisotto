<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="text" name="j_username"/>
    <input type="password" name="j_password" />
    <input type="submit" value="Login"/>
</form>
</body>
</html>