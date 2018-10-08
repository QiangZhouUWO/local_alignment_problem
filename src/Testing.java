import java.util.ArrayList;

public class Testing {
    public static void main(String[] args){

        Cell[][] temp = new Cell[2][10000];
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 5000; j++){
                temp[i][j] = new Cell();
                temp[i][j].addScore();
                temp[i][j].addScore();

                temp[i][j].addScore();
                temp[i][j].addScore();

                temp[i][j].addScore();
                temp[i][j].addScore();

                temp[i][j].addScore();
                temp[i][j].addScore();

                temp[i][j].addScore();
                temp[i][j].addScore();

                ArrayList newA = new ArrayList();
                newA.add(new Score());
                newA.add(new Score());
                newA.add(new Score());
                newA.add(new Score());
                newA.add(new Score());
                newA.add(new Score());
                newA.add(new Score());
                temp[i][j].scores = newA;

                Tree tr = new Tree(new Node(temp[i][j].scores.get(0)));
                for (int t = 1; t < temp[i][j].scores.size(); t++){
                    tr.add(new Node(temp[i][j].scores.get(t)));
                }
            }
        }
        System.out.println();
    }
}
