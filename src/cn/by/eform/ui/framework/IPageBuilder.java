package cn.by.eform.ui.framework;

import javax.swing.JComponent;

import cn.by.eform.model.Field;
import cn.by.eform.model.Form;

public interface IPageBuilder {
	
	public JComponent buildForm(Form form);
	
	public JComponent buildChoiceFiled(Field field);

	public JComponent buildSubFormField(Field field);

}
