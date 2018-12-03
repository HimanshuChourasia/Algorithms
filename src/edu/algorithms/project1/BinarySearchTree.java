/*+----------------------------------------------------------------------
||
||  Class BinarySearchTree
||
||         Author:  Himanshu
||
||        Purpose:  To perform operations on Binary search Tree
||
||  Inherits From:  None
||
||     Interfaces:  None
||
|+-----------------------------------------------------------------------
||
||      Constants:  	None
||
|+-----------------------------------------------------------------------
||
||   Constructors:  Node()
||					Node(String key)
||					BinarySearchTree()			
||
||  Class Methods:  boolean checkHeap(Node root)
||					boolean isMaxHeap(Node root)
||					String lexicographicSort(String key)
||					void insert(String key)
||					void delete(String key)					
||					int findHeight(Node root)					
||					void printInorder(Node root)					
||					void printPreorder(Node root)
||					void printPostorder(Node root)
||					int height(Node root) 
||					int getBalance(Node root)
||					int  max(int leftSubtree ,int rightSubtree)
||					Node rightRotate(Node root) 
||					Node leftRotate(Node root) 
||					BinarySearchTree clearMaps()
||					Node insertIntoBST(Node root, String key)
||					Node  inorderSuccessor(Node root) 
||					Node deleteInBST(Node root,String key) 
||					Map<String, Integer> travesal(Node root)		
||					Map<String, Integer>  findAnagrams(String key) 	    
||					void printAnagrams(Map<String, Integer> map) 
||	
||  Inst. Methods:  None
||
++-----------------------------------------------------------------------*/

package edu.algorithms.project1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Himanshu
 *
 */
public class BinarySearchTree  {

	/* Creating an Node inner class */
	class Node {
		
		String key ;
		int count ;
		Node left , right ;
		int height ;
		
		
		public Node() {
			// TODO Auto-generated constructor stub
		}
		
		public Node(String Key) {
			// TODO Auto-generated constructor stub
		this.key = Key ;
		this.left = this.right = null ;
		this.count = 1;
		this.height = 1 ;
		}
		

	}

	
	Node root ;
	HashMap<String, List<String>> stringMap ;
	HashMap<String, Integer> anagramCount ;
	List<String> inputList ;

	
	/**
	 * Binary Search Tree constructor
	 */
	public BinarySearchTree() {
		// TODO Auto-generated constructor stub
		root = null ;
		stringMap = new HashMap<>();
		anagramCount = new HashMap<>() ;
		inputList = new ArrayList<>();
	}
	/**
	 * @param args
	 */
	
	/**
	 *  method to check heap
	 * 
	 */
	
	public boolean checkHeap(Node root) 
    { 
        //  Base case : single node satisfies property 
        if(root.left == null && root.right==null) 
            return true; 
          
        //  node will be in second last level 
        if(root.right == null) 
        { 
            //  check heap property at Node 
            //  No recursive call , because no need to check last level 
            return root.key.compareTo(root.left.key) >= 0 ; 
        } 
        else if (root.left == null) {
        	return root.key.compareTo(root.right.key) >= 0 ;
		}
        else
        { 
            //  Check heap property at Node and 
            //  Recursive check heap property at left and right subtree 
            if(root.key.compareTo(root.left.key) >= 0 && root.key.compareTo(root.right.key) >= 0) 
                return checkHeap(root.left) && checkHeap(root.right); 
            else
                return false; 
        } 
    } 
	
	/*public int countNode(Node root) {
		if (root == null) {
			return 0 ;
		}
		return 1 + countNode(root.left) + countNode(root.right); 
	}
	*/
	// Method to check if it is a max heap
	public boolean isMaxHeap(Node  root) {
		if (root == null) {
			return true;
		}
		//int count = countNode(root);
		if ( checkHeap(root)) {
			return true ;
		}
		return false ;
	}
	
	/* Method to sort the key in lexicographic order */
	public String lexicographicSort(String key) {
		if (key.length() > 1) {
			String [] arr = key.split("") ;
			for (int i = 0; i < arr.length; i++) {
				for (int j = i+1; j < arr.length; j++) {
					if (arr[i].compareTo(arr[j]) > 0) {
						String temp = arr[i];
						arr[i] = arr[j];
						arr[j] = temp ;
					}
				}
			}
			StringBuilder sbBuilder = new StringBuilder();
			for (String string : arr) 
				sbBuilder.append(string);
			return sbBuilder.toString();	
			
		}
		
		else {
			return key ;
		}
	}
	
	/* insert a node in a BST */
	
	public void insert(String key) {
		root = insertIntoBST(root, key);
		
		
	}
	/* Delete a node in BST  */
	
