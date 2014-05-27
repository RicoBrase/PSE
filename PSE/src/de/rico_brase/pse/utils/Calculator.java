package de.rico_brase.pse.utils;

import java.math.BigDecimal;

public class Calculator {

	public static double molarMass(double density, double molarVolume){
		
		BigDecimal bd = new BigDecimal(molarVolume * density);
		bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
		
		return bd.doubleValue();
		
	}
	
}
