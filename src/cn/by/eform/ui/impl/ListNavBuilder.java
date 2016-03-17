package cn.by.eform.ui.impl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;

import cn.by.eform.draft.FormParser;
import cn.by.eform.draft.ListModel;
import cn.by.eform.model.Form;
import cn.by.eform.ui.factory.FieldFactory;
import cn.by.eform.ui.framework.IController;
import cn.by.eform.ui.framework.INavBuilder;
import cn.by.eform.ui.framework.ISelectionListener;

public class ListNavBuilder implements INavBuilder {
	
	private JList list;
	private ISelectionListener listener;

	public File getFormFile(){
		ListModel listModel = (ListModel) list.getSelectedValue();
		return listModel.getFile();
	}

	@Override
	public JComponent buildNav(File formFolder, final ISelectionListener listener) {
		
		list = new JList();
		list.setOpaque(false);
		list.setFont(FieldFactory.font);
		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File formFile=getFormFile();
				listener.selected(new FormParser().parseForm(formFile));
			}
		});
		
		File[] files=formFolder.listFiles();
		DefaultListModel dlm=new DefaultListModel();
		for(int i=0;i<files.length;i++){
			dlm.addElement(new ListModel(files[i]));
		}
		
		list.setModel(dlm);
		
		return list;
	}

}
