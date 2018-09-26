package br.com.exemplo.aula.veiculos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.exemplo.aula.daos.FipeDAO;
import br.com.exemplo.aula.daos.VeiculosDAO;
import br.com.exemplo.aula.logica.Logica;

public class Lista implements Logica {
	
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		VeiculosDAO veiculosDAO = new VeiculosDAO();
		req.setAttribute("veic", veiculosDAO.listaVeiculos());
		
		FipeDAO fipeDAO = new FipeDAO();
		req.setAttribute("marcas", fipeDAO.listaMarcas());
		
		return "veiculos.jsp";
		
	}
	
}
