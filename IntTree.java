import java.util.*;

public class IntTree {
    private static IntTreeNode overallRoot;   // null for an empty tree
    

    public static void main(String args[]) {
    	// Create the root node
    	overallRoot = new IntTreeNode(3);
    	 
    	// Create the left child node
    	overallRoot.left = new IntTreeNode(5);
    	 
    	// Create the right child node
    	overallRoot.right = new IntTreeNode(2);
    	
    	overallRoot.left.left = new IntTreeNode(1);
    	overallRoot.right.left = new IntTreeNode(4);
    	overallRoot.right.right = new IntTreeNode(6);
    	overallRoot.right.left.left = new IntTreeNode(7);
    	
    	
    	//trying the methods
    	preOrder();
    	print();
    	printSideways();
    	System.out.println(contains(100));
    	add(14);
    	print();
    	remove(55);
    	print();
    	System.out.println(getMin());
    	
    	
    }
    
    public IntTree(){
    	overallRoot=null;
    }
    
    public IntTree(int max){
    	if(max<0){
    		throw new IllegalArgumentException("max :"+max);
    	}
    	overallRoot=buildTree(1,max);
    	
    }
    private IntTreeNode buildTree(int n,int m ){
    	if(n>m){
    		return null;
    	}
    	else{
    		
    		IntTreeNode left=buildTree(2*n,m);
    		IntTreeNode right=buildTree(2*n+1,m);
    		
    		return new IntTreeNode(  n ,left,right);
    	}
    }

	public IntTree(IntTreeNode overallRoot) {
		IntTree.overallRoot=overallRoot;
	}


    
	public static void print(){
		print(overallRoot);
		System.out.println();
	}
	private static void print(IntTreeNode root){
		if(root!=null){
			print(root.left);
			System.out.print(root.data+" ");
			print(root.right);
			
		}
	}

    
    public static void printSideways() {
        printSideways(overallRoot, "");
    }

    private static void printSideways(IntTreeNode root,
                               String indent) { //recursive helper method
        if (root != null) {
            printSideways(root.right, indent + "    ");
            System.out.println(indent + root.data);
            printSideways(root.left, indent + "    ");
        }
    }
    
	public static void preOrder(){
		System.out.println("Pre Order: ");
		preorder(overallRoot);
		System.out.println();
	}
	private static void preorder(IntTreeNode root){
		if(root!=null){
			System.out.print(" "+root.data);
			preorder(root.left);
			preorder(root.right);
			
		}
	}

 // Returns whether this tree contains the given integer.
    public static boolean contains(int value) {
        return contains(overallRoot, value);
    }

    private static boolean contains(IntTreeNode root, int value) {
        if (root == null) {
            return false;
        } else if (root.data == value) {
            return true;
        } else if (root.data > value) {
            return contains(root.left, value);
        } else {   // root.data < value
            return contains(root.right, value);
        }
    }
 // Adds the given value to this BST in sorted order.
    public static void add(int value) {
        overallRoot = add(overallRoot, value);
    }

    private static IntTreeNode add(IntTreeNode root, int value) {
        if (root == null) {
            root = new IntTreeNode(value);
        } else if (root.data > value) {
            root.left = add(root.left, value);
        } else if (root.data < value) {
            root.right = add(root.right, value);
        } // else a duplicate

        return root;
    }
 // Removes the given value from this BST, if it exists.
    public static void remove(int value) {
        overallRoot = remove(overallRoot, value);
    }

    private static IntTreeNode remove(IntTreeNode root, int value) {
        if (root == null) {
            return null;
        } else if (root.data > value) {
            root.left = remove(root.left, value);
        } else if (root.data < value) {
            root.right = remove(root.right, value);
        } else {  // root.data == value; remove this node
            if (root.right == null) {
                return root.left;    // no R child; replace w/ L
            } else if (root.left == null) {
                return root.right;   // no L child; replace w/ R
            } else {
                // both children; replace w/ min from R
                root.data = getMin(root.right);
                root.right = remove(root.right, root.data);
            }
        }
        return root;
    }


 // Returns the minimum value from this BST.
 // Throws a NoSuchElementException if the tree is empty.
 public static  int getMin() {
     if (overallRoot == null) {
         throw new NoSuchElementException();
     }
     return getMin(overallRoot);
 }

 private static int getMin(IntTreeNode root) {
     if (root.left == null) {
         return root.data;
     } else {
         return getMin(root.left);
     }
 }
 
	public static int countLeftNodes()
	{
		return countLeftNodes(overallRoot);
	} 

	private static int countLeftNodes(IntTreeNode root){
		if(root == null) 
			return 0;
		
		int count = 0;
		
	    	if(root.left != null) 
	        	count += 1 + countLeftNodes(root.left);    

	    	if(root.right != null)
	        	count += countLeftNodes(root.right);

	    	return count;
	}
	

public static int countEmpty() {
    return countEmpty(overallRoot);
}

private static int countEmpty(IntTreeNode node) {
    if(node == null)
        return 1;

    return countEmpty(node.left) + countEmpty(node.right);
}



}

class IntTreeNode {
    public int data;            // data stored at this node
    public IntTreeNode left;    // reference to left subtree
    public IntTreeNode right;   // reference to right subtree
        
    // Constructs a leaf node with the given data.
    public IntTreeNode(int data) {
        this(data, null, null);
    }
                
    // Constructs a branch node with the given data and links.
    public IntTreeNode(int data, IntTreeNode left,
                                 IntTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}