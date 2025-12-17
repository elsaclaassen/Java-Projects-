//package hw2;
public class PostfixCalculator{ //this class with calculate the postfix equation from converter class 


	public static double evaluate(String postfix){ //take in the returned string from converter class 

		String [] hold = postfix.split(" "); //split the string by the spaces between each character
		ArrayStack<Double> display = new ArrayStack<>(); //create a new empty stack 
		for(int i = 0; i< hold.length; i ++){ //look at each character individually 
			double total = 0; //keep track of total 
			if(Character.isDigit(hold[i].charAt(0))){ //check if character can be converted to a double 
				Double nums = Double.valueOf(hold[i]);
				double numss = nums; //convert to double
				display.push(numss); //push the character onto the stack 
			}
			else if (hold[i].equals("+")){ //if the character in the array is not a character you will need to do math accordingly 
				total = display.top(); //look at the top value on stack 
				display.pop(); //pop top to get the second most top value 
				total = total + display.top(); // add them together 
				display.pop(); //make sure both are popped off stack 
				display.push(total); //push total of two onto the stack 

			}
			else if (hold[i].equals("-")){ //the remaining else if statements follow the same logic as the +  above
				total = display.top();
				display.pop();
				total = display.top() - total;
				display.pop();
				display.push(total);

			}
			else if (hold[i].equals("/")){
				total = display.top();
				display.pop();
				total = display.top()/total;
				display.pop();
				display.push(total);

			}
			else if (hold[i].equals("*")){
				total = display.top();
				display.pop();
				total = total * display.top();
				display.pop();
				display.push(total);

			}
			else if (hold[i].equals("^")){
				total = display.top();
				display.pop();
				total = Math.pow(display.top(),total); //use Math.pow for to the power of
				display.pop();
				display.push(total);

			}
			


		}
		return display.top(); //return the remaining single value in the stack which is also the top

		



	}
}

