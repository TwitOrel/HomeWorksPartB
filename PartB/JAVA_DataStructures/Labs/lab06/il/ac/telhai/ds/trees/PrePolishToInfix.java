package lab06.il.ac.telhai.ds.trees;

import java.io.*;

public class PrePolishToInfix {

    public static void main(String[] args) throws IOException {
        // Initialize a tokenizer to read input expressions
        StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
        tokenizer.slashSlashComments(false);
        tokenizer.ordinaryChar('/');

        // Create an expression tree from the input expression
        ExpressionTree expr = ExpressionTree.createTree(tokenizer);

        // Print various information about the expression tree
        System.out.println("Infix: " + expr.infix());
        System.out.println("Prefix: " + expr.prefix());
        System.out.println("Value: " + expr.evaluate());
        System.out.println("Heigt: " + expr.height());
        System.out.println("Size: " + expr.size());
        System.out.println("IsLeaf: " + expr.isLeaf());
    }

}
