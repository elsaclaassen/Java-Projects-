public class ExpressionTree{
	public ArrayStack<Node<String>> display = new ArrayStack<>(); //create a stack that will hold the nodes and the final tree 
	String [] elements; //create an array that will split the postfix string equation given to us into individual characters
	String prefix_result = ""; //placeholders for each expression
	String postfix_result = "";
	String infix_result = "";


	public ExpressionTree(String postfix){
		elements = postfix.split(" "); //split the string by " "

	}

	public ArrayStack<Node<String>> buildTree(){ //method to build the tree using a stack 
		for(int i = 0; i<elements.length; i++){ //iterate through each element in the array 
			 
			if(Character.isDigit(elements[i].charAt(0))){ //check if number
				
				Node value = new Node(elements[i]); //create a new node of the digit 
				display.push(value); //push the node onto the stack 
			}
			else if (elements[i].equals("^") || elements[i].equals("/") || elements[i].equals("*") || elements[i].equals("+") || elements[i].equals("-") ){ //check if reach a non digit 
				Node another = new Node(elements[i]); //create a new node
				Node hold = display.pop(); //pop two previous nodes 
				Node other = display.pop();
				another.leftChild = other; //set each equal to a child of the opperator node
				another.rightChild = hold;
				display.push(another); //push the opperator node onto the stack 
			}

		}
		return display; //return the stack of size one 

	}

	//use recursion for the following expression methods 

	public String infix(Node<String> node){ //creating an infix expression
		
		if(node != null){ //continue until reach null 
			if(node.leftChild != null || node.rightChild != null){ //using perethesis within the infix expression
				infix_result += "(";
			}
			
			infix(node.leftChild); //look at each left child until reach null 
			infix_result += node.element; //add each element on the left onto the string
			infix(node.rightChild); //look at each right child until reach null 

			if(node.leftChild != null || node.rightChild != null){
				infix_result += ")"; //closing perethesis added 
			}
			
			

		}
		return infix_result; //return the string



	}
	public String postfix(Node<String> node){ //create a postfix expression 
		if(node != null){ //continue until reach null 
			postfix(node.leftChild); //look at left child until reach null 
			postfix(node.rightChild); //start to look at each right child 
			postfix_result += node.element; //once reach null in right start adding onto string 


		}
		return postfix_result; //return the string 


	}
	public String prefix(Node<String> node){ //create prefix expression 
		if(node != null){ //continue until reach null 
			prefix_result += node.element; //add the new element you are viewing 
			prefix(node.leftChild); //look at left child until reach null 
			prefix(node.rightChild); //look at right child until reach null 
			

		}

		return prefix_result; //return string 
	}


}