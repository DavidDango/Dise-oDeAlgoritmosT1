package Utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Structure.Database;
import Structure.Nodo;

public class SingleFile extends AbstractFileManager {
	int fileCounter;
	Database d;
	
	public SingleFile(String consumidor, String producto, Database database) throws IOException {
		wc = new BufferedWriter(new FileWriter(consumidor), B);
		wp = new BufferedWriter(new FileWriter(producto), B);
		c = consumidor;
		p = producto;
		fileCounter = 0;
		d = database;
	}

	private void addProduct(Nodo p) throws IOException {
		wp.write(p.printData() + "\n");
	}

	private void addConsumer(Nodo c) throws IOException {
		wc.write(c.printData() + "\n");
	}

	@Override
	public void EndWriting() throws IOException {
		wc.flush();
		wp.flush();
		wc.close();
		wp.close();
	}

	@Override
	public void SortConsumer(String s) throws IOException {
		int counter = 0;
		fileCounter = 0;
		int size = 0;
		BufferedReader reader = new BufferedReader(new FileReader(c), B);
		BufferedWriter writer = new BufferedWriter(new FileWriter(c + counter), B);
		ArrayList<String> files = new ArrayList<String>();
		files.add(c + counter);
		String temp;
		
		while((temp = reader.readLine()) != null) {
			size = size + temp.length() + 1;
			if(size > B) {
				writer.flush();
				writer.close();
				counter++;
				writer = new BufferedWriter(new FileWriter(c + counter));
				files.add(c + counter);
				size = temp.length() + 1;
			}
			writer.write(temp + "\n");
		}
		writer.flush();
		writer.close();
		reader.close();
		String tempName = MergeSort(s, files);
		File tempFile = new File(c);
		tempFile.delete();
		tempFile = new File(tempName);
		tempFile.renameTo(new File(c));
	}
	
	@Override
	public void SortProduct(String s) throws IOException {
		int counter = 0;
		fileCounter = 0;
		int size = 0;
		BufferedReader reader = new BufferedReader(new FileReader(p), B);
		BufferedWriter writer = new BufferedWriter(new FileWriter(p + counter), B);
		ArrayList<String> files = new ArrayList<String>();
		files.add(p + counter);
		String temp;
		
		while((temp = reader.readLine()) != null) {
			size = size + temp.length() + 1;
			if(size > B) {
				writer.flush();
				writer.close();
				counter++;
				writer = new BufferedWriter(new FileWriter(p + counter));
				files.add(p + counter);
				size = temp.length() + 1;
			}
			writer.write(temp + "\n");
		}
		writer.flush();
		writer.close();
		reader.close();
		String tempName = MergeSort(s, files);
		File tempFile = new File(p);
		tempFile.delete();
		tempFile = new File(tempName);
		tempFile.renameTo(new File(p));
	}

	private String MergeSort(String s, ArrayList<String> files) throws IOException {
		if(files.size() > 1) {
			ArrayList<String> l1 = new ArrayList<String>();
			ArrayList<String> l2 = new ArrayList<String>();
			for(int i = 0; i < files.size(); i++) {
				if(i < files.size()/2) {
					l1.add(files.get(i));
					
				}
				else {
					l2.add(files.get(i));
				}
			}
			return Merge(MergeSort(s, l1), MergeSort(s, l2), s);
		}
		
		return Sort(files.get(0), s);
	}

	private String Merge(String m1, String m2, String req) throws IOException {
		BufferedReader br1 = new BufferedReader(new FileReader(m1), B);
		BufferedReader br2 = new BufferedReader(new FileReader(m2), B);
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileCounter + ""), B);
		String returnName = fileCounter + "";
		fileCounter++;
		String t1 = br1.readLine();
		String t2 = br2.readLine();
		Nodo n1;
		Nodo n2;
		while(t1 != null && t2 != null) {
			n1 = d.TranslateString(t1);
			n2 = d.TranslateString(t2);
			String temp1 = "" + n1.getData(req);
			String temp2 = "" + n2.getData(req);
			temp1 = temp1.replace("-", "");
			temp2 = temp2.replace("-", "");
			if((Integer.parseInt(temp1) > Integer.parseInt(temp2))) {
				bw.write(n2.printData() + "\n");
				t2 = br2.readLine();
			}
			else {
				bw.write(n1.printData() + "\n");
				t1 = br1.readLine();
			}
		}
		while(t1 != null) {
			n1 = d.TranslateString(t1);
			bw.write(n1.printData() + "\n");
			t1 = br1.readLine();
		}
		while(t2 != null) {
			n2 = d.TranslateString(t2);
			bw.write(n2.printData() + "\n");
			t2 = br2.readLine();
		}
		br1.close();
		br2.close();
		bw.flush();
		bw.close();
		File t = new File(m1);
		t.delete();
		t = new File(m2);
		t.delete();
		
		return returnName;
	}

	private String Sort(String s, String req) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(s), B);
		String temp;
		ArrayList<Nodo> n = new ArrayList<Nodo>();
		while((temp = br.readLine()) != null) {
			n.add(d.TranslateString(temp));
		}
		ArrayList<Nodo> ns = new ArrayList<Nodo>();
		while(n.size() > 0) {
			Nodo t = n.get(0);
			for(Nodo nodo : n) {
				String temp1 = "" + t.getData(req);
				String temp2 = "" + nodo.getData(req);
				temp1 = temp1.replace("-", "");
				temp2 = temp2.replace("-", "");
				if((Integer.parseInt(temp1) > Integer.parseInt(temp2))) {
					t = nodo;
				}
			}
			ns.add(t);
			n.remove(t);
		}
		br.close();
		File oldFile = new File(s);
		oldFile.delete();
		BufferedWriter bw = new BufferedWriter(new FileWriter(s), B);
		for(Nodo nodo : ns) {
			bw.write(nodo.printData() + "\n");
		}
		bw.flush();
		bw.close();
		return s;
	}

	@Override
	public void addNodo(Nodo n) throws IOException {
		if(n.isConsumer()) {
			addConsumer(n);
		}
		else if(n.isProduct()) {
			addProduct(n);
		}
	}
}
