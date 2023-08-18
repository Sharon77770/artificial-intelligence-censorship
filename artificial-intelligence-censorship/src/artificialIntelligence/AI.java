package artificialIntelligence;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.management.RuntimeErrorException;

import utills.Utill;

public class BadWordsChecker {

	private Vector<String> words;

	private String BadFile = "";

	public BadWordsChecker(String badFileDir) throws IOException {
		BadFile = badFileDir;
		words = Utill.loadLastInfo(BadFile);
	}
	
	public Vector<String> getWordsVector() { 
		return words;
	}
	
	public int getDataSize() {
		return words.size();
	}
	
	public void addInfo(String word) throws IOException {
		words.add(word);

		Utill.writeFileWithVector(words, BadFile);
	}

	public void learning(String word) throws IOException {		
		word = Utill.splitKorean(word);
		
		float maxPer = Utill.getMaxPer(words, word, BadFile);
		
		System.out.println(maxPer * 100 + "%Bad");
		
				
		if (maxPer > 0.6f) {
			if (maxPer != 1f)
				addInfo(word);
		}		
	}
	
	public boolean find(String str) {
		if (words.indexOf(str) != -1)
			return true;
		return false;
	}
	
	public void delInfo(String str) throws IOException {
		int idx = words.indexOf(str);
		
		if (idx != -1) {
			words.remove(idx);
			Utill.writeFileWithVector(words, BadFile);
		} 
		else {
			throw new RuntimeException("can't find string");
		}
	}
}
