//shows how to implement Binary Search Tree

public class IntSearchTree {
	
	public static void main(String args[]) {
		
	}
    private IntTreeNode overallRoot;

    // post: constructs an empty tree
    public IntSearchTree() {
        overallRoot = null;
    }

    // post: value is added to overall tree so as to preserve the
    //       binary search tree property
    public void add(int value) {
        overallRoot = add(overallRoot, value);
    }

    // post: value is added to given tree so as to preserve the
    //       binary search tree property
    private IntTreeNode add(IntTreeNode root, int value) {
        if (root == null) {
            root = new IntTreeNode(value);
        } else if (value <= root.data) {
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
        }
        return root;
    }

    // post: returns true if overall tree contains value
    public boolean contains(int value) {
        return contains(overallRoot, value);
    }

    // post: returns true if given tree contains value
    private boolean contains(IntTreeNode root, int value) {
        if (root == null) {
            return false;
        } else if (value == root.data) {
            return true;
        } else if (value < root.data) {
            return contains(root.left, value);
        } else { // value > root.data
            return contains(root.right, value);
        }
    }

    // post: prints the contents of the tree using an inorder traversal
    public void print() {
        printInOrder(overallRoot);
        System.out.println();
    }

    // post: prints the contents of the tree with the given root
    // using an inorder traversal
    private void printInOrder(IntTreeNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.data + " ");
            printInOrder(root.right);
        }
    }
    public void printPreOrder() {
    	printPreOrder(overallRoot);
    }
    private void printPreOrder(IntTreeNode root) {
        if(root != null){
            System.out.println(root.data+" ");
            printPreOrder(root.left);        
            printPreOrder(root.right);
           }
       
    }
    
    public void printPostOrder() {
    	printPreOrder(overallRoot);
    }
    private void printPostOrder(IntTreeNode root) {
        if(root != null){
        	printPreOrder(root.left); 
        	printPreOrder(root.right); 
            System.out.println(root.data+" ");
            
           }
    }
}

