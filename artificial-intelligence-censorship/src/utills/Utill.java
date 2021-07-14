package utills;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

public class Utill {
	
	final static String[] CHO = { "ぁ", "あ", "い", "ぇ", "え", "ぉ", "け", "げ", "こ", "さ", "ざ", "し", "じ", "す", "ず", "せ", "ぜ",
			"そ", "ぞ" };
	
	final static String[] JOONG = { "た", "だ", "ち", "ぢ", "っ", "つ", "づ", "て", "で", "と", "ど", "な", "に", "ぬ", "ね", "の", "は",
			"ば", "ぱ", "ひ", "び" };
	
	final static String[] JONG = { "", "ぁ", "あ", "ぃ", "い", "ぅ", "う", "ぇ", "ぉ", "お", "か", "が", "き", "ぎ", "く", "ぐ", "け",
			"げ", "ご", "さ", "ざ", "し", "じ", "ず", "せ", "ぜ", "そ", "ぞ" };
	
	public static final String fileRoute = "AI_Info.txt";

	public static void writeFileWithVector(Vector<String> v) throws IOException {
		
		FileWriter file = new FileWriter(fileRoute, false);
		
		for (int cnt = 0; cnt < v.size(); ++cnt) {
			file.write(v.get(cnt) + "\n");
		}
		
		file.close();
	}

	
	
	public static Vector<String> loadLastInfo() throws IOException {
		
        BufferedReader br = new BufferedReader(new FileReader(fileRoute));
        Vector<String> v = new Vector<>();
        
        while(true) {
        	
            String line = br.readLine();
            
            if (line == null) 
            	break;
            
            v.add(line);
        }
        
        br.close();
		
		return v;
	}
	
	public static void showAll(Vector<String> v) {
		Object[] arr = v.toArray();
		
		Arrays.sort(arr);
		
		for (int cnt = 0; cnt < arr.length; ++cnt) {
			System.out.println(arr[cnt]);
		}
	}
	
	public static String splitKorean(String text) {
		
		String result = "";
		
		for (int i = 0; i < text.length(); i++) {
			char uniVal = text.charAt(i);
			
			if (uniVal >= 0xAC00) {
				uniVal = (char) (uniVal - 0xAC00);
			
				char cho = (char) (uniVal / 28 / 21);
				char joong = (char) ((uniVal) / 28 % 21);
				char jong = (char) (uniVal % 28);
				
				result += CHO[cho] + JOONG[joong] + JONG[jong];
			} 
			else {
				result += uniVal;
			}
		}
		
		return result;
	}
	
	public static char bigChar(char c) {
		int t = (int)c;
		
		t -= 32;
		
		if(t < 0) {
			t = 0;
		}
		
		return (char)t;
	}
	
	public static char smallChar(char c) {
		int t = (int)c;
		
		t += 32;
		
		if(t > 127) {
			t = 0;
		}
		
		return (char)t;
	}
}
