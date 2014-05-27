package de.rico_brase.pse.utils;

import java.io.File;
import java.util.HashMap;

public class ElementFile extends File{

	private static final long serialVersionUID = -7684830022782881956L;
	
	private File file;
	private HashMap<String,String> props;
	
	public ElementFile(File file){
		super(file.getParentFile(), file.getName());
		this.file = file;
		props = new HashMap<String, String>();
		
	}
	
	public static ElementFile[] getElementFiles(File[] files){
		
		ElementFile[] efiles = new ElementFile[files.length];
		
		for(int i = 0; i < efiles.length; i++){
			efiles[i] = new ElementFile(files[i]);
		}
		
		return efiles;
		
	}
	
	public void readContent(){
		
	}
	
}
