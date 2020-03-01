package bowling;

import java.util.Scanner;

public class Bowling {

	public static void main(String[] args) {
		String input;
		if (args.length == 0) {
			Scanner lineIn = new Scanner(System.in);
			System.out.print("Enter your bowl string: ");
			input = lineIn.nextLine();
			lineIn.close();
		} else {
			System.out.print("Your inputted bowl String: " + args[0]);
			input = args[0];
		}
		System.out.println("Final Score: " + calcScore(input));
	}

	public static int calcScore(String input) {
		if (input == null || input.equals("")) {
			return -1;
		}

		int totalScore = 0;
		int frameCounter = 0;
		int i = 0;
		boolean initialBowl = false;
		while (frameCounter < 10) {
			if (input.charAt(i) == 'X') {
				initialBowl = false;
				frameCounter++;
				totalScore += 10;
				totalScore += sumOfNextNums(input.substring(i), 2);
			} else if (input.charAt(i) == '/') {
				initialBowl = false;
				frameCounter++;
				totalScore += (10-(input.charAt(i-1)-'0'));
				totalScore += sumOfNextNums(input.substring(i), 1);
			} else if (Character.isDigit(input.charAt(i))) {
				if (initialBowl) {
					initialBowl = false;
					frameCounter++;
				} else {
					initialBowl = true;
				}
				totalScore += (input.charAt(i)-'0');
			}
			i++;
		}
		return totalScore;
	}
	private static int sumOfNextNums(String input, int numOfInts) {
		int output = 0;
		int validNums = 0;
		int j = 1;
		while (validNums < numOfInts) {
			if (Character.isDigit(input.charAt(j))) {
				validNums++;
				output += (input.charAt(j)-'0');
			} else if (input.charAt(j) == 'X') {
				validNums++;
				output += 10;
			} else if (input.charAt(j) == '/') {
				validNums++;
				output = 10;
			}
			j++;
		}
		return output;
	}
}
