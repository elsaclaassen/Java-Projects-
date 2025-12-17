import java.util.StringTokenizer; //import tokenizer 

public class ReadTree{ //this reads through the input from the main class 
	public static TreeNode buildTree(String input){ //build tree using stack 
		StringTokenizer tokenizer = new StringTokenizer(input); //split input into tokens 
		ArrayStack<TreeNode<String>> stack = new ArrayStack<>(); //create a stack of string values 
		while(tokenizer.hasMoreTokens()){ //as long as there are still tokens from the user input 
			String token = tokenizer.nextToken(); // get the next token in the string 
			if(token.equals("(")){ //continue if open parenthesis 
				continue;
			}
			else if (token.equals(")")){ //if the next is a closed parenthesis 
				TreeNode<String> right = stack.pop(); //pop right child
				TreeNode<String> left = stack.pop(); //pop left child 
				TreeNode<String> parent = stack.pop(); //pop parent node 
				parent.leftChild = left; //set the children to parent 
				parent.rightChild = right;
				stack.push(parent); //push parent back onto the stack 
			}
			else{
				String value = token; 
				double distance = Double.parseDouble(tokenizer.nextToken()); //convert to double to find the distance 
				TreeNode<String> node = new TreeNode<>(value,distance); //create a new node value of the token from input and the newly found distance
				stack.push(node); //push new node onto the stack 
			}
		}
		return stack.pop(); //should only be one large connected tree now in the top position of stack so pop 
	}
}