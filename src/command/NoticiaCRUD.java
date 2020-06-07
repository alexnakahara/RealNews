package command;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Noticia;
import services.NoticiaService;

public class NoticiaCRUD implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id_noticia;
		String titulo, texto, descricao;
		Noticia noticia;
		
		request.setCharacterEncoding("UTF-8");
		StringBuilder builder = new StringBuilder();
		NoticiaService service = new NoticiaService();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");

		
		switch (request.getParameter("action")) {
			
		case "delete":
			
			id_noticia = Integer.parseInt(request.getParameter("id"));
			if (!service.delete(id_noticia)) {
				builder.append(
						"<center id='feedBackCRUD' style='background-color: red; color: white;'>Não foi possível deletar a notícia, tente novamente!</center>");

			} else {
				builder.append(
						"<center id='feedBackCRUD' style='background-color: green; color: white;'>Notícia deletada com sucesso!</center>");
			}
			
			break;
			
		case "alter":

			id_noticia = Integer.parseInt(request.getParameter("id_noticia"));
			Noticia old = service.getById(id_noticia);
			titulo = request.getParameter("titulo").isEmpty() ? old.getTitulo() : request.getParameter("titulo");
			descricao = request.getParameter("descricao").isEmpty() ? old.getDescricao()
					: request.getParameter("descricao");
			texto = request.getParameter("texto").isEmpty() ? old.getTexto() : request.getParameter("texto");

			noticia = new Noticia(id_noticia, titulo, descricao, texto);

			if (!service.alterar(noticia)) {
				builder.append(
						"<center id='feedBackCRUD' style='background-color: red; color: white;'>Não foi possível atualizar a notícia, tente novamente!</center>");
			} else {

				builder.append(
						"<center id='feedBackCRUD' style='background-color: green; color: white;'>Notícia atualizada com sucesso!</center>");
			}
			
			break;

		case "insert": // cadastro
			
			id_noticia = Integer.parseInt(request.getParameter("id_noticia"));
			titulo = request.getParameter("titulo");
			descricao = request.getParameter("descricao");
			texto = request.getParameter("texto");

			noticia = new Noticia(id_noticia, titulo, descricao, texto);

			if (!service.cadastrar(noticia)) {
				builder.append(
						"<center id='feedBackCRUD' style='background-color: red; color: white;'>Não foi possível cadastrar a notícia, tente novamente!</center>");

			} else {

				builder.append(
						"<center id='feedBackCRUD' style='background-color: green; color: white;'>Notícia cadastrada com sucesso!</center>");
			}

			break;
		}


		builder.append("<script> const feed = document.querySelector('#feedBackCRUD'); setTimeout(() => { \r\n"
				+ "    feed.remove();\r\n" + "}, 3500);  </script>");

		out.print(builder.toString());
		request.getRequestDispatcher("/").include(request, response);

	}

}
