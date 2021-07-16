package main;

import java.io.IOException;
import java.util.Scanner;

import artificialIntelligence.AI;
import utills.Utill;

public class Main {

	public static void main(String[] args) throws IOException {
		//입력 받을 때 사용하는 객체
		Scanner scan = new Scanner(System.in);
		
		//학습정보 불러오기
		AI.words = Utill.loadLastInfo();

		System.out.println("help to get commend\n");
		
		
		while (true) {
			System.out.print("cmd>");
			//커맨드
			String mode = scan.nextLine();

			System.out.println();
			//정보 원형 추가
			if (mode.equals("add")) {
				AI.addInfo();
			}

			//인공지능 시작
			else if (mode.equals("run")) {

				while (true) {
					System.out.print("run>");
					String word = scan.nextLine();

					if (word.equals("."))
						break;
					
					//욕일 확률
					float percentage = AI.learnin(word);

					System.out.println(percentage * 100 + "%확률로 욕입니다.");

					
					//60% 넘으면 욕으로 판단하고 정보 추가
					if (percentage > 0.6f) {
						if (percentage != 1f)
							AI.addInfo(word);
					}
				}
			}

			// 리스트 보여주기
			else if (mode.equals("list")) {
				Utill.showAll(AI.words);
			}
			
			// 리스트 사이즈
			else if (mode.equals("size")) {
				System.out.println("ListSize:" + AI.words.size());
			}
			
			// 정보 찾기
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

			//명령어들
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
