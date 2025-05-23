/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 * LinkedBinarySearchTree for tree traversal lab
 * @author Lachlan Plant
 * @param <E>
 */
public class LinkedBinarySearchTree <E extends Comparable>implements Iterable <E> {

	private class Node <E> {
		E elem;

		Node <E>parent;
		Node <E>left;
		Node <E>right;
		public Node( E e, Node <E>p, Node <E>l, Node <E>r ) {
			elem   = e;
			parent = p;
			left   = l;
			right  = r;
		}


	}

	private Node <E>root;
	private int size;

	/**
	 *
	 */
	public LinkedBinarySearchTree() {
		root = null;
		size = 0;
	}


	/**
	 * Adds elem into BST
	 * @param elem
	 * @return
	 */
	public boolean add( E elem ) {
		if( root == null ) {
			root = new Node <> ( elem, null, null, null );
			size++;
			return true;
		}
		else {
			root = insert ( elem, root, null );
			return true;
		}
	} /* add */


	/**
	 * Recursive BST insertion
	 * @param elem
	 * @param curr
	 * @param from
	 * @return
	 */
	private Node <E>insert( E elem, Node <E>curr, Node <E>from ) {
		if( curr == null ) {
			curr = new Node <> ( elem, from, null, null );
			size++;
			return curr;
		}

		if( elem.compareTo ( curr.elem ) < 0 )
			curr.left = insert ( elem, curr.left, curr );

		if( elem.compareTo ( curr.elem ) > 0 )
			curr.right = insert ( elem, curr.right, curr );
		return curr;
	} /* insert */


	/*****************************************************************
	*
	* Recursive Printing Functions
	*
	*
	*****************************************************************/

	/**
	 * Caller method for preorder recursive printing
	 */
	public void printPreorderRecursive() {
		System.out.print ( "Recursive Preorder Printing: " );
		preorderRecursive ( root );
	} /* printPreorderRecursive */


	/**
	 * preorder tree traversal, prints(curr.elem + ", ")
	 * @param curr
	 */
	private void preorderRecursive( Node <E>curr ) {
		//Implement Here
	} /* preorderRecursive */


	/**
	 * Caller method for inorder recursive printing
	 */
	public void printInorderRecursive() {
		System.out.print ( "Recursive Inorder Printing: " );
		inorderRecursive ( root );
	} /* printInorderRecursive */


	/**
	 * inorder tree traversal, prints(curr.elem + ", ")
	 * @param curr
	 */
	private void inorderRecursive( Node <E>curr ) {
		if (curr == null)
            return;
 
        inorderRecursive(curr.left);
 
        // Then print the data of node
        System.out.print(curr.elem + " ");

        inorderRecursive(curr.right);
	} /* inorderRecursive */


	/**
	 * Caller method for postorder recursive printing
	 */
	public void printPostorderRecursive() {
		System.out.print ( "Recursive Postorder Printing: " );
		postorderRecursive ( root );
	} /* printPostorderRecursive */


	/**
	 * postorder tree traversal, prints(curr.elem + ", ")
	 * @param curr
	 */
	private void postorderRecursive( Node <E>curr ) {
		if (curr == null)
            return;
 
        // First print data of node
        System.out.print(curr.elem + " ");
 
        // Then recur on left subtree
        postorderRecursive(curr.left);
 
        // Now recur on right subtree
        postorderRecursive(curr.right);
	} /* postorderRecursive */


	/*****************************************************************
	*
	* Iterator Functions
	*
	*
	*****************************************************************/
	public Iterator iterator() {
		return new InorderIterator();
	} /* iterator */


	public Iterator inorderIterator() {
		return new InorderIterator();
	} /* inorderIterator */


	public Iterator preorderIterator() {
		return new PreorderIterator();
	} /* preorderIterator */


	/*****************************************************************
	*
	* Iterators
	*
	*
	*****************************************************************/




	/**
	 * Tree Iterator using preorder traversal for ordering
	 */
	private class PreorderIterator implements Iterator <E> {
		Node <E>next;

		public PreorderIterator() {
			//Implement Here
		}


		public boolean hasNext() {
			return false;
			//Implement Here
		} /* hasNext */


		public E next() {
			return null;
			//Implement Here
		} /* next */


		public void remove() {
			// not implemented
		} /* remove */


	}

	/**
	 * Tree Iterator using inorder traversal for ordering
	 */
	private class InorderIterator implements Iterator <E> {

		Node <E>next;

		public InorderIterator() {
			//Implement Here
		}


		public boolean hasNext() {
			return false;
			//Implement Here
		} /* hasNext */


		public E next() {
			return null;
			//Implement Here
		} /* next */


		public void remove() {
			// not implemented
		} /* remove */


	}
}
