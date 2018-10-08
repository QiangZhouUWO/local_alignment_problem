/**
 * Created by qiangzhou on 2018-05-23.
 */
public class Tree {
    private Node root;
    private int count;

    public Tree(Node node){
        root = node;
        root.setColor(false);
        count = 1;
    }

    public boolean add(Node node){
        Node current = root;
        while (current.getScore() != null){
            if (node.getX() < current.getX()){
                current = current.getLeftChild();
            }
            else if(node.getX() == current.getX()){
                if (node.getY() < current.getY()) {
                    current.setScore(node.getScore());
                    return true;
                }
                return false;
            }
            else if(node.getY() < current.getY()){
                if ((current.getLeftChild().getScore() != null && node.getY() < current.getLeftChild().getMinY()) || (current.getLeftChild().getScore() == null)){
                    current = current.getRightChild();
                }
                else {
                    return false;
                }
            }
            else{
                return false;
            }
        }
        current.setScore(node.getScore());
        current.setLeftChild(new Node());
        current.setRightChild(new Node());
        current.getLeftChild().setParent(current);
        current.getRightChild().setParent(current);
        current.setColor(true);

        while (current.getParent() != null && current.getParent().getColor() == true){
            if (current.getParent().isLeftChild(current.getParent().getParent())){
                Node right = current.getParent().getParent().getRightChild();
                if (right.getColor() == true){
                    current.getParent().setColor(false);
                    right.setColor(false);
                    current.getParent().getParent().setColor(true);
                    current = current.getParent().getParent();
                }
                else if (current.isRightChild(current.getParent())) {
                    current = current.getParent();
                    this.leftRotate(current);
                    current.getParent().setColor(false);
                    current.getParent().getParent().setColor(true);
                    this.rightRotate(current.getParent().getParent());
                }
                else if (current.isLeftChild(current.getParent())){
                    current.getParent().setColor(false);
                    current.getParent().getParent().setColor(true);
                    this.rightRotate(current.getParent().getParent());
                }
            }
            else{
                Node left = current.getParent().getParent().getLeftChild();
                if (left.getColor() == true){
                    current.getParent().setColor(false);
                    left.setColor(false);
                    current.getParent().getParent().setColor(true);
                    current = current.getParent().getParent();
                }
                else if (current.isLeftChild(current.getParent())) {
                    current = current.getParent();
                    this.rightRotate(current);
                    current.getParent().setColor(false);
                    current.getParent().getParent().setColor(true);
                    this.leftRotate(current.getParent().getParent());
                }
                else if (current.isRightChild(current.getParent())){
                    current.getParent().setColor(false);
                    current.getParent().getParent().setColor(true);
                    this.leftRotate(current.getParent().getParent());
                }
                /*else{
                    return true;
                }
                this.leftRotate(current.getParent().getParent());*/
            }
        }
        root.setColor(false);
        count++;
        return true;
    }

    public void rightRotate(Node node){
        Node temp = node.getLeftChild();
        node.setLeftChild(temp.getRightChild());
        //if (temp.getRightChild().getScore() != null){
            temp.getRightChild().setParent(node);
        //}
        temp.setParent(node.getParent());
        if (node.getParent() == null){
            root = temp;
        }
        else if(node.isRightChild(node.getParent())){
            node.getParent().setRightChild(temp);
        }
        else{
            node.getParent().setLeftChild(temp);
        }
        temp.setRightChild(node);
        node.setParent(temp);
        node.renewMinY();
    }

    public void leftRotate(Node node){
        Node temp = node.getRightChild();
        node.setRightChild(temp.getLeftChild());
        //if (temp.getLeftChild().getScore() != null){
            temp.getLeftChild().setParent(node);
        //}
        temp.setParent(node.getParent());
        if (node.getParent() == null){
            root = temp;
        }
        else if(node.isLeftChild(node.getParent())){
            node.getParent().setLeftChild(temp);
        }
        else{
            node.getParent().setRightChild(temp);
        }
        temp.setLeftChild(node);
        node.setParent(temp);
        node.renewMinY();
    }
}
