
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by qiangzhou on 2018-05-21.
 */
public class MergingPaths {
    /*
    public static void main(String[] args){

        long startTime = System.nanoTime();

        String m = " ";
        String n = " ";

        Random rd = new Random();

        for (int i = 0; i < 700; i ++){
            int temp = rd.nextInt(4);
            int temp1 = rd.nextInt(4);
            if (temp == 1){
                m = m + "A";
            }
            else if(temp == 2){
                m = m + "C";
            }
            else if(temp == 3){
                m = m + "G";
            }
            else{
                m = m + "T";
            }
            if (temp1 == 1){
                n = n + "A";
            }
            else if(temp1 == 2){
                n = n + "C";
            }
            else if (temp1 == 3){
                n = n + "G";
            }
            else {
                n = n + "T";
            }
        }

        //String x = " CGTTGAGATACT";
        //String y = " GCTCTGCGAATA";

        String x = m;
        String y = n;

        //String x = " GTTACAAAGTGGTATCAATCAGATCCACCTCGTCTGCCCCATCTTCCACACCTGATGAGTCTTTCATTCATATTACCAAGATATCAACGATTAAGGCCCC";
        //String y = " CGCGCTAGGGCGTCTCAACGTCTTAGTATCTCGAGTGGCCCAACTTATGCATAGCAAGAGATCCCGTGCGGTTAGTATTTTCACTCCGGAGGTTCTGATG";
        //System.out.println(x);
        //System.out.println(y);

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

                //find if i,j is start point for new path.
                Score localNewPath = null;

                int top = 0;
                int dia = 0;
                int bot = 0;
                ArrayList topPaths = cells[i][j - 1].scores;
                ArrayList diaPaths = cells[i - 1][j - 1].scores;
                ArrayList botPaths = cells[i - 1][j].scores;
                int tempTopScore;
                int tempDiaScore;
                int tempBotScore;
                Score tempBestPath;

                while (top < topPaths.size() || dia < diaPaths.size() || bot < botPaths.size()){

                    boolean isTopTempBest = false;
                    boolean isDiaTempBest = false;
                    boolean isBotTempBest = false;
                    Score tempTop = null;
                    Score tempDia = null;
                    Score tempBot = null;

                    if (top < topPaths.size()) {

                        tempTopScore = ((Score) topPaths.get(top)).getScore() - 2;
                        if (tempTopScore <= 0){
                            top = topPaths.size();
                        }
                    }
                    else{
                        tempTopScore = -1;
                    }
                    if (dia < diaPaths.size()) {
                        if (x.charAt(i) == y.charAt(j)){
                            tempDiaScore = ((Score) diaPaths.get(dia)).getScore() + 2;
                            localNewPath = new Score(1, 1, 2);
                        }
                        else {
                            tempDiaScore = ((Score) diaPaths.get(dia)).getScore() - 1;
                            if (tempDiaScore <= 0){
                                dia = diaPaths.size();
                            }
                        }
                    }
                    else{
                        tempDiaScore = -1;
                    }
                    if (bot < botPaths.size()) {
                        tempBotScore = ((Score) botPaths.get(bot)).getScore() - 2;
                        if (tempBotScore <= 0){
                            bot = botPaths.size();
                        }
                    }
                    else{
                        tempBotScore = -1;
                    }
                    int max = Math.max(tempTopScore, Math.max(tempDiaScore, tempBotScore));

                    //if 3 paths have the same score, then add the shortest x-length path to cells[i][j]'s arraylist.
                    if (max > 0 && tempTopScore == max && tempDiaScore == max && tempBotScore == max){

                        tempTop = new Score(((Score) topPaths.get(top)).getX(), ((Score) topPaths.get(top)).getY() + 1, tempTopScore);
                        tempDia = new Score(((Score) diaPaths.get(dia)).getX() + 1, ((Score) diaPaths.get(dia)).getY() + 1, tempDiaScore);
                        tempBot = new Score(((Score) botPaths.get(bot)).getX() + 1, ((Score) botPaths.get(bot)).getY(), tempBotScore);

                        //comparing top and dia.
                        if (tempTop.getX() < tempDia.getX()){
                            tempBestPath = tempTop;
                            isTopTempBest = true;
                            if (tempTop.getY() <= tempDia.getY()){
                                dia++;
                                continue;
                            }
                        }
                        else if (tempTop.getX() == tempDia.getX()){
                             if (tempTop.getY() <= tempDia.getY()){
                                 //tempBestPath = tempTop;
                                 //isTopTempBest = true;
                                 dia++;
                                 continue;
                             }
                             else {
                                 //tempBestPath = tempDia;
                                 //isDiaTempBest = true;
                                 top++;
                                 continue;
                             }
                        }
                        else {
                            tempBestPath = tempDia;
                            if (tempTop.getY() >= tempDia.getY()){
                                top++;
                                continue;
                            }
                            isDiaTempBest = true;
                        }

                        //comparing temporary best and bot.
                        if (tempBestPath.getX() < tempBot.getX()){
                            if (tempBestPath.getY() <= tempBot.getY()){
                                bot++;
                                continue;
                            }
                        }
                        else if (tempBestPath.getX() == tempBot.getX()){
                            if (tempBestPath.getY() <= tempBot.getY()){
                                bot++;
                                continue;
                            }
                            else{
                                //tempBestPath = tempBot;
                                if (isTopTempBest == true){
                                    //isTopTempBest = false;
                                    top++;
                                    continue;
                                }
                                else{
                                    //isDiaTempBest = false;
                                    dia++;
                                    continue;
                                }
                                //isBotTempBest = true;
                            }
                        }
                        else{
                            if (tempBestPath.getY() >= tempBot.getY()){
                                if (isTopTempBest == true){
                                    //isTopTempBest = false;
                                    top++;
                                    continue;
                                }
                                else{
                                    //isDiaTempBest = false;
                                    dia++;
                                    continue;
                                }
                            }
                            tempBestPath = tempBot;
                            //isBotTempBest = true;
                        }
                    }

                    //if top and dia have the same highest score.
                    else if (max > 0 && tempTopScore == max && tempDiaScore == max){

                        tempTop = new Score(((Score) topPaths.get(top)).getX(), ((Score) topPaths.get(top)).getY() + 1, tempTopScore);
                        tempDia = new Score(((Score) diaPaths.get(dia)).getX() + 1, ((Score) diaPaths.get(dia)).getY() + 1, tempDiaScore);

                        if (tempTop.getX() < tempDia.getX()){
                            tempBestPath = tempTop;
                            isTopTempBest = true;
                            if (tempTop.getY() <= tempDia.getY()){
                                dia++;
                                continue;
                            }
                        }
                        else if (tempTop.getX() == tempDia.getX()){
                            if (tempTop.getY() <= tempDia.getY()){
                                //tempBestPath = tempTop;
                                //isTopTempBest = true;
                                dia++;
                                continue;
                            }
                            else {
                                tempBestPath = tempDia  ;
                                isDiaTempBest = true;
                                top++;
                                continue;
                            }
                        }
                        else {
                            tempBestPath = tempDia;
                            if (tempTop.getY() >= tempDia.getY()){
                                top++;
                                continue;
                            }
                            isDiaTempBest = true;
                        }
                    }

                    //if top and bot have the same highest score.
                    else if (max > 0 && tempTopScore == max && tempBotScore == max){
                        tempTop = new Score(((Score) topPaths.get(top)).getX(), ((Score) topPaths.get(top)).getY() + 1, tempTopScore);
                        tempBot = new Score(((Score) botPaths.get(bot)).getX() + 1, ((Score) botPaths.get(bot)).getY(), tempBotScore);

                        if (tempTop.getX() < tempBot.getX()){
                            tempBestPath = tempTop;
                            isTopTempBest = true;
                            if (tempTop.getY() <= tempBot.getY()){
                                bot++;
                                continue;
                            }
                        }
                        else if (tempTop.getX() == tempBot.getX()){
                            if (tempTop.getY() <= tempBot.getY()){
                                //tempBestPath = tempTop;
                                //isTopTempBest = true;
                                bot++;
                                continue;
                            }
                            else {
                                //tempBestPath = tempBot;
                                //isBotTempBest = true;
                                top++;
                                continue;
                            }
                        }
                        else {
                            tempBestPath = tempBot;
                            if (tempTop.getY() >= tempBot.getY()){
                                top++;
                                continue;
                            }
                            //isBotTempBest = true;
                        }
                    }

                    //if dia and bot have the same highest score.
                    else if (max > 0 && tempDiaScore == max && tempBotScore == max){

                        tempDia = new Score(((Score) diaPaths.get(dia)).getX() + 1, ((Score) diaPaths.get(dia)).getY() + 1, tempDiaScore);
                        tempBot = new Score(((Score) botPaths.get(bot)).getX() + 1, ((Score) botPaths.get(bot)).getY(), tempBotScore);

                        if (tempDia.getX() < tempBot.getX()){
                            tempBestPath = tempDia;
                            isDiaTempBest = true;
                            if (tempDia.getY() <= tempBot.getY()){
                                bot++;
                                continue;
                            }
                        }
                        else if (tempDia.getX() == tempBot.getX()){
                            if (tempDia.getY() <= tempBot.getY()){
                                //tempBestPath = tempDia;
                                //isDiaTempBest = true;
                                bot++;
                                continue;
                            }
                            else {
                                //tempBestPath = tempBot;
                                //isBotTempBest = true;
                                dia++;
                                continue;
                            }
                        }
                        else {
                            tempBestPath = tempBot;
                            if (tempDia.getY() >= tempBot.getY()){
                                dia++;
                                continue;
                            }
                            //isBotTempBest = true;
                        }
                    }

                    //if only one path has the highest temp score.
                    else if (max > 0 && tempTopScore == max){
                        tempBestPath = new Score(((Score) topPaths.get(top)).getX(), ((Score) topPaths.get(top)).getY() + 1, tempTopScore);
                        isTopTempBest = true;
                    }
                    else if (max > 0 && tempDiaScore == max){
                        tempBestPath = new Score(((Score) diaPaths.get(dia)).getX() + 1, ((Score) diaPaths.get(dia)).getY() + 1, tempDiaScore);
                        isDiaTempBest = true;
                    }
                    else if (max > 0 && tempBotScore == max){
                        tempBestPath = new Score(((Score) botPaths.get(bot)).getX() + 1, ((Score) botPaths.get(bot)).getY(), tempBotScore);
                        //isBotTempBest = true;
                    }
                    else{
                        break;
                    }

                    cells[i][j].scores.add(tempBestPath);
                    if (isTopTempBest){
                        top++;
                    }
                    else if (isDiaTempBest){
                        dia++;
                    }
                    else{
                        bot++;
                    }
                }

                //sort the paths for cells[i][j].
                if (i == 41 && j == 37){
                    System.out.println();
                }
                if (cells[i][j].scores.size() > 1){
                    ArrayList finalPaths = new ArrayList();
                    Node rt = new Node(cells[i][j].scores.get(0));
                    Tree sortingTree = new Tree(rt);
                    finalPaths.add(cells[i][j].scores.get(0));
                    for (int index = 1; index < cells[i][j].scores.size(); index++){
                        if (localNewPath == null) {
                            if (sortingTree.add(new Node(cells[i][j].scores.get(index)))) {
                                finalPaths.add(cells[i][j].scores.get(index));
                            }
                        }
                        else{
                            if (cells[i][j].scores.get(index).getScore() <= 2){
                                finalPaths.add(localNewPath);
                                break;
                            }
                            else if (sortingTree.add(new Node(cells[i][j].scores.get(index)))) {
                                finalPaths.add(cells[i][j].scores.get(index));
                            }
                        }
                    }
                    cells[i][j].scores = finalPaths;
                }
                else if (cells[i][j].scores.size() == 0){
                    cells[i][j].scores.add(new Score());
                }
                else{
                    if (localNewPath != null){
                        if (cells[i][j].scores.get(0).getScore() <= 2){
                            cells[i][j].scores = new ArrayList<>();
                            cells[i][j].scores.add(localNewPath);
                        }
                        else{
                            cells[i][j].scores.add(localNewPath);
                        }
                    }
                }
            }
        }

        int best = 0;
        Score bestScore = null;
        int iIndex = 0;
        int jIndex = 0;

        for (int i = 0; i < x.length(); i++){
            for (int j = 0; j < y.length(); j++){
                for (int l = 0; l < cells[i][j].scores.size(); l++){
                    if (cells[i][j].scores.get(l).getScore() > best){
                        best = cells[i][j].scores.get(l).getScore();
                        bestScore = cells[i][j].scores.get(l);
                        iIndex = i;
                        jIndex = j;
                    }
                }
                int localBest = 0;
                for (int l = 0; l < cells[i][j].scores.size(); l++){
                    if (cells[i][j].scores.get(l).getScore() > localBest){
                        localBest = cells[i][j].scores.get(l).getScore();
                    }
                }
                //System.out.print(localBest + "\t");
                /*if (cells[i][j].scores.size() > 1){
                    System.out.println(i + "\t" + j + "\t" + cells[i][j].scores.size());
                }*/
            }
            //System.out.println();

        //}
        /*
        int total = 0;
        for (int i = 0; i < x.length(); i++){
            for (int j = 0; j < y.length(); j++){
                if (cells[i][j].scores.size() > 1){
                    System.out.println(i + "\t" + j + "\t" + cells[i][j].scores.size());
                }
                total += cells[i][j].scores.size();
            }
        }
        */
        /*
        int avg = total / (x.length() * y.length());
        System.out.println("the average number of paths stored is: " + avg);
        System.out.println("the highest score is " + best + " at i: " + iIndex + " j: " + jIndex + " with xLength: " + bestScore.getX() + " with yLength: " + bestScore.getY());
        System.out.println();

        long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime)/1000000000;
        System.out.println(totalTime + " seconds");
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long free_mem = rt.freeMemory();
        double used_mem = (double)(total_mem - free_mem)/(1024*1024*1024);
        System.out.println("Amount of used memory: " + used_mem);
    }
}*/