	public void delete(String key) {
		root = deleteInBST(root,key);
	}
	/* find height of the tree */
	
	public int findHeight(Node root) {
		if (root == null) {
			return -1 ;
		}
		int leftheight = findHeight(root.left);
		int rightheight = findHeight(root.right);
		return  (1 + max(leftheight, rightheight)) ;
	}
	
	/* Print inorder travesal method */
	
	public void printInorder(Node root) {
		if (root!= null) {
			printInorder(root.left);
			System.out.print(root.key+ "("+root.count+")"+"\t");
			printInorder(root.right);
		}
		else {
			return ;
		}
	}
	
	/* Print Preorder travesal method */
	
	public void printPreorder(Node root) {
		if (root!= null) {
			System.out.print(root.key+ "("+root.count+")"+"\t");
			printInorder(root.left);
			printInorder(root.right);
		}
		
		else {
			return ;
		}
	}
	

	/* Print Postorder travesal method */
	
	public void printPostorder(Node root) {
		if (root!= null) {
			printInorder(root.left);
			printInorder(root.right);
			System.out.print(root.key+ "("+root.count+")"+"\t");
		}
		
		else {
			return ;
		}
	}
	
	/* get height of the node */
	
	public int height(Node root) {
		if (root == null) {
			return 0 ;
		}
		return root.height ;
	}
	
	/* get balance of the tree */
	
	public int getBalance(Node root) {
		if (root == null)
		{
			return 0 ;
		}
		else {
			return height(root.left) - height(root.right);
		}
		
	}
	
	/* get Max method */
	
	public int  max(int leftSubtree ,int rightSubtree) {
		return (leftSubtree > rightSubtree) ? leftSubtree : rightSubtree ;
	}
	
	/* Right rotation of AVL operations */
	
	public Node rightRotate(Node root) {
		/* Storing node for  right rotation 
		 * store root.left into x
		 * store x.right into temp
		 * replace root with x
		 * add a left child to right child of x with x.right 
		 * update the heights
		 *
		 */
		Node x = root.left;
		Node temp = x.right ;
		x.right = root;
		root.left = temp;
		
		root.height = max(height(root.left), height(root.right)) + 1 ;
		x.height = max(height(x.left), height(x.right)) + 1;
		
		return x ;
	}
	
	/* Left rotation of AVL operations */
	
	public Node leftRotate(Node root) {
		/* Storing node for  left rotation 
		 * store root.right into x
		 * store x.left into temp
		 * replace root with x
		 * add a right child to left child of x with x.left 
		 * update the height
		 * 
		 * */
		Node x = root.right;
		Node temp = x.left ;
		x.left = root;
		root.right = temp;
		
		root.height = max(height(root.left), height(root.right)) + 1 ;
		x.height = max(height(x.left), height(x.right)) + 1;
		
		
		return x ;
		}
	
	/* Clear Map method */
	
	public BinarySearchTree clearMaps() {
		this.stringMap.clear();
		this.anagramCount.clear();
		return this;
	}
	
	/* Insertion Implementation */
	
	public Node insertIntoBST(Node root, String key) {
		
		/* If root node is null create a new node */
		if (root == null) {
			root = new Node(key);
			return root ;
		}
		
		/* If root node key's value is equal to key increment count handling duplicates */
		
		else if (root.key.compareTo(key) == 0) {
			root.count ++ ;
			return root ;
		}
		
		/* If root node key's value is greater than key then add to left */
		
		else if (root.key.compareTo(key) > 0) {
			root.left = insertIntoBST(root.left, key);
		}
		
		/* If root node key's value is lesser than key then add to right */
		
		else {
			root.right = insertIntoBST(root.right, key) ;
 		}
		
		/* Update heights */
		
		root.height = 1 + max(height(root.left), height(root.right));
		
		/* Get balance after updating the weights */
		
		int bal = getBalance(root);
		
		/* Left -Left Move */
		
		if (bal > 1 && key.compareTo(root.left.key) < 0) {
			return rightRotate(root);
		}
		
		/* Right - Right Move */
		
		else if (bal < -1 && key.compareTo(root.right.key) > 0) {
			return leftRotate(root);
			
		}
		
		/* Left - Right Move */
		
		else if (bal > 1 && key.compareTo(root.left.key) > 0) {
			root.left = leftRotate(root.left);
			return rightRotate(root) ;
		}
		
		/* Right - Left Move */
		
		else if (bal > 1 && key.compareTo(root.right.key) < 0) {
			root.right = rightRotate(root.right);
			return leftRotate(root) ;
		}
		
		
		return root ;
	}
	
