package de.rico_brase.pse.element;

import java.math.BigDecimal;
import java.util.HashMap;

public class Properties {

	private String symbol;
	private int group;
	private int period;
	
	private char stateofmatter;
	
	private double mass;
	private double density;
	
	private double melting;
	private double boiling;
	
	public Properties(HashMap<String,String> map){
		if(map.containsKey("symbol") && map.containsKey("group") && map.containsKey("period") && map.containsKey("state") && 
		map.containsKey("mass") && map.containsKey("density") && map.containsKey("melting") && map.containsKey("boiling")){
			
			this.symbol = map.get("symbol");
			this.group = Integer.parseInt(map.get("group"));
			this.period = Integer.parseInt(map.get("period"));
			
			this.stateofmatter = map.get("state").charAt(0);
			
			this.mass = convertToDouble(map.get("mass"));
			this.density = convertToDouble(map.get("density"));
			
			this.melting = convertToDouble(map.get("melting"));
			this.boiling = convertToDouble(map.get("boiling"));
			
		}
	}
	
	public String getSymbol(){
		return this.symbol;
	}
	
	private double convertToDouble(String s){
		BigDecimal bd = new BigDecimal(Double.parseDouble(s));
		bd = bd.setScale(5, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
	
}
