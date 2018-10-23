package Utility;

import java.io.BufferedWriter;

public abstract class AbstractFileManager implements FileManager{
	String c;
	String p;
	BufferedWriter wc;
	BufferedWriter wp;
	int B = 4096;
}
