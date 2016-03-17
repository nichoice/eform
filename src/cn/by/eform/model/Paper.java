package cn.by.eform.model;

import java.util.ArrayList;
import java.util.List;

public class Paper {
	private int x;
	private int y;
	private int fontSize;
	private int maxLength;
	
	private List<Spliter> spliters=new ArrayList<Spliter>();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public List<Spliter> getSpliters() {
		return spliters;
	}

	public void setSpliters(List<Spliter> spliters) {
		this.spliters = spliters;
	}
	
	public boolean hasSpliter(){
		return spliters.size()>0;
	}
	
	public void setSpiterValue(String name,String value){
		for(int i=0;i<spliters.size();i++){
			if(name.equals(spliters.get(i).getName())){
				spliters.get(i).setValue(value);
				break;
			}
		}
	}
	
	public String getSpiterValue(String name){
		String value="";
		for(int i=0;i<spliters.size();i++){
			if(name.equals(spliters.get(i).getName())){
				value=spliters.get(i).getValue();
				break;
			}
		}
		return value;
	}
}
