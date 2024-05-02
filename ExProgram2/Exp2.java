import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exp2 {
    // reads a csv file of 3D points (rethrow exceptions!)
  public static List<Point3D> read(String filename) throws Exception {
	  
    List<Point3D> points= new ArrayList<Point3D>(); 
	double x,y,z;
	
	Scanner sc = new Scanner(new File(filename));  
	// sets the delimiter pattern to be , or \n \r
	sc.useDelimiter(",|\n|\r");  

	// skipping the first line x y z
	sc.next(); sc.next(); sc.next();
	
	// read points
	while (sc.hasNext())  
	{  
		x= Double.parseDouble(sc.next());
		y= Double.parseDouble(sc.next());
		z= Double.parseDouble(sc.next());
		points.add(new Point3D(x,y,z));  
	}   
	
	sc.close();  //closes the scanner  
	
	return points;
  }

  public static void main(String[] args) throws Exception{
    boolean isLinear = args[0].equals("lin");
    // not reading args[0]
	double eps= Double.parseDouble(args[1]);
  
    // reads the csv file
    List<Point3D> points= Exp2.read(args[2]);

    int step = Integer.parseInt(args[3]);

    NearestNeighbors nn= new NearestNeighbors(points);
	NearestNeighborsKD nKd = new NearestNeighborsKD(points);

    int i = 0;
    int j = 0;
    long totalTime_lin = 0;
    long totalTime_Kd = 0;
    long startTime = 0;
    long endTime = 0;
    long duration = 0;

    while (i < points.size()) {
        j++;
        if (isLinear) {
            startTime = System.nanoTime();
            nn.rangeQuery(points.get(i), eps);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            totalTime_lin = totalTime_lin + duration;
        } else {
            startTime = System.nanoTime();
            nKd.rangeQuery(points.get(i), eps);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            totalTime_Kd = totalTime_Kd + duration;
        }
        i = i + step;
    }

    j = j - 1;


    if (isLinear) {
        double averageTime_lin = (totalTime_lin/j)/ (double)1000000;
        System.out.println("Average time (Linear): " + averageTime_lin+" ms");
    } else {
        double averageTime_Kd = (totalTime_Kd/j)/ (double)1000000;
        System.out.println("Average time (KD Tree): " + averageTime_Kd+" ms");
    }
  }
}
