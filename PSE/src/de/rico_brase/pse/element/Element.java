package de.rico_brase.pse.element;

import java.awt.Graphics;

public class Element {

	private String gname;
	private String latin;
	
	private Properties prop;
	
	public Element(Properties p){
		this.prop = p;
	}
	
	public Properties getProperties(){
		return this.prop;
	}
	
	public void draw(Graphics g){
		
	}
	
}
