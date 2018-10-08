import java.util.LinkedList;

/**
 * Created by qiangzhou on 2018-05-10.
 */
public class Score {

    private int score;
    private int xLen;
    private int yLen;
    private boolean mark;
    private int xx;
    private int yy;

    public Score(){
        score = 0;
        xLen = 0;
        yLen = 0;
        mark = false;
        xx = 0;
        yy = 0;
    }

    public Score(int x, int y, int score, int xx, int yy){
        this.score = score;
        xLen = x;
        yLen = y;
        mark = false;
        this.xx = xx;
        this.yy = yy;
    }

    public  void setScore(int score){
        this.score = score;
    }

    public void setXY(int x, int y){
        xLen = x;
        yLen = y;
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

    public boolean isMarked(){
        return mark;
    }

    public int getXX(){
        return xx;
    }

    public int getYY(){
        return yy;
    }
}