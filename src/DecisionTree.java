import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.stream.Stream;

public class DecisionTree {

	private int numObjects;
	private DecisionNode root;
	
	/**
	 * Initialize DecisionTree with "Dog" as the root.
	 */
	public DecisionTree() {
		root = new DecisionNode("Dog");
		numObjects = 1;
	}
	
	/**
	 * Initializes DecisionTree from file
	 * @param file, a handle to a text file
	 * @throws FileNotFoundException
	 */
	public DecisionTree(File file) throws FileNotFoundException {
		// thanks Zach for explaing this!
		Scanner in = new Scanner(file);
		
		root = read(in);
	}
	
	/**
	 * Read in the serialized DecisionTree
	 * @param in
	 * @return
	 */
	public DecisionNode read(Scanner in) {
		String line = in.nextLine();
		if(line.charAt(0) == '#') {
			DecisionNode result = new DecisionNode(line.substring(1));
			result.yes = read(in);
			result.no = read(in);
			return result;
		} else {
			return new DecisionNode(line);
		}
	}
	
	/**
	 * @return The number of objects in the DecisionTree
	 */
	public int countObjects() {
		return numObjects;
	}
	
	/**
	 * Play a round of guessing, and learn a new object if not already in tree.
	 * @param in, Scanner with standard input
	 */
	public void guess(Scanner in) {
		root = root.guess(in);
		numObjects += 1;
	}
	
	/**
	 * Recursively serialize a given node into an output file
	 * @param out, a handle to a File where the output goes.
	 * @param cur, current decision node.
	 * @throws IOException
	 */
	private void writeH(FileWriter out, DecisionNode cur) throws IOException {
		if(cur != null) {
			cur.write(out);
			writeH(out, cur.yes);
			writeH(out, cur.no);
		}
	}
	
	/**
	 * Serializes the DecisionTree into a file.
	 * @param out, a handle to a File where the output goes.
	 * @throws IOException
	 */
	public void write(FileWriter out) throws IOException {
		 writeH(out, root);
	}
	
	
}
