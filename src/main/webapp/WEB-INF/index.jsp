<%@page import="com.br.atomicweb.model.Usuario"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Desafio JSP</title>
</head>
<body>

<%@ include file="menu.jsp" %> 

	Bem vindo <% Usuario usuario = (Usuario)request.getSession().getAttribute("usuarioAutenticado");
		out.print(usuario.getNome());
	%>

	
	
</body>
</html>