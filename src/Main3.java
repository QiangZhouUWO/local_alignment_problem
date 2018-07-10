import java.util.ArrayList;

/**
 * Created by qiangzhou on 2018-06-20.
 */
public class Main3 {
    public static void main(String[] args){
        Score[] paths = new Score[14];
        Score path0 = new Score(120, 150, 80);
        Score path1 = new Score(100, 120, 65);
        Score path2 = new Score(90, 120, 60);
        Score path3 = new Score(115, 98,60);
        Score path4 = new Score(110, 100, 55);
        Score path5 = new Score(80, 130, 50);
        Score path6 = new Score(100, 100, 50);
        Score path7 = new Score(120, 95, 50);
        Score path8 = new Score(40, 110, 40);
        Score path9 = new Score(50, 100, 40);
        Score path10 = new Score(105, 95, 40);
        Score path11 = new Score(100, 90, 35);
        Score path12 = new Score(30, 30, 15);
        Score path13 = new Score(20, 20, 10);

        paths[0] = path0;
        paths[1] = path1;
        paths[2] = path2;
        paths[3] = path3;
        paths[4] = path4;
        paths[5] = path5;
        paths[6] = path6;
        paths[7] = path7;
        paths[8] = path8;
        paths[9] = path9;
        paths[10] = path10;
        paths[11] = path11;
        paths[12] = path12;
        paths[13] = path13;

        for (int i = 0; i < 13; i++){
            for (int j = i + 1; j < 14; j++){
                if (paths[i].getScore() == paths[j].getScore()){
                    if (paths[i].getX() == paths[j].getX()){
                        if (paths[i].getY() > paths[j].getY()){
                            paths[i].mark();
                        }
                        else{
                            paths[j].mark();
                        }
                    }
                    else if (paths[i].getY() == paths[j].getY()){
                        if (paths[i].getX() > paths[j].getX()){
                            paths[i].mark();
                        }
                        else{
                            paths[j].mark();
                        }
                    }
                }
                if (paths[i].getScore() > paths[j].getScore()) {
                    if (paths[i].getX() <= paths[j].getX() && paths[i].getY() <= paths[j].getY()) {
                        paths[j].mark();
                    }
                }
                else{
                    if (paths[i].getX() >= paths[j].getX() && paths[i].getY() >= paths[j].getY()){
                        paths[i].mark();
                    }
                }
            }
        }


        int j = 0;
        for (int i = 0; i < 14; i++){
            if (paths[i].isMarked() == false){
                j++;
                int x = paths[i].getX();
                int y = paths[i].getY();
                int s = paths[i].getScore();
                System.out.print(j + "." + "\t");
                System.out.print(x + "\t");
                System.out.print(y + "\t");
                System.out.println(s + "\t");
            }
        }
    }
}
