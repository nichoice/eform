package cn.by.eform.ui.impl;

import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import cn.by.eform.draft.FormParser;
import cn.by.eform.draft.ListModel;
import cn.by.eform.ui.factory.FieldFactory;
import cn.by.eform.ui.framework.INavBuilder;
import cn.by.eform.ui.framework.ISelectionListener;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class ButtonNavBuilder implements INavBuilder{
	
	private DefaultFormBuilder builder;

	/**
	 * Create the panel.
	 */
	public ButtonNavBuilder() {
		FormLayout layout=new FormLayout("100dlu, 3dlu, default:grow,3dlu,100dlu","");
		builder=new DefaultFormBuilder(layout);
	}

	@Override
	public JComponent buildNav(File formFolder, final ISelectionListener listener) {
		builder.appendRow("35dlu");
		builder.nextLine();
		//builder.append("请选择表单进行操作");
		// TODO Auto-generated method stub
		File[] files=formFolder.listFiles();
		
		for(int i=0;i<files.length;i++){
			final File file=files[i];
			String fullName = file.getName();
			String name = fullName.substring(0, fullName.lastIndexOf("."));
			
			JButton button=FieldFactory.createButton(name);
			button.addMouseListener(new MouseAdapter(){
				 public void mouseClicked(MouseEvent e) {
					 File formFile=file;
						listener.selected(new FormParser().parseForm(formFile));
				 }
			});
			
			builder.append("",button,2);
			//builder.appendRow("5dlu");
			//builder.nextLine(2);
		}
		JPanel panel=builder.getPanel();
		panel.setOpaque(false);
		
		return panel;
	}

}
