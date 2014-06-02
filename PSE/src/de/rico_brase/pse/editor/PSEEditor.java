package de.rico_brase.pse.editor;

import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.UIManager;

import de.rico_brase.pse.utils.ElementFile;
import de.rico_brase.pse.utils.PSEFilter;

public class PSEEditor extends JFrame{

	private static final long serialVersionUID = 9191868031240999463L;
	
	JComboBox<String> listbox;
	
	public PSEEditor(){
		super("PSE-Editor");
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 350, 500);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		init();
		
	}
	
	public static void main(String[] args){
		new PSEEditor();
	}
	
	public void init(){
		
		String[] filenames = getFileNames(getFiles());
		listbox = new JComboBox<String>(filenames);
		
		listbox.setBounds(5, 5, 200, 25);
		
		this.add(listbox);
		
		this.setVisible(true);
	}
	
	public String[] getFileNames(File[] files){
		String[] data = new String[files.length];
		for(int i = 0; i < files.length; i++){
			data[i] = files[i].getName().substring(0, files[i].getName().indexOf('.'));
		}
		return data;
	}
	
	public File[] getFiles(){
		try{
			URL url = ElementFile.class.getResource("/psefiles/");
			URI uri = url.toURI();
			File path = new File(uri);
			PSEFilter pse = new PSEFilter("pse");
			return path.listFiles(pse);
		}catch(Exception e){
			e.printStackTrace();
			return new File[0];
		}
	}
	
	

}
