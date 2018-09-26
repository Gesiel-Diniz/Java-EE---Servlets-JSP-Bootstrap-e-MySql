package br.com.exemplo.aula.veiculos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.exemplo.aula.daos.VeiculosDAO;
import br.com.exemplo.aula.entidades.AnoModelos;
import br.com.exemplo.aula.entidades.Marcas;
import br.com.exemplo.aula.entidades.Modelos;
import br.com.exemplo.aula.entidades.Veiculos;
import br.com.exemplo.aula.logica.Logica;

public class Add implements Logica {
	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		boolean sucesso;

		VeiculosDAO veiculosDAO = new VeiculosDAO();
		Veiculos veiculos = new Veiculos();
		
		Marcas marca = new Marcas();
		marca.setId(Long.parseLong(request.getParameter("id_marca")));
		
		Modelos modelo = new Modelos();
		modelo.setId(request.getParameter("id_modelo"));
		
		AnoModelos anoModelo = new AnoModelos();
		anoModelo.setId(Long.parseLong(request.getParameter("id_ano_modelo")));

		veiculos.setIdMarca(marca);
		veiculos.setIdModelo(modelo);
		veiculos.setIdAnoModelo(anoModelo);
		veiculos.setPlaca(request.getParameter("placa"));
		veiculos.setAnoFabricacao(request.getParameter("ano_fabricacao"));
		veiculos.setTipo(request.getParameter("tipo"));

		if (request.getParameter("id") != "") {
			veiculos.setId(Long.parseLong(request.getParameter("id")));
			sucesso = veiculosDAO.edita(veiculos);
		} else {
			sucesso = veiculosDAO.insert(veiculos);
		}

		request.setAttribute("veic", veiculosDAO.listaVeiculos());
		request.setAttribute("sucesso", sucesso);
		
		return "/execute?control=veiculos&view=Lista";

	}

}
