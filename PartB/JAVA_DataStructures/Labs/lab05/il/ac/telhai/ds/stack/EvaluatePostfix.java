package lab05.il.ac.telhai.ds.stack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Objects;

public class EvaluatePostfix {

	private static StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
	private static Stack<Double> myStack = new DLinkedListStack<Double>();
	
	public static void main(String[] args) throws IOException {
		tokenizer.slashSlashComments(false);
		tokenizer.ordinaryChar('/');
		boolean isQuit = false;
		
		//TODO add your code here.
		while (!isQuit){
			try {
						tokenizer.nextToken();
						switch (tokenizer.ttype) {
							case StreamTokenizer.TT_WORD -> {
								// Handle "quit" command
								String word = tokenizer.sval;
								if (Objects.equals(word, "quit")) {
									isQuit = true;
								} else {
									// Invalid word token, print an error message
									System.err.println(tokenizer);
									System.err.println(myStack);
									System.exit(-1);
								}
							}
							case StreamTokenizer.TT_EOF -> {
								// Exit the loop when reaching the end of input
								isQuit = true;
							}
							case StreamTokenizer.TT_NUMBER -> myStack.push(tokenizer.nval);
							case '+' -> {
								// Addition operation
								double one = myStack.pop();
								double two = myStack.pop();
								myStack.push(one + two);
							}
							case '-' -> {
								// Subtraction operation
								double one = myStack.pop();
								double two = myStack.pop();
								myStack.push(two - one);
							}
							case '*' -> {
								// Multiplication operation
								double one = myStack.pop();
								double two = myStack.pop();
								myStack.push(one * two);
							}
							case '/' -> {
								// Division operation
								double one = myStack.pop();
								double two = myStack.pop();
								myStack.push(two / one);
							}
							default -> {
								// Handle unknown token or unexpected situation
								isQuit = true;
								System.err.println(tokenizer);
								System.err.println(myStack);
								System.exit(-2);
							}
						}
			}
			catch (NullPointerException e){
				// Handle potential NullPointerException
				System.err.println(tokenizer);
				System.err.println(myStack);
				System.exit(-2);
			}
		}

		// Check if the stack is empty or contains the final result
		if (myStack.isEmpty()){
			System.err.println(tokenizer);
			System.err.println(myStack);
			System.exit(-3);
		}
		else {
			double res = myStack.pop();
			// Check if the stack is empty after popping the result
			if (myStack.isEmpty()){
				System.out.println(res);
			}
			else {
				System.err.println(tokenizer);
				System.err.println(myStack);
			}
		}
	}
}
