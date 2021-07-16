package artificialIntelligence;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import utills.Utill;

public class AI {
	
	//�弳 ����Ʈ
	public static Vector<String> words;
	
	//�ܾ� �߰�
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
	
	//�Ѵܾ �߰�
	public static void addInfo(String word) throws IOException {
		words.add(word);
		
		Utill.writeFileWithVector(words);
	}
	
	
	
	//���� �ܾ� �� Ȯ�� ����
	public static float learnin(String word) throws IOException {
		
		//���� ���� Ȯ��
		float maxPer = -1f;
		
		//��� ����Ʈ ��ȸ
		for(int cnt = 0; cnt < words.size(); ++cnt) {
			
			//��ġ�ҽ� ����
			float point = 0f;
			
			//����Ʈ ���� �ܾ� ö�� �ɰ��� ��ȸ
			for(int i = 0; i < words.get(cnt).length(); ++i) {
				//���� ����� �ܾ� ö�� �ɰ��� ��ȸ
				for(int j = 0; j < word.length(); ++j) {
					
					//��ҹ��� ������� ��ġ�ϸ� �߰�
					if((word.charAt(j) == words.get(cnt).charAt(i)) || 
							(bigChar(word.charAt(j)) == words.get(cnt).charAt(i)) ||
							(smallChar(word.charAt(j)) == words.get(cnt).charAt(i))) {
						
						
						++point;
						//��ġ�ϴ°��� ã���� ���̻� ��ȸ�� �ʿ� ����
						break;
					}
				}
			}
			
			//���ο� Ȯ���� ������ Ȯ���� �� ū�� ���
			maxPer = Math.max(maxPer, point / words.get(cnt).length());
			
			//Ȯ���� 100%��� �� Ȯ���̹Ƿ� ����Ʈ�� �߰�
			if(maxPer == 1) {
				words.add(word);
				Utill.writeFileWithVector(words);
				break;
			}
		}
		
		//���� ū Ȯ�� ��ȯ
		return maxPer;
	}
	
	
	
	
	
	//��ҹ��� ����
	private static char bigChar(char c) {
		int t = (int)c;
		
		t -= 32;
		
		if(t < 0) {
			t = 0;
		}
		
		return (char)t;
	}
	//��ҹ��� ����
	private static char smallChar(char c) {
		int t = (int)c;
		
		t += 32;
		
		if(t > 127) {
			t = 0;
		}
		
		return (char)t;
	}
}
