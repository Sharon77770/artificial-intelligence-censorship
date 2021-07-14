package main;

import java.io.IOException;
import java.util.Scanner;

import artificialIntelligence.AI;
import utills.Utill;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);

		System.out.println("Info:");

		AI.words = Utill.loadLastInfo();

		AI.addInfo();

		while (true) {
			System.out.print("\nmode:");

			String mode = scan.nextLine();

			System.out.println();
			// add Info
			if (mode.equals("1")) {
				AI.addInfo();
			}

			// learning
			else if (mode.equals("2")) {

				while (true) {

					String word = scan.nextLine();

					if (word.equals("."))
						break;

					float percentage = AI.learnin(word);

					System.out.println(percentage * 100 + "%È®·ü·Î ¿åÀÔ´Ï´Ù.");

					if (percentage > 0.6f) {
						if (percentage != 1f)
							AI.addInfo(word);
					}
				}
			}

			// show all
			else if (mode.equals("3")) {
				Utill.showAll(AI.words);
			}
			
			// list size
			else if (mode.equals("4")) {
				System.out.println("ListSize:" + AI.words.size());
			}
			
			// find word in list
			else if (mode.equals("5")) {

				while (true) {
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

			// exit
			else {
				break;
			}

		}
	}
}
