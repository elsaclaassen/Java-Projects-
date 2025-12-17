import java.util.Scanner;

public class Main { //main to test the program 
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); //create new scanner for user input 
		
		while (true) { //keep asking until abort in terminal 
			System.out.print("type your expression: "); //get user input 
            String expression = scanner.nextLine(); //read user input 
            if (expression.isEmpty()) break;  // Exit if input is empty
            Converter converter = new Converter(expression); 
            String postfixexpression = converter.toPostFix();//create the postifx expression to start with 
            ExpressionTree tree = new ExpressionTree(postfixexpression); 
            tree.buildTree(); //build the tree 
            String prefix = tree.prefix(tree.display.top()); //top element on stack put into each expression method 
            System.out.println("converted to prefix: " + prefix);
            String infix = tree.infix(tree.display.top());
            System.out.println("converted to infix: " + infix);
            String postfix = tree.postfix(tree.display.top());
            System.out.println("converted to postfix: " + postfix);
            
            
            
		}
		scanner.close(); //close scanner 
	}
}
