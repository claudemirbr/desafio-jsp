package com.br.atomicweb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.atomicweb.model.Usuario;
import com.br.atomicweb.repository.Usuarios;

@WebServlet("/usuario.do")
public class UsuarioController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		Usuarios usuarios = new Usuarios();
		String acao = "";
		if (req.getParameter("acao") == null ) {
			try {
				List<Usuario> lista = usuarios.getUsuarios();
				req.setAttribute("lista", lista);
				RequestDispatcher dispacher = req.getRequestDispatcher("WEB-INF/usuariolist.jsp");
				dispacher.forward(req, resp);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			acao = req.getParameter("acao");

			if (acao.equals("novo")) {
				usuario.setCodigo(Long.valueOf("0"));
				usuario.setNome("");
				usuario.setLogin("");
				usuario.setSenha("");
				req.setAttribute("usuario", usuario);
				RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/usuario.jsp");
				dispatcher.forward(req, resp);
			} else if (acao.equals("editar")) {
				usuario.setCodigo(Long.valueOf(req.getParameter("codigo")));
				usuario = usuarios.getUsuario(usuario);
				req.setAttribute("usuario", usuario);
				RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/usuario.jsp");
				dispatcher.forward(req, resp);
			} else if (acao.equals("excluir")) {
				if (req.getParameter("id") != null) {
					usuario.setCodigo(Long.valueOf(req.getParameter("id")));
				}
				usuarios.deletaUsuario(usuario);
				resp.sendRedirect("usuario.do");
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String codigo = req.getParameter("codigo");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");

		Usuario usuario = new Usuario();
		Usuarios usuarios = new Usuarios();

		if (codigo == null && codigo.equals(0)) {
			usuario.setNome(nome);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuarios.salvaUsuario(usuario);
		} else {
			usuario.setCodigo(Long.valueOf(codigo));
			usuario.setNome(nome);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuarios.editaUsuario(usuario);
		}
		resp.sendRedirect("usuario.do");
	}

}
