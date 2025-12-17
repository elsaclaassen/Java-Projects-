public class HuffmanNode implements Comparable{ //HuffmanNode class where the new Huffman Node will be instantiated 
	public String letter; //determine what the character is assigned to the node
	public Double frequency; //frequency also assigned to the node to determine how many of the same character are in the string
	public HuffmanNode left, right; //create left and right children for each node 
	public HuffmanNode(String letter, Double frequency){ //constructor creates the new node 
		this.letter = letter;
		this.frequency = frequency;
		this.right = null;
		this.left = null;//initially set each left and right child to null 


	}
	public HuffmanNode(HuffmanNode left, HuffmanNode right){ //creates a new Huffman node depending on its children 
		this.letter = left.letter + right.letter; //concatenation of the right and left letter
		this.frequency = left.frequency + right.frequency; //sum of right and left frequency 
		this.left = left; //reset left child
		this.right = right; //reset right child 

	}
	public int compareTo(Object o){
		HuffmanNode huff = (HuffmanNode) o; //cast object o into a Huffman Node 
		return this.frequency.compareTo(huff.frequency); //determine the largest nodes 

	}
	public String toString(){
		return "<" + letter + ", "+ frequency + ">"; //return for print statements 
	}


}
