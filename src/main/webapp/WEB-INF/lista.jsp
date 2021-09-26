<%@page import="com.br.atomicweb.model.Contato"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contatos</title>
<script type="text/javascript">
	function confirmaExclusao(id) {
		if (window.confirm("Tem certeza que deseja excluir?")) {
			location.href = "contatocontroller.do?acao=excluir&id=" + id;
		}
	}
</script>
</head>
<body>
	<%
		List<Contato> lista = (List<Contato>) request.getAttribute("lista");
	%>
	<a href="contatocontroller.do?acao=novo">Novo</a>
	<table border="1">

		<tr>
			<th>Código</th>
			<th>Nome</th>
			<th>Email</th>
			<th>Ação</th>
		</tr>
		<%
			for (Contato c : lista) {
		%>
		<tr>
			<td><%=c.getId()%></td>
			<td><%=c.getNome()%></td>
			<td><%=c.getEmail()%></td>
			<td> <a href="contatocontroller.do?acao=editar&id=<%=c.getId()%>">Editar</a> |
				<a href="javascript:confirmaExclusao(<%=c.getId()%>)">Excluir</a>
			</td>
		</tr>

		<%
			}
		%>
	</table>
</body>
</html>