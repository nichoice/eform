package cn.by.eform.ui.trigger;

import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;

import javax.swing.event.AncestorEvent;

import cn.by.eform.model.Field;
import cn.by.eform.ui.framework.ITrigger;

public class TriggerAdapter implements ITrigger {
	
	private Field field;

	@Override
	public void ancestorAdded(AncestorEvent event) {
		// TODO Auto-generated method stub

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
