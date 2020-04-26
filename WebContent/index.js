var asyncRequest;    
var idPost;
function start(){
    try
    {
        asyncRequest = new XMLHttpRequest();
        asyncRequest.addEventListener("readystatechange", stateChange, false);
        asyncRequest.open('GET', '/RealNews/NoticiaServlet.do', true);
        asyncRequest.send(null);
    }
    catch(exception)
   {
    console.error("Request failed");
   }
}

function stateChange(){
if(asyncRequest.readyState == 4 && asyncRequest.status == 200)
    {
	const titleNews = document.querySelector("#titleNews");
	
    var text = document.querySelector("#bodyNoticia");         
    text.innerHTML = asyncRequest.responseText;         
    }
}

window.addEventListener("load", start(), false);


function seeComments(id_post){
	idPost = id_post
	//console.log('Aquiii idPost', idPost);
	try
    {
        asyncRequest = new XMLHttpRequest();
        asyncRequest.addEventListener("readystatechange", stateChangeComments, false);
        asyncRequest.open('Post', '/RealNews/ComentarioServlet.do', true);
        asyncRequest.send(id_post);
    }
    catch(exception)
   {
    console.error("Request failed");
   }
}

function stateChangeComments(){
	if(asyncRequest.readyState == 4 && asyncRequest.status == 200)
	    {
		 	let resp = asyncRequest.responseText;
		    let text = document.querySelector('#verComentarios'+ idPost);  
		    
		    if(resp === ''){
		    	text.innerHTML = '<div class="empty-comment">Está notícia não possuí nenhum comentario no momento!</div>';
		    }else{
		    	text.innerHTML = resp;    
		    	
		    }
		    //console.log('text.innerHTML', text.innerHTML);
		    
		   
	    }
	}
