<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h3> Students Information </h3>
<button onclick="myFunction()">Click me</button>
<table>
<tr> 
<% List<String> tableheader = (ArrayList<String>)request.getAttribute("TableHeader");%>
for(String s : tableheader){
	out.print("the header us : " + s);
    out.print("<br/>");
    <td><%=s %></td>
}


</tr>
</table>

</body>
</html>