package main;

import java.io.IOException;
import java.util.Scanner;

import artificialIntelligence.BadWordsChecker;
import utills.Utill;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		
		BadWordsChecker bwc = new BadWordsChecker("AI_Info.txt");
		
		exitPoint:
		while (true) {
			System.out.print("cmd>");
			String mode = scan.nextLine();
			System.out.println();
			
			
			
			switch (mode) {
			case "add":
				System.out.print("add>");
				bwc.addInfo(scan.nextLine());
				break;

			case "run":
				System.out.print("run>");
				bwc.learning(scan.nextLine());
				break;
				
			case "list":
				Utill.showAll(bwc.getWordsVector());
				break;
				
			case "size":
				System.out.println("ListSize:" + bwc.getDataSize());
				break;
				
			case "find":
				System.out.print("find>");
				bwc.find(scan.nextLine());
				break;
				
			case "del":
				System.out.print("del>");
				bwc.delInfo(scan.nextLine());
				break;
				
			case ".":
				System.out.println("Exit");
				break exitPoint;
				
			default:
				System.out.println("can't find commend");
				break;
			}
		}
		
		scan.close();
	}
}
