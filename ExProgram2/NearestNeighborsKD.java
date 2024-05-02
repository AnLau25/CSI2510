import java.util.ArrayList;
import java.util.List;

public class NearestNeighborsKD {
    
    private KDTree kdTree;

    public NearestNeighborsKD(List<Point3D> points) {
        kdTree = new KDTree();
        for (Point3D p : points) {
            kdTree.add(p); 
        }

    }

    public List<Point3D> rangeQuery(Point3D pt, double eps) {
        List<Point3D> neighbors = new ArrayList<Point3D>();
        rangeQuery(pt, eps, neighbors, kdTree.root());
        return neighbors;
    }

    private void rangeQuery(Point3D p, double eps, List<Point3D> neighbors, KDTree.KDnode node){
        if (node == null){
            return;
        }

        if (p.distance(node.point) <= eps) {
            neighbors.add(node.point);
        }

        if (p.get(node.axis) - eps <= node.value){
            rangeQuery(p, eps, neighbors, node.left);
        }

        if (p.get(node.axis) + eps > node.value){
            rangeQuery(p, eps, neighbors, node.right);
        }

        return;
    }
}
