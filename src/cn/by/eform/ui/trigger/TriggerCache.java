package cn.by.eform.ui.trigger;

import java.util.HashMap;
import java.util.Map;

import cn.by.eform.model.Field;
import cn.by.eform.ui.framework.ITrigger;

public class TriggerCache {

	private TriggerCache() {
	}

	public static TriggerCache triggerCache = new TriggerCache();

	public static TriggerCache getInstance() {
		return triggerCache;
	}

	private Map<String, ITrigger> cache = new HashMap<String, ITrigger>();

	public ITrigger getTrigger(Field field) {
		ITrigger trigger = null;
		
		// return id card reader each time
		String className=field.getTrigger().getTriggerClass();
		if(className.equals(IDCardTrigger.class.getName())){
			trigger = createTrigger(className);
			trigger.setField(field);
			return trigger;
		}
		
		if (!cache.containsKey(className)&&!className.isEmpty()) {
			trigger = createTrigger(className);
			trigger.setField(field);
			cache.put(className, trigger);
		} else {
			trigger = cache.get(className);
		}
		return trigger;
	}

	public ITrigger createTrigger(String className) {
		ITrigger trigger = null;
		//System.out.println(className);
		try {
			Object o = Class.forName(className).newInstance();
			trigger = (ITrigger) o;
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			
			trigger=new TriggerAdapter(){};
		}
		return trigger;
	}

}
