import java.util.Iterator;
import java.util.LinkedList;

import static java.lang.Math.pow;

/**
 * Created by qiangzhou on 2018-05-21.
 */
public class Main2 {
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
            outerLoop:
            for (int j = 1; j < ylength; j++){

                int max = -1;
                int bestX = 0;
                int bestY = 0;
                LinkedList<Score> tempScores;
                Score tempScore;
                int tempSimilarityScore;
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
                            cells[i][j].addScore(tempSimilarityScore, tempX, tempY);
                            if (tempSimilarityScore > max){
                                max = tempSimilarityScore;
                                bestX = tempX;
                                bestY = tempY;
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
                            cells[i][j].addScore(tempSimilarityScore, tempX, tempY);
                            if (tempSimilarityScore > max){
                                max = tempSimilarityScore;
                                bestX = tempX;
                                bestY = tempY;
                            }
                        }
                    }
                }

                if (cells[i - 1][j - 1].scores.getFirst().getScore() != 0){
                    tempScores = cells[i - 1][j - 1].scores;
                    Iterator iter = tempScores.iterator();
                    while (iter.hasNext()){
                        tempScore = (Score) iter.next();
                        if (x.charAt(i) == y.charAt(j)) {
                            tempSimilarityScore = tempScore.getScore() + 2;
                        }
                        else{
                            tempSimilarityScore = tempScore.getScore() - 1;
                        }
                        if (tempSimilarityScore > 0) {
                            tempX = tempScore.getX() + 1;
                            tempY = tempScore.getY() + 1;
                            cells[i][j].addScore(tempSimilarityScore, tempX, tempY);
                            if (tempSimilarityScore > max){
                                max = tempSimilarityScore;
                                bestX = tempX;
                                bestY = tempY;
                            }
                        }
                    }
                }
                else if(x.charAt(i) == y.charAt(j)){
                    tempSimilarityScore = 2;
                    tempX = 1;
                    tempY = 1;
                    cells[i][j].addScore(tempSimilarityScore, tempX, tempY);
                    if (tempSimilarityScore > max){
                        max = tempSimilarityScore;
                        bestX = tempX;
                        bestY = tempY;
                    }
                }
                else{
                    Score zeroScore = new Score(0,0,0);
                    cells[i][j].scores.add(zeroScore);
                }

                //test if the max alignment is efficient enough.
                boolean best = false;
                innerLoop:
                while (best == false) {
                    Iterator it2 = cells[i][j].scores.iterator();
                    while (it2.hasNext()) {
                        tempScore = (Score) it2.next();
                        tempX = tempScore.getX();
                        tempY = tempScore.getY();
                        if (max == -1){
                            continue outerLoop;
                        }
                        if (tempScore.getScore() == max) {
                            if ((tempX <= bestX && tempY < bestY) || (tempX < bestX && tempY <= bestY)) {
                                bestX = tempX;
                                bestY = tempY;
                                continue innerLoop;
                            }
                        }
                    }
                    best = true;
                }
                if (cells[i][j].scores.size() == 0){
                    cells[i][j].addScore(0, 0, 0);
                }
                else {
                    LinkedList<Score> itemNeedToBeDelete = new LinkedList<>();
                    Iterator it2 = cells[i][j].scores.iterator();
                    while (it2.hasNext()) {
                        tempScore = (Score) it2.next();
                        tempX = tempScore.getX();
                        tempY = tempScore.getY();
                        if (tempScore.getScore() == max) {
                            if ((tempX >= bestX && tempY > bestY) || (tempY >= bestY && tempX > bestX)) {
                                itemNeedToBeDelete.add(tempScore);
                            }
                        } else if (tempScore.getScore() < max) {
                            if (tempX >= bestX && tempY >= bestY) {
                                itemNeedToBeDelete.add(tempScore);
                            }
                        }
                    }
                    Iterator deleteIt = itemNeedToBeDelete.iterator();
                    while (deleteIt.hasNext()){
                        cells[i][j].scores.remove(deleteIt.next());
                    }
                }
            }
        }

        int[][] ar = new int[xlength][ylength];
        for (int i = 0; i < xlength; i++){
            for (int j = 0; j <ylength; j++){

                Score[] uniqScore = cells[i][j].scores.toArray(new Score[cells[i][j].scores.size()]);
                if (uniqScore.length != 1) {
                    if (i == 12 && j == 12){
                        System.out.print("*");
                    }
                    for (int m = 0; m < uniqScore.length; m++) {
                        if (uniqScore[m].getScore() != 0){
                            for (int n = m + 1; n < uniqScore.length; n++) {
                                if (uniqScore[m].getScore() == uniqScore[n].getScore() && uniqScore[m].getX() == uniqScore[n].getX() && uniqScore[m].getY() == uniqScore[n].getY()) {
                                    uniqScore[n].setScore(0);
                                    uniqScore[n].setXY(0, 0);
                                }
                            }
                        }
                    }
                    cells[i][j].scores = new LinkedList<>();
                    for (int m = 0; m < uniqScore.length; m++) {
                        if (uniqScore[m].getScore() != 0) {
                            cells[i][j].addScore(uniqScore[m].getScore(), uniqScore[m].getX(), uniqScore[m].getY());
                        }
                    }
                }
                /*int count = 0;
                Score[] uniqScore = cells[i][j].scores.toArray(new Score[cells[i][j].scores.size()]);
                LinkedList copy = (LinkedList) cells[i][j].scores.clone();
                Iterator it = copy.iterator();
                while (it.hasNext()){
                    Score tempScore = (Score) it.next();
                    for (int m = 0; m < uniqScore.length; m++){
                        if (tempScore.getScore() == uniqScore[m].getScore() && tempScore.getX() == uniqScore[m].getX() && tempScore.getY() == uniqScore[m].getY()){
                            if (count != 0) {
                                cells[i][j].scores.remove(tempScore);
                                Score zero = new Score(0, 0, 0);
                                uniqScore[m] = zero;
                            }
                            else{
                                count++;
                            }
                        }
                    }
                    count = 0;
                }*/

                ar[i][j] = cells[i][j].scores.size();
                System.out.print(ar[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("table Finished");
    }


    private static float concave(float i){
        return -(float)pow(i, 2);
    }
}