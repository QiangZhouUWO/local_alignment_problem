import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by qiangzhou on 2018-05-13.
 */
public class Cell {

    //public LinkedList<Score> scores;

    public ArrayList<Score> scores;

    public Cell(){
        //scores = new LinkedList<>();

        scores = new ArrayList<>();
    }

    public void addScore(int score, int x, int y){
        Score tempScore = new Score(score, x, y);
        scores.add(tempScore);
    }
}
