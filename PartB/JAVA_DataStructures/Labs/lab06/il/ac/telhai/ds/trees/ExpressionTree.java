package lab06.il.ac.telhai.ds.trees;

import java.io.IOException;
import java.io.StreamTokenizer;

public class ExpressionTree extends FullBinaryTree<String> {
    private static final String operators = "+-*/";

    // Constructor for creating an ExpressionTree with a given element
    public ExpressionTree(String element) {
        super(element);
    }

    // Constructor for creating an ExpressionTree with a given element and left and right subtrees
    public ExpressionTree(String element, ExpressionTree left, ExpressionTree right) {
        super(element, left, right);
    }

    // Static method to create an ExpressionTree from a StreamTokenizer
    public static ExpressionTree createTree(StreamTokenizer tokenizer) throws IOException {
        String token;
        if (tokenizer.nextToken() == StreamTokenizer.TT_NUMBER) {
            token = String.valueOf(tokenizer.nval);
            return new ExpressionTree(token);
        } else if (operators.contains(String.valueOf((char) tokenizer.ttype))) {
            token = String.valueOf((char) tokenizer.ttype);
            return new ExpressionTree(token, createTree(tokenizer), createTree(tokenizer));
        } else {
            throw new IllegalArgumentException("");
        }
    }

    // Method to return the infix representation of the expression
    public String infix() {
        if (operators.contains(getValue())) {
            return "(" + ((ExpressionTree) getLeft()).infix() + " " + getValue() + " " + ((ExpressionTree) getRight()).infix() + ")";
        } else {
            return getValue();
        }
    }

    // Method to return the prefix representation of the expression
    public String prefix() {
        return preOrder();
    }

    // Method to evaluate the expression and return the result as a double
    public double evaluate() {
        return switch (getValue()) {
            case "+" -> ((ExpressionTree) getLeft()).evaluate() + ((ExpressionTree) getRight()).evaluate();
            case "-" -> ((ExpressionTree) getLeft()).evaluate() - ((ExpressionTree) getRight()).evaluate();
            case "*" -> ((ExpressionTree) getLeft()).evaluate() * ((ExpressionTree) getRight()).evaluate();
            case "/" -> ((ExpressionTree) getLeft()).evaluate() / ((ExpressionTree) getRight()).evaluate();
            default -> Double.parseDouble(getValue());
        };
    }
}
