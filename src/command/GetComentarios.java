package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Comentario;
import services.ComentarioService;

public class GetComentarios implements Command{
	private ComentarioService service = new ComentarioService();
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int id_noticia = Integer.parseInt(request.getParameter("id_noticia"));
		StringBuilder builder = new StringBuilder();
		PrintWriter out = response.getWriter();
		ArrayList<Comentario> lista = new ArrayList<Comentario>(); 
		lista = this.service.listarComentarios(id_noticia);
		response.setContentType("text/html; charset=UTF-8");

		if(!lista.isEmpty()) {
			
			for (Comentario i : lista) {
				builder.append("<div class='-container-user'>" +
						 "<div class='icon-user'><i class=\"fas fa-user\"></i></div>" +
						 "<div class='-comentario'>" +
						 "<div class='font-weight-bold ml-1'>"+ i.getNome() + "<span class='-spanIdComment'>(Id: " + i.getId() + ")</span></div>"+ 
						 "<div class='-comentario__text'>\"" + i.getTexto()+ "\"</div>"+ 
						 "</div>" +
						 "</div>"
						);
			}
			
		} else {
			builder.append("<div class='empty-comment'>Está notícia não possuí nenhum comentário no momento!</div>");
		}
		
		out.print(builder.toString());
		
	}


	
}
