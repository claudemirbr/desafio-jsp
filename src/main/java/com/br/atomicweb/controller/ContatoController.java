package com.br.atomicweb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.atomicweb.model.Contato;
import com.br.atomicweb.repository.Contatos;

@SuppressWarnings("serial")
@WebServlet("/contato.do")
public class ContatoController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Contato contato = new Contato();
		Contatos contatos = new Contatos();
		String acao = "";
		if (req.getParameter("acao") == null ) {
			try {
				List<Contato> lista = contatos.getContatos();
				req.setAttribute("lista", lista);
				RequestDispatcher dispacher = req.getRequestDispatcher("WEB-INF/contatolist.jsp");
				dispacher.forward(req, resp);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			acao = req.getParameter("acao");

			if (acao.equals("novo")) {
				contato.setId(Long.valueOf("0"));
				contato.setNome("");
				contato.setEmail("");
				req.setAttribute("contato", contato);
				RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/contato.jsp");
				dispatcher.forward(req, resp);
			} else if (acao.equals("editar")) {
				contato.setId(Long.valueOf(req.getParameter("id")));
				contato = contatos.getContato(contato);
				req.setAttribute("contato", contato);
				RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/contato.jsp");
				dispatcher.forward(req, resp);
			} else if (acao.equals("excluir")) {
				if (req.getParameter("id") != null) {
					contato.setId(Long.valueOf(req.getParameter("id")));
				}
				contatos.deletaContato(contato);
				resp.sendRedirect("contato.do");
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");

		Contato contato = new Contato();
		Contatos contatos = new Contatos();

		if (id == null && id.equals(0)) {
			contato.setNome(nome);
			contato.setEmail(email);
			contatos.salvaContato(contato);
		} else {
			contato.setId(Long.valueOf(id));
			contato.setNome(nome);
			contato.setEmail(email);
			contatos.editaContato(contato);
		}
		resp.sendRedirect("contato.do");
	}
}
