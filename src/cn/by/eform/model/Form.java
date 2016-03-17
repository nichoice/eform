package cn.by.eform.model;

import java.util.ArrayList;
import java.util.List;

public class Form {
	
	private String name;
	
	private List<Field> fields = new ArrayList<Field>();
	
	//private int fieldCount;
	
	public Form() {

	}

	public void addField(Field field) {
		fields.add(field);
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public void resetData() {
		for(int i=0;i<fields.size();i++){
			fields.get(i).resetData();
		}
	}
/*
	public int getFieldCount() {
		return fieldCount;
	}

	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
