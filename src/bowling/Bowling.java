package bowling;

import java.util.Scanner;

public class Bowling {

	// Simple command line input using System.in and System.out
	// Can be swapped out with any type of User input
	public static void main(String[] args) {
		String input;
		if (args.length == 0) { // User input
			Scanner lineIn = new Scanner(System.in);
			System.out.print("Enter your bowl string: ");
			input = lineIn.nextLine();
			lineIn.close();
		} else { // Command Line arguments as input 
			System.out.print("Your inputted bowl String: " + args[0]);
			input = args[0];
		}
		System.out.println("Final Score: " + calcScore(input));
	}

	public static int calcScore(String input) {
		if (input == null || input.equals("")) {
			return -1;
		}

		// Iterates until 10 FRAMES have elapsed
		// One strike counts as one frame
		// One spare counts as one frame (as spares can only occur at the end of a frame)
		// Two consecutive digits count as one frame
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
	// Loops over the input String until receives enough valid bowls (X, /, or #) 
	// then outputs the sum of valid bowls
	private static int sumOfNextNums(String input, int numOfThrows) {
		int output = 0;
		int validNums = 0;
		int j = 1;
		while (validNums < numOfThrows) {
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
