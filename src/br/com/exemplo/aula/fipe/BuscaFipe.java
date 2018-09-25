package br.com.exemplo.aula.fipe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import br.com.exemplo.aula.daos.FipeDAO;
import br.com.exemplo.aula.entidades.AnoModelos;
import br.com.exemplo.aula.entidades.Modelos;

@WebServlet("/json-fipe")
public class BuscaFipe extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	FipeDAO fipeDAO = new FipeDAO();
	String json;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("id_marca") != null) {

			List<Modelos> modelos = new ArrayList<Modelos>();
			modelos = fipeDAO.buscaModelo(Long.parseLong(request.getParameter("id_marca")));
			Gson gson = new Gson();
			 json = gson.toJson(modelos);

		}else{
			
			if (request.getParameter("id_modelo") != null) {

				List<AnoModelos> anoModelos = new ArrayList<AnoModelos>();
				anoModelos = fipeDAO.buscaAnoModelos(request.getParameter("id_modelo"));
				Gson gson = new Gson();
				json = gson.toJson(anoModelos);
				
			}
			
		}
		
		PrintWriter out = response.getWriter();
		out.println(json);

	}

}
