package de.rico_brase.pse.listeners;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import de.rico_brase.pse.SingleElementGUI;
import de.rico_brase.pse.element.Element;

public class SymbolClickListener implements MouseListener{

	private Element el;
	
	public SymbolClickListener(Element el){
		super();
		this.el = el;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		new SingleElementGUI(el);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		label.setForeground(Color.RED);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		label.setForeground(Color.BLACK);
		label.setCursor(Cursor.getDefaultCursor());
	}

}
