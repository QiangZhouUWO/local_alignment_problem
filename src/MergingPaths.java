import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import static java.lang.Math.pow;

/**
 * Created by qiangzhou on 2018-05-21.
 */
public class MergingPaths {
    public static void main(String[] args){
        String x = " CGTTGAGATACT";
        String y = " GCTCTGCGAATA";

        int xlength = x.length();
        int ylength = y.length();


        //initialize the matrix

        Cell[][] cells = new Cell[xlength][ylength];
        for (int i = 0; i < xlength; i++){
            for (int j = 0; j < ylength; j++){
                cells[i][j] = new Cell();
            }
        }

        for (int i = 0; i < xlength; i++){
            cells[i][0].addScore(0, 0, 0);
        }

        for (int j = 1; j < ylength; j++){
            cells[0][j].addScore(0, 0, 0);
        }

        for (int i = 1; i < xlength; i++){
            for (int j = 1; j < ylength; j++){
                int top = 0;
                int dia = 0;
                int bot = 0;
                ArrayList topPaths = cells[i - 1][j].scores;
                ArrayList diaPaths = cells[i - 1][j - 1].scores;
                ArrayList botPaths = cells[i][j - 1].scores;
                int tempTopScore = ((Score)topPaths.get(0)).getScore() - 2;
                int tempDiaScore = ((Score)diaPaths.get(0)).getScore() - 1;
                int tempBotScore = ((Score)topPaths.get(0)).getScore() - 2;
                while (top < topPaths.size() || dia < diaPaths.size() || bot < botPaths.size()){
                    if (((Score)topPaths.get(top)).getScore() - 2 > ((Score)diaPaths.get(dia)).getScore() - 1 && ((Score)topPaths.get(top)).getScore() - 2 > ) {

                    }
                }
            }
        }
    }


    private static float concave(float i){
        return -(float)pow(i, 2);
    }
}