/*
 * Incomplete Experiment 1 
 *
 * CSI2510 Algorithmes et Structures de Donnees
 * www.uottawa.ca
 *
 * Robert Laganiere, 2022
 *
*/ 
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;  

public class Exp1 {
  
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

  public static void save(String filename, List<Point3D> listPts) {
	OutputStreamWriter out = null;

	try {
		out = new OutputStreamWriter(new FileOutputStream(filename));
		for (Point3D point3d : listPts) {
			out.write(point3d.toString()+"\n");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		out.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  
  public static void main(String[] args) throws Exception {  
  
    // not reading args[0]
	double eps= Double.parseDouble(args[1]);
  
    // reads the csv file
    List<Point3D> points= Exp1.read(args[2]);

	Point3D query= new Point3D(Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5]));

	// creates the NearestNeighbor instance
	NearestNeighbors nn= new NearestNeighbors(points);
	NearestNeighborsKD nKd = new NearestNeighborsKD(points);

	List<Point3D> compareList = new ArrayList<>();
	compareList.add(new Point3D(-5.429850155, 0.807567048, -0.398216823));
	compareList.add(new Point3D(-12.97637373, 5.09061138, 0.762238889));
	compareList.add(new Point3D(-36.10818686, 14.2416184, 4.293473762));
	compareList.add(new Point3D(3.107437007, 0.032869335, 0.428397562));
	compareList.add(new Point3D(11.58047393, 2.990601868, 1.865463342));
	compareList.add(new Point3D(14.15982089, 4.680702457, -0.133791584));
	
	int j = -1;

	for (Point3D point3d : compareList) {
		if (query.equals(point3d)) {
			j = compareList.indexOf(point3d) + 1;
		}
	}
	if (j == -1) {
		System.out.println("Point is not part of experiment. Shutting down!");
		System.exit(0);
	}


	List<Point3D> neighbors = new ArrayList<Point3D>();
	if (args[0].equals("lin")) {
		neighbors = nn.rangeQuery(query, eps);
		save("pt"+j+"_lin.txt", neighbors);
		System.out.println("Number of neighbors (Linear): "+neighbors.size());
	} else {
		neighbors = nKd.rangeQuery(query, eps);
		save("pt"+j+"_kd.txt", neighbors);
		System.out.println("Number of neighbors (KD Tree): "+neighbors.size());
	}

	for (Point3D point3d : neighbors) {
		System.out.println(point3d.toString());
	}
	
	
  }
}
