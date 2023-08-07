package bricey;

import java.util.Arrays;
import java.util.Scanner;

class Utils {

	public static <T> T[] concatArrays(T[] firstArr, T[] secondArr) {
		T[] both = Arrays.copyOf(firstArr, firstArr.length + secondArr.length);
		// Don't ask me what this does, I got it from stack overflow
		System.arraycopy(secondArr, 0, both, firstArr.length, secondArr.length);
		// Eh, just look it up, its pretty simple!
		return both;		
	}

	public static boolean askYesOrNoQuestion(Scanner scanner, String prompt) {
		// We can do this since we return on a valid
		// answer on the inside
		while (true) {
	        System.out.print(prompt + "(y/n)");
	        String response = scanner.nextLine().toLowerCase();
            
	        if (response.equals("y")) {
				return true;
	        } else if (response.equals("n")) {
				return false;
	        } else {
	            System.out.println("Invalid response. Please answer 'y' or 'n'.");
	        }
		}
	}
}
