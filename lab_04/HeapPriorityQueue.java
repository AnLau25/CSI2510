/**
 * Array Heap implementation of a priority queue
 * @author Lachlan Plant
 */
public class HeapPriorityQueue<K extends Comparable,V> implements PriorityQueue<K ,V> {
    
    private Entry[] storage; //The Heap itself in array form
    private int tail;        //Index of last element in the heap
    
    /**
    * Default constructor
    */
    public HeapPriorityQueue(){
        storage = new Entry[100];
        tail=-1;
    }
    
    /**
    * HeapPriorityQueue constructor with max storage of size elements
    */
    public HeapPriorityQueue(int size){
        storage = new Entry[size];
        tail=-1;
    }
    
    /****************************************************
     * 
     *             Priority Queue Methods
     * 
     ****************************************************/
    
    /**
    * Returns the number of items in the priority queue.
    * O(1)
    * @return number of items
    */
    public int size(){
        return tail+1;
    }

    /**
    * Tests whether the priority queue is empty.
    * O(1)
    * @return true if the priority queue is empty, false otherwise
    */
    public boolean isEmpty(){
        if(tail==-1){
            return true;
        }
        return false;
    }
    
    /**
    * Inserts a key-value pair and returns the entry created.
    * O(log(n))
    * @param key     the key of the new entry
    * @param value   the associated value of the new entry
    * @return the entry storing the new key-value pair
    * @throws IllegalArgumentException if the heap is full
    */
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
        if(key==null||value==null){
            throw new IllegalArgumentException();
        }
        if(tail==storage.length-1){
            throw new IllegalArgumentException();
        }
        Entry<K,V> newEntry = new Entry<>(key,value);
        storage[++tail] = newEntry;
        upHeap(tail);
        //tail++;
        return newEntry;
    }

    /**
    * Returns (but does not remove) an entry with minimal key.
    * O(1)
    * @return entry having a minimal key (or null if empty)
    */
    public Entry<K,V> min(){
        if(isEmpty()){
            return null;
        }
        return storage[0];
    } 
    
    /**
    * Removes and returns an entry with minimal key.
    * O(log(n))
    * @return the removed entry (or null if empty)
    */ 
    public Entry<K,V> removeMin(){
        if(isEmpty()){
            return null;
        }

        Entry<K,V> tmp = storage[0];
        storage[0] = storage[tail];
        storage[tail] = null;
        tail--;
        downHeap(0);

        return tmp;
    }  
    
    
    /****************************************************
     * 
     *           Methods for Heap Operations
     * 
     ****************************************************/
    
    /**
    * Algorithm to place element after insertion at the tail.
    * O(log(n))
    */
    private void upHeap(int location){

        Entry<K,V> element = storage[location];
        int i = location;
        while(location!=size()){
            int parentI = parent(location);
            Entry<K,V> parentN = storage[parentI];
            if(parentN.key.compareTo(element.key)>0){
                swap(parentI,location);
                location = parentI;
            } else {
                break;
            }
        }
    }
    
    /**
    * Algorithm to place element after removal of root and tail element placed at root.
    * O(log(n))
    */
    private void downHeap(int location){
        while(location*2+1 < storage.length && storage[location*2+1] != null){
            int leftChild = location*2 + 1;
            int smallerChild = leftChild;
            if(storage[location*2+2] != null){
                int rightChildIndex = location*2+2;
                if(storage[leftChild].getKey().compareTo(storage[rightChildIndex].getKey()) > 0)
                    smallerChild = rightChildIndex;
            }else if(storage[smallerChild].getKey().compareTo(storage[location].getKey()) > 0)
                break;
            swap(location, smallerChild);
            location = smallerChild;
        }
    }
    
    /**
    * Find parent of a given location,
    * Parent of the root is the root
    * O(1)
    */
    private int parent(int location){
        return (location-1)/2;
    }
    
   
    /**
    * Inplace swap of 2 elements, assumes locations are in array
    * O(1)
    */
    private void swap(int location1, int location2){
        Entry<K,V> tmp = storage[location1];
        storage[location1] = storage[location2];
        storage[location2] = tmp;
    }
    
}
