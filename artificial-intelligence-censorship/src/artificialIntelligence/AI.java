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
			System.out.print("add>");
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
	
	
	public static void delInfo(String word) {
		int idx = words.indexOf(word);
		
		if (words.indexOf(word) != -1) {
			words.remove(idx);
			System.out.println("Remove");
		} 
		else {
			System.out.println("Fail");
		}
	}
	
	
	public static float learnin(String word) throws IOException {
		
		word = Utill.splitKorean(word);
		
		float maxPer = -1f;
		
		for(int cnt = 0; cnt < words.size(); ++cnt) {
			
			float point = 0f;
			
			for(int i = 0; i < words.get(cnt).length(); ++i) {
				String target = Utill.splitKorean(words.get(cnt));
				
				for(int j = 0; j < word.length(); ++j) {
					if((word.charAt(j) == target.charAt(i)) || 
							(Utill.bigChar(word.charAt(j)) == target.charAt(i)) ||
							(Utill.smallChar(word.charAt(j)) == target.charAt(i))) {
						
						
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
}
