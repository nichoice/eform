package cn.by.eform.ui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Global {
	
	private static  Global global=new Global();
	
	static{
		global.setFontSize(20);
		
	}
	private Global(){}
	
	public static Global getInstance(){
		return global;
	}
	
	private int fontSize;
	
	public Rectangle getAppRect() {
		Rectangle rect=new Rectangle();
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		
		rect.setSize(screensize);
		rect.setSize(1024, 768);
		//rect.setLocation(100, 100);
		
		return rect;
	}
	
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
}
