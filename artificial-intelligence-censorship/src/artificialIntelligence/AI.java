package artificialIntelligence;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import utills.Utill;

public class AI {
	
	//욕설 리스트
	public static Vector<String> words;
	
	//단어 추가
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
	
	//한단어만 추가
	public static void addInfo(String word) throws IOException {
		words.add(word);
		
		Utill.writeFileWithVector(words);
	}
	
	
	
	//받은 단어 욕 확률 구별
	public static float learnin(String word) throws IOException {
		
		//가장 높은 확률
		float maxPer = -1f;
		
		//모든 리스트 순회
		for(int cnt = 0; cnt < words.size(); ++cnt) {
			
			//일치할시 증가
			float point = 0f;
			
			//리스트 내의 단어 철자 쪼개서 순회
			for(int i = 0; i < words.get(cnt).length(); ++i) {
				//비교할 대상의 단어 철자 쪼개서 순회
				for(int j = 0; j < word.length(); ++j) {
					
					//대소문자 상관없이 일치하면 추가
					if((word.charAt(j) == words.get(cnt).charAt(i)) || 
							(bigChar(word.charAt(j)) == words.get(cnt).charAt(i)) ||
							(smallChar(word.charAt(j)) == words.get(cnt).charAt(i))) {
						
						
						++point;
						//일치하는것을 찾으면 더이상 순회할 필요 없음
						break;
					}
				}
			}
			
			//새로운 확률과 기존의 확률중 더 큰값 사용
			maxPer = Math.max(maxPer, point / words.get(cnt).length());
			
			//확률이 100%라면 욕 확정이므로 리스트에 추가
			if(maxPer == 1) {
				words.add(word);
				Utill.writeFileWithVector(words);
				break;
			}
		}
		
		//가장 큰 확률 반환
		return maxPer;
	}
	
	
	
	
	
	//대소문자 변경
	private static char bigChar(char c) {
		int t = (int)c;
		
		t -= 32;
		
		if(t < 0) {
			t = 0;
		}
		
		return (char)t;
	}
	//대소문자 변경
	private static char smallChar(char c) {
		int t = (int)c;
		
		t += 32;
		
		if(t > 127) {
			t = 0;
		}
		
		return (char)t;
	}
}
