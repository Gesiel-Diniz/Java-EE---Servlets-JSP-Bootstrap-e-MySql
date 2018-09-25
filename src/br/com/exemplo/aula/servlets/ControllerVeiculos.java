package br.com.exemplo.aula.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.exemplo.aula.logica.Logica;

@WebServlet("/execute")
public class ControllerVeiculos extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String control = request.getParameter("control");
		String view = request.getParameter("view");
		
		if(control != null && view != null){
			
			String nomeDaClasse = "br.com.exemplo.aula."+control+"."+view;

			try {
				
				Class classe = Class.forName(nomeDaClasse);
				
				Logica logica = (Logica) classe.newInstance();
				String pagina = logica.executa(request, response);
				
				request.getRequestDispatcher(pagina).forward(request, response);
				
				
			} catch (Exception e) {
				throw new ServletException("A lógica de negócios causou uma exceção!", e);
			}
			
		}else{
			
			throw new ServletException("Erro nos parâmetros!");
			
		}

	}

}