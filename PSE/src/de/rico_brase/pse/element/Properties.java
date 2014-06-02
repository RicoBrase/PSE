package de.rico_brase.pse.element;

import java.awt.Color;
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
	
	public Color getSeriesColor(){
		
		if(getGroup() == 1 && getPeriod() >= 2){
			return Color.decode("#FF4500");
		}
		if((getGroup() == 1 && getPeriod() == 1) || ((getPeriod() == 2 && getGroup() >= 14 && getGroup() <= 16) || (getPeriod() == 3 && getGroup() >= 15 && getGroup() <= 16))){
			return Color.decode("#32CD32");
		}
		if(getGroup() == 2){
			return Color.decode("#FF8C00");
		}
		if(getGroup() >= 3 && getGroup() <=12 && getPeriod() != 8 && getPeriod() != 9){
			return Color.decode("#F08080");
		}
		
		if(getPeriod() == 8){
			return Color.decode("#DDA0DD");
		}
		if(getPeriod() == 9){
			return Color.decode("#DA70D6");
		}
		if(getGroup() == 17){
			return Color.decode("#F0E68C");
		}
		if(getGroup() == 18){
			return Color.decode("#87CEFA");
		}
		
		return Color.decode("#eeeeee");
	}
	
	private double convertToDouble(String s){
		BigDecimal bd = new BigDecimal(Double.parseDouble(s));
		bd = bd.setScale(5, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
	
}
