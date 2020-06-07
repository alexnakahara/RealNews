package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.Command;

@WebServlet("/Controller.do")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Enviando a requisi��o para o doExecute:
		doExecute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Enviando a requisi��o para o doExecute:
		doExecute(request, response);
	}
	
	/**
	 * M�todo respons�vel pro tratar todas as requisi��es
	 * que chegarem dentro da aplica��o:
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doExecute(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		try {
			Command comando = 
					/*Procurando a classe dentro do pacote command
					 *Classe que representa a a��o que o usu�rio est� executando*/
					(Command)Class.forName(
							"command."+request.getParameter("command")).newInstance();
						
			comando.executar(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}