package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Noticia;

public class NoticiaDAO {
	private Connection conexao;

	public NoticiaDAO() {
		this.conexao = ConnectionFactory.conectar();
	}

	public ArrayList<Noticia> listNoticias() {

		String query = "SELECT * FROM noticia ORDER BY id DESC";

		try (PreparedStatement pst = conexao.prepareStatement(query)) {

			ResultSet resultado = pst.executeQuery();

			ArrayList<Noticia> list = new ArrayList<>();
			while (resultado.next()) {
				Noticia n = new Noticia();
				n.setId(resultado.getInt("id"));
				n.setTitulo(resultado.getString("titulo"));
				n.setDescricao(resultado.getString("descricao"));
				n.setTexto(resultado.getString("texto"));
				list.add(n);
			}
			return list;

		} catch (SQLException ex) {

			System.err.println("Não foi possivel listar as noticias");
			ex.printStackTrace();

			return null;
		}
	}

	public Noticia getNoticia(int id) {

		String query = "SELECT * FROM noticia WHERE id =?";

		try (PreparedStatement pst = conexao.prepareStatement(query)) {

			pst.setInt(1, id);
			ResultSet resultado = pst.executeQuery();

			Noticia n = new Noticia();
			if (resultado.next()) {
				n.setId(resultado.getInt("id"));
				n.setTitulo(resultado.getString("titulo"));
				n.setDescricao(resultado.getString("descricao"));
				n.setTexto(resultado.getString("texto"));
			}
			return n;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean cadastrar(Noticia noticia) {
		String inserir = "INSERT INTO noticia(id, titulo, descricao, texto) VALUES(?, ?, ?, ?)";

		try (PreparedStatement pst = conexao.prepareStatement(inserir)) {

			pst.setInt(1, noticia.getId());
			pst.setString(2, noticia.getTitulo());
			pst.setString(3, noticia.getDescricao());
			pst.setString(4, noticia.getTexto());

			pst.execute();
			return true;
		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela de noticia.");
			ex.printStackTrace();
			return false;
		}
	}

	public boolean update(Noticia noticia) {

		String inserir = "UPDATE noticia SET " + "titulo =IFNULL(?, titulo)," + "descricao = IFNULL(?, descricao),"
				+ "texto = IFNULL(?, texto) " + "WHERE id = ?";

		try (PreparedStatement pst = conexao.prepareStatement(inserir)) {

			pst.setString(1, noticia.getTitulo());
			pst.setString(2, noticia.getDescricao());
			pst.setString(3, noticia.getTexto());
			pst.setInt(4, noticia.getId());

			pst.execute();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean deleteNoticia(int id) {

		if (!deleteComentario(id))
			return false;

		String inserir = "DELETE FROM noticia WHERE id= ?";

		try (PreparedStatement pst = conexao.prepareStatement(inserir)) {

			pst.setInt(1, id);
			pst.execute();

			return true;
		} catch (SQLException ex) {

			System.err.println("não foi possivel deletar a partir do id");
			ex.printStackTrace();
			return false;
		}

	}

	public boolean deleteComentario(int id) {

		String inserir = "DELETE FROM comentario WHERE fk_noticia_id= ?";

		try (PreparedStatement pst = conexao.prepareStatement(inserir)) {

			pst.setInt(1, id);
			pst.execute();

			return true;
		} catch (SQLException ex) {

			System.err.println("não foi possivel deletar os comentarios a partir do id");
			ex.printStackTrace();
			return false;
		}

	}

}
