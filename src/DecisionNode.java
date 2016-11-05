import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DecisionNode {

	private String value;
	DecisionNode no, yes;
	
	
	/**
	 * Initialize DecisionNode with value
	 * @param value, a String.
	 */
	public DecisionNode(String value) {
		this.value = value;
		no = null;
		yes = null;
	}
	
	/**
	 * Initialize DecisionNode with a value and left/right children.
	 * @param value, a String
	 * @param yes, a DecisionNode
	 * @param no, a DecisionNode
	 */
	public DecisionNode(String value,  DecisionNode yes, DecisionNode no) {
		this.value = value;
		this.yes = yes;
		this.no = no;
	}
	
	/**
	 * @return Is this DecisionNode a leaf? If it is, then it must be a guess node,
	 * its a question node.
	 */
	public boolean isLeaf() {
		return (no == null && yes == null);
	}
	
	/**
	 * Starting at this node, try to guess the user's object by walking down a tree.
	 * If we encounter a new object, store it a subtree of this node. Also store the
	 * question that differentiates the new object from the old object.
	 * @param in, a Scanner from stdout
	 * @return An updated DecisionNode
	 */
	public DecisionNode guess(Scanner in) {
		if(isLeaf()) {
			String response = Question.askBinaryQuestion(in, "Are you thinking of " + value + "?");
			
			if(response.toLowerCase().equals("yes")) {
				System.out.println("Excellent, thanks!");
				return this;
			} else {
				System.out.println("Ohs noes, I was wrong!");
				String animal = Question.ask(in, "What animal were you thinking of?");
				no = new DecisionNode(animal);
				yes = new DecisionNode(value);
				String question = Question.ask(in, 
						"What is a yes/no question that distinguishes a " + value
						+ " from a " + animal + "? (where the answer for " + value + " is yes)");
				DecisionNode ret = new DecisionNode(question, yes, no);
				System.out.println("Thanks I will learn from this experience");
				return ret;
			}
		} else {
			System.out.println(value);
			String response = in.nextLine();
			if(response.toLowerCase().equals("yes")) {
				yes = yes.guess(in);
			} else {
				no = no.guess(in);
			}
			return this;
		}
	}
	
	/**
	 * Serialize this node and write it to a file.
	 * @param out, a handle to an output file
	 * @throws IOException
	 */
	public void write(FileWriter out) throws IOException {
		if(isLeaf()) {
			out.write(value + "\n");
		} else {
			out.write("#" + value + "\n");
		}
	}
	
}
