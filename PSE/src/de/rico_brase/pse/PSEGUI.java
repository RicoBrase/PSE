package de.rico_brase.pse;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;

import de.rico_brase.pse.element.Properties;
import de.rico_brase.pse.listeners.SymbolClickListener;
import de.rico_brase.pse.utils.ElementFile;
import de.rico_brase.pse.utils.PSEFilter;

public class PSEGUI extends JFrame{

	private static final long serialVersionUID = -3590792696466282382L;
	
	private ElementFile[] elements;
	
	private HashMap<String,JLabel> symbols;
	
	private Font elementFont;
	private boolean paint = true;
	
	public PSEGUI(){
		super("Periodensystem der Elemente");
		
		elements = ElementFile.getElementFiles(getFiles());
		
		for(ElementFile ef : elements){
			PSEMain.elements.add(ef.getElement());
			System.out.println("Loaded Element: " + ef.getElement().getProperties().getSymbol());
		}
		
		elementFont = new Font("Arial",Font.BOLD, 28);
		
		symbols = new HashMap<String,JLabel>();
		
		int height = new Double(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue();
		int width = new Double(Toolkit.getDefaultToolkit().getScreenSize().getWidth()).intValue();
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBounds(0, 0, width, height);
		//this.addWindowStateListener(new GUIListener(this));
		
		showWindow();
	}
	
	public void paint(Graphics g){
		
		paintComponents(g);
		paintPSE(g);
	}
	
	public void showWindow(){
		
		this.setVisible(true);
		this.readyLabels();
		for(JLabel l : symbols.values()){			
			this.add(l);
			System.out.println("Added Label: " + l.getText());
			//System.out.println("x: " + l.getBounds().getX() + " y: " + l.getBounds().getY());			
		}
		repaint();
	}
	
	public void paintPSE(Graphics g){
		for(int y = 1; y <= 9; y++){
			for(int x = 1; x <= 18; x++){
				if(elementExists(x, y)){
					g.setColor(Color.BLACK);
					if((y == 8 || y == 9)){
						if((x >= 3 && x != 18)){
							g.drawRect((x*60)+15, (y*75)+55, 60, 75);		
						}
					}else{
						g.drawRect((x*60)+15, (y*75)+35, 60, 75);
					}
				}
			}
		}		
	}
	
	public void readyLabels(){
		for(int y = 1; y <= 9; y++){
			for(int x = 1; x <= 18; x++){
				if(elementExists(x, y)){					
					if((y == 8 || y == 9)){
						if((x >= 3 && x != 18)){
							for(ElementFile ef : elements){
								if(ef.getElement().getProperties().getGroup() == x && ef.getElement().getProperties().getPeriod() == y){
									
								}
							}
							
						}
					}else{
						for(ElementFile ef : elements){
							Properties p = ef.getElement().getProperties();
							if(p.getGroup() == x && p.getPeriod() == y){
								JLabel elem = new JLabel(p.getSymbol());
								elem.setFont(elementFont);
								elem.setForeground(Color.BLACK);
								int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
								FontMetrics fm = elem.getFontMetrics(elementFont);
								Float cW = new Float(fm.charsWidth(p.getSymbol().toCharArray(), 0, p.getSymbol().length()));
								elem.setBounds((x*60)+8+30-cW.intValue()/2, (y*75)+15, cW.intValue(), 50);
								elem.addMouseListener(new SymbolClickListener(ef.getElement()));
								symbols.put(p.getSymbol(), elem);
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
