<%@page import="com.br.atomicweb.model.Contato"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contatos</title>
</head>
<body>
	<%Contato contato = (Contato) request.getAttribute("contato"); %>
	<form action="contatocontroller.do" method="post">
		<input type="hidden", name="id" value="<%=contato.getId()%>"/>
		Nome:<input type="text" name="nome" value="<%=contato.getNome()%>"/>
		Email:<input type="text" name="email" value="<%=contato.getEmail()%>"/>
		<input type="submit" value="Salvar" />
	</form>

</body>
</html>