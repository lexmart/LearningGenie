import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LearningGenie {
	
	public static void main(String[] args) throws IOException {
		DecisionTree t = new DecisionTree(new File("data.tree"));
		System.out.println("I know " + t.countObjects() + " many things");
		Scanner in = new Scanner(System.in);
		boolean askQuestion = true;
		while(askQuestion) {
			t.guess(in);
			String response = Question.askBinaryQuestion(in, "Do you want to continue?");
			askQuestion = response.equals("yes");
		}
		FileWriter out = new FileWriter("data.tree");
		t.write(out);
		out.close();
	}

}
