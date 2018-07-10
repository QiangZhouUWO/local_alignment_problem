/**
 * Created by qiangzhou on 2018-05-23.
 */
public class Tree {
    private Node root;

    public Tree(Node node){
        root = node;
        root.setColor(false);
    }

    public void add(Node node){
        Node current = root;
        while (current.getScore() != null){
            if (node.getX() < current.getX() && node.getY() <= current.getY()){
                current.mark();
                current = current.getLeftChild();
            }
            else if (node.getX() < current.getX() && node.getY() > current.getY()){
                current = current.getLeftChild();
            }
            else if (node.getX() == current.getX() && node.getY() <= current.getMinY()){
                root = current.getLeftChild();
                root.setRootParent();
                current = root;
            }
            else if (node.getX() == current.getX() && node.getY() > current.getMinY() && node.getY() < current.getY()){
                current.mark();
                current = current.getRightChild();
            }
            else if (node.getX() > current.getX() && node.getY() < current.getY()){
                current = current.getRightChild();
            }
            else{
                return;
            }
        }
        current.setScore(node.getScore());
        current.setLeftChild(new Node());
        current.setRightChild(new Node());
        current.getLeftChild().setParent(current);
        current.getRightChild().setParent(current);

    }
}
