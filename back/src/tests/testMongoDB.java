package tests;

import service.Comments;
import service.Likes;
import service.Message;

public class testMongoDB {
	public static void main(String[] args) {
		//Message a = new Message();
		//Message.postMessage("6fg5NrO6D8CYDNMt", "Je m'appelle tata");
		//a.postMessage("gMkEGTJsoycwKXs9", "Bonjour je suis tata");
		//a.postMessage("gMkEGTJsoycwKXs9", "Moi,tata a faim");
		//a.postMessage("I8dZFCw2eTzYY1Po", "Salut, je suis toto");
		//a.postMessage("I8dZFCw2eTzYY1Po", "Moi,toto je fais du velo");
		//Message.listMessage("VaN6l1aPjnnUqnVd", null,null);
		//Message.removeMessage("QwZhNkoArN3euVxd", "5ca878a1b2371b0f4a95c05a");
		//Likes.addLike("joris lecon", "5ca906120e66e036a43efcf9");
		Comments.postComment("tata", "5ca9e108589fcd05dd28ffe7", "COMMENTAIRE2");
		//Message.listMessage("3CT5wvtrTwurZTTl", "","");
	}
	
}