package de.rico_brase.pse;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.rico_brase.pse.element.Properties;
import de.rico_brase.pse.listeners.SymbolClickListener;
import de.rico_brase.pse.utils.ElementFile;
import de.rico_brase.pse.utils.PSEFilter;

public class PSEGUI extends JFrame{

	private static final long serialVersionUID = -3590792696466282382L;
	
	private ElementFile[] elements;
	
	private HashMap<String,JLabel> symbols;
	public static HashMap<Integer,Color> colors;
	
	private Font elementFont;
	
	private JLabel title;
	
	private PSEPanel panel;
	
	private int height;
	private int width;
	
	public PSEGUI(){
		super("Periodensystem der Elemente");
		
		panel = new PSEPanel();
		
		
		elements = ElementFile.getElementFiles(getFiles());
		
		for(ElementFile ef : elements){
			PSEMain.elements.add(ef.getElement());
			System.out.println("Loaded Element: " + ef.getElement().getProperties().getSymbol());
		}
		
		elementFont = new Font("Arial",Font.BOLD, 28);
		
		symbols = new HashMap<String,JLabel>();
		colors = new HashMap<Integer,Color>();
		for(int i = 0; i<= 17; i++){
			colors.put(i, Color.decode("#eeeeee"));
		}
		colors.put(18, Color.decode("#87CEFA"));
		
		Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		Font boldUnderline = elementFont.deriveFont(fontAttributes);
		
		title = new JLabel("Periodensystem der Elemente");
		title.setFont(boldUnderline);
		FontMetrics fm = title.getFontMetrics(elementFont);
		Float cW = new Float(fm.charsWidth(title.getText().toCharArray(), 0, title.getText().length()));
		
		height = new Double(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue();
		width = new Double(Toolkit.getDefaultToolkit().getScreenSize().getWidth()).intValue();
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBounds(0, 0, width-200, height-200);
		//this.addWindowStateListener(new GUIListener(this));
		
		title.setBounds(this.getWidth()/2 - cW.intValue()/2, 20, cW.intValue(), 50);
		this.add(title);
		
		showWindow();
	}
	
	public void paint(Graphics g){
		paintComponents(g);
		paintPSE(g);
	}
	
	public void showWindow(){
		
		this.setVisible(true);
//		paintPSE(this.getGraphics());
		this.readyLabels();
		for(JLabel l : symbols.values()){			
			this.add(l);
//			//System.out.println("Added Label: " + l.getText());
//			//System.out.println("x: " + l.getBounds().getX() + " y: " + l.getBounds().getY());			
		}
		validate();
		repaint();
	}
	
	public void paintPSE(Graphics g){
		
		g.drawLine(width/2 - (19*60)/2 - 15, 0, width/2 - (19*60)/2 - 15, height);
		g.drawLine(width/2 + (20*60)/2, 0, width/2 + (20*60)/2, height);
		
		for(int y = 1; y <= 9; y++){
			for(int x = 1; x <= 18; x++){
				if(elementExists(x, y)){
					g.setColor(Color.BLACK);
					if((y == 8 || y == 9)){
						if((x >= 3 && x != 18)){
							Graphics2D g2 = (Graphics2D) g;
							g.setColor(Color.BLACK);
							g2.setStroke(new BasicStroke(2f));
							g.drawRect(width/2 - (20*60)/2 + (x*60), (y*75)+55, 60, 75);
							for(ElementFile ef : elements){
								Properties p = ef.getElement().getProperties();
								if(p.getGroup() == x && p.getPeriod() == y){
//									g.setColor(Color.BLACK);
//									g2.setStroke(new BasicStroke(2f));
//									g.drawRect(width/2 - (20*60)/2 + (x*60), (y*75)+55, 60, 75);
							
									g2.setStroke(new BasicStroke(5f));
									g2.setColor(p.getSeriesColor());
									g2.drawRect(width/2 - (20*60)/2 + (x*60) + 3, (y*75)+55 + 3, 53, 68);
								}
							}
						}
					}else{
						Graphics2D g2 = (Graphics2D) g;
						g.setColor(Color.BLACK);
						g2.setStroke(new BasicStroke(2f));
						g.drawRect(width/2 - (20*60)/2 + (x*60), (y*75)+35, 60, 75);
						for(ElementFile ef : elements){
							Properties p = ef.getElement().getProperties();
							if(p.getGroup() == x && p.getPeriod() == y){
//								g.setColor(Color.BLACK);
//								g2.setStroke(new BasicStroke(2f));
//								g.drawRect(width/2 - (20*60)/2 + (x*60), (y*75)+35, 60, 75);
						
								g2.setStroke(new BasicStroke(5f));
								g2.setColor(p.getSeriesColor());
								g2.drawRect(width/2 - (20*60)/2 + (x*60) + 3, (y*75)+35 + 3, 53, 68);
							}
						}
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
								Properties p = ef.getElement().getProperties();
								if(p.getGroup() == x && p.getPeriod() == y){
									JLabel elem = new JLabel(p.getSymbol());
									elem.setFont(elementFont);
									elem.setOpaque(true);
									elem.setForeground(Color.BLACK);
									elem.setBackground(colors.get(x));
									FontMetrics fm = elem.getFontMetrics(elementFont);
									Float cW = new Float(fm.charsWidth(p.getSymbol().toCharArray(), 0, p.getSymbol().length()));
									elem.setBounds(width/2 - (20*60)/2 + (x*60)+8+15-cW.intValue()/2, (y*75)+35, cW.intValue(), 50);
									elem.addMouseListener(new SymbolClickListener(ef.getElement()));
									symbols.put(p.getSymbol(), elem);
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
								FontMetrics fm = elem.getFontMetrics(elementFont);
								Float cW = new Float(fm.charsWidth(p.getSymbol().toCharArray(), 0, p.getSymbol().length()));
								elem.setBounds(width/2 - (20*60)/2 + (x*60)+8+15-cW.intValue()/2, (y*75)+15, cW.intValue(), 50);
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
