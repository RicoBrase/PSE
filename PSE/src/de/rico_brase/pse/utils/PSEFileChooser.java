package de.rico_brase.pse.utils;

import java.awt.Component;
import java.awt.HeadlessException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

@SuppressWarnings("serial")
public class PSEFileChooser extends JFileChooser{

	protected JDialog createDialog(Component parent) throws HeadlessException{
		JDialog dlg = super.createDialog(parent);
		dlg.setLocationRelativeTo(null);
		return dlg;
	}
	
}
