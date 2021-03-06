package utills;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

public class Utill {

	final static String[] CHO = { "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ",
			"ㅍ", "ㅎ" };

	final static String[] JOONG = { "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ",
			"ㅠ", "ㅡ", "ㅢ", "ㅣ" };

	final static String[] JONG = { "", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ",
			"ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ" };

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
	// 정보 저장
	public static void writeFileWithVector(Vector<String> v, String fileRoute) throws IOException {

		FileWriter file = new FileWriter(fileRoute, false);

		for (int cnt = 0; cnt < v.size(); ++cnt) {
			file.write(v.get(cnt) + "\n");
		}

		file.close();
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
	
	// 정보 불러오기
	public static Vector<String> loadLastInfo(String fileRoute) throws IOException {
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader(fileRoute));
		}
		catch(IOException e) {
			FileWriter file = new FileWriter(fileRoute);
			file.write("");
			file.close();
			br = new BufferedReader(new FileReader(fileRoute));
		}
		
		Vector<String> v = new Vector<>();

		while (true) {

			String line = br.readLine();

			if (line == null)
				break;

			v.add(line);
		}

		br.close();

		return v;
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

	// 리스트 출력
	public static void showAll(Vector<String> v) {
		Object[] arr = v.toArray();

		Arrays.sort(arr);

		for (int cnt = 0; cnt < arr.length; ++cnt) {
			System.out.println(arr[cnt]);
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
	public static String splitKorean(String text) {
		String newStr = "";
		
		for (int i = 0; i < text.length(); i++) {
			char uniVal = text.charAt(i);

			if (uniVal >= 0xAC00) {
				uniVal = (char) (uniVal - 0xAC00);
				char cho = (char) (uniVal / 28 / 21);
				char joong = (char) ((uniVal) / 28 % 21);
				char jong = (char) (uniVal % 28);
				
				newStr += CHO[cho] + JOONG[joong] + JONG[jong];
			} 
			else {
				newStr += uniVal;
			}
		}
		
		return newStr;

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
	
	public static float getMaxPer(Vector<String> words, String word, String FileRoute) throws IOException {
		float maxPer = -1f;

		// 모든 리스트 순회
		for (int cnt = 0; cnt < words.size(); ++cnt) {
			String targetWord = Utill.splitKorean(words.get(cnt));

			// 일치할시 증가
			float point = 0f;

			// 리스트 내의 단어 철자 쪼개서 순회
			for (int i = 0; i < targetWord.length(); ++i) {
				// 비교할 대상의 단어 철자 쪼개서 순회
				for (int j = 0; j < word.length(); ++j) {

					// 대소문자 상관없이 일치하면 추가
					if (Utill.Equal(targetWord, i, word, j)) {
						++point;
						break;
					}

					if (i == 0 && j == 0)
						break;
				}
			}

			// 새로운 확률과 기존의 확률중 더 큰값 사용
			maxPer = Math.max(maxPer, point / targetWord.length());
		}
		
		return maxPer;
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
	public static boolean Equal(String targetWord, int i, String word, int j) {
		return (word.charAt(j) == targetWord.charAt(i))
		|| (bigChar(word.charAt(j)) == targetWord.charAt(i))
		|| (smallChar(word.charAt(j)) == targetWord.charAt(i));
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
	//대소문자 변경
	public static char bigChar(char c) {
		if (c>='가' && c<='힣')
			return c;
		
		int t = (int)c;
		
		t -= 32;
		
		if(t < 0) {
			t = 0;
		}
		
		return (char)t;
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
	//대소문자 변경
	public static char smallChar(char c) {
		if (c>='가' && c<='힣')
			return c;
		
		int t = (int)c;
		
		t += 32;
		
		if(t > 127) {
			t = 0;
		}
		
		return (char)t;
	}
}
