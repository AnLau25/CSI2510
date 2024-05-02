//Ana Laura Valdés Rodríguez : 300299219

import java.util.ArrayList;

public class KNN {
    static int ver, k;
    static PointSet dataSet, query;

    public KNN(PointSet points) {

    }

    public void setK(int n) {
        k = n;
    }

    public static ArrayList<LabelledPoint> findNN(PriorityQueueIF<LabelledPoint> queue) {
        ArrayList<LabelledPoint> voisins = new ArrayList<LabelledPoint>(100);

        for (int i = 0; i < dataSet.getPointsList().size(); i++) {
            queue.offer(dataSet.getPointsList().get(i));

            if(queue.size()>k){
                queue.poll();
            }
        }

        while (!queue.isEmpty()) {
            voisins.add(queue.poll());
        }

        return voisins;
    }

    public static void main(String[] args) {

        int ver = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
        String dataFile = args[2];
        String queryFile = args[3];

        dataSet = new PointSet(PointSet.read_ANN_SIFT(dataFile));
        PointSet query = new PointSet(PointSet.read_ANN_SIFT(queryFile));

        ArrayList<LabelledPoint> points = new ArrayList<LabelledPoint>();

        long start = System.nanoTime();
        for (int i = 0; i < query.getPointsList().size(); i++) {
            switch (ver) {
                case 1:
                    PriorityQueue1<LabelledPoint> queue1 = new PriorityQueue1<LabelledPoint>(k,
                            query.getPointsList().get(i));
                    points = findNN(queue1);
                    break;
                case 2:
                    PriorityQueue2<LabelledPoint> queue2 = new PriorityQueue2<LabelledPoint>(k,
                            query.getPointsList().get(i));
                    points = findNN(queue2);
                    break;
                case 3:
                    PriorityQueue3<LabelledPoint> queue3 = new PriorityQueue3<LabelledPoint>(k,
                            query.getPointsList().get(i));
                    points = findNN(queue3);
                    break;
            }

            System.out.print(query.getPointsList().get(i).getLabel()+":");
            for (int j = 0; j < points.size(); j++) {
                System.out.print(" " + points.get(j).getLabel());
            }
            System.out.println("");
        }
        long end = System.nanoTime();
        System.out.println("Excecution time : "+(end-start));
    }
}
