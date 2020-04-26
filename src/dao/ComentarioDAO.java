package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Comentario;

public class ComentarioDAO {
	private Connection conexao;

	public ComentarioDAO() {
		this.conexao = ConnectionFactory.conectar();
	}
	
	public boolean cadastrar(Comentario c) {
		String inserir = "INSERT INTO comentario(id, nome, texto, fk_noticia_id) VALUES(?, ?, ?, ?)";

		try (PreparedStatement pst = conexao.prepareStatement(inserir)) {

			pst.setInt(1, c.getId());
			pst.setString(2, c.getNome());
			pst.setString(3, c.getTexto());
			pst.setInt(4, c.getIdNoticia());

			pst.execute();
			return true;
		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela de noticia.");
			ex.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Comentario> listComentarios(int id_noticia) {

		String query = "SELECT * FROM comentario WHERE fk_noticia_id =?";

		try (PreparedStatement pst = conexao.prepareStatement(query)) {
			pst.setInt(1, id_noticia);
			ResultSet resultado = pst.executeQuery();

			ArrayList<Comentario> list = new ArrayList<>();
			while (resultado.next()) {
				Comentario c = new Comentario();
				c.setId(resultado.getInt("id"));
				c.setNome(resultado.getString("nome"));
				c.setTexto(resultado.getString("texto"));
				list.add(c);
			}
			return list;

		} catch (SQLException ex) {

			System.err.println("Não foi possivel listar as noticias");
			ex.printStackTrace();

			return null;
		}
	} 
	
	
}
