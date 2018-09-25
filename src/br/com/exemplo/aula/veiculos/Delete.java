package br.com.exemplo.aula.veiculos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.exemplo.aula.daos.VeiculosDAO;
import br.com.exemplo.aula.entidades.Veiculos;
import br.com.exemplo.aula.logica.Logica;

public class Delete implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {

		VeiculosDAO veiculosDAO = new VeiculosDAO();
		Veiculos veiculo = new Veiculos();
		
		veiculo.setId(Long.parseLong(request.getParameter("id")));
		
		veiculosDAO.delete(veiculo);
		
		request.setAttribute("veic", veiculosDAO.listaVeiculos());
		return "/execute?control=veiculos&view=Lista";
		
	}

}
