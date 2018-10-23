package Structure;

import Utility.FileManager;
import Utility.SingleFile;
import java.io.IOException;

public class Database {
	String p;
	String c;
	FileManager f;
	
	
	public Database(String producto, String consumidor) throws IOException {
		p = producto;
		c = consumidor;
		f = new SingleFile(p, c, this);
	}
	
	public void AddElement(String s) throws NumberFormatException, IOException {
		f.addNodo(TranslateString(s));
	}
	
	public Nodo TranslateString(String s) {
		s = s.replace("\n", "");
		String[] temp = s.split(" ");
		if(temp.length == 3) {
			return new Consumer(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]));
		}
		else if(temp.length == 4) {
			return new Product(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]));
		}
		return null;
	}
	
	public void EndWriting() throws IOException {
		f.EndWriting();
	}
	
	public void SortProduct(String s) throws IOException {
		f.SortProduct(s);
	}
	
	public void SortConsumer(String s) throws IOException {
		f.SortConsumer(s);
	}
}
