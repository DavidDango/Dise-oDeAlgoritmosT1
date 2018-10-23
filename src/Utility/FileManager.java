package Utility;

import java.io.IOException;
import Structure.Nodo;

public interface FileManager {

	void EndWriting() throws IOException;
	void SortConsumer(String s) throws IOException;
	void SortProduct(String s) throws IOException;
	void addNodo(Nodo translateString) throws IOException;
}
