public class HuffmanTree{ //huffman tree class 
	HuffmanNode root;
	public HuffmanTree(HuffmanNode huff){ //first create a binary heap of huffman nodes before calling this constructor 
	this.root = huff; //set root to huff 

	}
	public void printLegend(){ //create a printLegend method to call initially before the helper method is called recursively 
		printLegend(root, "");
	}
	public void printLegend(HuffmanNode node, String bits){ //helper method for print legend 
		if(node != null){ //continue as long as node it not a leaf 
			if(node.left == null && node.right == null){ //node is a single character then it is a leaf 
				System.out.println(node.letter + " = " + bits);
			}
			else{
				printLegend(node.left, bits + "0"); //recursively print the left children adding 0
				printLegend(node.right, bits + "1"); //then right adding 1
			}

		}
	}
	public static BinaryHeap<HuffmanNode> legendToHeap(String legend){
		String [] everything = legend.split(" "); //create an array splitting the string by the spaces in between characters 
		int size = everything.length/2; //you dont want to count frequency so divide the array by 2

		HuffmanNode [] nodes = new HuffmanNode[size]; //create a new array with the size without frequency 
		int location = 0; //counter to know where i am in the array 
		//nodes[0] = null;
		for(int i = 0; i<size; i++){ //for all values in the split array 
			String letter = everything[i *2]; //take every other letter or character
			String freq = everything[i*2+1]; // find the frequency by adding one
			double newFreq = Double.valueOf(freq); //turn the string frequency value into a double 
			nodes[location] = new HuffmanNode(letter, newFreq); //create a new huffman node given the value found in the array 

			location ++; //increase the location by one 

		}
		return new BinaryHeap<HuffmanNode>(nodes);//return the heap 

	}
	public static HuffmanTree createFromHeap(BinaryHeap<HuffmanNode> b){ //run the huffman algorithm 
		while (b.getSize() >1){ // continue as long as there isnt only one element left 
			HuffmanNode child1 = b.deleteMin(); //find all children deleting min after min assigning them to left and right children 
			HuffmanNode child2 = b.deleteMin();
			HuffmanNode parent = new HuffmanNode(child1, child2); //create the new parent node using the children removed above 
			b.insert(parent);//add the parent to the heap 
		}
		HuffmanNode root = b.deleteMin(); //get the last element in the heap 
		return new HuffmanTree(root); //create the new tree using root 

	}
	public static void main(String[] args){ //main method to test 
		String legend = "A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2"; //legend example
	    BinaryHeap<HuffmanNode> heap = legendToHeap(legend); //split the legend characters using legendToHeap and create the new binary heap 
	    heap.printHeap(); //print the heap 
	    HuffmanTree tree = createFromHeap(heap); //create huffman tree from heap created 
	    tree.printLegend(); //print the tree with bits 

	}

}
