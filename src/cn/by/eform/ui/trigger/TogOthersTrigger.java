package cn.by.eform.ui.trigger;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.event.AncestorEvent;

import cn.by.eform.model.Field;
import cn.by.eform.ui.framework.ITrigger;

public class TogOthersTrigger implements ITrigger {
	
	private Field field;

	@Override
	public void ancestorAdded(AncestorEvent event) {
		// TODO Auto-generated method stub
		if(!event.getAncestor().getBounds().equals(new Rectangle(0,0,0,0))){
			JToggleButton chkBox=(JToggleButton)event.getSource();
			boolean isSelected=chkBox.getModel().isSelected();
			
			Component[] cmps=chkBox.getParent().getComponents();
			
			for(Component c:cmps){
				if(c!=chkBox){
					//c.setVisible(!isSelected);
					c.setEnabled(isSelected);
				}
			}
		}
	}

	@Override
	public void ancestorRemoved(AncestorEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ancestorMoved(AncestorEvent event) {
		// TODO Auto-generated method stub

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
		JToggleButton chkBox=(JToggleButton)e.getSource();
		boolean isSelected=chkBox.getModel().isSelected();
		
		Component[] cmps=chkBox.getParent().getComponents();
		
		for(Component c:cmps){
			if(c!=chkBox){
				//c.setVisible(!isSelected);
				c.setEnabled(isSelected);
			}
		}
		
		/*
		Field parentField=getField().getParent();
		List<Field> siblings=parentField.getSubFields();
		
		for(int i=1;i<siblings.size();i++){
			JComponent cmpn=siblings.get(i).getComponent();
			cmpn.setEnabled(isSelected);
		}
		*/
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
	public void setField(Field field) {
		// TODO Auto-generated method stub
		this.field=field;
	}

	@Override
	public Field getField() {
		// TODO Auto-generated method stub
		return field;
	}

}
