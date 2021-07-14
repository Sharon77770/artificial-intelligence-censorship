package artificialIntelligence;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import utills.Utill;

public class AI {
	
	public static Vector<String> words;
	
	public static void addInfo() throws IOException {
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			String str = scan.nextLine();
			
			words.add(str);

			if (words.get(words.size() - 1).equals(".")) {
				words.remove(words.size() - 1);
				break;
			}
		}
		
		Utill.writeFileWithVector(words);
	}
	
	public static void addInfo(String word) throws IOException {
		words.add(word);
		
		Utill.writeFileWithVector(words);
	}
	
	
	
	
	public static float learnin(String word) throws IOException {
		
		float maxPer = -1f;
		
		for(int cnt = 0; cnt < words.size(); ++cnt) {
			
			float point = 0f;
			
			for(int i = 0; i < words.get(cnt).length(); ++i) {
				for(int j = 0; j < word.length(); ++j) {
					if((word.charAt(j) == words.get(cnt).charAt(i)) || 
							(bigChar(word.charAt(j)) == words.get(cnt).charAt(i)) ||
							(smallChar(word.charAt(j)) == words.get(cnt).charAt(i))) {
						
						
						++point;
						break;
					}
				}
			}
			
			maxPer = Math.max(maxPer, point / words.get(cnt).length());
			
			if(maxPer == 1) {
				words.add(word);
				Utill.writeFileWithVector(words);
				break;
			}
		}
		
		return maxPer;
	}
	
	
	
	
	
	
	private static char bigChar(char c) {
		int t = (int)c;
		
		t -= 32;
		
		if(t < 0) {
			t = 0;
		}
		
		return (char)t;
	}
	
	private static char smallChar(char c) {
		int t = (int)c;
		
		t += 32;
		
		if(t > 127) {
			t = 0;
		}
		
		return (char)t;
	}
}
