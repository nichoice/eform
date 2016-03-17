package cn.by.eform.ui.trigger;

import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;

import javax.swing.event.AncestorEvent;

import cn.by.eform.model.Field;
import cn.by.eform.ui.framework.ITrigger;
import cn.device.idcard.IDCardReader;

public class IDCardTrigger implements ITrigger{

	private IDCardReader idcardReader=new IDCardReader();
	
	private Field field;

	@Override
	public void ancestorAdded(AncestorEvent event) {
		// TODO Auto-generated method stub
		/*System.out.println(event);
		System.out.println(event.getAncestor());
		System.out.println(event.getAncestorParent());
		System.out.println(event.getSource());
		System.out.println(event.getComponent());
		System.out.println("))");*/
		
		if(!event.getAncestor().getBounds().equals(new Rectangle(0,0,0,0))){
			idcardReader=new IDCardReader();
			idcardReader.setField(getField());
			//if(!idcardReader.isAlive()){
			idcardReader.start();
			//}
		}
		
	}

	@Override
	public void ancestorRemoved(AncestorEvent event) {
		// TODO Auto-generated method stub
		//System.out.println("ancestorRemoved");
		idcardReader.disactive();
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

}
