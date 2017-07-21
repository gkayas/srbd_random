package fxviewer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class CustomConsole extends OutputStream{

	private String currentLine;
	int lineCount;
	private TextArea    output;
	List <Byte> data = new ArrayList <Byte> ();

	public CustomConsole(TextArea ta){
		this.output = ta;
		this.currentLine = "";
		lineCount=0;
	}

//	public void appendText(String valueOf) {
//		Platform.runLater(() -> output.appendText(valueOf));
//	}

	private void writteByte(int d) {
		char c = (char) d;
		if(c == '\n'){
			lineCount++;
			if(currentLine.contains("app execution start...")){
				output.clear();
				lineCount=0;
				currentLine += c;
				output.appendText(currentLine);
				currentLine = "";
				return;
			}
			else if(lineCount > 300)
			{
				output.clear();
				lineCount=0;
				currentLine += c;
				output.appendText(currentLine);
				currentLine = "";
				return;
			}
			currentLine = "";
		}else{
			currentLine += c;
		}
		output.appendText(String.valueOf(c));
    }

	@Override
	public void write(int i) throws IOException{
		Platform.runLater(() -> writteByte(i));
		//fireDataWritten(i);
	}

}