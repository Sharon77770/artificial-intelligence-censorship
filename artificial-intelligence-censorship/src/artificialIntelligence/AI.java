package artificialIntelligence;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import utills.Utill;

public class AI {

	// 욕설 리스트
	public static Vector<String> words;

	public static Vector<String> notBad;

	// 파일 경로
	public static final String BadFile = "AI_Info.txt";
	public static final String NotBadFile = "AI_InfoNotBad.txt";
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 *
 *
 *
 *
 */
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

		Utill.writeFileWithVector(words, BadFile);
	}
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 *
 *
 *
 *
 */
	//한단어만 추가
	public static void addInfo(String word) throws IOException {
		words.add(word);

		Utill.writeFileWithVector(words, BadFile);
	}
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 *
 *
 *
 *
 */
	
	// 받은 단어 욕 확률 구별
	public static void learning() throws IOException {
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			System.out.print("run>");
			String word = scan.nextLine();
			if (word.equals("."))
				break;
			word = Utill.splitKorean(word);
			
			float maxPer = Utill.getMaxPer(words, word, BadFile);
			float notBadmaxPer = Utill.getMaxPer(notBad, word, NotBadFile);

			if(maxPer > notBadmaxPer) {
				System.out.println(maxPer * 100 + "%Bad");
				
				if (maxPer > 0.6f) {
					if (maxPer != 1f)
						addInfo(word);
				}
			}
			else {
				System.out.println(notBadmaxPer * 100 + "%NotBad");
			}
		}
	}
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 *
 *
 *
 *
 */
	
	public static void find() {
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			System.out.print("find>");
			String str = scan.nextLine();

			if(str.equals("."))
				break;
			
			if (AI.words.indexOf(str) != -1) {
				System.out.println("Find:" + str);
			} 
			else {
				System.out.println("Fail");
			}
		}
	}
	
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 *
 *
 *
 *
 */
	public static void delInfo() throws IOException {
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			System.out.print("del>");
			String str = scan.nextLine();

			if(str.equals("."))
				break;

			int idx = AI.words.indexOf(str);
			
			if (idx != -1) {
				words.remove(idx);
				notBad.add(str);
				
				System.out.println("delInfo:" + str);
				
				Utill.writeFileWithVector(words, BadFile);
				Utill.writeFileWithVector(notBad, NotBadFile);
			} 
			else {
				System.out.println("Fail");
			}
		}
	}
}
