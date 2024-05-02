/* ---------------------------------------------------------------------------------
The GraphA1NN class is the starting class for the graph-based ANN search

(c) Robert Laganiere, CSI2510 2023
------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.Comparator;

class GraphA1NN {
    int S, k;
    static PointSet dataSet, query;

    UndirectedGraph<LabelledPoint> annGraph;
    private PointSet dataset;
    private Set<LabelledPoint> allVertices; // Keep track of vertices

    public void setK(int k) {
        this.k = k;
    }

    public void setS(int s) {
        this.S = s;
    }

    public GraphA1NN(PointSet set) {//Constructor for Graph1ANN 
        annGraph = new UndirectedGraph<>();//Instance of UndirectedGraph
        this.dataset = set;//Dataset
    }

    public void constructANNGraph(int k) throws Exception, IOException {//Method to buld ann graph bassed on knn.txt as adjacency list
        ArrayList<List<Integer>> adjacency = readAdjacencyFile("knn.txt", 10000);//Reads file as an array list of integer lists

        for (int i = 0; i < adjacency.size(); i++) {//read thru the Array list
            List<Integer> neighbors = adjacency.get(i);
            LabelledPoint a = dataSet.getPointsList().get(i);

            for (int j = 0; j < k && j < k; j++) {//Usses int k to determine the number of neighbors considered for each vertex
                int neighborIndex = neighbors.get(j);
                LabelledPoint b = dataSet.getPointsList().get(neighborIndex);
                annGraph.addEdge(a, b);
            }
        }
    }

    public boolean allChecked(List<LabelledPoint> list) {//Support method to check if all the elemnts of a list are checked
        if (list.isEmpty()){//false if empty
            return false;
        }

        for (LabelledPoint point : list) {//False if at least element is unchecked
            if (!point.isChecked()) {
                return false;
            }
        }
        return true;
    }    

    public LabelledPoint getNextUnchecked(List<LabelledPoint> list) {//Support method to iterate thru the list to get unchecked elements  
        for (LabelledPoint point : list) {//Loops
            if (!point.isChecked()) {//Gets unchecked
                return point;
            }
        }
        return null; // Return null if no unchecked element is found
    }

    public LabelledPoint find1ANN(LabelledPoint point) {//Metode qui resort une liste des S closest neighbours
        int randomIndex = (int) (Math.random() * dataSet.getPointsList().size());
        LabelledPoint startVertex = dataSet.getPointsList().get(randomIndex);//Set first vertex visited as a randomly selected point
    
        startVertex.setKey(point.distanceTo(startVertex));//Key is distance from point to vertex in question
        startVertex.setIKey(point.getLabel());//Label of point is the IKey
    
        List<LabelledPoint> kNearestNeighbors = new ArrayList<>(S);//Create List of size S
        kNearestNeighbors.add(startVertex);
    
        while (!allChecked(kNearestNeighbors)) {//Loop thru list until all elements are checked
            LabelledPoint currentVertex = getNextUnchecked(kNearestNeighbors);//Get next unchecked
    
            if (currentVertex != null) {//If vertex not null
                currentVertex.checked();
    
                for (LabelledPoint neighbor : annGraph.getNeighbors(currentVertex)) {//explore it's neighbors in graph
                    if (neighbor.getIKey() != point.getLabel()) {//determine if they belong to the same group
                        neighbor.setKey(point.distanceTo(neighbor));//Update Key and Ikey
                        neighbor.setIKey(point.getLabel());
    
                        Collections.sort(kNearestNeighbors);//Sort based on distance from point
    
                        if (kNearestNeighbors.size() == S) {//If list full replace last element with new element
                            if (Double.compare(neighbor.getKey(), kNearestNeighbors.get(S - 1).getKey()) < 0) {
                                kNearestNeighbors.get(S - 1).unchecked();
                                kNearestNeighbors.remove(S - 1);
                                kNearestNeighbors.add(neighbor);    
                                 Collections.sort(kNearestNeighbors);//Sort regardless
                            }
                        } else {//Else add new
                            kNearestNeighbors.add(neighbor);
                             Collections.sort(kNearestNeighbors);//Sort regardless
                        }
                    }
                }
            }
        }
    
        return kNearestNeighbors.get(0);//Return the first element of list or, the ANN
    }

    // Lecteur du adjacensy
    public static ArrayList<List<Integer>> readAdjacencyFile(String fileName, int numberOfVertices)//Provided by teacher
            throws Exception, IOException {
        ArrayList<List<Integer>> adjacency = new ArrayList<List<Integer>>(numberOfVertices);
        for (int i = 0; i < numberOfVertices; i++)
            adjacency.add(new LinkedList<>());

        // read the file line by line
        String line;
        BufferedReader flightFile = new BufferedReader(new FileReader(fileName));

        // each line contains the vertex number followed
        // by the adjacency list
        while ((line = flightFile.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, ":,");
            int vertex = Integer.parseInt(st.nextToken().trim());
            while (st.hasMoreTokens()) {
                adjacency.get(vertex).add(Integer.parseInt(st.nextToken().trim()));
            }
        }
        return adjacency;
    }

    public int size() {
        return annGraph.size();
    }

    public int Accurracy(LabelledPoint Q, LabelledPoint result, ArrayList<List<Integer>> adjacency)//Calculates the acurracy of output
            throws Exception, IOException {
        for (Integer i : adjacency.get(Q.getLabel())) {//Checks if Q is indeedin the 10 nearests neighbors for the point
            if (i == result.getLabel()) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {//Main
        int S = Integer.parseInt(args[0]);//Inisialisation de variables
        int k = Integer.parseInt(args[1]);
        String dataFile = args[2];
        String queryFile = args[3];

        dataSet = new PointSet(PointSet.read_ANN_SIFT(dataFile));
        query = new PointSet(PointSet.read_ANN_SIFT(queryFile)); // Initialize the class-level query variable

        GraphA1NN graphANN = new GraphA1NN(dataSet);
        // set K and S
        graphANN.setK(k);
        graphANN.setS(S);

        graphANN.constructANNGraph(k);//Construct graph
        ArrayList<List<Integer>> adjacency = readAdjacencyFile("knn_3_10_100_10000.txt", 100);//Read the 10 closests file

        int accuracy = 0;//Counter for accuracy
        for (int i = 0; i < query.getPointsList().size(); i++) {//loops thru quiery points
            LabelledPoint result = graphANN.find1ANN(query.getPointsList().get(i));//Get ANN
            System.out.print(query.getPointsList().get(i).getLabel() + " : ");//Print ANN
            System.out.print(result.getLabel());

            accuracy += graphANN.Accurracy(query.getPointsList().get(i), result, adjacency);
            System.out.println();
        }
        System.out.println("Accuracy: " + accuracy);//Print
    }

}
