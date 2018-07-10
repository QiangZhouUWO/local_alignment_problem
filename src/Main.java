import java.util.Iterator;
import java.util.LinkedList;

import static java.lang.Math.pow;

/**
 * Created by qiangzhou on 2018-05-10.
 */
/*
public class Main {
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

        for (int i = 1; i < xlength; i++){
            for (int j = 1; j < ylength; j++){

                float max = -1;
                LinkedList<Score> tempScores;
                Score tempScore;
                float tempSimilarityScore;
                int tempX;
                int tempY;

                if (cells[i - 1][j].scores.getFirst().getScore() != 0){
                    tempScores = cells[i - 1][j].scores;

                    Iterator it = tempScores.iterator();
                    while (it.hasNext()){
                        tempScore = (Score) it.next();
                        tempSimilarityScore = tempScore.getScore() - 2;
                        if (tempSimilarityScore > 0) {
                            tempX = tempScore.getX() + 1;
                            tempY = tempScore.getY();
                            float cc = (float)pow((pow((tempX * 2 - tempSimilarityScore), 1) + pow((tempY * 2 - tempSimilarityScore), 1)), 1) + tempSimilarityScore;
                            if (tempSimilarityScore / concave(cc) == max) {
                                cells[i][j].addScore(tempSimilarityScore, tempX, tempY);
                            } else if (tempSimilarityScore / concave(cc) > max) {
                                cells[i][j].scores = new LinkedList<>();
                                cells[i][j].addScore(tempSimilarityScore, tempX, tempY);
                                max = tempSimilarityScore / concave(cc);
                            }
                        }
                    }
                }
                if (cells[i][j - 1].scores.getFirst().getScore() != 0){
                    tempScores = cells[i][j - 1].scores;

                    Iterator it = tempScores.iterator();
                    while (it.hasNext()){
                        tempScore = (Score) it.next();
                        tempSimilarityScore = tempScore.getScore() - 2;
                        if (tempSimilarityScore > 0) {
                            tempX = tempScore.getX();
                            tempY = tempScore.getY() + 1;
                            float cc = (float)pow((pow((tempX * 2 - tempSimilarityScore), 1) + pow((tempY * 2 - tempSimilarityScore), 1)), 1) + tempSimilarityScore;
                            if (tempSimilarityScore / concave(cc) == max) {
                                cells[i][j].addScore(tempSimilarityScore, tempX, tempY);
                            } else if (tempSimilarityScore / concave(cc) > max) {
                                cells[i][j].scores = new LinkedList<>();
                                cells[i][j].addScore(tempSimilarityScore, tempX, tempY);
                                max = tempSimilarityScore / concave(cc);

                            }
                        }
                    }
                }
                if (cells[i - 1][j - 1].scores.getFirst().getScore() != 0){
                    tempScores = cells[i - 1][j - 1].scores;

                    Iterator it = tempScores.iterator();
                    while (it.hasNext()){
                        tempScore = (Score) it.next();
                        if (x.charAt(i) == y.charAt(j)){
                            tempSimilarityScore = tempScore.getScore() + 2;
                        }
                        else{
                            tempSimilarityScore = tempScore.getScore() - 1;
                        }
                        if (tempSimilarityScore > 0) {
                            tempX = tempScore.getX() + 1;
                            tempY = tempScore.getY() + 1;
                            float cc = (float)pow((pow((tempX * 2 - tempSimilarityScore), 1) + pow((tempY * 2 - tempSimilarityScore), 1)), 1) + tempSimilarityScore;
                            if (tempSimilarityScore / concave(cc) == max) {
                                cells[i][j].addScore(tempSimilarityScore, tempX, tempY);
                            } else if (tempSimilarityScore / concave(cc) > max) {
                                cells[i][j].scores = new LinkedList<>();
                                cells[i][j].addScore(tempSimilarityScore, tempX, tempY);
                                max = tempSimilarityScore / concave(cc);
                            }
                        }
                    }
                }
                else if(x.charAt(i) == y.charAt(j)){
                    tempSimilarityScore = 2;
                    tempX = 1;
                    tempY = 1;
                    float cc = (float)pow((pow((tempX * 2 - tempSimilarityScore), 1) + pow((tempY * 2 - tempSimilarityScore), 1)), 1) + tempSimilarityScore;
                    if (tempSimilarityScore / concave(cc) == max) {
                        cells[i][j].addScore(tempSimilarityScore, tempX, tempY);
                    } else if (tempSimilarityScore / concave(cc) > max) {
                        cells[i][j].scores = new LinkedList<>();
                        cells[i][j].addScore(tempSimilarityScore, tempX, tempY);
                    }
                }

                if (cells[i - 1][j - 1].scores.getFirst().getScore() == 0 && cells[i - 1][j].scores.getFirst().getScore() == 0 && cells[i][j - 1].scores.getFirst().getScore() == 0){
                    char a = x.charAt(i);
                    char b = y.charAt(j);
                    if (a == b){
                        cells[i][j].scores = new LinkedList<>();
                        cells[i][j].addScore(2, 1, 1);
                    }
                }
            }
        }
        int maxX = 0;
        int maxY = 0;
        float max = 0;
        for (int i = 0; i < xlength; i++){
            for (int j = 0; j < ylength; j++){
                LinkedList<Score> tempScores = cells[i][j].scores;

                Iterator it = tempScores.iterator();
                while (it.hasNext()){
                    float temp = ((Score)it.next()).getScore();
                    if (temp > max){
                        max = temp;
                        maxX = i;
                        maxY = j;
                    }
                }
            }
        }
        System.out.println("X: " + maxX + " Y: " + maxY);
    }

    private static float concave(float i){
        return (float)pow(i, 2);
    }
}
*/