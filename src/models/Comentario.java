package models;

public class Comentario {
	private int id;
	private int id_noticia;
	private String nome;
	private String texto;
	
	public Comentario(int id, String nome, String texto, int id_noticia) {
		this.id = id;
		this.nome = nome;
		this.texto = texto;
		this.id_noticia = id_noticia;
	}
	public Comentario() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getIdNoticia() {
		return id_noticia;
	}

	public void setIdNoticia(int id_noticia) {
		this.id_noticia = id_noticia;
	}

}



