package Project4;

/**
 * This class is a generic implementation of a binary search tree using recursive methods 
 * to add to and remove from the tree
 * @author Julie Cestaro
 * @version 23 April 2017 
 *
 */

public class MyBST<E extends Comparable<E>> {

	protected BSTNode<E> root;
	protected int numOfNodes;
	
	/**
	 * This is the constructor for the binary search tree. It creates an empty tree
	 * with a null root node
	 */
	public MyBST(){
		this.root = null;
		this.numOfNodes = 0;
	}

	/**
	 * This method is used to add new data to the binary search tree. It makes sure that
	 * the new data is not already in the tree and then calls a recursive method to add the 
	 * data to the right spot.
	 * @param e
	 * @return true if the data is not already in the tree and can be added
	 * @return false if the data is already in the tree and cannot be added
	 */
	public boolean add(E e){
		if(this.contains(e)){
			return false;
		}else{
			this.root = recAdd(e, root);
			this.numOfNodes ++;
			return true;
		}
	}
	
	/**
	 * This is the recursive method that actually does the task of adding to the tree.
	 * it recursively searches through the tree until it finds a proper place for the 
	 * new node and then adds it at that location
	 * @param e
	 * @param root
	 * @return the BSTNode that has been added
	 */
	//pretty sure this is right
	@SuppressWarnings("unchecked")
	private BSTNode<E> recAdd(E e, BSTNode<E> root){
		if(root == null){
			//found an empty place to add it
			root = new BSTNode<E>(e);
		}else if(e.compareTo((E)root.getData()) <= 0){
			root.setLeft(recAdd(e, root.getLeft()));
		}else if(e.compareTo((E)root.getData()) > 0){
			root.setRight(recAdd(e, root.getRight()));
		}
		return root;
	}

	/**
	 * This method is used to remove data from the binary search tree. It makes sure that
	 * the data is already in the tree and then calls a recursive method to remove the data
	 * @param e
	 * @return true if the data is already in the tree and can be removed
	 * @return false if the data is not already in the tree and cannot be removed
	 */
	@SuppressWarnings("unchecked")
	public boolean remove(Object o){
		if(this.contains(o)){
			root = recRemove((E) o, root);
			numOfNodes--;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This is the recursive method that determines the location of the data that needs to 
	 * be removed. Once it does, it calls another recursive method to actually complete 
	 * the task of removing the node
	 * @param e
	 * @param root
	 * @return the BSTNode that will be the new root of the subtree. it may be the same node
	 * that was already there, or it may be a new node after the old root has been removed
	 */
	@SuppressWarnings("unchecked")
	private BSTNode<E> recRemove(E e, BSTNode root){
		if(root == null){
			//not in tree
			//should not reach this case bc contains if statement
			//maybe throw an exception if you're feeling it??
		}else if(e.compareTo((E)root.getData()) < 0){
			//search left subtree
			root.setLeft(recRemove(e, root.getLeft()));
		}else if(e.compareTo((E)root.getData()) > 0){
			//search right subtree
			root.setRight(recRemove(e, root.getRight()));
		}else{
			//found it!
			//NOW YOU NEED TO WRITE A REMOVE METHOD THAT DEALS WITH DIFFERENT NUMBERS OF CHILDREN -- done
			root = removeANode(root);
		}
		return root;
	}
	
	/**
	 * This is the recursive method that actually completes the task of removing the node
	 * from the tree.
	 * @param node
	 * @return the BSTNode that will replace the removed node
	 */
	private BSTNode<E> removeANode(BSTNode<E> node){
		if(node.getLeft() == null){
			//have the right child replace the node?
			return node.getRight();
		}else if(node.getRight() == null){
			//have the left child replace the node?
			return node.getLeft();
		}else{
			E data = getPredecessor(node);
			BSTNode<E> newNode = new BSTNode(data);
			newNode.setLeft(recRemove(data, node.getLeft()));
			return newNode;
		}
	}
	
	/**
	 * This method helps with removing nodes that have two children. It determines which
	 * node preceeds the node that has two children and returns the data from that node.
	 * It does not remove anything.
	 * @param node
	 * @return the data from the node preceeding the node to be removed
	 */
	private E getPredecessor(BSTNode<E> node){
		if(node.getLeft() == null){
			//return error condition
			//this should never actually get to the user
			//something is very very wrong if you get to this point
			throw new NullPointerException("Your getLeft() is null!");
		}else{
			BSTNode<E> current = node.getLeft();
			while(current.getRight() != null){
				current = current.getRight();
			}
			return (E)current.getData();
		}
	}

	/**
	 * This is the public wrapper method for the recursive implementation of contains
	 * @param o
	 * @return true if the object is contained in the tree, false if it is not
	 */
	@SuppressWarnings("unchecked")
	public boolean contains(Object o){
		return recContains((E) o, root);
	}
	
	/**
	 * This is the recursive method that actually determines if a specified piece of
	 * data is contained in the tree
	 * @param target
	 * @param root
	 * @return true if the object is contained in the tree, false if it is not
	 */
	@SuppressWarnings("unchecked")
	private boolean recContains(E target, BSTNode root){
		if(root == null){
			return false;
		}else if(target.compareTo((E)root.getData()) < 0){
			//search the left subtree of smaller values
			return recContains(target, root.getLeft());
		
		}else if(target.compareTo((E)root.getData()) > 0){
			//search the right subtree of larger values
			return recContains(target, root.getRight());
		
		}else{ //the case where compareTo = 0
			return true; //because target is found
		}
	}
	
	/**
	 * This is an iterative method that returns the smallest element in the tree
	 * @return the smallest element in the tree
	 */
	//returns smallest element in tree
	public E first(){
		if(root == null){
			return null;
		}else{
			BSTNode<E> node = root;
			while(node.getLeft() != null){
				node = node.getLeft();
			}
			return node.getData();
		}
	}
	
	/**
	 * This is an iterative method that returns the largest element in the tree
	 * @return the largest element in the tree
	 */
	//returns largest element in tree
	public E last(){
		if(root == null){
			return null;
		}else{
			BSTNode<E> node = root;
			while(node.getRight() != null){
				node = node.getRight();
			}
			return node.getData();
		}
	}
	
	/**
	 * This method creates a string representation of the MyBST by calling a recursive method
	 * to iterate through the current MyBST
	 * @return the string representation
	 */
	@Override
	public String toString(){
		String returnString = "[" + recToString(root).substring(0, (recToString(root).length() - 2)) + "]";	
		return returnString;
	}
	
	/**
	 * This is the recursive method that iterates through the MyBST and adds a string representation
	 * of the data in each node to the string that will be returned
	 * @param root
	 * @return the string representation
	 */
	private String recToString(BSTNode<E> root){
		String returnString = "";
		if(root == null){
			return returnString;
		}
		returnString += recToString(root.getLeft());
		returnString += root.getData().toString();
		returnString += ", ";
		returnString += recToString(root.getRight());
		return returnString;
	}


}








