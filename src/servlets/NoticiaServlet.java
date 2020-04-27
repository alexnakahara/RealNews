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
			
		}else {
			
			builder.append("<div class='no-data-content'>"
					+ "<div>Não possuí nenhuma notícia no momento!</div>"
					+ " <div>Cadastre clique <a class='no-data-clique' data-toggle=\"modal\" data-target=\"#modalNoticia\"> aqui </a> </div>"
					+ "</div>");
		}
		
		out.print(builder.toString());
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		StringBuilder builder = new StringBuilder();
		NoticiaService service = new NoticiaService();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		if ("delete".equals(request.getParameter("action"))) {
			
			int id = Integer.parseInt(request.getParameter("id"));//pode ser que esse cara seja um comentario tb
			if(!service.delete(id)) {
				builder.append("<center style='background-color: red; color: white;'>Não foi possível deletar a notícia, tente novamente!</center>");
				
			} else {
				builder.append("<center style='background-color: green; color: white;'>Notícia deletada com sucesso!</center>");
			}
			
		} else if("alter".equals(request.getParameter("action"))){
			
			int id = Integer.parseInt(request.getParameter("id_noticia"));
			String titulo = request.getParameter("titulo");
			String descricao = request.getParameter("descricao");
			String texto = request.getParameter("texto");
	
			Noticia noticia = new Noticia(id, titulo, descricao, texto);
			
			if (!service.alterar(noticia)) {
				builder.append("<center style='background-color: red; color: white;'>Não foi possível atualizar a notícia, tente novamente!</center>");
				
			} else {
				
				builder.append("<center style='background-color: green; color: white;'>Notícia atualizada com sucesso!</center>");
			}
			
		}else {
		
			int id = Integer.parseInt(request.getParameter("id_noticia"));
			String titulo = request.getParameter("titulo");
			String descricao = request.getParameter("descricao");
			String texto = request.getParameter("texto");
	
			Noticia noticia = new Noticia(id, titulo, descricao, texto);
	
			
			if (!service.cadastrar(noticia)) {
				builder.append("<center style='background-color: red; color: white;'>Não foi possível cadastrar a notícia, tente novamente!</center>");
				
			} else {
				
				builder.append("<center style='background-color: green; color: white;'>Notícia cadastrada com sucesso!</center>");
			}
		}
		
		out.print(builder.toString());
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		rd.include(request, response);

	}


}
