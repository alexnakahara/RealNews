package services;

import java.util.ArrayList;

import dao.ComentarioDAO;
import models.Comentario;

public class ComentarioService {

	public ArrayList<Comentario> listarComentarios(int id_noticia) {
		if (id_noticia <= 0)
			return null;

		ComentarioDAO dao = new ComentarioDAO();

		return dao.listComentarios(id_noticia) != null ? dao.listComentarios(id_noticia) : null;
	}

	public boolean cadastrarComentario(Comentario c) {

		if (c.getId() >= 1 && c.getIdNoticia() >= 1 && 
				c.getNome() != null && c.getTexto() != null) {

			ComentarioDAO dao = new ComentarioDAO();
			return dao.cadastrar(c);

		} else
			return false;
	}

}
