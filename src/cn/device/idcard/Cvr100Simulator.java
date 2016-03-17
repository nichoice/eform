package cn.device.idcard;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;

public class Cvr100Simulator extends Cvr100{

	private String strTmp = "";
	private int len = -1;

	String idCode = "123412341234";
	String name = "测试";
	String birthday = "1985年08月08日";
	String address = "广州市天河区中山大道西88号";
	String sex = "男";
	String department = "天河公安局";
	String enddate = "8888年8月8日";
	
	public int CVR_InitComm(int Port) throws NativeException,
			IllegalAccessException, UnsupportedEncodingException {
		return 1;
	}

	public int GetPeopleName() throws NativeException, IllegalAccessException {
		strTmp=name;
		return 1;
	}

	public int GetPeopleAddress() throws NativeException,
			IllegalAccessException {
		strTmp=address;
		return 1;
	}

	public int GetPeopleIDCode() throws NativeException, IllegalAccessException {
		strTmp=idCode;
		return 1;
	}

	public int GetPeopleSex() throws NativeException, IllegalAccessException {
		strTmp=sex;
		return 1;
	}

	public int GetDepartment() throws NativeException, IllegalAccessException {
		strTmp=department;
		return 1;
	}

	public int GetEndDate() throws NativeException, IllegalAccessException {
		strTmp=enddate;
		return 1;
	}

	public int CVR_Authenticate() throws NativeException,
			IllegalAccessException {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public int CVR_Read_Content(int Active) throws NativeException,
			IllegalAccessException {
		return 1;
	}

	public int CVR_CloseComm() throws NativeException, IllegalAccessException {
		return 1;
	}

	public String getStringTmp() {
		return strTmp;
	}

	public int GetPeopleBirthday() throws NativeException,
			IllegalAccessException {
		strTmp=birthday;
		return 1;
	}
}
