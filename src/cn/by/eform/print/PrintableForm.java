package cn.by.eform.print;

import java.awt.print.*;
import java.awt.*;
import java.io.File;
import java.util.List;

import cn.by.eform.draft.FormParser;
import cn.by.eform.model.Field;
import cn.by.eform.model.Form;
import cn.by.eform.model.Spliter;

public class PrintableForm implements Printable {
	private Form form;
	
	public PrintableForm(Form form){
		this.form=form;
	}
	
	protected void paintField(Graphics g2d,Field field){
		
		if(field.hasSubFields()){
    		java.util.List<Field>subFields=field.getSubFields();
    		for(int j=0;j<subFields.size();j++){
    			paintField(g2d,subFields.get(j));
    		}
    	}else{
    		String str = field.getValue();
    		
    		cn.by.eform.model.Paper p=field.getPaper();
    		
    		Font font = new Font("宋体", Font.PLAIN,  p.getFontSize()); 
            g2d.setFont(font); 
            
    		if(p.hasSpliter()){
    			List<Spliter> spliters = p.getSpliters();
    			for(int i=0;i<spliters.size();i++){
    				Spliter spliter=spliters.get(i);
    				//System.out.println(spliter.getName());
    				if(!spliter.getValue().isEmpty()){
    					g2d.drawString(spliter.getValue(), spliter.getX(), spliter.getY());
    				}
    			}
    		}else{
	            int x = field.getPaper().getX();
	            int y = field.getPaper().getY();
	            g2d.drawString(str, x, y);
    		}
    	}

	}

    /**
     * implements Printable
     * PageFormat
     */
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        
       // System.out.println(pageIndex);
         
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        
        java.util.List<Field> fields=form.getFields();
        
        for (int i = 0; i < fields.size(); i++) {
        	Field field= fields.get(i);
        	paintField(g2d,field);
        }
        return Printable.PAGE_EXISTS;
    }

    public static void main(String[] args) {
    	Form form=new FormParser().parseForm(new File("./forms/�?1.xml"));
    	java.util.List<Field> fields=form.getFields();
    	
    	fields.get(0).setValue("field1111");
    	
    	fields.get(2).setValue("2222222222");
    	
        Printer.printReport(new PrintableForm(form));
    }

  

}
