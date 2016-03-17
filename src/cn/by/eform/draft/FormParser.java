package cn.by.eform.draft;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.by.eform.model.Field;
import cn.by.eform.model.Form;
import cn.by.eform.model.Option;
import cn.by.eform.model.Paper;
import cn.by.eform.model.Spliter;
import cn.by.eform.model.Trigger;
import cn.by.eform.model.UI;
import cn.by.eform.model.Validator;
import cn.by.eform.ui.factory.FieldFactory;

public class FormParser {
	
	
	public void parsePaper(Paper paper,Element paperE){
		paper.setFontSize(Integer.valueOf(paperE.attributeValue("fontSize")));
		paper.setX(Integer.valueOf(paperE.attributeValue("x")));
		paper.setY(Integer.valueOf(paperE.attributeValue("y")));
		paper.setMaxLength(Integer.valueOf(paperE.attributeValue("maxLength")));
		
		List<?> spliterEList=paperE.elements("spliter");
		List<Spliter> spliterList=new ArrayList<Spliter>();
		for(int i=0;i<spliterEList.size();i++){
			Element spliterE=(Element) spliterEList.get(i);
			
			Spliter spliter=new Spliter();
			parsePaper(spliter,spliterE);
			
			String name=spliterE.attributeValue("name");
			spliter.setName(name);
			spliterList.add(spliter);
		}
		
		paper.setSpliters(spliterList);
	}
	
	public Field parseField(Form form,Element fieldE){
		
		Field field=new Field();
		field.setForm(form);
		//common attribute
		field.setName(fieldE.attributeValue("name"));
		field.setColumn(fieldE.attributeValue("column"));
		String type=fieldE.attributeValue("type");
		field.setType(type==null?"single":type);
		
		Element triggerE=fieldE.element("trigger");
		if(triggerE!=null){
			Trigger trigger=new Trigger();
			trigger.setName(triggerE.attributeValue("name"));
			trigger.setTriggerClass(triggerE.attributeValue("triggerClass"));
			field.setTrigger(trigger);
		}
		
		List<?>subFieldListE=fieldE.elements("field");
		
		// if has sub field
		if(subFieldListE.size()>0){
			//System.out.println(field.getName()+" has subFieldLIst");
			List<Field>subFieldList=new ArrayList<Field>();
			for(int i=0;i<subFieldListE.size();i++){
				Element subFieldE=(Element) subFieldListE.get(i);
				Field subField=parseField(form,subFieldE);
				subField.setParent(field);
				subFieldList.add(subField);
			}
			field.setSubFields(subFieldList);
		}else{
			//has no sub-field
			UI ui=new UI();
			Element uiE=fieldE.element("ui");
			ui.setType(uiE.attributeValue("type"));
			List<?> optionElist=uiE.elements("option");
			
			List<Option> options=new ArrayList<Option>();
			
			for(int j=0;j<optionElist.size();j++){
				Element optionE=(Element) optionElist.get(j);
				String name=optionE.attributeValue("name");
				String value=optionE.attributeValue("value");
				
				Option option=new Option();
				option.setKey(name);
				option.setValue(value);
				
				options.add(option);
			}
			
			ui.setOptions(options);
			field.setUi(ui);
			
			//				
			Paper paper=new Paper();
			Element paperE=fieldE.element("paper");
			parsePaper(paper,paperE);
			field.setPaper(paper);
			
			//
			Validator validator=new Validator();
			Element vE=fieldE.element("validator");
			if(vE!=null){
				String name=vE.attributeValue("name");
				String className=vE.attributeValue("validatorClass");
				validator.setName(name);
				validator.setValidatorClass(className);
			}
			field.setValidator(validator);
		}
		return field;
	}
	
	/*	
	public List<Field> parseFieldList(Element formE){
		List<?> fieldsE=formE.elements("field");
		List<Field> fields = new ArrayList<Field>();
		for(int i=0;i<fieldsE.size();i++){
			Element fieldE=(Element) fieldsE.get(i);
			Field field=new Field();
			parseField(field,fieldE);
			fields.add(field);
		}
		return fields;
	}*/
	
	public Form parseForm(Element formE){
		Form eform=new Form();
		
		List<?> fieldsE=formE.elements("field");
		//System.out.println("field size:"+fieldsE.size());
		List<Field> fields = new ArrayList<Field>();
		for(int i=0;i<fieldsE.size();i++){
			Element fieldE=(Element) fieldsE.get(i);
			
			Field field=parseField(eform,fieldE);
			fields.add(field);
		}
		eform.setFields(fields);
		return eform;
	}
	
	public Form parseForm(File tmplFile){
		
		Form eform=null;
		try {
			Document doc = new SAXReader().read(tmplFile);
			Element formE=doc.getRootElement();
			eform=parseForm(formE);
			
			String name=tmplFile.getName();
			name=name.substring(0,name.indexOf("."));
			eform.setName(name);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return eform;
	}

	public FieldFactory parseUIFactory(File config){
		try {
			Document doc = new SAXReader().read(config);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
}
