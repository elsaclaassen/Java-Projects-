import java.util.NoSuchElementException;
import java.util.Scanner; //import scanner for the main class 

public class BinaryTree<E> { //given binary tree class 
  private TreeNode<E> root;

  public BinaryTree(TreeNode<E> root) { //create binary tree staring with a root 
    this.root = root; 
  }

  public double findClosest(E target) { //find the closest to the target value based on distance 
		// TODO
    BinaryHeap<TreeNode<E>> heap = new BinaryHeap<>(); //create a min heap using the class from previous assignment 
    root.distance = 0; //the distance at the root should be set to zero 
    heap.insert(root); //insert the root in the heap 
    while(!heap.isEmpty()){ // continue as long as the heap still contains nodes 
      TreeNode<E> parent = heap.deleteMin(); //removes the root node 
      if(parent.element.equals(target)){ //if the root is the element you are looking for 
        return parent.distance; //return the element 
      }
      if(parent.leftChild != null){ //if the current element has a left child 
        TreeNode leftChild = parent.leftChild;
        leftChild.distance += parent.distance; //create a total distance from root to node 
        heap.insert(leftChild); //add the child to the heap 
      }
      if(parent.rightChild != null){ //if the current element has a right child 
        TreeNode rightChild = parent.rightChild; 
        rightChild.distance += parent.distance; //create a total distance from root to node 
        heap.insert(rightChild); //insert right child to heap 

      }

    }
    throw new NoSuchElementException("Element " + target + " not found!"); //if no element returned before heap is empty return no element found
      
	}

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); //create new scanner 

    System.out.println("Enter your space and parentheses-separated tree below: "); //prompt to enter tree 
    String input = scanner.nextLine(); //save user input as input 
    
    TreeNode<String> root = ReadTree.buildTree(input); //take input and return root value of the tree  
    BinaryTree<String> tree = new BinaryTree<String>(root); //create the tree starting from root 
    double distance = tree.findClosest("*"); //find the distance of the closest element 
    if(distance != 0){ //as long as the distance is not zero 

      System.out.println("Found '*' at distance " + distance); //return distance of element 
    }
    else{
      System.out.println("Element '*' not found!"); //otherwise element is not found 

    }

  }
    

  
}
