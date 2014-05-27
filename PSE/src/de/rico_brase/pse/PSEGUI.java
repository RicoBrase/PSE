package de.rico_brase.pse;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.JFrame;

import de.rico_brase.pse.utils.ElementFile;
import de.rico_brase.pse.utils.PSEFilter;

public class PSEGUI extends JFrame{

	private static final long serialVersionUID = -3590792696466282382L;
	
	@SuppressWarnings("unused")
	private ElementFile[] elements;
	
	public PSEGUI(){
		super("Periodensystem der Elemente");
		
		elements = ElementFile.getElementFiles(getFiles());
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		showWindow();
	}
	
	public void paint(Graphics g){
		paintPSE(g);
	}
	
	public void showWindow(){
		this.setVisible(true);
	}
	
	public void paintPSE(Graphics g){
		
		for(int y = 1; y <= 9; y++){
			for(int x = 1; x <= 18; x++){
				if(elementExists(x, y)){
					g.setColor(Color.BLACK);
					
					if((y == 8 || y == 9)){
						if((x >= 3 && x != 18)){
							g.drawRect((x*50)+15, (y*75)+55, 50, 75);
						}
					}else{
						g.drawRect((x*50)+15, (y*75)+35, 50, 75);
					}
				}
			}
		}
		
	}
	
	public boolean elementExists(int x, int y){
		if(y == 1 && (x >=2 && x < 18)){
			return false;
		}else if((y == 2 || y == 3) && (x >= 3 && x < 13)){
			return false;
		}else{
			return true;
		}
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
