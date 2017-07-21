package com.turbovnc.vncviewer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import javax.swing.text.JTextComponent;

import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.fife.ui.autocomplete.ShorthandCompletion;

public class CustomCompletionProvider extends DefaultCompletionProvider {

	private ArrayList<Completion> keyWords = new ArrayList<Completion>(); 
	private ArrayList<String> keyWordsList = new ArrayList<String>(); 
	
	private Hashtable<String, ArrayList<Completion>> apisForKeys = new Hashtable<String, ArrayList<Completion>>();
	
	public void addApi(String key, String api) {
		addKeyWord(key);
		key = key+".";
		if(null == apisForKeys.get(key)) {
			apisForKeys.put(key, new ArrayList<Completion>());
		}
		Completion com = new  ShorthandCompletion(this, api, api+";");
		apisForKeys.get(key).add(com);
		addCompletion(com);
	}
	
	public void addKeyWord(String key) {
		if(!keyWordsList.contains(key)) {
			keyWordsList.add(key);
			BasicCompletion basic = new BasicCompletion(this, key);
			keyWords.add(basic);
			addCompletion(basic);
		}

	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Completion> getCompletionByInputText(String inputText) {

		if(apisForKeys.containsKey(inputText)) {
			return apisForKeys.get(inputText);
		}
		
		// Find any entry that matches this input text (there may be > 1).
		int end = Collections.binarySearch(completions, inputText, comparator);
		if (end<0) {
			return null;
		}
		
		// There might be multiple entries with the same input text.
		int start = end;
		while (start>0 &&
				comparator.compare(completions.get(start-1), inputText)==0) {
			start--;
		}
		int count = completions.size();
		while (++end<count &&
				comparator.compare(completions.get(end), inputText)==0);

		return completions.subList(start, end); // (inclusive, exclusive)

	}
	
	String getCursorWord(int pos, String text) {
		
		String lastWord = "";
		char[] ar = text.toCharArray();
		for (int i = pos-1 ; i >= 0; i--) {
			if((ar[i] == ' ' || ar[i] == '.' || ar[i] == '\n') && i!=pos-1){
				break;
			}else{
				lastWord = ar[i] + lastWord;
			}
		}
		return lastWord;
	}
	
	@Override
	public List<Completion> getCompletions(JTextComponent comp) {
		
		String lastWord = getCursorWord(comp.getCaretPosition(), comp.getText());
//		System.out.println("lastword "+lastWord);

		if(apisForKeys.containsKey(lastWord)) {
			return apisForKeys.get(lastWord);
		}

		return super.getCompletions(comp);
	}
	
	@Override
	public List<Completion> getCompletionsAt(JTextComponent comp, Point p) {

		return getCompletionByInputText(comp.getText());
	}

	@Override
	public List<ParameterizedCompletion> getParameterizedCompletions(JTextComponent tc) {
		
		return super.getParameterizedCompletions(tc);
	}


}
