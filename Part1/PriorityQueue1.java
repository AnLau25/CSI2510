//Ana Laura Valdés Rodríguez : 300299219

public class PriorityQueue1<E> implements PriorityQueueIF<E>{ 

    private LabelledPoint q;
    public int size=0;
    public  Entree<E>[] storage; 

    @SuppressWarnings ("unchecked")
    public PriorityQueue1(int k, LabelledPoint q) {
        storage= new Entree[k+1];
        this.q=q;
    }

    // Inserts the specified element into this queue if it is possible to do so immediately 
    // without violating capacity restrictions.
    public boolean offer(E elem){
        if (this.size==this.storage.length){
            return false;
        }
        double dist = q.distanceTo((LabelledPoint)elem);
        
        int index;
        
        for(index=0;index<this.size();index++){
            if (dist<this.storage[index].getDist()){
                break;
            }
        }
        for (int j=this.size(); j>index;j--){
            this.storage[j]=this.storage[j-1];
        }

        this.storage[index]= new Entree<E>(elem,dist);
        this.size++;

        return true;
    }

    // Retrieves and removes the head of this queue, or returns null if this queue is empty.
    public E poll(){
        if (this.isEmpty()){
            return null;   
        }
        E temp=this.storage[size-1].getPoint();
        size--;
        return temp;
    }

    // Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
    public E peek(){
        if (this.isEmpty()){
            return null;
        }
        return (E)this.storage[size-1].getPoint();
    }

    // Returns the number of elements in this queue.
    public int size(){
        return size;
    }

    // Returns true if this queue contains no elements.
    public boolean isEmpty(){
        return size==0;
    }

    
}
