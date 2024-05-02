public class KDTree {

    public class KDnode{
        Point3D point;
        int axis;
        double value;
        KDnode left;
        KDnode right;

        public KDnode(Point3D pt, int axis){
            point = pt;
            this.axis = axis;
            value = pt.get(axis);
            left = right = null;
        }
    }

    private KDnode root;

    public KDTree() {
        root = null;
    }

    public KDnode root(){
        return root;
    }

    public void add(Point3D pt){
        if (root == null) {
            root = new KDnode(pt, 0);
        }
        else {
            insert(pt, root, 0);
        }
    }

    private KDnode insert(Point3D pt, KDnode currentNode, int axis) {
        if (currentNode == null){
            currentNode = new KDnode(pt, axis);
        }
        else if (pt.get(axis) <= currentNode.value) {
            currentNode.left = insert(pt, currentNode.left, (axis+1)%3);
        } 
        else {
            currentNode.right = insert(pt, currentNode.right, (axis+1)%3);
        }

        return currentNode;
    }

}
