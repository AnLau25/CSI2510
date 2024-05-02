//Ana Laura Valdés Rodríguez : 300299219
public class Entree<E> {
    E point;
    double dist;
    int secondNumber;
    
    public Entree( E point, double dist ) {
		this.point   = point;
		this.dist = dist;
	}

    public double getDist() {
        return dist;
    }

    public E getPoint(){
        return  point;
    }

    public void setSecondNumber(int secondNumber) {
        // Add a method to set the second number of the Entree.
        this.secondNumber = secondNumber;
    }
    
    public int getSecondNumber() {
        return secondNumber;
    }
    
}