package cn.by.eform.ui.framework;

import java.io.File;

import javax.swing.JComponent;

public interface INavBuilder {
	public JComponent buildNav(File formFolder, ISelectionListener listener);
}
