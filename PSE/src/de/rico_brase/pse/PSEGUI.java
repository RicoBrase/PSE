package de.rico_brase.pse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.JFrame;

import de.rico_brase.pse.element.Properties;
import de.rico_brase.pse.listeners.GUIListener;
import de.rico_brase.pse.utils.ElementFile;
import de.rico_brase.pse.utils.PSEFilter;

public class PSEGUI extends JFrame{

	private static final long serialVersionUID = -3590792696466282382L;
	
	private ElementFile[] elements;
	
	private Font elementFont;
	
	public PSEGUI(){
		super("Periodensystem der Elemente");
		
		elements = ElementFile.getElementFiles(getFiles());
		
		for(ElementFile ef : elements){
			PSEMain.elements.add(ef.getElement());
			System.out.println("Loaded Element: " + ef.getElement().getProperties().getSymbol());
		}
		
		elementFont = new Font("Arial",Font.BOLD, 28);
		
		int height = new Double(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue();
		int width = new Double(Toolkit.getDefaultToolkit().getScreenSize().getWidth()).intValue();
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, width, height);
		this.addWindowStateListener(new GUIListener(this));
		
		showWindow();
	}
	
	public void paint(Graphics g){
		paintPSE(g);
	}
	
	public void showWindow(){
		this.setVisible(true);
	}
	
	public void paintPSE(Graphics g){
		
//		g.setColor(Color.decode("#e8e8e8"));
//		g.drawRect(0, 0, this.getWidth(), this.getHeight());
//		g.setColor(Color.BLACK);
		
		for(int y = 1; y <= 9; y++){
			for(int x = 1; x <= 18; x++){
				if(elementExists(x, y)){
					g.setColor(Color.BLACK);
					
					if((y == 8 || y == 9)){
						if((x >= 3 && x != 18)){
							g.drawRect((x*50)+15, (y*75)+55, 50, 75);
							for(ElementFile ef : elements){
								if(ef.getElement().getProperties().getGroup() == x && ef.getElement().getProperties().getPeriod() == y){
									g.setFont(elementFont);
									g.drawString(ef.getElement().getProperties().getSymbol(), (x*50)+15, (y*75)+55);
									
								}
							}
							
						}
					}else{
						g.drawRect((x*50)+15, (y*75)+35, 50, 75);
						for(ElementFile ef : elements){
							Properties p = ef.getElement().getProperties();
							if(p.getGroup() == x && p.getPeriod() == y){
								g.setFont(elementFont);
								g.drawString(p.getSymbol(), (x*50)+15+(14/p.getSymbol().length()), (y*75)+55+14);
							}
						}
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
