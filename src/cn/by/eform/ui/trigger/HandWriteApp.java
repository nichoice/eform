package cn.by.eform.ui.trigger;

import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.event.AncestorEvent;

import cn.by.eform.model.Field;
import cn.by.eform.ui.framework.ITrigger;

public class HandWriteApp implements MouseListener,ITrigger{
	
	private Field field;
	
	private Process p = null;
	
	public void showMe(){
		/*new Runnable(){

		@Override
		public void run() {
			*/
			// TODO Auto-generated method stub
			Runtime rn = Runtime.getRuntime();
			

			try {
				p = rn.exec("./shouxie/by.exe", null, new File(
						"./shouxie/"));
				//p.waitFor();
			} catch (Exception ex) {
				System.out.println("Error exec AnyQ");
			}
			
		/*}
		
	}.run();*/
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		showMe();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorAdded(AncestorEvent event) {
		// TODO Auto-generated method stub
		showMe();
	}

	@Override
	public void ancestorRemoved(AncestorEvent event) {
		// TODO Auto-generated method stub
		p.destroy();
	}

	@Override
	public void ancestorMoved(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setField(Field field) {
		// TODO Auto-generated method stub
		this.field=field;
	}

	@Override
	public Field getField() {
		// TODO Auto-generated method stub
		return field;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

}
