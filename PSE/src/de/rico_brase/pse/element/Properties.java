package de.rico_brase.pse.element;

import java.math.BigDecimal;
import java.util.HashMap;

public class Properties {

	private String symbol = "empty";
	private int group = 0;
	private int period = 0;
	
	private char stateofmatter = '-';
	
	private double mass = 0.0;
	private double density = 0.0;
	
	private double melting = 0.0;
	private double boiling = 0.0;
	
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
	
	public int getGroup(){
		return this.group;
	}
	
	public int getPeriod(){
		return this.period;
	}
	
	private double convertToDouble(String s){
		BigDecimal bd = new BigDecimal(Double.parseDouble(s));
		bd = bd.setScale(5, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
	
}
