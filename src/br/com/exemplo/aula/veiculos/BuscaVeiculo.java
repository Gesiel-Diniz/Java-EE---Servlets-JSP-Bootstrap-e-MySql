package br.com.exemplo.aula.veiculos;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import br.com.exemplo.aula.daos.VeiculosDAO;
import br.com.exemplo.aula.entidades.Veiculos;

@WebServlet("/json-veiculo")
public class BuscaVeiculo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	VeiculosDAO veiculosDAO = new VeiculosDAO();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("id") != null) {

			Veiculos veiculo = new Veiculos();
			veiculo = veiculosDAO.buscaVeiculo(Long.parseLong(request.getParameter("id")));

			Gson gson = new Gson();
			String json = gson.toJson(veiculo);

			PrintWriter out = response.getWriter();
			out.println(json);

		}

	}

}