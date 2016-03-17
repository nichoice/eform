package cn.by.eform.draft;

import java.io.File;

public class ListModel {
	private File file;
	private String name;

	public ListModel(File file) {
		this.file = file;
		String fullName = file.getName();
		this.name = fullName.substring(0, fullName.lastIndexOf("."));
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
