package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Noticia;
import services.NoticiaService;

@WebServlet("/NoticiaServlet.do")
public class NoticiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NoticiaServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuilder builder = new StringBuilder();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		NoticiaService service = new NoticiaService();
		ArrayList<Noticia> lista = new ArrayList<Noticia>();
		lista = service.listarNoticias();
		
		if(lista!= null) {
			
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
			
		}else {
			
			builder.append("<center>Não possuí nenhuma nóticia no momento!</center>");
		}
		
		out.print(builder.toString());
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id_noticia"));
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String texto = request.getParameter("texto");

		NoticiaService service = new NoticiaService();
		Noticia noticia = new Noticia(id, titulo, descricao, texto);

		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		if (!service.cadastrar(noticia))
			out.print("<center style='background-color: red; color: white;'>Não foi possível cadastrar a notícia, tente novamente!</center>");
			

			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);

		

	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		NoticiaService service = new NoticiaService();
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		if (service.delete(id)) {
			out.print("Deletado com sucesso");

		} else {
			out.print("Deu ruim!");
		}
	}

}