	/* Method to find inorder successor */
	
	public Node  inorderSuccessor(Node root) {
		Node temp = root ;
		while (temp.left != null) {
			temp = temp.left;
		}
		return temp ;
	}
	
	/* Delete Implementation */
	
	public Node deleteInBST(Node root,String key) {
		
		/* Base condition */
		
		if (root == null) {
			return root;
	}
		/* check in  right subtree key if key > root.key */
		
		else if (key.compareTo(root.key) > 0) {
			root.right = deleteInBST(root.right, key);
		}
		
		/* check in  left subtree key if  key < root.key */
		
		else if (key.compareTo(root.key)  < 0) {
			root.left = deleteInBST(root.left, key);
		}
		
		/* decrement count if same value */
		
		else if (key.compareTo(root.key) == 0 && root.count > 1) {
			root.count -- ;
			return root;
		}
		
		/* If key and root.key has the same value then check children of to be deleted node. */
		
		else {
		
			/* To be deleted node has only  child */
			if (root.left == null || root.right == null) {
				Node temp = null ;
				/* To be deleted node has only Right  child */
				if (temp == root.left) {
					temp = root.right ;
				}
				
				/* To be deleted node has only Left  child */
				else {
					temp = root.left ;
				}
				
				/* No child leaf node */
				if (temp == null){
					temp = root ;
					root = null ;
				}
				else {
					temp = root ;
				}
			}
			
			
			else {
			
				/* To be deleted node has 2  children 
				 * Find the minimum value in the right subtree and replace that with root
				 * */
				
				Node temp = inorderSuccessor(root.right);
				root.key = temp.key ;
				root.right = deleteInBST(root.right, temp.key);
				
			}
	
	
			
		}
		/* If only Node then return */
		
		if (root == null) {
			return root;
		}
		
		/* Update Height of the tree */
		root.height =  1 + max(height(root.left),height(root.right)) ;
		
		/* Get Balance of the tree */
		
		int balance = getBalance(root);
		/* If Unbalanced then solve using AVL operations */
		/* conditions : balance > 1 and left subtree is heavy 
		 * do a Right rotate to balance the tree.
		 */
		if (balance > 1 && getBalance(root.left) >= 0) {
			return rightRotate(root);
		} 
		
		/* conditions : balance > 1 and right subtree is heavy 
		 * do a Right rotate to balance the tree.
		 */
		
		if (balance > 1 && getBalance(root.left) < 0 ) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}
		
		/* conditions : balance < -1 and right subtree is heavy 
		 * do a Left rotate to balance the tree.
		 */
		if (balance < -1 && getBalance(root.right) <= 0) {
			return leftRotate(root);
		}
		
		/* conditions : balance < -1 and right subtree is heavy 
		 * do a Left rotate to balance the tree.
		 */
		
