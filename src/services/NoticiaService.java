package services;

import java.util.ArrayList;

import dao.NoticiaDAO;
import models.Noticia;

public class NoticiaService {

	public boolean cadastrar(Noticia noticia) {
		if (noticia.getId() > 0 && noticia.getTitulo() != null && noticia.getDescricao() != null
				&& noticia.getTexto() != null) {
			NoticiaDAO dao = new NoticiaDAO();
			return dao.cadastrar(noticia) ? true : false;
		}
		return false;

	}
	
	public boolean delete(int id) {
		
		if(id >= 1) {
			NoticiaDAO dao = new NoticiaDAO();
			return dao.delete(id) ? true : false;
		}
		
		return false;
	}
	
	public ArrayList<Noticia>listarNoticias(){
		NoticiaDAO dao = new NoticiaDAO();
		
		return dao.listNoticias() != null ? dao.listNoticias(): null;
		
	}
}
