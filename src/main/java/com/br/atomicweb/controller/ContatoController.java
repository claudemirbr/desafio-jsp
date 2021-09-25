package com.br.atomicweb.controller;

import java.io.IOException;

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
		
		Contato contato = new Contato();
		Contatos contatos = new Contatos();
		
		contato.setNome("Claudemir");
		contato.setEmail("claudemir@bsd.com.br");
		contatos.salvaContato(contato);
		
		System.out.println("Chamou");
		
		
	}

}
