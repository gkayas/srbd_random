package com.turbovnc.vncviewer;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public abstract class CodeGenerator implements ICodeGenerator, ActionListener{

	protected CConn cc;

	public CodeGenerator(CConn cc_) {
		cc = cc_;
	}

	abstract double getScaledX(int x);
	abstract double getScaledY(int y);
	abstract void createCodeBox(int x, int y);
	abstract void closeCodeBox();
	abstract CodeBox getCodeBox();
	abstract boolean isCodeRunning();
	abstract void performCompileRun();
	abstract void writeCode();
	abstract void generateRCPTTCode();
	abstract void setCursorPosition(int x, int y);
	abstract void updateFileTree(String parent,String newFile);
		
	protected BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	        return (BufferedImage) img;

	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();
	    // Return the buffered image
	    return bimage;
	}
	
	protected boolean isWindows() {
		String osName = System.getProperty("os.name");

		if (osName.toLowerCase().contains("windows")) {
			return true;
		}
		return false;
	}
}
