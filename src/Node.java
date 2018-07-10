/**
 * Created by qiangzhou on 2018-05-23.
 */
public class Node {
    private Score score;
    private Node leftChild;
    private Node rightChild;
    private Node parent;

    //if the node is marked, it must be deleted after.
    private boolean mark;

    //true for red, false for black.
    private boolean color;

    public Node(){
        color = false;
        mark = false;
    }

    public Node(Score sc){
        score = sc;
        leftChild = new Node();
        rightChild = new Node();
        leftChild.setParent(this);
        rightChild.setParent(this);
        parent = null;
        color = false;
        mark = false;
    }

    public void setMinY(int min){
        score.setMinY(min);
    }

    public void setScore(Score sc){
        score = sc;
    }

    public void setLeftChild(Node left){
        leftChild = left;
    }

    public void setRightChild(Node right){
        rightChild = right;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public void setRootParent(){
        parent = null;
    }

    public void setColor(boolean col){
        color = col;
    }

    public void mark(){
        mark = true;
    }

    public void renewMinY(){
        if (leftChild != null){
            if (leftChild.getMinY() < score.getMinY()){
                score.setMinY(leftChild.getMinY());
            }
        }
        if (rightChild != null){
            if (rightChild.getMinY() < score.getMinY()){
                score.setMinY(rightChild.getMinY());
            }
        }
    }

    public int getX(){
        return score.getX();
    }

    public int getY(){
        return score.getY();
    }

    public Score getScore(){
        return score;
    }

    public Node getLeftChild(){
        return leftChild;
    }

    public Node getRightChild(){
        return rightChild;
    }

    public Node getParent(){
        return parent;
    }

    public int getMinY(){
        return score.getMinY();
    }

    public boolean getColor(){
        return color;
    }

    public boolean isMarked(){
        return mark;
    }
}