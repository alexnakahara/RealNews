<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>RealNews</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<script src="https://kit.fontawesome.com/e71e2a1db7.js" crossorigin="anonymous"></script>
<script src="index.js">
	
</script>
<script>
	function sendId(id) {
		const titleModalComentario = document.querySelector('#titleModalComentarios');
		titleModalComentario.innerHTML = 'Adicionando Comentário na Notícia';
		
		//escondendo os grupos de idNoticia
		let field = document.querySelector('#fieldIdNoticia');
		field.style.display = 'none';

		let input = document.querySelector('#id_noticia');
		input.value = id;

	}
	
	function openModalComentario() {
		const form = document.querySelector('#formComentario');
		form.reset();
		const titleModalComentario = document.querySelector('#titleModalComentarios');
		titleModalComentario.innerHTML = 'Adicionando Comentário em uma Notícia';
		
		//Mostrando os grupos de idNoticia 
		let field = document.querySelector('#fieldIdNoticia');
		field.style.display = 'block';
	}
	
	function openModalDelNoticia(){
		const text = document.querySelector('#text-obs');
		const form = document.querySelector('#formExcluir');
		const title = document.querySelector('#titleExcluir');
		const label = document.querySelector('#labelExcluir');
		const input = document.querySelector('#inputExcluir');
		const command = document.querySelector("#modalExcluirCommand");
				
		title.innerHTML = 'Excluir Notícia';
		text.innerHTML = '*Se a Notícia possuir comentários, eles serão deletados também*';
		label.innerHTML = 'Id da Notícia';
		command.value = "NoticiaCRUD";
		input.placeholder = 'Digite o Id da notícia que deseja excluir';
	}
	
	function openModalDelComentario(){
		const text = document.querySelector('#text-obs');
		const form = document.querySelector('#formExcluir');
		const title = document.querySelector('#titleExcluir');
		const label = document.querySelector('#labelExcluir');
		const input = document.querySelector('#inputExcluir');
		const command = document.querySelector('#modalExcluirCommand');

		text.innerHTML = null;
		title.innerHTML = 'Excluir Comentário';
		label.innerHTML = 'Id do Comentário';
		command.value="ComentarioCRUD";
		input.placeholder = 'Digite o Id do comentário que deseja excluir';
	}
