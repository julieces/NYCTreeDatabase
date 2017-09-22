package Project4;

public class BSTNode<E> {
	
	private E data;
	private BSTNode<E> left;
	private BSTNode<E> right;
	
	public BSTNode(E data){
		this.data = data;
		this.left = null;
		this.right = null;
	}
	
	
	public E getData(){
		return this.data;
	}
	
	public void setLeft(BSTNode<E> newNode){
		this.left = newNode;
	}
	
	public void setRight(BSTNode<E> newNode){
		this.right = newNode;
	}
	
	public BSTNode getLeft(){
		return this.left;
	}
	
	public BSTNode getRight(){
		return this.right;
	}

}
