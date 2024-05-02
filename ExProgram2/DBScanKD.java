
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.lang.Math;

public class DBScanKD {
    private double eps;
    private int minPts;
    private int clusters;

    private List<Point3D> listPts;

    public DBScanKD(List<Point3D> listPts){
        eps = 0.0;
        minPts = 0;
        clusters = 0;


        this.listPts = listPts; 
    }

    public double setEps(double eps1){
        this.eps = eps1;
        return eps;
    }
    public int setMinPts(int minPts1){
        this.minPts = minPts1;
        return minPts;
    }

    /**
     * This method reads a file and extracts all the points in that file
     * @param filename the file
     * @return a list of the points
     */
    public static List<Point3D> read(String filename){
        List<Point3D> pList = new ArrayList<Point3D>();
        String pointStr;
        BufferedReader in = null;
        
        try {
            in = new BufferedReader(new FileReader(filename));
            int line = 0;
            
            while ((pointStr = in.readLine()) != null) {
                if (line > 0) {
                    String[] point = pointStr.split(",");

                    pList.add(new Point3D(Double.parseDouble(point[0]), Double.parseDouble(point[1]), Double.parseDouble(point[2])));
                }
                line++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return pList;
    }

    /**
     * This method performs the DBScan algorithm
     */
    public void findClusters(){
        for (Point3D point3d : listPts) {
            if (point3d.getLabel() == -1) {
                NearestNeighborsKD nearN = new NearestNeighborsKD(listPts);

                List<Point3D> listNeighb = nearN.rangeQuery(point3d, eps);
                if (listNeighb.size() < minPts) {
                    point3d.setLabel(0);
                } else {
                    clusters++;
                    point3d.setLabel(clusters);

                    Stack<Point3D> stackP = new Stack<Point3D>();
                    stackP.addAll(listNeighb);

                    while (!stackP.isEmpty()) {
                        Point3D pt1 = stackP.pop();
                        if (pt1.getLabel() <= 0) {
                            pt1.setLabel(clusters);

                            List<Point3D> pt1Neighb = nearN.rangeQuery(pt1, eps);
                            if (pt1Neighb.size() >= minPts) {
                                stackP.addAll(pt1Neighb);
                            }
                        }
                    }
                }
            }
        }


    }

    public int getNumberOfClusters(){
        return clusters;
    }
    public List<Point3D> getPoints(){
        return listPts;
    }

    /**
     * This method mesures the size of any given cluster
     * @param label random cluster of int value
     * @return the size of that cluster
     */
    private int getClusterSize(int label) {
        int i = 0;
        for (Point3D point3d : listPts) {
            if (point3d.getLabel() == label) {
                i++;
            }
        }
        return i;
    }

    /**
     * This method generates an array containing the sizes of each cluster
     * @return an array
     */
    public int[] clusterSizeList() {
        int[] sizeList = new int[getNumberOfClusters()];

        for (int i = 0; i < sizeList.length; i++) {
            sizeList[i] = getClusterSize(i+1);
        }

        return sizeList;

    }

    /**
     * This method finds each noise point after running the DBScan
     * @return the number of noise points
     */
    public int getNoisePoints(){
        int n = 0;
        for (Point3D point3d : listPts) {
            if (point3d.getLabel() == 0) {
                n++;
            }
        }

        return n;
    }

    private double[][] generateRGB(){
        int nbClusters = getNumberOfClusters();
        double[][] rgbValues = new double[nbClusters][3];
        Random r = new Random();

        for (int i = 0; i < nbClusters; i++) {
            rgbValues[i][0] = Math.round(r.nextDouble()*100.0)/100.0;
            rgbValues[i][1] = Math.round(r.nextDouble()*100.0)/100.0;
            rgbValues[i][2] = Math.round(r.nextDouble()*100.0)/100.0;
        }
        return rgbValues;
    }

    public void save(String filename){
        double[][] rgb = generateRGB();
        OutputStreamWriter out = null;
        try {
            int c;
            out = new OutputStreamWriter(new FileOutputStream(filename));
            out.write("x,y,z,C,R,G,B\n");
            for (Point3D point3d : listPts) {
                if (point3d.getLabel() == 0) {
                    out.write(point3d.toString()+",0.0,0.0,0.0\n");
                } else {
                    c = point3d.getLabel()-1;
                    out.write(point3d.toString()+","+rgb[c][0]+","+rgb[c][1]+","+rgb[c][2]+"\n");
                }
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


}

