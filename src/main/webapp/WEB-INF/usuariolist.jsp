<%@page import="com.br.atomicweb.model.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Usuário</title>
<script type="text/javascript">
	function confirmaExclusao(id) {
		if (window.confirm("Tem certeza que deseja excluir?")) {
			location.href = "usuario.do?acao=excluir&codigo=" + id;
		}
	}
</script>
</head>
<body>

<%@ include file="menu.jsp" %>
	<%
		List<Usuario> lista = (List<Usuario>) request.getAttribute("lista");
	%>
	<a href="usuario.do?acao=novo">Novo</a>
	<table border="1">

		<tr>
			<th>Código</th>
			<th>Nome</th>
			<th>Login</th>
			<th>Ação</th>
		</tr>
		<%
			for (Usuario u : lista) {
		%>
		<tr>
			<td><%=u.getCodigo()%></td>
			<td><%=u.getNome()%></td>
			<td><%=u.getLogin()%></td>
			<td><a href="usuario.do?acao=editar&codigo=<%=u.getCodigo()%>">Editar</a>
				| <a href="javascript:confirmaExclusao(<%=u.getCodigo()%>)">Excluir</a>
			</td>
		</tr>

		<%
			}
		%>
	</table>
</body>
</html>