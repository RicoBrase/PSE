package de.rico_brase.pse;

import javax.swing.JFrame;

import de.rico_brase.pse.element.Element;

public class SingleElementGUI extends JFrame{

	private static final long serialVersionUID = 6998999548710209555L;

	private Element e;
	
	public SingleElementGUI(Element element){
		super(element.getProperties().getSymbol() + " - PSE");
		this.e = element;
		this.setLayout(null);
		this.setResizable(false);
		this.setBounds(10, 10, 350, 150);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}
	
	
	
}
