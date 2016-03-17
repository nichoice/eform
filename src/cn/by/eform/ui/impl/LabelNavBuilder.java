package cn.by.eform.ui.impl;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.by.eform.draft.FormParser;
import cn.by.eform.draft.ListModel;
import cn.by.eform.ui.factory.FieldFactory;
import cn.by.eform.ui.framework.INavBuilder;
import cn.by.eform.ui.framework.ISelectionListener;
import cn.by.eform.ui.trigger.SoundPlay;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class LabelNavBuilder implements INavBuilder{
	
	private DefaultFormBuilder builder;

	/**
	 * Create the panel.
	 */
	public LabelNavBuilder() {
		FormLayout layout=new FormLayout("10px,100px,center:330px,10px,90px,10px,center:330px,10px","");
		builder=new DefaultFormBuilder(layout);
	}

	@Override
	public JComponent buildNav(File formFolder, final ISelectionListener listener) {
		builder.appendRow("65dlu");
		builder.nextLine();
		//builder.append("请选择表单进行操作");
		// TODO Auto-generated method stub
		File[] files=formFolder.listFiles();
		
		for(int i=0;i<files.length;i++){
			final File file=files[i];
			String fullName = file.getName();
			String name = fullName.substring(0, fullName.lastIndexOf("."));
			
			JLabel label=FieldFactory.createLabel(name);
			//label.setForeground(Color.black);
			label.setForeground(new Color(70,11,11));
			//label.setIcon(bgImg1);
			/*ImageIcon bgImg1 = new ImageIcon("./img/btn.png");
			
			JLabel label=new JLabel(bgImg1);
			label.setText(name);
			label.setBounds(0, 0, bgImg1.getIconWidth(), bgImg1.getIconHeight());  
			*/
			label.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					File formFile = file;
					new SoundPlay().playOnce("beep");
					listener.selected(new FormParser().parseForm(formFile));
				}
			});
			
			builder.append(FieldFactory.createLabel(""),1);
			//builder.nextColumn();
			builder.append(label,1);
			
			if(i%2==1){
				builder.appendRow("52px");
				builder.nextLine(2);
			}
			//builder.nextLine();
			//builder.appendRow("5dlu");
			//builder.nextLine(2);
		}
		JPanel panel=builder.getPanel();
		panel.setOpaque(false);
		
		return panel;
	}

}
