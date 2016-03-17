package cn.by.eform.model;

import java.util.ArrayList;
import java.util.List;

public class UI {
	private String type;
	private String desc;
	private List<Option> options = new ArrayList<Option>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public List<String> getKeys() {
		List<String> keys = new ArrayList<String>();
		for (int i = 0; i < options.size(); i++) {
			keys.add(options.get(i).getKey());
		}
		return keys;
	}

	public List<String> getValues() {
		List<String> values = new ArrayList<String>();
		for (int i = 0; i < options.size(); i++) {
			values.add(options.get(i).getValue());
		}
		return values;
	}

}
