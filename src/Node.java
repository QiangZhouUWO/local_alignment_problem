/**
 * Created by qiangzhou on 2018-05-23.
 */
public class Node {
    private Score score;
    private Node leftChild;
    private Node rightChild;
    private Node parent;
    private int minY;

    //true for red, false for black.
    private boolean color;

    public Node(){
        color = false;
        minY = Integer.MAX_VALUE;
    }

    public Node(Score sc){
        score = sc;
        leftChild = new Node();
        rightChild = new Node();
        leftChild.setParent(this);
        rightChild.setParent(this);
        parent = null;
        color = false;
        minY = score.getY();
    }

    public void setMinY(int min){
        minY = min;
    }

    public void setScore(Score sc){
        score = sc;
        minY = this.getY();
        renewMinY();
        Node prt = this.getParent();
        while (prt != null){
            prt.renewMinY();
            prt = prt.getParent();
        }
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

    public void renewMinY(){
        minY = this.getY();
        if (leftChild != null){
            if (leftChild.getMinY() < minY){
                minY = leftChild.getMinY();
            }
        }
        if (rightChild != null){
            if (rightChild.getMinY() < minY){
                minY = rightChild.getMinY();
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
        return minY;
    }

    public boolean getColor(){
        return color;
    }

    public boolean isLeftChild(Node p){
        if (p.getLeftChild().getScore() == null){
            return false;
        }
        else if (this.getX() == p.getLeftChild().getX() && this.getY() == p.getLeftChild().getY() && this.getScore() == p.getLeftChild().getScore()){
            return true;
        }
        return false;
    }

    public boolean isRightChild(Node p){
        if (p.getRightChild().getScore() == null){
            return false;
        }
        else if (this.getX() == p.getRightChild().getX() && this.getY() == p.getRightChild().getY() && this.getScore() == p.getRightChild().getScore()){
            return true;
        }
        return false;
    }

    public boolean isLeaf(){
        if (score == null){
            return true;
        }
        return false;
    }
}