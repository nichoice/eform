package cn.by.eform.model;

import java.util.HashMap;
import java.util.Map;

public class Validator {
	private String name = "";
	private String validatorClass = "";
	private Map<String, String> properies = new HashMap<String, String>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValidatorClass() {
		return validatorClass;
	}

	public void setValidatorClass(String ValidatorClass) {
		this.validatorClass = ValidatorClass;
	}

	public Map<String, String> getProperies() {
		return properies;
	}

	public void setProperies(Map<String, String> properies) {
		this.properies = properies;
	}

}
