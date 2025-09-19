package tree;
// Name: Luca Sambat
// Computing ID: aqc8qt
// Homework Name: HW 9 BST
// Resources used (if applicable): https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> implements Tree<T>{

    @Override
    public void insert(T data) {
        this.root = insert(data, root);
    }

    /**
     * Helper method for inserting recursively
     * @param data
     * @param curNode
     * @return a reference to the new root of the subtree
     */
    protected TreeNode<T> insert(T data, TreeNode<T> curNode) {
        //TODO: Implement this method
        TreeNode<T> temp=new TreeNode<>(data);
        // if (!find(data)){ //makes sure that the item is not a duplicate
            if (curNode==null){ //the actual insertion of the item
                curNode=temp;
//                System.out.println("item inserted: "+temp.data);
            } else {
//                System.out.println("(sub)root exists, comparing");
                if (curNode.data.compareTo(temp.data)>0){ //temp less, recursive call to left subtree
//                    System.out.println("temp is less: "+temp.data);
                    curNode.left=insert(data,curNode.left);
                } else if (curNode.data.compareTo(temp.data)<0){ //temp more, recursive call to right subtree
//                    System.out.println("temp is more "+temp.data);
                    curNode.right=insert(data,curNode.right);
                }
            }
        return curNode;
    }


    /**
     * Returns a boolean (true of false) if the element was found in a BST
     */
    @Override
    public boolean find(T data) {
        return find(data, root);
    }

    // Helper method
    private boolean find(T data, TreeNode<T> curNode) {
        //TODO: Implement this method
        if (curNode!=null && data!=null){ //makes sure that the current node is not null
            if (data.compareTo(curNode.data)<0){
                return find(data,curNode.left); //calls recursive on left if data less than current node data
            } else if (data.compareTo(curNode.data)>0){
                return find(data,curNode.right); //calls recursive on right if data more than current node data
            } else { //if it is equal to zero, return true (should be same values)
                return true;
            }
        } else { //return false if the node is null
            return false;
        }
    }

    /**
     * Returns the max item in the given subtree
     */
    public T findMax() {
        return findMax(this.root);
    }

    // Helper method
    private T findMax(TreeNode<T> curNode) {
        //TODO: Implement this method
        if (curNode.right!=null){
            return findMax(curNode.right); //recursive call to the right most node (since it's the highest)
        } else {
            return curNode.data;
        }
    }

    //-----------------------------------------------------------------------------
    //THE METHOD BELOW IS PARTIALLY IMPLEMENTED    
    //YOU NEED TO COMPLETE THE IMPLEMENTATION
    //-----------------------------------------------------------------------------

    @Override
    public void remove(T data) {
        this.root = remove(data, root); // Call remove at the root of the tree
    }

    protected TreeNode<T> remove(T data, TreeNode<T> curNode) {
        /* Note the use of compareTo() in the solution! */

	// first check if the node to be inserted is null, if so return the node
        if(curNode == null) return curNode;


        // if item I want to remove is smaller than the data of the current node...
        else if (data.compareTo(curNode.data) < 0) {
            // recursively call remove on LEFT subtree
	    /* TODO: insert code here */
            curNode.left=remove(data,curNode.left);
        }
        // if item I want to remove is larger than the data of the current node...
        else if (data.compareTo(curNode.data) > 0) {
	    // recursively call remove on RIGHT subtree
	    /* TODO: insert code here */
            curNode.right=remove(data,curNode.right);
        }
	/* Found item to remove, time to remove */
        else {
            if (curNode.left==null && curNode.right==null){
                return null;
            } else if (curNode.left!=null && curNode.right==null){
                return curNode.left;
            } else if (curNode.left==null && curNode.right!=null){
                return curNode.right;
            } else {
                T toDel = findMax(curNode.left);  // find inorder predecessor (max of left child)
                curNode.data = toDel;		  // copy value from inorder predecessor to node
                curNode.left = remove(toDel, curNode.left);  // delete the inorder predecessor
                return curNode;
            }
	    /* Case 1: node is a leaf, return null */
            
	    /* Case 2A: else if node only has a left child, then return left child */

	    /* Case 2B: else if node only has a right child, then return right child */
           
            /* Case 3: else (node has 2 children) so:           
               UNCOMMENT CODE BELOW AND PUT IN APPROPRIATE SPOT
            */
        }
        return curNode;
    }


}
