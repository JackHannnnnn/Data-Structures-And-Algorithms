package chaofan;


public class BinarySearchTree {
	
	private class TreeNode {		// declare class; no (); () used for method
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int val) {
			this.val = val;
		}
		
	}
	
	public TreeNode root;
	public BinarySearchTree() {}
	
	public int treeHeight(TreeNode root) {
		if (root == null)
			return -1;
		return 1 + Math.max(treeHeight(root.left), treeHeight(root.right));
	}
	
	
	public void inOrderTraversal() {
		inOrderTraversalHelper(root);
	}
	
	public void inOrderTraversalHelper(TreeNode node) {
		if (node != null) {
			inOrderTraversalHelper(node.left);
			visit(node);
			inOrderTraversalHelper(node.right);
		}
	}
	
	public void preOrderTraversal(TreeNode node) {
		if (node != null) {
			visit(node);
			preOrderTraversal(node.left);
			preOrderTraversal(node.right);
		}
	}
	
	public void postOrderTraversal(TreeNode node) {
		if (node != null) {
			postOrderTraversal(node.left);
			postOrderTraversal(node.right);
			visit(node);
		}
	}
	
	public boolean find(TreeNode node, int data) {
		// all base case is if a node is null
		if (node == null)
			return false;
		if (node.val < data) 
			return find(node.right, data);
		if (node.val > data)
			return find(node.left, data);
		return true;
	}
	
	public boolean findIter(TreeNode node, int data) {
		while (node != null && node.val != data) {    // forget to use && rather than &
			if (node.val < data)
				node = node.right;
			if (node.val > data);
				node = node.left;
		}

		if (node == null)
			return false;
		return true;
	}
	
	public int findMin(TreeNode node) {
		if (node == null)
			return -1;
		if (node.left != null)
			return findMin(node.left);
		return node.val;
	}
	
	public int findMinIter(TreeNode node) {
		if (node == null)
			return -1;
		while (node.left != null)
			node = node.left;
		return node.val;
	}
	
	
	public void remove(int data) {		// define an instance variable to record the state of removing
		root = removeHelper(root, data);
	}
	
	private TreeNode removeHelper(TreeNode root, int data) {
		// base case
		if (root == null) 
			return null;
		
		// Otherwise, recur down the tree
		if (root.val < data)
			root.right = removeHelper(root.right, data);
		else if (root.val > data) 
			root.left = removeHelper(root.left, data);
		// if the data == root.val, this is the node to be deleted
		else {
			// the root has one child or no child
			if (root.right == null)
				return root.left;
			else if (root.left == null)
				return root.right;
			
			// the root has two children
			// get the smallest of the right subtree
			int minRight = minValue(root.right);
			// replace the root value with new value
			root.val = minRight;
			// delete 
			root.right = removeHelper(root.right, minRight);
			
		}
		return root;
	}
	
	private int minValue(TreeNode root) {
		if (root == null) return -1;
		while (root.left != null)
			root = root.left;
		return root.val;
	}
	
	public void insert(int data) {
		root = addHelper(root, data);
	}
	
	public TreeNode addHelper(TreeNode root, int data) {
		// return a node is necessary unless writing something different
		if (root == null) {
			return new TreeNode(data);
		}
		if (root.val < data) {
			root.right = addHelper(root.right, data);  
		}
		
		if (root.val >= data) {
			root.left = addHelper(root.left, data);
		}
		return root;
		
	}
	
	
	private void visit(TreeNode node) {
		System.out.print(node.val + " ");
	}
	
	public static void main(String[] args)
    {
        BinarySearchTree tree = new BinarySearchTree();
 
        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
        20   40  60   80 */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
 
        System.out.println("Inorder traversal of the given tree");
        tree.inOrderTraversal();
 
        System.out.println("\nDelete 20");
        tree.remove(20);
        System.out.println("Inorder traversal of the modified tree");
        tree.inOrderTraversal();
 
        System.out.println("\nDelete 30");
        tree.remove(30);
        System.out.println("Inorder traversal of the modified tree");
        tree.inOrderTraversal();
 
        System.out.println("\nDelete 50");
        tree.remove(50);
        System.out.println("Inorder traversal of the modified tree");
        tree.inOrderTraversal();
    }
	
}
