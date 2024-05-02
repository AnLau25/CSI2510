//Ana Laura Valdés Rodríguez : 300299219

public class PriorityQueue2<E> implements PriorityQueueIF<E> {

    private Entree<E>[] storage;
    private int size;
    private LabelledPoint q;

    @SuppressWarnings("unchecked")
    public PriorityQueue2(int k, LabelledPoint q) {
        storage = new Entree[k];
        size = 0;
        this.q = q;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean offer(E elem) {
        double dist = q.distanceTo((LabelledPoint) elem);
        
        if (size < storage.length) {
            storage[size++] = new Entree<>(elem, dist);
            upHeap(size - 1);
        } else {
            // Find the index of the maximum distance element
            int maxIndex = 0;
            double maxDist = storage[0].getDist();
            for (int i = 1; i < size; i++) {
                if (storage[i].getDist() > maxDist) {
                    maxIndex = i;
                    maxDist = storage[i].getDist();
                }
            }
    
            // Check if the new element has a smaller distance
            if (dist < maxDist) {
                storage[maxIndex] = new Entree<>(elem, dist);
                downHeap(maxIndex);
            }
        }
        return true;
    }
    

    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E result = storage[0].getPoint();
        storage[0] = storage[size - 1];
        size--;
        downHeap(0);
        return result;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return storage[0].getPoint();
    }
    
    private void upHeap(int location) {
        int parent;
        Entree<E> element = storage[location];
        while (location > 0) {
            parent = (location - 1) / 2;
            if (storage[parent].getDist() <= element.getDist()) {
                break;
            }
            storage[location] = storage[parent];
            location = parent;
        }
        storage[location] = element;
    }
    
    private void downHeap(int location) {
        int smallerChild;
        int leftChild;
        Entree<E> element = storage[location];
        while (true) {
            leftChild = 2 * location + 1;
            smallerChild = leftChild;
            if (leftChild >= size) {
                break;
            }
            int rightChild = leftChild + 1;
            if (rightChild < size && storage[rightChild].getDist() < storage[leftChild].getDist()) {
                smallerChild = rightChild;
            }
            if (element.getDist() <= storage[smallerChild].getDist()) {
                break;
            }
            storage[location] = storage[smallerChild];
            location = smallerChild;
        }
        storage[location] = element;
    }
}
