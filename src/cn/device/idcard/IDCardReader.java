package cn.device.idcard;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.xvolks.jnative.exceptions.NativeException;

import cn.by.eform.model.Field;
import cn.by.eform.model.Paper;
import cn.by.eform.model.Spliter;
import cn.by.eform.ui.trigger.SoundPlay;

public class IDCardReader extends Thread {

	private static final String 截止日期 = "截止日期";
	private static final String 发证机关 = "发证机关";
	private static final String 性别 = "性别";
	private static final String 身份证号 = "身份证号";
	private static final String 姓名 = "姓名";
	private static final String 地址 = "地址";
	private static final String 出生日期 = "出生日期";
	
	
	private boolean stopFlag = false;
	private Cvr100 cvr100 = new Cvr100();
	//private Cvr100 cvr100 = new Cvr100Simulator();
	private SoundPlay sp = new SoundPlay();

	private Field field;
	
	public IDCardReader(){}

	public void setField(Field field) {
		this.field = field;
	}

	public boolean tryOpenComm() {

		boolean opened = false;
		for (int count = 0; count < 3; count++) {
			System.out.println("try open " + count + " times");
			int i = 0;
			try {
				i = cvr100.CVR_InitComm(5);
				System.out.println("open" + i);
				if (i == 1) {
					opened = true;
					break;
				}

				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (NativeException e) {
				e.printStackTrace();
			}

		}
		return opened;
	}

	public void run() {
		List<Field> subFields = field.getSubFields();

		HashMap<String, Field> map = new HashMap<String, Field>();
		for (int j = 0; j < subFields.size(); j++) {
			Field subField = subFields.get(j);
			map.put(subField.getName(), subField);
		}

		int i = 0;
		try {
			// sp.playOnce("beep");
			sp.playOnce("请放二代身份证");

			if (tryOpenComm()) {
				while (!stopFlag) {
					i = cvr100.CVR_Authenticate();
					System.out.println("auth" + i);

					if (1 == i && 1 == cvr100.CVR_Read_Content(1)) {
						
						String idCode = "";
						String name = "";
						String birthday = "";
						String address = "";
						String sex = "";
						String department = "";
						String enddate = "";

						System.out.println("readed");

						i = cvr100.GetPeopleIDCode();
						System.out.println("getIdcode" + i);
						idCode = cvr100.getStringTmp();

						cvr100.GetPeopleName();
						name = cvr100.getStringTmp();

						cvr100.GetPeopleAddress();
						address = cvr100.getStringTmp();

						cvr100.GetPeopleBirthday();
						birthday = cvr100.getStringTmp();
						
						cvr100.GetPeopleSex();
						sex = cvr100.getStringTmp();
						
						cvr100.GetDepartment();
						department = cvr100.getStringTmp();
						
						cvr100.GetEndDate();
						enddate = cvr100.getStringTmp();
						
						setValue(map, 姓名, name.trim());
						setValue(map, 地址, address.trim());
						setValue(map, 出生日期, birthday.trim());
						setValue(map, 身份证号, idCode.trim());
						setValue(map, 性别, sex.trim());
						setValue(map, 发证机关, department.trim());
						setValue(map, 截止日期, enddate.trim());
						
						// split
						splitAddress(map,address.trim());
						splitBirthday(map,birthday.trim());

						// break;
					} else {
						try {
							Thread.sleep(300);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}// Sleep(300);
					}
				}
				
			} else {
				sp.playOnce("打开二代证阅读器失败");
			}

		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (NativeException e1) {
			e1.printStackTrace();
		} finally {
			try {
				//System.out.println("try close");
				i = cvr100.CVR_CloseComm();
				System.out.println("close" + i);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NativeException e) {
				e.printStackTrace();
			}
		}
	}

	private void setValue(Map<String, Field> map, String name, String value) {
		if (map.containsKey(name)) {
			map.get(name).setValue(value);
		}
	}
	
	private void setSplitValue(Map<String, Field> map, String name,String splitName, String value) {
		if (map.containsKey(name)) {
			Paper p=map.get(name).getPaper();
			p.setSpiterValue(splitName, value);
		}
	}
	
	private void splitAddress(Map<String, Field> map,String address){
		Map<String,String> map2=split(address,"省市区县");
		Iterator<Entry<String, String>> itrt2 = map2.entrySet().iterator();
		while(itrt2.hasNext()){
			Entry<String, String> entry = itrt2.next();
			setSplitValue(map,地址,entry.getKey(),entry.getValue());
			//setValue(map,entry.getKey(),entry.getValue());
		}
		
		setSplitValue(map,地址,地址,map2.get("其他"));
		
	}
	
	private void splitBirthday(Map<String, Field> map,String birthday){
		Map<String,String> map1=split(birthday,"年月日");
		Iterator<Entry<String, String>> itrt = map1.entrySet().iterator();
		while(itrt.hasNext()){
			Entry<String, String> entry = itrt.next();
			setSplitValue(map,出生日期,entry.getKey(),entry.getValue());
			//setValue(map,entry.getKey(),entry.getValue());
		}
	}
	
	public static Map<String,String> split(String source,String spliters){
		
		Map<String,String> map=new HashMap<String,String>();
		
		int currentIndex=-1;
		for(int i=0;i<spliters.length();i++){
			String spliter=String.valueOf(spliters.charAt(i));
			int index=source.indexOf(spliter);
			
			if(index!=-1){
				map.put(spliter, source.substring(currentIndex+1, index));
				currentIndex=index;
			}
			
		}
		map.put("其他", source.substring(currentIndex+1));

		return map;
	}

	public void disactive() {
		stopFlag = true;
	}
}
