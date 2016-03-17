package cn.by.eform.ui.impl;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import cn.by.eform.model.Field;
import cn.by.eform.model.Form;
import cn.by.eform.model.Trigger;
import cn.by.eform.ui.factory.FieldFactory;
import cn.by.eform.ui.framework.ITrigger;
import cn.by.eform.ui.framework.IValidator;
import cn.by.eform.ui.trigger.SoundPlay;
import cn.by.eform.ui.trigger.TriggerAdapter;
import cn.by.eform.ui.trigger.TriggerCache;
import cn.by.eform.ui.validator.ValidatorAdapter;
import cn.device.idcard.Cvr100;
import cn.device.idcard.IDCardReader;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.JTextComponent;

import org.xvolks.jnative.exceptions.NativeException;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationResultViewFactory;

public class StepPage extends TablePage{

	private CardLayout cl=new CardLayout(0, 0);
	
	private JPanel fieldPanel = FieldFactory.createPanel();
	
	private int currentStep=0;
	
	private List<Field> fields;

	private JLabel tipLabel;

	private JLabel titleLabel;
	
	private SoundPlay sp = new SoundPlay();

	//private NumberKeyboard numberKeyboard;
	/**
	 * Create the panel.
	 */
	public StepPage() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("50dlu"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("50dlu"),},
			new RowSpec[] {
				RowSpec.decode("30dlu"),
				RowSpec.decode("5dlu"),
				RowSpec.decode("30px"),
				RowSpec.decode("default:grow"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("67px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("10dlu"),}));
		
		titleLabel = FieldFactory.createLabel("");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(titleLabel, "1, 1, 5, 1");
		
		tipLabel = FieldFactory.createLabel("");
		tipLabel.setText("");
		tipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(tipLabel, "1, 3, 5, 1");
		add(fieldPanel, "2, 4, 3, 1, fill, fill");
		fieldPanel.setLayout(cl);
		
		final JButton btnNewButton_1 =FieldFactory.createButton("上一页");
		btnNewButton_1.setText("上一步");
		final JButton btnNewButton =FieldFactory.createButton("下一页");
		btnNewButton.setText("下一步");
		
		btnNewButton_1.setVisible(false);
		btnNewButton_1.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e) {
				 
				 IValidator validator=getValidator(fields.get(currentStep));
				 ValidationResult result = validator.validate();
				 if(result.hasErrors()){
					 showValidation(result);
				 }else{
					 btnNewButton.setVisible(true);
					 if(currentStep>0){
						 currentStep--;
						 Field field=fields.get(currentStep);
						 setTip(field);
						 cl.show(fieldPanel, fields.get(currentStep).getName());
						 requestFocus(getFirstText());
						 if(currentStep==0){
							 btnNewButton_1.setVisible(false);
						 }
					 }
				 }
			 }
		});
		add(btnNewButton_1, "2, 6, left, top");
		
		
		btnNewButton.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e) {
				 IValidator validator=getValidator(fields.get(currentStep));
				 ValidationResult result = validator.validate();
				 if(result.hasErrors()){
					 showValidation(result);
				 }else{
					 btnNewButton_1.setVisible(true);
					 if(currentStep<fields.size()-1){
						 currentStep++;
						 Field field=fields.get(currentStep);
						 setTip(field);
						 cl.show(fieldPanel, fields.get(currentStep).getName());
						 requestFocus(getFirstText());
					 }
					 
					 if(currentStep==fields.size()-1){
						 btnNewButton.setVisible(false);
					 }
				 }
			 }
		});
		add(btnNewButton, "4, 6, left, top");
		this.setOpaque(false);
	}
	
	
	public static void showMessageBox(final String strTitle,final String strMessage) {
		// Redone for larger OK button
		JOptionPane theOptionPane = new JOptionPane(strMessage,
				JOptionPane.ERROR_MESSAGE);
		
		JDialog theDialog = theOptionPane.createDialog(null, strTitle);
		theDialog.setVisible(true); // present your new optionpane to the world.
	}
	
	protected void showValidation(ValidationResult result) {
		// TODO Auto-generated method stub
		/*ValidationResultModel vrm=new DefaultValidationResultModel();
		vrm.setResult(result);
		final JComponent comp=ValidationResultViewFactory.createReportIconAndTextLabel(vrm);
		*/
		
		///*
		//UIManager.put("OptionPane.buttonFont", new FontUIResource(FieldFactory.font));
		//UIManager.put("OptionPane.messageFont", FieldFactory.font);
		
		JLabel customFontText = new JLabel();
		customFontText.setFont (new Font("隶书", Font.PLAIN, 30));
		String text="<html>";
		for(ValidationMessage vm:result.getMessages()){
			text+=vm.formattedText()+"<br>";
		}
		customFontText.setText (text+"</html>");
		customFontText.setOpaque(false);
		JOptionPane.showMessageDialog(this, customFontText, "输入错误", JOptionPane.ERROR_MESSAGE);
		//*/
		
		
		//showMessageBox("输入错误",result.getMessagesText());
		/*
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JDialog dlg=new JDialog();
					dlg.add(comp);
					dlg.setVisible(true);
					
					Rectangle rect=new Rectangle(350,350);
					
					Point location=new Point(337,280);//text.getLocationOnScreen();
					//location.x=312;
					//location.y+=FieldFactory.font.getSize()*1.3;
					rect.setLocation(location);
					dlg.setBounds(rect);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
	}

	@Override
	public JComponent buildForm(Form form) {
		
		fields = form.getFields();

		for (int i = 0; i < fields.size(); i++) {
			final Field field = fields.get(i);

			if (field.hasSubFields()) {

				if (field.getType().equals("choice")) {// add choice
					JComponent panel = buildChoiceFiled(field);
					fieldPanel.add(panel,field.getName());
				} else if (field.getType().equals("subform")) {// add subform
					JComponent panel = buildSubFormField(field);
					panel.addAncestorListener(TriggerCache.getInstance().getTrigger(field));
					fieldPanel.add(panel,field.getName());
				}

			} else { // add one field
				
				FormLayout layout=new FormLayout("pref:grow","");
				DefaultFormBuilder innerBuilder=new DefaultFormBuilder(layout);
				//innerBuilder.appendSeparator();
				innerBuilder.appendRow("5dlu");
				innerBuilder.nextLine();
				
				JComponent inputField=FieldFactory.create(field);
				
				innerBuilder.append(inputField);
				fieldPanel.add(innerBuilder.getPanel(),field.getName());
			}
		}
		
		setTip(fields.get(0));
		setTitle(form.getName());
		//numberKeyboard.changeEditor(getFirstText());
		requestFocus(getFirstText());
		return this;
	}
	
	public void setTitle(String title){
		titleLabel.setText(title);
	}
	

	public void setTip(Field field){
		String tips="";
		if(field.getType().equals("single")){
			String type=field.getUi().getType();
			if (("checkbox combo radio".indexOf(type) != -1)) {
				tips = "请选择";
				sp.playOnce("请选择");
			} else if (("text textarea".indexOf(type) != -1)) {
				tips = "请输入";
				sp.playOnce("请输入");
			}
		}
		
		if(field.getType().equals("choice")){
			tips="请选择";
			sp.playOnce("请选择");
		}
		
		tips+=field.getName();
		tipLabel.setText(tips);
	}

	private JTextComponent getFirstText(){
		Component[] cpns = fieldPanel.getComponents();
		
		JPanel vPanel=null;
		for(int i=0;i<cpns.length;i++){
			if(cpns[i].isVisible()){
				vPanel=(JPanel)cpns[i];
				break;
			}
		}
		
		Component[] subCpns=vPanel.getComponents();
		
		JTextComponent text=null;
		for(int i=0;i<subCpns.length;i++){
			if(subCpns[i] instanceof JTextComponent){
				text=(JTextComponent)subCpns[i];
				break;
			}
		}
		/*addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        text.requestFocus();
		    }
		}); */

		//text.grabFocus();
		
		return text;
		
	}
	
	private void requestFocus(final JComponent text){
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	if(text!=null){
		    		text.requestFocus();
		    	}
				//text.dispatchEvent(e);
		    }
		}); 
	}
	
	protected IValidator getValidator(Field field){
		String className=field.getValidator().getValidatorClass();

		IValidator validator = null;
		// System.out.println(className);
		if (className.isEmpty()) {
			validator = new ValidatorAdapter();
		} else {
			try {
				Object o = Class.forName(className).newInstance();
				validator = (IValidator) o;
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		validator.setField(field);
		return validator;
	}
	
}
