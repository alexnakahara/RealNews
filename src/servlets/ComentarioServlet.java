package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Comentario;
import services.ComentarioService;


@WebServlet("/ComentarioServlet.do")
public class ComentarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ComentarioServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String payloadRequest = getBody(request);
		int id_noticia = Integer.parseInt(payloadRequest);
		
		StringBuilder builder = new StringBuilder();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		  ComentarioService service = new ComentarioService(); ArrayList<Comentario>
		  lista = new ArrayList<Comentario>(); 
		  lista = service.listarComentarios(id_noticia);
		  
		  if(lista!= null) {
		  
		  for (Comentario i : lista) {
		  builder.append("<div class='container-user'><i class=\"fas fa-user\"></i></div>"
		  				+ "<div class='-comentario'>"
				  		+ "<div class='font-weight-bold ml-1'>"+ i.getNome() + "</div>"+ 
				  		"<div class='-comentario__text'>\"" + i.getTexto()+ "\"</div>"+ 
				  		"</div>" 
				  		);
		  }
		  
		  } else {
			  builder.append("<script> alert('Está notícia não possuí nenhum comentario no momento!')</script>");
		  }
		  
		  out.print(builder.toString());
		  System.out.println("payloadRequest " + payloadRequest);
	}
	
	public static String getBody(HttpServletRequest request) throws IOException {

	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();
	    return body;
	}

}
