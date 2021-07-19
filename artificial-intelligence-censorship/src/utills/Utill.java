package utills;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

public class Utill {

	final static String[] CHO = { "¤¡", "¤¢", "¤¤", "¤§", "¤¨", "¤©", "¤±", "¤²", "¤³", "¤µ", "¤¶", "¤·", "¤¸", "¤¹", "¤º", "¤»", "¤¼",
			"¤½", "¤¾" };

	final static String[] JOONG = { "¤¿", "¤À", "¤Á", "¤Â", "¤Ã", "¤Ä", "¤Å", "¤Æ", "¤Ç", "¤È", "¤É", "¤Ê", "¤Ë", "¤Ì", "¤Í", "¤Î", "¤Ï",
			"¤Ð", "¤Ñ", "¤Ò", "¤Ó" };

	final static String[] JONG = { "", "¤¡", "¤¢", "¤£", "¤¤", "¤¥", "¤¦", "¤§", "¤©", "¤ª", "¤«", "¤¬", "¤­", "¤®", "¤¯", "¤°", "¤±",
			"¤²", "¤´", "¤µ", "¤¶", "¤·", "¤¸", "¤º", "¤»", "¤¼", "¤½", "¤¾" };

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
	// Á¤º¸ ÀúÀå
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
	
	// Á¤º¸ ºÒ·¯¿À±â
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

	// ¸®½ºÆ® Ãâ·Â
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

		// ¸ðµç ¸®½ºÆ® ¼øÈ¸
		for (int cnt = 0; cnt < words.size(); ++cnt) {
			String targetWord = Utill.splitKorean(words.get(cnt));

			// ÀÏÄ¡ÇÒ½Ã Áõ°¡
			float point = 0f;

			// ¸®½ºÆ® ³»ÀÇ ´Ü¾î Ã¶ÀÚ ÂÉ°³¼­ ¼øÈ¸
			for (int i = 0; i < targetWord.length(); ++i) {
				// ºñ±³ÇÒ ´ë»óÀÇ ´Ü¾î Ã¶ÀÚ ÂÉ°³¼­ ¼øÈ¸
				for (int j = 0; j < word.length(); ++j) {

					// ´ë¼Ò¹®ÀÚ »ó°ü¾øÀÌ ÀÏÄ¡ÇÏ¸é Ãß°¡
					if (Utill.Equal(targetWord, i, word, j)) {
						++point;
						break;
					}

					if (i == 0 && j == 0)
						break;
				}
			}

			// »õ·Î¿î È®·ü°ú ±âÁ¸ÀÇ È®·üÁß ´õ Å«°ª »ç¿ë
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
	//´ë¼Ò¹®ÀÚ º¯°æ
	public static char bigChar(char c) {
		if (c>='°¡' && c<='ÆR')
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
	//´ë¼Ò¹®ÀÚ º¯°æ
	public static char smallChar(char c) {
		if (c>='°¡' && c<='ÆR')
			return c;
		
		int t = (int)c;
		
		t += 32;
		
		if(t > 127) {
			t = 0;
		}
		
		return (char)t;
	}
}
