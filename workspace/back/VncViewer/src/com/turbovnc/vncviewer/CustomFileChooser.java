package com.turbovnc.vncviewer;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class CustomFileChooser extends JFileChooser {

	public CustomFileChooser(String cuurentDirPath) {
		super(cuurentDirPath);
	}

	@Override
	public void approveSelection() {
		if (getDialogType() == SAVE_DIALOG) {
			File selectedFile = getSelectedFile();
			String fileName = selectedFile.getName();

			String filePath = "";

			try {
				filePath = selectedFile.getCanonicalPath();
			} catch (IOException e) {
			}

			String refPath = System.getProperty("user.dir") + File.separator + "res" + File.separator + "ref_images";

			if (!filePath.contains(refPath)) {
				JOptionPane.showMessageDialog(this, "Please save ref image under " + refPath + " directory");
				return;
			}

			if (!(fileName.toLowerCase().contains(".png"))) {
				fileName = getCurrentDirectory().toString().trim() + File.separator + fileName + ".png";
				selectedFile = new File(fileName);
			}

			if ((selectedFile != null) && selectedFile.exists()) {
				int response = JOptionPane.showConfirmDialog(this, "The file " + selectedFile.getName()	+ " already exists. Do you want to replace the existing file?",	"Ovewrite file", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (response != JOptionPane.YES_OPTION)
					return;
			}
		}

		super.approveSelection();
	}
}
