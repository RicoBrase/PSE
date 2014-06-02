package de.rico_brase.pse.element;

import java.awt.Graphics;

import de.rico_brase.pse.PSEMain;

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
	
	public static Element getByPeriodAndGroup(int g, int p){
		
		for(Element e : PSEMain.elements){
			if(e.getProperties().getGroup() == g && e.getProperties().getPeriod() == p){
				return e;
			}
		}
		
		return null;
		
	}
	
}
