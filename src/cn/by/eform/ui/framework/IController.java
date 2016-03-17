package cn.by.eform.ui.framework;

import java.io.File;

import cn.by.eform.model.Form;

public interface IController {
	
	//public void setNavBuilder(INavBuilder navBuilder);

	//public void setPageBuilder(IPageBuilder builder);
	
	public Form getForm();

	public void print();

	public void resetForm();

	public void resetField();

	public void reflash();

	void setForm(Form form);
}
