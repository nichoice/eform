package cn.by.eform.ui.framework;

import cn.by.eform.model.Form;

public interface ISelectionListener {
	public void selected(Form form);
	public void selectedGroup(String path);
}
