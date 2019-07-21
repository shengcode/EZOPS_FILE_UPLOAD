<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<button onclick="myFunction()">Click me</button>

<script>
function myFunction() {
	var table = document.createElement('table');
	for (var i = 1; i < 4; i++){
	    var tr = document.createElement('tr');   

	    var td1 = document.createElement('td');
	    var td2 = document.createElement('td');

	    var text1 = document.createTextNode('Text1');
	    var text2 = document.createTextNode('Text2');

	    td1.appendChild(text1);
	    td2.appendChild(text2);
	    tr.appendChild(td1);
	    tr.appendChild(td2);

	    table.appendChild(tr);
	}
	document.body.appendChild(table);
}
</script>
</body>
</html>