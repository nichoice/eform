package cn.by.eform.ui.validator;

import java.util.regex.Pattern;

import com.jgoodies.validation.ValidationResult;

import cn.by.eform.model.Field;
import cn.by.eform.ui.framework.IValidator;

public class CellPhoneNumberValidator extends ValidatorAdapter {
	
	@Override
	public ValidationResult validate() {
		// TODO Auto-generated method stub
		ValidationResult result=super.validate();
		String value=this.getField().getValue();
		if(!Pattern.matches("^1[0-9]{10}$",value)){
			result.addError("请输入合法的手机号");
		}
		
		if(value.length()!=11){
			result.addInfo("手机号码必须为11位");
		}
		
		return result;
	}

}
