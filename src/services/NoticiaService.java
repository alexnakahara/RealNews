package services;

import java.util.ArrayList;

import dao.NoticiaDAO;
import models.Noticia;

public class NoticiaService {
	private NoticiaDAO dao = new NoticiaDAO();

	public boolean cadastrar(Noticia noticia) {
		if (noticia.getId() > 0 && noticia.getTitulo() != null && noticia.getDescricao() != null
				&& noticia.getTexto() != null) {
			NoticiaDAO dao = new NoticiaDAO();
			return dao.cadastrar(noticia) ? true : false;
		}
		return false;

	}

	public boolean delete(int id) {

		if (id >= 1) {
			NoticiaDAO dao = new NoticiaDAO();
			return dao.deleteNoticia(id);
		}

		return false;
	}

	public ArrayList<Noticia> listarNoticias() {
		NoticiaDAO dao = new NoticiaDAO();

		return dao.listNoticias() != null ? dao.listNoticias() : null;

	}

	public boolean alterar(Noticia n) {
		if (n.getId() <= 0)
			return false;

		NoticiaDAO dao = new NoticiaDAO();
		return dao.update(n);

	}

	public Noticia getById(int id) {
		
		if (id <= 0) return null;

		return this.dao.getNoticia(id);
	}
}
