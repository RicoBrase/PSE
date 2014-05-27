package de.rico_brase.pse;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.rico_brase.pse.element.Element;

public class PSEMain {
	
	public static List<Element> elements;

	public static void main(String[] args){
		elements = new ArrayList<Element>();
		
		new PSEGUI();
	}
	
}
