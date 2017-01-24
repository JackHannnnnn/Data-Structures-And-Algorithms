package chaofan;

public class AVLTree {
	private static class Node {
		int key, height;
		Node left, right;
		public Node(int key) {
			this.key = key;
			this.height = 0;
		}
	}
	
	Node root;
	public AVLTree() {}
	
	public int height(Node node) {
		// in case some node is null ; can not use node.height
		if (node == null)
			return -1;
		return node.height;
	}
	
	public int max(int a, int b) {
		return (a < b) ? b : a;
	}
	
	public Node leftRotate(Node node) {
		Node root = node.right;
		Node move = root.left;
		
		root.left = node;
		node.right = move;
		
		// two nodes' heights change and need a update
		// parent height = max child's height + 1
		node.height = max(height(node.left), height(node.right)) + 1;
		root.height = max(height(root.left), height(root.right)) + 1;
		
		return root;
	}
	
	public Node rightRotate(Node node) {
		Node root = node.left;
		Node move = root.right;
		
		root.right = node;
		node.left = move;
		
		node.height = max(height(node.left), height(node.right)) + 1;
		root.height = max(height(root.left), height(root.right)) + 1;
		
		return root;
	}
	
	
	public int getBalance(Node node) {
		if (node == null)
			return 0;
		return height(node.left) - height(node.right);
	}
	
	public void insert(int key) {
		root = insertHelper(root, key);
	}
	
	private Node insertHelper(Node node, int key) {
		if (node == null)
			return new Node(key);
		if (node.key < key)
			node.right = insertHelper(node.right, key);
		else if (node.key > key)
			node.left = insertHelper(node.left, key);
		else   // Dont allow duplicates
			return node;
		
		// insertion completed and update the height
		// only insert at leaves and the update actually percolate up the tree
		node.height = max(height(node.left), height(node.right)) + 1;
		
		int balance = getBalance(node);
		if (balance > 1 && key < node.left.key)
			return rightRotate(node);
		else if (balance < -1 && key > node.right.key) 
			return leftRotate(node);
		else if (balance > 1 && key > node.left.key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		} else if (balance < -1 && key < node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		
		return node;
	}
	
	public void preOrder(Node node) {
		if (node != null) {
			System.out.print(node.key + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		tree.insert(10);
		tree.insert(20);
		tree.insert(30);
		tree.insert(40);
		tree.insert(50);
		tree.insert(25);
		tree.insert(37);

 
        /* The constructed AVL Tree would be
             30
            /  \
          20   40
         /  \     \
        10  25    50
        */
        System.out.println("Preorder traversal" +
                        " of constructed tree is : ");
        tree.preOrder(tree.root);
	}
	
}
