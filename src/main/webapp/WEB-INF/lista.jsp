<%@page import="com.br.atomicweb.model.Contato"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contatos</title>
</head>
<body>
	<%
		List<Contato> lista = (List<Contato>) request.getAttribute("lista");
	%>

	<table border="1">

		<tr>
			<th>Código</th>
			<th>Nome</th>
			<th>Email</th>
		</tr>
		<%
			for (Contato c : lista) {
		%>
		<tr>
			<td><%=c.getId()%></td>
			<td><%=c.getNome()%></td>
			<td><%=c.getEmail()%></td>
		</tr>

		<%
			}
		%>
	</table>
</body>
</html>