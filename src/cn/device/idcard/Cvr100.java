package cn.device.idcard;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;

public class Cvr100 {

	private String strTmp = "";
	private int len = -1;
	private String CVR100_DLL = "Termb.dll";

	public int CVR_InitComm(int Port) throws NativeException,
			IllegalAccessException, UnsupportedEncodingException {
		JNative n = null;
		try {
			n = new JNative(CVR100_DLL, "CVR_InitComm");
			n.setRetVal(Type.INT);
			n.setParameter(0, Port);
			n.invoke();
			return Integer.parseInt(n.getRetVal());
		} finally {

		}
	}

	public int GetPeopleName() throws NativeException, IllegalAccessException {
		JNative n = null;
		try {
			n = new JNative(CVR100_DLL, "GetPeopleName");
			n.setRetVal(Type.INT);
			Pointer a = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 10));
			Pointer b = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 30));
			n.setParameter(0, b);
			n.setParameter(1, a);
			n.invoke();
			byte[] by = new byte[120];
			by = b.getMemory();
			try {
				strTmp = new String(by, "gb2312");
			} catch (UnsupportedEncodingException ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						null, ex);
			}

			byte[] bt = new byte[40];
			bt = a.getMemory();
			len = a.getAsInt(0);
			a.dispose();
			b.dispose();
			return Integer.parseInt(n.getRetVal());
		} finally {

		}
	}

	public int GetPeopleAddress() throws NativeException,
			IllegalAccessException {
		JNative n = null;
		try {
			n = new JNative(CVR100_DLL, "GetPeopleAddress");
			n.setRetVal(Type.INT);
			Pointer a = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 10));
			Pointer b = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 30));
			n.setParameter(0, b);
			n.setParameter(1, a);
			n.invoke();
			byte[] by = new byte[120];
			by = b.getMemory();
			try {
				strTmp = new String(by, "gb2312");
			} catch (UnsupportedEncodingException ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						null, ex);
			}

			len = a.getAsInt(0);

			a.dispose();
			b.dispose();
			return Integer.parseInt(n.getRetVal());
		} finally {

		}
	}

	public int GetPeopleIDCode() throws NativeException, IllegalAccessException {
		JNative n = null;
		try {
			n = new JNative(CVR100_DLL, "GetPeopleIDCode");
			n.setRetVal(Type.INT);
			Pointer a = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 10));
			Pointer b = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 30));
			n.setParameter(0, b);
			n.setParameter(1, a);
			n.invoke();
			byte[] by = new byte[120];
			by = b.getMemory();
			try {
				strTmp = new String(by, "gb2312");
			} catch (UnsupportedEncodingException ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						null, ex);
			}

			len = a.getAsInt(0);

			a.dispose();
			b.dispose();
			return Integer.parseInt(n.getRetVal());
		} finally {

		}
	}

	public int GetPeopleSex() throws NativeException, IllegalAccessException {
		JNative n = null;
		try {
			n = new JNative(CVR100_DLL, "GetPeopleSex");
			n.setRetVal(Type.INT);
			Pointer a = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 10));
			Pointer b = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 30));
			n.setParameter(0, b);
			n.setParameter(1, a);
			n.invoke();
			byte[] by = new byte[120];
			by = b.getMemory();
			try {
				strTmp = new String(by, "gb2312");
			} catch (UnsupportedEncodingException ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						null, ex);
			}

			len = a.getAsInt(0);

			a.dispose();
			b.dispose();
			return Integer.parseInt(n.getRetVal());
		} finally {

		}
	}

	public int GetDepartment() throws NativeException, IllegalAccessException {
		JNative n = null;
		try {
			n = new JNative(CVR100_DLL, "GetDepartment");
			n.setRetVal(Type.INT);
			Pointer a = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 10));
			Pointer b = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 30));
			n.setParameter(0, b);
			n.setParameter(1, a);
			n.invoke();
			byte[] by = new byte[120];
			by = b.getMemory();
			try {
				strTmp = new String(by, "gb2312");
			} catch (UnsupportedEncodingException ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						null, ex);
			}

			len = a.getAsInt(0);

			a.dispose();
			b.dispose();
			return Integer.parseInt(n.getRetVal());
		} finally {

		}
	}

	public int GetEndDate() throws NativeException, IllegalAccessException {
		JNative n = null;
		try {
			n = new JNative(CVR100_DLL, "GetEndDate");
			n.setRetVal(Type.INT);
			Pointer a = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 10));
			Pointer b = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 30));
			n.setParameter(0, b);
			n.setParameter(1, a);
			n.invoke();
			byte[] by = new byte[120];
			by = b.getMemory();
			try {
				strTmp = new String(by, "gb2312");
			} catch (UnsupportedEncodingException ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						null, ex);
			}

			len = a.getAsInt(0);

			a.dispose();
			b.dispose();
			return Integer.parseInt(n.getRetVal());
		} finally {

		}
	}

	public int CVR_Authenticate() throws NativeException,
			IllegalAccessException {
		JNative n = null;
		try {
			n = new JNative(CVR100_DLL, "CVR_Authenticate");
			n.setRetVal(Type.INT);
			n.invoke();
			return Integer.parseInt(n.getRetVal());
		} finally {

		}
	}

	public int CVR_Read_Content(int Active) throws NativeException,
			IllegalAccessException {
		JNative n = null;
		try {
			n = new JNative(CVR100_DLL, "CVR_Read_Content");
			n.setRetVal(Type.INT);
			n.setParameter(0, Active);
			n.invoke();
			return Integer.parseInt(n.getRetVal());
		} finally {

		}
	}

	public int CVR_CloseComm() throws NativeException, IllegalAccessException {
		JNative n = null;
		try {
			n = new JNative(CVR100_DLL, "CVR_CloseComm");
			n.setRetVal(Type.INT);
			n.invoke();
			return Integer.parseInt(n.getRetVal());
		} finally {

		}
	}

	public String getStringTmp() {
		return strTmp;
	}

	public int GetPeopleBirthday() throws NativeException,
			IllegalAccessException {
		JNative n = null;
		try {
			n = new JNative(CVR100_DLL, "GetPeopleBirthday");
			n.setRetVal(Type.INT);
			Pointer a = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 10));
			Pointer b = new Pointer(
					MemoryBlockFactory.createMemoryBlock(4 * 30));
			n.setParameter(0, b);
			n.setParameter(1, a);
			n.invoke();
			byte[] by = new byte[120];
			by = b.getMemory();
			try {
				strTmp = new String(by, "gb2312");
			} catch (UnsupportedEncodingException ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
						null, ex);
			}

			len = a.getAsInt(0);

			a.dispose();
			b.dispose();
			return Integer.parseInt(n.getRetVal());
		} finally {

		}
	}
}
