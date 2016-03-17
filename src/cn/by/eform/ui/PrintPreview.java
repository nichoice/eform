package cn.by.eform.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import cn.by.eform.model.Field;
import cn.by.eform.model.Form;
import cn.by.eform.model.Paper;

public class PrintPreview extends JPanel {
	private Form form;

	/**
	 * Create the panel.
	 */
	public PrintPreview(Form form) {
		this.form=form;
	}
	
	protected void paintField(Graphics g,Field field){
		
		if(field.hasSubFields()){
			List<Field>subFields=field.getSubFields();
			for(int j=0;j<subFields.size();j++){
				paintField(g,subFields.get(j));
			}
		}
		else{
			Paper p=field.getPaper();
			int fontSize=p.getFontSize();
			
			g.setColor(Color.red);
			g.fillRect(p.getX(), p.getY(), p.getMaxLength(),fontSize);
			
			g.setPaintMode();
			g.setColor(Color.BLACK);
			g.drawString(field.getValue(), p.getX(), p.getY()+fontSize);
		}
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		List<Field> fields = form.getFields();
		
		int netRow=800;
		int netCol=1024;
		int netSize=10;
		
		for(int i=0;i<netRow;i++){
			g.drawLine(0, i*netSize, netCol, i*netSize);
		}
		for(int i=0;i<netCol;i++){
			g.drawLine(i*netSize, 0, i*netSize, netRow);
		}
		
		
		for(int i=0;i<fields.size();i++){
			Field field=fields.get(i);
			paintField(g,field);
			
		}
	}
}
