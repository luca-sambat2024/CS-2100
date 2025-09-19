package tree;

// Name: Luca Sambat
// Computing ID: aqc8qt
// Homework Name: HW 10 AVL
// Resources used (if applicable): https://www.geeksforgeeks.org/insertion-in-an-avl-tree/

/**
 * Self-balancing AVL Tree
 * @author CS 2100 Team
 *
 * @param <T>
 */
 
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T>{
	
	@Override
	public void insert(T data) {
		this.root = insert(data, this.root);
	}
	protected TreeNode<T> insert(T data, TreeNode<T> curNode) {
		curNode=super.insert(data,curNode); //call the BST insert first
		int lefth=0;
		int righth=0;
		if (curNode!=null){ //checking for a null node. if curnode==null then do nothing and return itself
			lefth=height(curNode.left);
			righth=height(curNode.right);
			curNode.height=1+Math.max(righth,lefth); //update current node height
			curNode=balance(curNode); //set the current node to the balanced current node
		}
		return curNode;
	}

	
	@Override
	public void remove(T data) {
		/* Call remove starting at the root of the tree */
		this.root = remove(data, this.root);
	}
	protected TreeNode<T> remove(T data, TreeNode<T> curNode) {
		/* Call BST remove before balancing, use “super” to achieve this */
		curNode = super.remove(data,  curNode);
		
		/* Handle the case when remove returns null */
		if(curNode == null) return null;
		
		/* update the height of this node if necessary (if no change, that’s OK) */
		curNode.height = Math.max(height(curNode.left), height(curNode.right))+1;
		
		/* rotate if necessary (call balance() method to balance the node) */
		curNode = this.balance(curNode);
		
		return curNode;
	}

	
	/**
	 * Balances the given node. Assumes it is the lowest unbalanced node if unbalanced
	 * @param node
	 * @return
	 */
	private TreeNode<T> balance(TreeNode<T> curNode) {
		//TODO: Implement this method
		int bf=balanceFactor(curNode);
		if (bf>1){
			int leftbf=balanceFactor(curNode.left);
			if (leftbf<0){ //double rotation
				curNode.left=rotateLeft(curNode.left);
			}
			curNode=rotateRight(curNode); //rotate right
		} else if (bf<-1){
			int rightbf=balanceFactor(curNode.right);
			if (rightbf>0){ //double rotation
				curNode.right=rotateRight(curNode.right);
			}
			curNode=rotateLeft(curNode); //rotate left
		}
		return curNode;
	}
	
	private TreeNode<T> rotateRight(TreeNode<T> curNode) {
		//TODO: Implement this method
		TreeNode<T> x=curNode.left; //method reassigning using geeksforgeeks
		TreeNode<T> t=x.right;
		x.right=curNode;
		curNode.left=t;
		curNode.height=Math.max(height(curNode.left),height(curNode.right))+1; //height updates
		x.height=Math.max(height(x.left),height(x.right))+1;
		return x;
	}
	
	private TreeNode<T> rotateLeft(TreeNode<T> curNode){
		//TODO: Implement this method
		TreeNode<T> y=curNode.right; //method reassigning using geeksforgeeks
		TreeNode<T> t=y.left;
		y.left=curNode;
		curNode.right=t;
		curNode.height=Math.max(height(curNode.left),height(curNode.right))+1; //height updates
		y.height=Math.max(height(y.left),height(y.right))+1;
		return y;
	}
	
	private int balanceFactor(TreeNode<T> node) {
		//TODO: Implement this method
		if (node==null){
			return 0;
		}
		return (height(node.left)-height(node.right)); //call to find the balance factor is node!=null
	}
}
