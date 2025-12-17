/*
Name: Elsa Claassen 
Net ID: epc9784
Date: 11/10/2024
Description: Battleship inspired game
*/

import java.util.Scanner; //import scanner to get user input 
import java.util.Random; //import random to get random placement 
public class Elsa_Claassen_Battleship{ //create class to hold all my methods
	public static void main (String[]args){ //create main method to call all my methods 
		final int SIZE=10; //set the size of the battle ship game equal to 10
        String[][] board = new String[SIZE][SIZE]; //create a board 10 x 10 for the game 
        UserSees(board); //set every value of the board equal to blank to start. This means there is no X or no #
        String[] boats = AssignShip(board); //array containing all of the coordinates essentially for the boat 

        String guesses = ""; //string that contains all of the users guesses

        

        int guess_count = 0; //amount of guesses the user has made 
        int hits = 0; //number of hits the user has made on the hidden ship
        System.out.println("Try to find the battle ship!");
        while(hits<boats.length){ //continue to ask the user for a guess until the hits is equal to the length of the boat 
        	PrintBoard(board); //print the board first as fully blank and then with each # or X depending on correct or incorrect guesses 
        	Scanner in = new Scanner(System.in); //creat scanner for user input 
        	String guess = ""; //initialize guess before while loop
        	while(true){ //while loop to continue asking user for input until it is valid 
        		System.out.println("Guess a capital letter and number combo (ex: A3): "); //ask user for guess
		        guess = in.nextLine().toUpperCase(); //make sure the letter of the guess is upper case 
		        int valid = 0; //check if valid
		        for(char c = 'A'; c <= 'J'; c++){ //for all letter possibilities it is also here that i relaized char have single quotes around them 
		        	for(int i = 0; i<=9; i++){ //for all number possibilities
		        		String compare = c + "" + i; //together
		        		if (guess.equals(compare)){ //does its exists in any possibility
		        			valid ++; //if yes valid plus one
		        			break; //break to stop of the for loop from iterating 
		        		}
		        	}
		        }
		        if (valid == 0){ //if valid is 0 it is not a valid guess
		        	System.out.println("That is an invalid guess. Please try again."); //try again
		        }
		        else{ //if valid is greater than zero
		        	break; //stop asking user for input 
		        }

        	}
	        

	        char first_character = guess.charAt(0); //seperate the guess from letter and number 
	        int first_letter = first_character - 'A'; //i tried to find the value of the letter by subtracting the uppercase A this way it will set the number in terms of 0 instead of 65 using getNumericValue()
	        char second_letter = guess.charAt(1); //number
	        int second_character = second_letter - '0'; //make sure the number is in terms of 0 otherwise A1 will be marked in A0 
	        String new_guess = first_letter + "" + second_character; //put the two number together as a string not by adding them. The "" insures it is a string 
	        guesses = guesses + new_guess + " "; //add the guesses, serperated by a space 
	        String assignment = GuessCheck(new_guess, boats); //guess check checks the guess user has input and if it is a hit assigns X and a miss #

	        board[second_character][first_letter] = assignment; //whatever the assignment is from above (X or #) make sure it appears on the board 
	        if (assignment.equals("X")){
	        	hits++; //if it is a hit add one so we know when to exit the while loop
	        }
	        guess_count ++; //add one to the guess count 

        }
        System.out.println("You win! You found all of the hidden ship!"); //when the hits equal length of ship exit the while loop and let user know they win
        if (guess_count == 4){ //the best game 
        	System.out.println("You are a super winner because you guessed the ship using the least amount of guesses possible!");
        }
        else if (guess_count == 100){ //the worst game 
        	System.out.println("Few!!! You barely won the game. It took you the maximum amount of guesses possible to find the hidden ship!");
        }
        
        PrintBoard(board); //print the final results of the game 

        

	}

	public static void UserSees(String[][] matrix){ //this is the board that the user will first see. It sets every colums and row equal to blank
		for (int i=0; i < matrix.length; i++){ //iterate though every row
            for (int j=0; j < matrix[i].length; j++){ //iterate through every column 
            	matrix[i][j] = " "; // a space for the empty board 

            }
        }
	}

	public static String GuessCheck(String is_in,String[] boats){ //guess check to check if hit or miss
		String assignment = "#"; //assume it is a miss
		for (int i = 0; i< boats.length; i++){ //iterate through array of random boat assignments
			if (is_in.equals(boats[i])){ //if the guess eqauls the boat coordinates 
				assignment = "X"; //it is a hit
				break; //break for loop to exit and not compare to other boat coordinates
			}
		}
		return assignment; //return hit or miss

		
	}

	public static void PrintBoard(String[][] matrix){ //print the board 
		System.out.println("    A   B   C   D   E   F   G   H   I   J"); //show the letter coordinates at the top
		
        for (int i=0; i < matrix.length; i++){ //iterate through the rows of the matrix
        	System.out.print("  "); //through trial and error i created spacer throughout the board to make everything alligned
            for (int j=0; j < matrix[i].length; j++){ //iterate through the columns of the matrix

                System.out.print("+---"); //print part of the box 

            }
            System.out.println("+"); //print additional plus

            
            System.out.print(i +" "); // print the number of each row 
            
            for (int j=0; j < matrix[i].length; j++){ //looking again at the columns 
                System.out.print("| "+ matrix[i][j]+ " "); //print middle of the box along with what the matrix has assigned to its coordinates. Whether is is blank # or X

            }
            System.out.println("|"); //print the other side of the box and create new line
            

        }
        System.out.println("  +---+---+---+---+---+---+---+---+---+---+"); //when all boxes have been made print the bottom of the board
    }    

      

    public static String[] AssignShip(String[][]matrix){ //this method decides where the ship is going to be
    	String direction [] = {"vertical", "horizontal"}; //options to pick vertical or horizontal direction for the ship
    	String boats [] = new String[4]; //create an array that will hold the 4 coordinated for the boat 
    	Random pick = new Random(); //create random

		int index = pick.nextInt(direction.length); //pick random index for vertical or horizontal ship 
		String way = direction[index]; //determine if the index corisponds to vertical or horizontal 
		int counter = 0; //set counter for all the additiona 3 coordinates not initially chosen 

		if (way.equals("vertical")){ //if vertical 
			int row = pick.nextInt(matrix.length-4); //only the rows will chnage because its vertical so go back 4 places to make sure there is space
			int column = pick.nextInt(matrix[row].length); //column will remain the same 
			for(int i =0;i<4;i++){ //only four coordinates will be assigned	
				boats[counter] = column + "" + (row+i); //assign a position in the array to string of column and row + i to make suer we are assigning a new position
				counter ++; //counter plus one to move onto the next array value 
				
			}
			
		} 
		else if (way.equals("horizontal")){ // if horizontal 
			int row = pick.nextInt(matrix.length); //row stays the same 
			int column = pick.nextInt(matrix[row].length-4); //this time hte column is the one changing because its horizontal so subtract 4 to make sure there is space for 3 other values 
			for(int i = 0; i<4 ;i++){ //only four coordinates will be assigned to array 
				boats[counter] = (column + i)+ "" + row; //add i to the counter to make sure we are assigning a new coordinate each time
				counter ++; //counter plus one to make sure we are assigning a value to a new array position
				
			}

		}
		return boats; //return the array of random coordinates 
    }
	
}
