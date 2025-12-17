public class TreeNode<E> implements Comparable<TreeNode<E>> {
  public E element;
	public TreeNode<E> leftChild;
	public TreeNode<E> rightChild;
	public double distance; //add double distance
	
	
	public TreeNode (E element, double distance) { //every time a tree node is created it should include the elements and the distance
		this.element = element; 
		this.distance = distance;
		this.leftChild = null; //set the left child to null to start 
		this.rightChild = null; //set right child to null to start 
	}
	
	public String toString() { //return the element as a string type
		return element.toString();
	}

	public int compareTo(TreeNode<E> other) { //compare the distance between two nodes to determine which is shorter distance 
		// TODO
		return Double.compare(this.distance, other.distance); //double since you are comparing distance 
	}
}
