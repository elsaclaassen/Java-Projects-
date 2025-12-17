import java.util.List; 

public class Converter{
	private List <String> helper; //used to store the characters from the parser helper in a list to iterate thorugh 
	private ArrayStack<String> adds = new ArrayStack<String>(); //create an empty stack 
	private String output = ""; //create an empty string
	private String [] order = new String[]{"-", "+", "/", "*", "^", "(", ")"}; //this was to compare and know what sign was greater i did not end up using this
	//private int total  = 0;
	//get user input
	//tokenize
	//convert 
	public Converter(String expression){ //everytime the converter class is called we should create a new list of character elements 
		this.helper = ParserHelper.parse(expression.toCharArray());

	}

	public String toPostFix(){ //method to turn an equation into a postfix equation 
		for(int i = 0; i< helper.size(); i++){
			
			if(Character.isDigit(helper.get(i).charAt(0))){ //digits should be appended onto the string 
				output = output + helper.get(i) + " ";
			}
			else if (helper.get(i).equals("*") || helper.get(i).equals("/") || 
	                   helper.get(i).equals("+") || helper.get(i).equals("^") || 
	                   helper.get(i).equals("-")){ //if its not a digit is should be one of these characters
				if(helper.get(i).equals("*")){// if it is multiplication it is greater only that + and - everything else should be popped off the stack 
					if(adds.isEmpty()){ //if the stack is empty then just push 
						adds.push(helper.get(i));
					}
					else{
						
						if(adds.top() != null && (adds.top().equals("^") || adds.top().equals("/")|| adds.top().equals("*"))){
							while(!adds.isEmpty()&& (adds.top().equals("^") || adds.top().equals("/")|| adds.top().equals("*"))){ //check all elements in stack and pop until find one less than 
								output = output + adds.top() + " "; //add to the string of numbers 
								adds.pop();	
							}
							
						}
						adds.push(helper.get(i));	//push * when there is nothing greater than on top 
					}

				}
				else if (helper.get(i).equals("/")){ //the following else if statements have the same structure as the * one above 
					if(adds.isEmpty()){
						adds.push(helper.get(i));
					}
					else{
						
						if(adds.top() != null && (adds.top().equals("^") || adds.top().equals("/")|| adds.top().equals("*"))){
							while(!adds.isEmpty()&& (adds.top().equals("^") || adds.top().equals("/")|| adds.top().equals("*"))){
								output = output + adds.top() + " ";
								adds.pop();	
							}
							
						}
						adds.push(helper.get(i));	
					}

				}
				else if(helper.get(i).equals("+")){
					if(adds.isEmpty()){
						adds.push(helper.get(i));
					}
					else{
						
						if(adds.top() != null && (adds.top().equals("*") || adds.top().equals("/")||adds.top().equals("^") || adds.top().equals("-")|| adds.top().equals("+"))){
							while(!adds.isEmpty()&& (adds.top().equals("*") || adds.top().equals("/")||adds.top().equals("^")||adds.top().equals("+")||adds.top().equals("-"))){
								output = output + adds.top() + " ";
								adds.pop();	
							}
							
						}
						adds.push(helper.get(i));	
						
					}

				}
				else if(helper.get(i).equals("-")){
					if(adds.isEmpty()){
						adds.push(helper.get(i));
					}
					else{
						
						if(adds.top() != null && (adds.top().equals("*") || adds.top().equals("/")||adds.top().equals("^") || adds.top().equals("+")|| adds.top().equals("-"))){
							while(!adds.isEmpty()&& (adds.top().equals("*") || adds.top().equals("/")||adds.top().equals("^")||adds.top().equals("+")||adds.top().equals("-"))){
								output = output + adds.top() + " ";
								adds.pop();	
							}
								
						}
						adds.push(helper.get(i));
						
					}

				}
				else if (helper.get(i).equals("^")){ //^ is greater than all other symbols so you push onto stack no matter what 
					adds.push(helper.get(i));

				}

						
			}
			else if (helper.get(i).equals("(")){ //an open ( should also be pushed onto stack no matter what 
				adds.push(helper.get(i));

			}
			else if (helper.get(i).equals(")")){ // a cloaed )  you should pop everything off the stack until you reach ( then get rid of both ()
				//String hold = adds.top();
				while(!adds.isEmpty()&&( !adds.top().equals("(") )){
					output = output + adds.top() + " ";
					adds.pop();
				}
				if(!adds.isEmpty() && adds.top().equals("(")){
					adds.pop();
				}
				


			}
		}
		while(!adds.isEmpty()){ //once all the character of the list have been accounted for pop them off the stack in the correct order 
			
			output = output + adds.top() + " ";
			adds.pop();

			
			
		}
		return output; //return the string of new equation 


	}
	

}