//Ana Laura Valdés Rodríguez : 300299219

import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueue3<E> extends PriorityQueue<E> implements PriorityQueueIF<E> {

    PriorityQueue<Entree<E>> storage;
    LabelledPoint q;
    PointSet queryPts;

    public PriorityQueue3(int k, LabelledPoint q) {
        // Create a custom comparator to sort by largest distance first
        Comparator<Entree<E>> comparator = (a, b) -> Double.compare(b.getDist(), a.getDist());
        this.storage = new PriorityQueue<>(comparator);
        this.q = q;
    }

    // Inserts the specified element into this queue if it is possible to do so immediately 
	// without violating capacity restrictions.
    public boolean offer(E e){
        return storage.offer(new Entree<E>(e,q.distanceTo((LabelledPoint)e)));
    }

    // Retrieves and removes the head of this queue, or returns null if this queue is empty.
    public E poll(){
        return storage.poll().getPoint();
    }

    // Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
    public E peek(){
        return storage.peek().getPoint();
    }

    // Returns the number of elements in this queue.
    public int size(){
        return storage.size();
    }

    // Returns true if this queue contains no elements.
    public boolean isEmpty(){
        return storage.isEmpty();
    }

}

