package command;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Comentario;
import services.ComentarioService;

public class ComentarioCRUD implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		ComentarioService service = new ComentarioService();
		StringBuilder builder = new StringBuilder();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");

		switch (request.getParameter("action")) {
		case "cadastrar":
			int id_comentario = Integer.parseInt(request.getParameter("id_comentario"));
			int id_noticia = Integer.parseInt(request.getParameter("id_noticia"));
			String nome = request.getParameter("nome");
			String texto = request.getParameter("texto");

			Comentario c = new Comentario(id_comentario, nome, texto, id_noticia);

			if (service.cadastrarComentario(c)) {

				builder.append(
						"<center id='feedBackCRUD' style='background-color: green; color: white;'>O comentário foi postado com sucesso!</center>");
			} else {

				builder.append(
						"<center id='feedBackCRUD' style='background-color: red; color: white;'>Não foi possível comentar, tente novamente!</center>");
			}

			break;

		case "delete":

			int idComentario = Integer.parseInt(request.getParameter("id"));

			if (!service.delete(idComentario)) {

				builder.append(
						"<center id='feedBackCRUD' style='background-color: red; color: white;'>Não foi possível deletar o comentário, tente novamente!</center>");
			} else {

				builder.append(
						"<center id='feedBackCRUD' style='background-color: green; color: white;'>O comentário foi deletado com sucesso!</center>");
			}

			break;
		}

		builder.append("<script> const feed = document.querySelector('#feedBackCRUD'); setTimeout(() => { \r\n"
				+ "    feed.remove();\r\n" + "}, 3500);  </script>");

		out.print(builder.toString());
		request.getRequestDispatcher("/").include(request, response);

	}

}
