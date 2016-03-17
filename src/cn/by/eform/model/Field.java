package cn.by.eform.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import com.jgoodies.binding.beans.Model;


public class Field extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2752912797321481800L;
	
	private UI ui;
	private Paper paper;
	private String column;
	private String value="";
	
	private String name;
	private String title;
	private String desc;
	private int fontSize;
	
	private String type;
	
	private Trigger trigger=new Trigger();
	
	private Field parent;
	
	private Form form;
	
	private List<Field>subFields=new ArrayList<Field>();

	private Validator validator=new Validator();
	
	private JComponent component;

	public UI getUi() {
		return ui;
	}

	public void setUi(UI ui) {
		this.ui = ui;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		
		String oldValue = this.value;
		this.value = value;
		firePropertyChange("value", oldValue, value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public List<Field> getSubFields() {
		return subFields;
	}

	public void setSubFields(List<Field> subFields) {
		this.subFields = subFields;
	}

	public boolean hasSubFields(){
		return subFields.size()>0;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}
	
	public Field getParent() {
		return parent;
	}

	public void setParent(Field parent) {
		this.parent = parent;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public void resetData(){
		if(hasSubFields()){
			for(int i=0;i<subFields.size();i++){
				subFields.get(i).resetData();
			}
		}else{
			setValue("");
		}
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator=validator;
	}

	public JComponent getComponent() {
		return component;
	}

	public void setComponent(JComponent component) {
		this.component = component;
	}
	
}
