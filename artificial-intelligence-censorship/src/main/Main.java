package main;

import java.io.IOException;
import java.util.Scanner;

import artificialIntelligence.AI;
import utills.Utill;

public class Main {

	public static void main(String[] args) throws IOException {
		//�Է� ���� �� ����ϴ� ��ü
		Scanner scan = new Scanner(System.in);
		
		//�н����� �ҷ�����
		AI.words = Utill.loadLastInfo();

		System.out.println("help to get commend\n");
		
		
		while (true) {
			System.out.print("cmd>");
			//Ŀ�ǵ�
			String mode = scan.nextLine();

			System.out.println();
			//���� ���� �߰�
			if (mode.equals("add")) {
				AI.addInfo();
			}

			//�ΰ����� ����
			else if (mode.equals("run")) {

				while (true) {
					System.out.print("run>");
					String word = scan.nextLine();

					if (word.equals("."))
						break;
					
					//���� Ȯ��
					float percentage = AI.learnin(word);

					System.out.println(percentage * 100 + "%Ȯ���� ���Դϴ�.");

					
					//60% ������ ������ �Ǵ��ϰ� ���� �߰�
					if (percentage > 0.6f) {
						if (percentage != 1f)
							AI.addInfo(word);
					}
				}
			}

			// ����Ʈ �����ֱ�
			else if (mode.equals("list")) {
				Utill.showAll(AI.words);
			}
			
			// ����Ʈ ������
			else if (mode.equals("size")) {
				System.out.println("ListSize:" + AI.words.size());
			}
			
			// ���� ã��
			else if (mode.equals("find")) {

				while (true) {
					System.out.print("find>");
					String str = scan.nextLine();

					if(str.equals("."))
						break;
					
					if (AI.words.indexOf(str) != -1) {
						System.out.println("Find:" + str);
					} else {
						System.out.println("Fail");
					}
				}
			}

			//��ɾ��
			else if(mode.equals("help")) {
				System.out.println("add:add info\nrun:run A.I\nlist:show list\nsize:show list size\nfind:find word in list\n\n. to exit");
			}
			
			// exit
			else if(mode.equals(".")) {
				System.out.println("Exit");
				break;
			}
			
			else {
				System.out.println("can't find commend");
			}
		}
	}
}
