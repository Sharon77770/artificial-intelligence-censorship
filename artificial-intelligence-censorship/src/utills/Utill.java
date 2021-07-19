package utills;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

public class Utill {

	final static String[] CHO = { "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
			"��", "��" };

	final static String[] JOONG = { "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
			"��", "��", "��", "��" };

	final static String[] JONG = { "", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
			"��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��" };

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
	// ���� ����
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
	
	// ���� �ҷ�����
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

	// ����Ʈ ���
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

		// ��� ����Ʈ ��ȸ
		for (int cnt = 0; cnt < words.size(); ++cnt) {
			String targetWord = Utill.splitKorean(words.get(cnt));

			// ��ġ�ҽ� ����
			float point = 0f;

			// ����Ʈ ���� �ܾ� ö�� �ɰ��� ��ȸ
			for (int i = 0; i < targetWord.length(); ++i) {
				// ���� ����� �ܾ� ö�� �ɰ��� ��ȸ
				for (int j = 0; j < word.length(); ++j) {

					// ��ҹ��� ������� ��ġ�ϸ� �߰�
					if (Utill.Equal(targetWord, i, word, j)) {
						++point;
						break;
					}

					if (i == 0 && j == 0)
						break;
				}
			}

			// ���ο� Ȯ���� ������ Ȯ���� �� ū�� ���
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
	//��ҹ��� ����
	public static char bigChar(char c) {
		if (c>='��' && c<='�R')
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
	//��ҹ��� ����
	public static char smallChar(char c) {
		if (c>='��' && c<='�R')
			return c;
		
		int t = (int)c;
		
		t += 32;
		
		if(t > 127) {
			t = 0;
		}
		
		return (char)t;
	}
}
