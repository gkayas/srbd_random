package com.turbovnc.vncviewer;

import java.io.File;

public class FileTreeElement {
	 private String fileName;
	 private File file;
	 
	 public FileTreeElement(String fileName) {
		 this.fileName =fileName;
		 file = new File(fileName);
	 }
	 
	 public String getfileName() {
		 return fileName;
	 }
	 
	 public File getFile() {
		 return file;
	 }
	 
	 @Override
	 public String toString() {
		 return file.getName();
	 } 
}
