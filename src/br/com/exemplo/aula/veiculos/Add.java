package br.com.exemplo.aula.veiculos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.exemplo.aula.daos.VeiculosDAO;
import br.com.exemplo.aula.entidades.Veiculos;
import br.com.exemplo.aula.logica.Logica;

public class Add implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {

		VeiculosDAO veiculosDAO = new VeiculosDAO();
		Veiculos veiculos = new Veiculos();

		veiculos.setIdModelo(Long.parseLong(request.getParameter("id_modelo")));
		veiculos.setPlaca(request.getParameter("placa"));
		veiculos.setAnoFabricacao(request.getParameter("ano_fabricacao"));
		veiculos.setTipo(request.getParameter("tipo"));

		if (request.getParameter("id") != null) {

			veiculos.setId(Long.parseLong(request.getParameter("id")));
			// veiculosDAO.update(veiculos);

		} else {

			veiculosDAO.insert(veiculos);

		}


		request.setAttribute("veic", veiculosDAO.listaVeiculos());
		
		return "/execute?control=veiculos&view=Lista";

	}

}
