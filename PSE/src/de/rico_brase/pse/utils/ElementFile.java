package de.rico_brase.pse.utils;

import java.io.File;
import java.net.URI;
import java.net.URL;

public class ElementFile extends File{
	
	private File file;

	private static final long serialVersionUID = -7684830022782881956L;

	public ElementFile(File path, String fileName) {
		super(path, fileName);
	}
	
	public ElementFile(URI uri){
		super(uri);
	}
	
	public static ElementFile[] getElementFiles(File[] files){
		
		ElementFile[] efiles = new ElementFile[files.length];
		
		return efiles;
		
	}
	
//	public 
	
}
