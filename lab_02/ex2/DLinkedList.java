import org.w3c.dom.Node;

/**
 *  Class doubly linked list.
 *  @author Jeff Souza
 */
class DLinkedList {

	// First node of the list.
	ListNode firstNode;

	// Last node of the list.
	ListNode lastNode;

	/**
	 * Appends a node to the end of the list.
	 * @param ListNode nNode Node to append.
	 */
	void AppendNode( ListNode nNode ) {
		InsertNode ( nNode, lastNode );
	} /* AppendNode */


	/**
	 * Inserts a node into the list after pAfter node.
	 * @param ListNode nNode  Node to insert.
	 * @param ListNode pAfter Node after which the insertion is done.
	 */
	void InsertNode( ListNode nNode, ListNode pAfter ) {

		if (firstNode==null){
            firstNode=nNode;        
        }else{
            pAfter = firstNode;
            while (lastNode.next!=null){
                lastNode=lastNode.next;
            }
            lastNode.next=nNode;
		}	
	} /* InsertNode */


	/**
	 * Removes the specified node from the list.
	 * @param ListNode nNode Node to remove.
	 */
	void RemoveNode( ListNode nNode ) {
        
		//Cas de base
		if (firstNode == null || nNode == null) {
            return;}
  
        //Si la node a effacer est la tete
        if (firstNode == nNode) {
            firstNode = nNode.next;}
  
        //Changer le next de nNode di nNode n'est pas lastnode
        if (nNode.next != null) {
            nNode.next.previous = nNode.previous;}
  
        //Changer le prev seulement si nNode n'est pas firstNode
        if (nNode.previous != null) {
            nNode.previous.next = nNode.next;}
        
        return;
	} /* RemoveNode */


	/**
	 * Prints the content of the list.
	 */
	void print() {
		ListNode    nNode = null;

		System.out.print ( "Current list: " );

		for( nNode = firstNode; nNode != null; nNode = nNode.next ) {
			System.out.print ( nNode.data + " " );
		}
		System.out.println ( "" );
	} /* print */


}
