package cn.by.eform.ui.impl;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.Printable;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import cn.by.eform.draft.FormParser;
import cn.by.eform.model.Field;
import cn.by.eform.model.Form;
import cn.by.eform.model.Trigger;
import cn.by.eform.print.PrintableForm;
import cn.by.eform.print.Printer;
import cn.by.eform.ui.factory.FieldFactory;
import cn.by.eform.ui.framework.IPageBuilder;
import cn.by.eform.ui.framework.ITrigger;

public class TablePage extends JPanel implements IPageBuilder {

	private DefaultFormBuilder builder;

	/**
	 * Create the panel.
	 */
	public TablePage() {
		String cols = "right:pref, 10dlu, 100dlu:grow";
		String rows = "";

		FormLayout layout = new FormLayout(cols, rows);

		builder = new DefaultFormBuilder(layout,this);
	}

	public JComponent buildChoiceFiled(Field field) {
		List<Field> subFields = field.getSubFields();
		String cols="right:pref, 10dlu, 100dlu:grow";
		if(subFields.size()>5){
			cols="10dlu, 10dlu, 200dlu,10dlu,10dlu,10dlu,200dlu";
		}
		FormLayout layout=new FormLayout(cols,"");
		DefaultFormBuilder innerBuilder=new DefaultFormBuilder(layout);
		//JPanel panel = new JPanel(new GridLayout(1, 0, 0, 0));
		innerBuilder.appendRow("5dlu");
		innerBuilder.nextLine();

		
		for (int j = 0; j < subFields.size(); j++) {
			Field subField = subFields.get(j);
			innerBuilder.append("",FieldFactory.create(subField));
		}
		JPanel panel=innerBuilder.getPanel();
		panel.setOpaque(false);
		return panel;
	}

	public JComponent buildSubFormField(Field field) {
		List<Field> subFields = field.getSubFields();

		String cols = "right:pref, 10dlu, 100dlu:grow";
		String rows = "";
		FormLayout layout = new FormLayout(cols, rows);
		DefaultFormBuilder innerBuilder = new DefaultFormBuilder(layout);
		innerBuilder.appendRow("5dlu");
		innerBuilder.nextLine();

		for (int j = 0; j < subFields.size(); j++) {
			Field subField = subFields.get(j);
			
			JComponent comp=FieldFactory.create(subField);
			
			String label="";
			if(comp instanceof JCheckBox){
			}else{
				label=subField.getName();
			}
			innerBuilder.append(FieldFactory.createLabel(label),
					comp);
			
			innerBuilder.appendRow("5dlu");
			innerBuilder.nextLine(2);
		}

		JPanel panel = innerBuilder.getPanel();
		panel.setOpaque(false);
		return panel;
	}

	public JComponent buildForm(Form form) {
		
		//builder.appendSeparator(title);
		
		List<Field> fields = form.getFields();

		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);

			if (field.hasSubFields()) {

				if (field.getType().equals("choice")) {// add choice
					JComponent panel = buildChoiceFiled(field);
					builder.append(FieldFactory.createLabel(field.getName()),
							panel);
				} else if (field.getType().equals("device")) {// add device
					JComponent panel = buildSubFormField(field);
					panel.setBorder(new TitledBorder(field.getName()));
					builder.append(panel, 3);

				}

			} else { // add one field

				builder.append(FieldFactory.createLabel(field.getName()),
						FieldFactory.create(field));
			}
		}
		//this.setOpaque(false);
		return this;
		
	}

}
