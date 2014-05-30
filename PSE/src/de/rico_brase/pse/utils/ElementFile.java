package de.rico_brase.pse.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import de.rico_brase.pse.PSEMain;
import de.rico_brase.pse.element.Element;
import de.rico_brase.pse.element.Properties;

public class ElementFile extends File{

	private static final long serialVersionUID = -7684830022782881956L;
	
	private File file;
	private HashMap<String,String> props;
	private Element element;
	
	public ElementFile(File file){
		super(file.getParentFile(), file.getName());
		this.file = file;
		props = readContent();
		element = new Element(new Properties(props));
	}
	
	public static ElementFile[] getElementFiles(File[] files){
		
		ElementFile[] efiles = new ElementFile[files.length];
		
		for(int i = 0; i < efiles.length; i++){
			efiles[i] = new ElementFile(files[i]);
		}
		
		return efiles;
		
	}
	
	public File getFile(){
		return this.file;
	}
	
	public Element getElement(){
		return this.element;
	}
	
	public HashMap<String,String> readContent(){
		
		HashMap<String,String> cnt = new HashMap<String,String>();
		
		String line;
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(getFile()));
			while((line = br.readLine()) != null){
				if(line.startsWith(">") && line.endsWith(";")){
					if(line.contains(":")){
						String[] w = line.split(":");
						if(w.length > 1){
							cnt.put(w[0].substring(1), w[1].substring(0, w[1].length()-1));
							//System.out.println(w[0].substring(1) + " " + w[1].substring(0, w[1].length()-1));
						}
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return cnt;
		
	}
	
}
