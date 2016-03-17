package cn.by.eform.ui.validator;

import com.jgoodies.validation.ValidationResult;

import cn.by.eform.model.Field;
import cn.by.eform.ui.framework.IValidator;

public class ValidatorAdapter implements IValidator {
	/*
	private int maxLength;
	private boolean allowEmpty=true;
	private int minLength;*/
	
	private Field field;

	@Override
	public ValidationResult validate() {
		// TODO Auto-generated method stub
		ValidationResult  result=new ValidationResult();
		validateMaxLength(result);
		return result;
	}

	@Override
	public void setField(Field field) {
		// TODO Auto-generated method stub
		this.field=field;
	}

	@Override
	public Field getField() {
		// TODO Auto-generated method stub
		return field;
	}
	
	public void validateMaxLength(ValidationResult result) {
		if (field.hasSubFields()) {
			return;
		}
		int length = field.getPaper().getMaxLength();
		if (length < field.getValue().length()) {
			result.addError("输入字符长度必须小于:" + length);
		}
	}

}
