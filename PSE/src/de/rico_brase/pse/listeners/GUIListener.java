package de.rico_brase.pse.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import de.rico_brase.pse.PSEGUI;

public class GUIListener implements WindowStateListener{
	
	PSEGUI gui;
	
	public GUIListener(PSEGUI gui){
		this.gui = gui;
	}

	@Override
	public void windowStateChanged(WindowEvent e) {
		gui.repaint();
	}



}
