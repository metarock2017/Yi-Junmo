<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.redrock.SendGet.TokenThread" %>
<html>
  <body>
    access_token is :<%=TokenThread.accessToken.getAccessToken()%>
  </body>
</html>