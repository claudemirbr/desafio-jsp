package com.br.atomicweb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		super.doPost(req, resp);
		
		
		HttpSession sessao = req.getSession();
		//sessao.setAttribute(name, value);
		sessao.setMaxInactiveInterval(3600);
		req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
		
		resp.getWriter().print("<script> window.alert('Não encontrado!'); location.href='login.html'");
		
	}

}