</script>

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">RealNews</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item active"><a class="nav-link" href="index.jsp">Home
							<span class="sr-only">(current)</span>
					</a></li>
				
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Cadastrar </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item"data-toggle="modal" data-target="#modalNoticia" >Notícia</a> 
							<a class="dropdown-item" data-toggle="modal" data-target="#exampleModal" onclick='openModalComentario()'>Comentário</a>
						</div></li>
						
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Alterar </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" data-toggle="modal" data-target="#modalAlterNoticia" >Notícia</a> 
							<!-- <a class="dropdown-item" href="#">Comentário</a> -->
						</div>
					</li>
						
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> Excluir </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" data-toggle="modal" data-target="#modalExcluir" onclick='openModalDelNoticia()'>Notícia</a> 
							<a class="dropdown-item" data-toggle="modal" data-target="#modalExcluir" onclick='openModalDelComentario()'>Comentário</a>
						</div>
					</li>
				</ul>
			</div>
		</nav>
	</header>

	<div class="container">
		<div id="titleLastNews">Últimas Notícias</div>
		<div id="bodyNoticia" class="container-noticias"></div>
	</div>

	<!-- Modal de Cadastro Comentario -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="titleModalComentarios"></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="formComentario" method="post" action="Controller.do">
						<div class="form-group" id="fieldIdNoticia">
							<label for="id" class="col-form-label">Id Notícia</label> <input
								type="text" class="form-control" id="id_noticia"
								name="id_noticia" placeholder="Digite o Id da notícia que deseja comentar">
						</div>
						<div class="form-group" id="fieldIdComentario">
							<label for="id" class="col-form-label">Id Comentario</label> <input
								type="text" class="form-control" id="id_comentario"
								name="id_comentario" placeholder="Digite um número identificador para seu comentário">
						</div>
						<div class="form-group">
							<label for="nome" class="col-form-label">Nome</label> <input
								type="text" class="form-control" placeholder="Digite seu nome"
								id="nome" name="nome">
						</div>
						<div class="form-group">
							<label for="message-text" class="col-form-label">Comentário:</label>
							<textarea class="form-control"
								placeholder="Digite seu comentário" id="texto" name="texto"></textarea>
							<input type="hidden" name="command" value="ComentarioCRUD">
							<input type="hidden" name="action" value="cadastrar">
						</div>
				
					<div class="modal-footer d-flex justify-content-between">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Fechar</button>
						<button type="submit" class="btn btn-primary">Postar</button>
					</div>
				</form>
			</div>
			</div>
		</div>
	</div>

	<!-- Modal de Cadastro Noticias -->
	<div class="modal fade" id="modalNoticia" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Adicionando Notícia</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="formNoticia" method="POST" action="Controller.do">
						<div class="form-group">
							<label for="id" class="col-form-label">Id Notícia</label> <input
								type="text" class="form-control" id="id_noticia"
								name="id_noticia" placeholder="Digite um número identificador para a notícia">
						</div>
						<div class="form-group">
							<label for="id" class="col-form-label">Título</label> <input
								type="text" class="form-control" id="titulo"
								name="titulo" placeholder="Digite um título para a notícia">
						</div>
						<div class="form-group">
							<label for="nome" class="col-form-label">Descrição</label> <input
								type="text" class="form-control" placeholder="Digite uma descrição para a notícia"
								id="nome" name="descricao">
						</div>
						<div class="form-group">
							<label for="message-text" class="col-form-label">Texto</label>
							<textarea class="form-control"
								placeholder="Digite o texto da notícia" id="texto" name="texto"></textarea>
							<input type="hidden" name="action" value="insert">
							<input type="hidden" name="command" value="NoticiaCRUD">
						</div>
				
					<div class="modal-footer d-flex justify-content-between">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Fechar</button>
						<button type="submit" class="btn btn-primary">Postar</button>
					</div>
				</form>
			</div>
			</div>
		</div>
	</div>
	
		<!-- Modal de Alteração de Noticias -->
	<div class="modal fade" id="modalAlterNoticia" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Alterar Notícia</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="formAlterNoticia" method="POST" action="Controller.do">
						<div class="form-group">
							<label for="id" class="col-form-label">Id Notícia</label> 
							<input type="text" class="form-control" id="id_noticia" required
								name="id_noticia" placeholder="Digite o número identificador da notícia">
						</div>
						<div class="form-group">
							<label for="id" class="col-form-label">Título</label> <input
								type="text" class="form-control" id="titulo"
								name="titulo" placeholder="Caso queira atualizar digite um título">
						</div>
						<div class="form-group">
							<label for="nome" class="col-form-label">Descrição</label> <input
								type="text" class="form-control" placeholder="Digite uma descrição para a notícia"
								id="nome" name="descricao">
						</div>
						<div class="form-group">
							<input type="hidden" name="action" value="alter"/>	
							<input type="hidden" name="command" value="NoticiaCRUD">
							<label for="message-text" class="col-form-label">Texto</label>
							<textarea class="form-control"
								placeholder="Caso queira atualizar digite um texto" id="texto" name="texto"></textarea>
						</div>
				
					<div class="modal-footer d-flex justify-content-between">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Fechar</button>
						<button type="submit" class="btn btn-primary">Alterar</button>
					</div>
				</form>
			</div>
			</div>
		</div>
	</div>

	<!-- Modal de Remoção de Noticias  e Comentarios-->
	<div class="modal fade" id="modalExcluir" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="titleExcluir"></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div id="text-obs"></div>
					<form id="formExcluir" method="post" action="Controller.do">
						<div class="form-group">
							<label for="id" class="col-form-label" id="labelExcluir"></label> 
							 <input type="hidden" name="action" value="delete"/>
							 <input type="hidden" id="modalExcluirCommand" name="command" value=""/>
							 
							<input type="text" class="form-control" id="inputExcluir" name="id" placeholder="">
						</div>
				
					<div class="modal-footer d-flex justify-content-between">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Fechar</button>
						<button type="submit" class="btn btn-primary">Excluir</button>
					</div>
				</form>
			</div>
			</div>
		</div>
	</div>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</body>
</html>