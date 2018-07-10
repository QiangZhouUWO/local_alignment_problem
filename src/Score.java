import java.util.LinkedList;

/**
 * Created by qiangzhou on 2018-05-10.
 */
public class Score {


    private int score;
    private int xLen;
    private int yLen;
    private int minY;
    private boolean mark;

    public Score(){
        score = 0;
        xLen = 0;
        yLen = 0;
        minY = 0;
        mark = false;
    }

    public Score(int x, int y, int score){
        this.score = score;
        xLen = x;
        yLen = y;
        minY = y;
        mark = false;
    }


    public  void setScore(int score){
        this.score = score;
    }

    public void setXY(int x, int y){
        xLen = x;
        yLen = y;
    }

    public void setMinY(int min){
        minY = min;
    }

    public void mark(){
        mark = true;
    }

    public int getScore(){
        return score;
    }

    public int getX(){
        return xLen;
    }

    public int getY(){
        return yLen;
    }

    public int getMinY(){
        return minY;
    }

    public boolean isMarked(){
        return mark;
    }
}