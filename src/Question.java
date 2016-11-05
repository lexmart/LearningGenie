import java.util.Scanner;

public class Question {
	
	public static String ask(Scanner in, String question) {
		System.out.println(question);
		return in.nextLine();
	}
	
	public static String askBinaryQuestion(Scanner in, String question) {
		System.out.printf("%s ", question);
		String response = in.nextLine();
		while(!response.toLowerCase().equals("yes") && !response.toLowerCase().equals("no")) {
			System.out.printf("%s ", question);
			response = in.nextLine().toLowerCase();
		}
		System.out.println();
		return response;
	}

}
