window.addEventListener("load", getNoticias(), false);

var idPost;

function getNoticias() {
	const http = new XMLHttpRequest();
	const params = `command=GetNoticias`;
	http.open('POST', '/RealNews/Controller.do', true);
	http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

	http.onreadystatechange = function() {
		
	    if(http.readyState == 4 && http.status == 200) {
	    	let titleNews = document.querySelector("#titleNews");
	        let text = document.querySelector("#bodyNoticia");         
	        text.innerHTML = http.responseText;         
	    } 
	}
	
	http.send(params);
}

function seeComments(id_post) {
	idPost = id_post;
	
	const http = new XMLHttpRequest();
    const params = `command=GetComentarios&id_noticia=${id_post}`;
	http.open('POST', '/RealNews/Controller.do', true);
	http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

	http.onreadystatechange = function() {
		
	    if(http.readyState == 4 && http.status == 200) {
	    	
	    	let resp = http.responseText;
		    let text = document.querySelector('#verComentarios'+ idPost);  
		    
		    text.innerHTML = resp; 
		    text.style.borderTop = '1px solid';
	    } 
	}
	
	http.send(params);
}
