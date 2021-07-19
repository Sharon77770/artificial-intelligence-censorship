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
		AI.words = Utill.loadLastInfo(AI.BadFile);
		AI.notBad = Utill.loadLastInfo(AI.NotBadFile);
		
		exitPoint:
		while (true) {
			System.out.print("cmd>");
			String mode = scan.nextLine();
			System.out.println();
			
			
			
			switch (mode) {
			case "add":
				AI.addInfo();
				break;

			case "run":
				AI.learning();
				break;
				
			case "list":
				Utill.showAll(AI.words);
				break;
				
			case "size":
				System.out.println("ListSize:" + AI.words.size());
				break;
				
			case "find":
				AI.find();
				break;
				
			case "del":
				AI.delInfo();
				break;
				
			case ".":
				System.out.println("Exit");
				break exitPoint;
				
			default:
				System.out.println("can't find commend");
				break;
			}
		}
	}
}
