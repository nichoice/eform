package cn.by.eform.ui.factory;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.adapter.RadioButtonAdapter;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ConverterFactory;
import com.jgoodies.binding.value.ConverterValueModel;
import com.jgoodies.binding.value.ValueModel;
import com.seaglasslookandfeel.SeaGlassStyle;

import cn.by.eform.model.Field;
import cn.by.eform.model.Option;
import cn.by.eform.model.Trigger;
import cn.by.eform.model.UI;
import cn.by.eform.ui.framework.ITrigger;
import cn.by.eform.ui.framework.IValidator;
import cn.by.eform.ui.trigger.HandWriteApp;
import cn.by.eform.ui.trigger.NumberKeyboard;
import cn.by.eform.ui.trigger.SoundPlay;
import cn.by.eform.ui.trigger.TriggerCache;

/**
 * simple factory implement use abstract factory later,if the Field type is
 * complex enough
 * 
 * @author Henry
 * 
 */
public class FieldFactory {
	
	public static Font font=new Font("隶书", Font.PLAIN, 40);
	
	public static Color color=new Color(70,11,11);
	
	public static JLabel createLabel(String name) {
		//Border border=BorderFactory.createLineBorder(Color.BLACK);
		
		JLabel label = new JLabel(name);
		//label.setBorder(border);
		label.setFont(font);
		label.setForeground(color);
		
		return label;
	}
	
	public static JPanel createPanel(){
		JPanel panel=new JPanel();
		panel.setOpaque(false);
		return panel;
	}
	
	public static JButton createButton(String name) {
		JButton button = new JButton(name);
		
		button.setFont(new Font("隶书", Font.PLAIN, 40));
		button.putClientProperty("JComponent.sizeVariant", SeaGlassStyle.NATURAL_KEY);
		//button.putClientProperty("JButton.buttonType", "segmentedTextured");
		button.setForeground(color);
		button.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				new SoundPlay().playOnce("beep");
			}
		});
		
		return button;
	}

	public static JComponent create(Field field) {
		UI ui = field.getUi();
		String type = ui.getType();
		Trigger trigger=field.getTrigger();
		//ITrigger it=TriggerCache.getInstance().getTrigger(trigger.getTriggerClass());

		List<Option> options = ui.getOptions();
		
		BeanAdapter beanAdapter = new BeanAdapter(field, true);
		ValueModel stringValueModel = beanAdapter.getValueModel("value");

		JComponent compn = null;

		// @TODO: use abstract factory to avoid so many 'if' later
		if (type.equals("text")||type.equals("textarea")) {
			JTextArea textArea = BasicComponentFactory.createTextArea(stringValueModel);
			textArea.setAutoscrolls(true);
			textArea.setLineWrap(true);
			//textArea.setToolTipText(field.getDesc());
			//textArea.setRows(2);

			if (!trigger.getTriggerClass().isEmpty()) {
				textArea.addFocusListener(TriggerCache.getInstance().getTrigger(field));
				textArea.addMouseListener(TriggerCache.getInstance().getTrigger(field));
				
				if(field.getParent()==null){
					textArea.addAncestorListener(TriggerCache.getInstance().getTrigger(field));
				}
			} else {
				textArea.addMouseListener(new HandWriteApp());
			}
			compn = textArea;

			//trigger.getTriggerClass();
		}
		
		if (type.equals("button")) {
			compn = new JButton(field.getName());
		}
		
		if (type.equals("toggle")){
			JToggleButton togBtn=new JToggleButton(field.getName());
			compn=togBtn;
		}

		if (type.equals("radio")) {
			JPanel panel = FieldFactory.createPanel();
			panel.setLayout(new GridLayout(1, 0, 0, 0));

			ButtonGroup bg=new ButtonGroup();
			for (int i = 0; i < options.size(); i++) {
				Option option = options.get(i);
				JRadioButton radio = createRadioBtn(stringValueModel,
						option.getValue(), option.getKey());
				panel.add(radio);
			}
			compn = panel;
		}

		if (type.equals("combo")) {
			ComboBoxModel cbModel = new ComboBoxAdapter(ui.getValues(),
					stringValueModel);
			compn = new JComboBox(cbModel);
		}
		
		if (type.equals("checkbox")){
			ConverterValueModel newVM=new ConverterValueModel(stringValueModel,new StringToBooleanConverter("√","",""));
			JCheckBox checkBox=BasicComponentFactory.createCheckBox(newVM, field.getName());
			checkBox.putClientProperty("JComponent.sizeVariant", SeaGlassStyle.LARGE_KEY);
			
			if (!trigger.getTriggerClass().isEmpty()) {
				checkBox.addMouseListener(TriggerCache.getInstance().getTrigger(field));
				checkBox.addAncestorListener(TriggerCache.getInstance().getTrigger(field));
			}
			compn = checkBox;
		}

		if (type.equals("list")) {
			SelectionInList selectionList = new SelectionInList(ui.getValues(),
					stringValueModel);

			compn = BasicComponentFactory.createList(selectionList);
		}
		
		if (type.equals("textarea")) {
			// ..
		}
		
		if (type.equals("idcard")){
			//..
		}
		
		if(compn==null){
			compn=new JLabel(ui.getType()+" not implemented!");
		}
		
		compn.setFont(font);
		compn.setForeground(color);
		
		field.setComponent(compn);
		
		return compn;
	}

	private static JRadioButton createRadioBtn(ValueModel valueModel,
			Object choice, String text) {
		JRadioButton radioBtn = new JRadioButton(text);
		RadioButtonAdapter rbAdapter = new RadioButtonAdapter(valueModel,
				choice);
		radioBtn.setModel(rbAdapter);
		return radioBtn;
	}

}