		if (balance < -1 && getBalance(root.right) > 0) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}
		
		return root ;
	}
	
	/* Method to travese the tree and find anagrams */
	
	public Map<String, Integer> travesal(Node root) {
		Map<String, Integer> map = new HashMap<>();
		if (root != null) {
			travesal(root.left);
			map = this.findAnagrams(root.key);
			travesal(root.right);
			return map ;
		}
		else {
			return map;
		}
	}
	
	/* find anagrams of a given key */
	
	
	public Map<String, Integer>  findAnagrams(String key) {
		List<String> list = new ArrayList<>();
		int count = 0 ;
		String sKey = lexicographicSort(key);
		if (this.stringMap.containsKey(sKey)) {
			list = this.stringMap.get(sKey) ;
			list.add(key);
			this.stringMap.put(sKey, list) ;
		}
		else {
			list.add(key);
			this.stringMap.put(sKey, list);
		}
		
		if (list.size() > 1) {
			count = 1 ;
		}
		
		for (String string : list) {
			this.anagramCount.put(string,count ) ;
		}
		
		
		return this.anagramCount ;
	}
	
	/* Print Anagrams */
	
	public void printAnagrams(Map<String, Integer> map) {
		map.forEach((k,v)-> System.out.println(k +" -" + v));
	}
	
	/* Main method */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		String exitChoice = "yes";
		String choice ;
		String [] input = null;
		String exp = "[a-zA-Z]+" ;
		Pattern pattern = Pattern.compile(exp);
		Matcher matcher = null ;
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		do {
			
			System.out.println("******************Please enter one of the following choices.**************************** ");
			System.out.println("1.Enter Input");
			System.out.println("2.Length of the Balanced BST");
			System.out.println("3.Add Element");
			System.out.println("4.Delete Element");
			System.out.println("5.Print Elements of the BST");
			System.out.println("6.Check BST is MAX Binary heap ");
			System.out.println("7.Find number of Anagrams");
			System.out.println("8.Exit");
			choice = scanner.nextLine();
			if (choice.trim().equals("1") || choice.trim().equals("2") || choice.trim().equals("3") || choice.trim().equals("4") || choice.trim().equals("5")
				|| choice.trim().equals("6") || choice.trim().equals("7") || choice.trim().equals("8") )
			{
				if (choice.trim().equals("1")) {
					System.out.println("Please enter the number of strings you wish to enter");
					try {
						boolean flag = false ;
						binarySearchTree = new BinarySearchTree() ;
						int string_num = Integer.parseInt(scanner.nextLine());
						input = new String[string_num];
						
						for (int i = 0; i < string_num; i++) {
							System.out.println("Enter String "+""+(i+1)+ " :") ;
							String s = scanner.nextLine().toLowerCase();
							matcher = pattern.matcher(s);
							boolean match = matcher.matches();
							if (match) {
								input[i] = s ;
								binarySearchTree.inputList.add(s);
							}
							else {
								System.out.println("Invalid String entered...");
								flag = true ;
								break ;
							}
						}
						if (flag) {
							continue ;
						}
						for (String string : input) {
							binarySearchTree.insert(string);
						}
						
						System.out.println("Do you want to continue? [yes/no]");
						exitChoice = scanner.nextLine();
					} catch (NumberFormatException e) {
						// TODO: handle exception
						System.out.println("Please enter an Integer");
					}
					
					
					
				} 
				
			else if (choice.trim().equals("2")) {
				System.out.println("The length of the BST :" + binarySearchTree.findHeight(binarySearchTree.root));
			}	
			
			else if (choice.trim().equals("3")) {
				System.out.println("Enter a String to be inserted in BST");
				String key = scanner.nextLine().toLowerCase();
				matcher = pattern.matcher(key);
				boolean match = matcher.matches();
				if (match) {
					binarySearchTree.insert(key);
					binarySearchTree.inputList.add(key);
				}
				else {
					System.out.println("Invalid String entered");
				}
				
			}	
			
			
			else if (choice.trim().equals("4")) {
				System.out.println("Enter a String to be deleted in BST");
				String key = scanner.nextLine().toLowerCase();
				matcher = pattern.matcher(key);
				boolean match = matcher.matches();
				if (match) {
					if (binarySearchTree.inputList.contains(key)) {
						binarySearchTree.delete(key);
						binarySearchTree.inputList.remove(key);
					}
					else {
						System.out.println("Input entered not present in the binary tree");
					}
					
				}
				else {
					System.out.println("Invalid String entered");
				}
				
			}	
			
			else if (choice.trim().equals("5")) {
				System.out.println("Please enter one of choices for accessing the BST");
				System.out.println("1.Inorder Travesal");
				System.out.println("2.Preorder Travesal");
				System.out.println("3.Postorder Travesal");
				String pchoice = scanner.nextLine();
				if(pchoice.trim().equals("1") || pchoice.trim().equals("2") || pchoice.trim().equals("3"))
				{
					if (pchoice.trim().equals("1") ) {
						
						binarySearchTree.printInorder(binarySearchTree.root);
						System.out.println();
					}
					else if (pchoice.trim().equals("2")) {
						
						binarySearchTree.printPreorder(binarySearchTree.root);
						System.out.println();
					}
					
					else  {
						
						binarySearchTree.printPostorder(binarySearchTree.root);
						System.out.println();
					}
					
					System.out.println("Do you want to continue? [yes/no]");
					exitChoice = scanner.nextLine();
					
				}
				else {
					System.out.println("Invalid choice .....");
				}
			}	
			
			else if (choice.trim().equals("6")) {
				if (binarySearchTree.isMaxHeap(binarySearchTree.root)) {
					System.out.println("The Binary Search Tree is a Max Heap.");
				}
				else {
					System.out.println("The Binary Search Tree is not a Max Heap.");
				}
			}	
			else if (choice.trim().equals("7")) {
				Map<String, Integer> map = new HashMap<>();
				map = binarySearchTree.travesal(binarySearchTree.root);
				binarySearchTree.printAnagrams(map);
				binarySearchTree = binarySearchTree.clearMaps();
				
			}
		
			else {
				break ;
			}	
			
				
				
			}
			
			else {
				System.out.println("Invalid Input...Please enter number between [1-7]");
			}
		} while (exitChoice.equalsIgnoreCase("yes"));
		
		System.out.println("Thank you....");
					
		scanner.close();
	}

}
