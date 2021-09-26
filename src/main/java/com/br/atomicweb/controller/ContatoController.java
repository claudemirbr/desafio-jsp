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
@WebServlet("/contatocontroller.do")
public class ContatoController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String acao = req.getParameter("acao");
		
		if (acao.equals("lis")) {
			Contatos contatos = new Contatos();
			try {
				List<Contato> lista = contatos.getContatos();
				req.setAttribute("lista", lista);
				RequestDispatcher dispacher = req.getRequestDispatcher("WEB-INF/lista.jsp");
				dispacher.forward(req, resp);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		
		Contato contato = new Contato();
		
		contato.setNome(nome);
		contato.setEmail(email);
		
		Contatos contatos = new Contatos();
		contatos.salvaContato(contato);
		
		resp.getWriter().print("Sucesso!");
		
	}

}
