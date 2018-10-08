import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by qiangzhou on 2018-05-13.
 */
public class Cell {

    public ArrayList<Score> scores;

    public Cell(){

        scores = new ArrayList<>();
    }

    public void addScore(int score, int x, int y, int xx, int yy){
        Score tempScore = new Score(score, x, y, xx, yy);
        scores.add(tempScore);
    }

    public void addScore(){
        scores.add(new Score());
    }

    /*public void addBestPaths(int concaveNum, int i, int j, int x, int y, double score){
        if (bp.size() < concaveNum){
            bp.add(new ArrayList<BestPath>());
            bp.get(concaveNum - 1).add(new BestPath(i, j, x, y, score));
        }
        else {
            if (score > bp.get(concaveNum - 1).get(0).getScore()) {
                bp.set(concaveNum - 1, new ArrayList<BestPath>());
                bp.get(concaveNum - 1).add(new BestPath(i, j, x, y, score));
            } else if (score == bp.get(concaveNum - 1).get(0).getScore()) {
                bp.get(concaveNum - 1).add(new BestPath(i, j, x, y, score));
            }
        }
    }*/
}
