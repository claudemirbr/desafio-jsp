package com.br.atomicweb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.br.atomicweb.model.Usuario;
import com.br.atomicweb.repository.Usuarios;

@WebServlet("/autenticador.do")
public class AutenticadorController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sessao = req.getSession(false);

		if (sessao != null) {
			sessao.invalidate();
		}

		resp.sendRedirect("login.html");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String login = req.getParameter("login");
		String senha = req.getParameter("senha");

		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);

		Usuarios usuarios = new Usuarios();
		Usuario usuarioAutenticado = usuarios.autenticaUsuario(usuario);

		if (usuarioAutenticado != null) {
			HttpSession sessao = req.getSession();
			sessao.setAttribute("usuarioAutenticado", usuarioAutenticado);
			sessao.setMaxInactiveInterval(3600);
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
		} else {
			resp.getWriter().print("<script> window.alert('Não encontrado!'); location.href='login.html'");
		}
	}
}
