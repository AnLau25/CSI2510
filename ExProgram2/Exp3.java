import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exp3 {
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
        long startTime = 0;
        long endTime = 0;
        double durationN = 0;
        double durationKD = 0;
        int clustrs = 0;
        String file = args[1].replace(".csv", "");
        List<Point3D> listOfPoints;

        boolean isLinear = args[0].equals("lin");

        if (isLinear) {
            startTime = System.nanoTime();
            listOfPoints = DBScan.read(args[1]);
            DBScan newScanN = new DBScan(listOfPoints);
            newScanN.setEps(Double.parseDouble(args[2]));
            newScanN.setMinPts(Integer.parseInt(args[3]));
            newScanN.findClusters();

            clustrs = newScanN.getNumberOfClusters();
            newScanN.save(file+"_clusters_lin_"+args[2]+"_"+args[3]+"_"+clustrs+".csv");


            System.out.println("Noise points: " + newScanN.getNoisePoints());
            endTime = System.nanoTime();
            durationN = (endTime - startTime)/ (double)1000000;
            System.out.println("Time ellapsed (Linear): "+durationN+" ms");
        } else {
            startTime = System.nanoTime();
            listOfPoints = DBScanKD.read(args[1]);
            DBScanKD newScanKd = new DBScanKD(listOfPoints);
            newScanKd.setEps(Double.parseDouble(args[2]));
            newScanKd.setMinPts(Integer.parseInt(args[3]));
            newScanKd.findClusters();

            clustrs = newScanKd.getNumberOfClusters();
            newScanKd.save(file+"_clusters_kd_"+args[2]+"_"+args[3]+"_"+clustrs+".csv");


            System.out.println("Noise points: " + newScanKd.getNoisePoints());
            endTime = System.nanoTime();
            durationKD = (endTime - startTime)/ (double)1000000;
            System.out.println("Time ellapsed (K-D Tree): "+durationKD+" ms");

        }
        


        
        
        
      }
}
