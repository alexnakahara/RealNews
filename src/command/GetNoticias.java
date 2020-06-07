package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Noticia;
import services.NoticiaService;

public class GetNoticias implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		StringBuilder builder = new StringBuilder();
		PrintWriter out = response.getWriter();
		NoticiaService service = new NoticiaService();
		ArrayList<Noticia> lista = new ArrayList<Noticia>();
		lista = service.listarNoticias();
		response.setContentType("text/html; charset=UTF-8");
		
		if(!lista.isEmpty()) {
			
			for (Noticia i : lista) {
				builder.append("<div class='-content-noticias'>" +
						"<div class='d-flex justify-content-end font-weight-light'> Notícia Id: " + i.getId() + "</div>"+
						"<div class='body-noticia'>" +
						"<div class='title-noticia'>"+ i.getTitulo() + "</div>"+
						"<div class='descricao-noticia'>"+ i.getDescricao()+ "</div>"+
						"<div class='texto-noticia'>"+ i.getTexto() + "</div>"+
						"</div>" +
						"<div class='content-toggles'>"+
						"<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#exampleModal\" onclick='sendId("+ i.getId() + ")'>"
						+ "<i class=\"far fa-comment-alt\"></i> Comentar</button>"+
						"<div id='labelVerComentarios' onclick='seeComments(" + i.getId() + ")'> Ver Comentários</div>" +
						"</div>"+
						"<div class='container-comentario' id='verComentarios"+ i.getId() + "'> </div>"+
						"</div>"
						);
			    }
			
		} else {
			
			builder.append("<div class='no-data-content'>"
					+ "<div>Não possuí nenhuma notícia no momento!</div>"
					+ " <div>Cadastre clique <a class='no-data-clique' data-toggle=\"modal\" data-target=\"#modalNoticia\"> aqui </a> </div>"
					+ "</div>");
		}
		
		out.print(builder.toString());
		
	}

}
