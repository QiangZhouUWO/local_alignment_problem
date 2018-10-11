import java.io.File;
import java.util.*;

import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 * Created by qiangzhou on 2018-10-04.
 * This class modified the maxConcaveScorePositions structure.
 * Instead of using linkedlist, I used min heap to sort the highest normalized score alignments.
 */
public class MergingPaths5 {

    public static void main(String[] args){
        long startTime = System.nanoTime();

        int[][] BLOSUM62 = new int[25][25];
        for (int i = 0; i < 25; i++){
            BLOSUM62[0][i] = 0;
            BLOSUM62[i][0] = 0;
        }
        int[] indexTable = new int[128];
        for (int i = 0; i < indexTable.length; i++){
            indexTable[i] = -1;
        }

        int resultSize = 100;
        //LinkedList<BestPath>[] maxConcaveScorePositions = new LinkedList[concaveScores.length];
        MinHeap[] heaps = new MinHeap[3];

        for (int i = 0; i < 3; i++){

            heaps[i] = new MinHeap(resultSize);
        }

        int totalPaths = 0;
        int maxPathNum = 0;

        //input Blosum62 table.
        try {
            Scanner sc = new Scanner(new File("BLOSUM62.txt"));
            String currentLine = sc.nextLine();
            Scanner names = new Scanner(currentLine);
            int tempIndex = 1;
            while(names.hasNext()){
                indexTable[(int)names.next().charAt(0)] = tempIndex;
                tempIndex++;
            }
            int i = 1, j = 1;
            currentLine = sc.nextLine();
            while (!currentLine.isEmpty()){
                Scanner scores = new Scanner(currentLine);
                scores.next();
                while (scores.hasNext()){
                    int score = Integer.parseInt(scores.next());
                    BLOSUM62[i][j] = score;
                    j++;
                }
                i++;
                j = 1;
                currentLine = sc.nextLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < BLOSUM62[0].length; i ++){
            for (int j = 0; j < BLOSUM62[0].length; j++){
                System.out.print(BLOSUM62[i][j] + "\t");
            }
            System.out.println();
        }

        //create 2 random protein sequences.
        String m = " ";
        String n = " ";

        Random rd = new Random();

        for (int i = 0; i < 2000; i ++){
            int temp = rd.nextInt(23);
            int temp1 = rd.nextInt(23);
            if (temp == 1){
                m = m + "A";
            }
            else if(temp == 2){
                m = m + "B";
            }
            else if(temp == 3){
                m = m + "C";
            }
            else if(temp == 4){
                m = m + "D";
            }
            else if(temp == 5){
                m = m + "E";
            }
            else if(temp == 6){
                m = m + "F";
            }
            else if(temp == 7){
                m = m + "G";
            }
            else if(temp == 8){
                m = m + "H";
            }
            else if(temp == 9){
                m = m + "I";
            }
            else if(temp == 10){
                m = m + "K";
            }
            else if(temp == 11){
                m = m + "L";
            }
            else if(temp == 12){
                m = m + "M";
            }
            else if(temp == 13){
                m = m + "N";
            }
            else if(temp == 14){
                m = m + "P";
            }
            else if(temp == 15){
                m = m + "Q";
            }
            else if(temp == 16){
                m = m + "R";
            }
            else if(temp == 17){
                m = m + "S";
            }
            else if(temp == 18){
                m = m + "T";
            }
            else if(temp == 19){
                m = m + "V";
            }
            else if(temp == 20){
                m = m + "W";
            }
            else if(temp == 21){
                m = m + "X";
            }
            else if(temp == 22){
                m = m + "Y";
            }
            else{
                m = m + "Z";
            }

            if (temp1 == 1){
                n = n + "A";
            }
            else if(temp1 == 2){
                n = n + "B";
            }
            else if(temp1 == 3){
                n = n + "C";
            }
            else if(temp1 == 4){
                n = n + "D";
            }
            else if(temp1 == 5){
                n = n + "E";
            }
            else if(temp1 == 6){
                n = n + "F";
            }
            else if(temp1 == 7){
                n = n + "G";
            }
            else if(temp1 == 8){
                n = n + "H";
            }
            else if(temp1 == 9){
                n = n + "I";
            }
            else if(temp1 == 10){
                n = n + "K";
            }
            else if(temp1 == 11){
                n = n + "L";
            }
            else if(temp1 == 12){
                n = n + "M";
            }
            else if(temp1 == 13){
                n = n + "N";
            }
            else if(temp1 == 14){
                n = n + "P";
            }
            else if(temp1 == 15){
                n = n + "Q";
            }
            else if(temp1 == 16){
                n = n + "R";
            }
            else if(temp1 == 17){
                n = n + "S";
            }
            else if(temp1 == 18){
                n = n + "T";
            }
            else if(temp1 == 19){
                n = n + "V";
            }
            else if(temp1 == 20){
                n = n + "W";
            }
            else if(temp1 == 21){
                n = n + "X";
            }
            else if(temp1 == 22){
                n = n + "Y";
            }
            else{
                n = n + "Z";
            }
        }

        String x = " ";
        String y = " ";
        try {
            Scanner fasta1 = new Scanner(new File("Q92794.fasta.txt"));
            Scanner fasta2 = new Scanner(new File("Q99250.fasta.txt"));
            //Scanner fasta1 = new Scanner(new File("Q6NUK1.fasta.txt"));
            //Scanner fasta2 = new Scanner(new File("Q92552.fasta.txt"));
            fasta1.nextLine();
            fasta2.nextLine();
            while (fasta1.hasNext()){
                x = x + fasta1.nextLine();
            }
            while(fasta2.hasNext()){
                y = y + fasta2.nextLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //String x = " SPZSLHHQDL";
        //String y = " SSQTMTRFTA";

        //String x = " TXBVFQBZRICHNMHRDNMLFVMYNXTNXQFFKZRSGMZPLPEZNPLTBNMHCHVQZFAEQECTIPSGTRZZZDNNZRXYNKLFSNCVKBLDIHSKNXWWDHMFGWRXHSXRXYMHSYZTTVPLVFFDWPRNBQPLFZXBGBEFVZNRFTSCKQEHWDEVWQRSSSHQEDSVNQKARSNAEEYSXYWQAAHLQLQGYXXTYLLHIHMCGMXTHKLTYAIKWSPMRWIVHTYHDEVTVZPXPENPMHGBDXFGKEISNLHNESSAZVHBKXHCHZWMAIWCACGDDPGGBMXTLRNVEMAEXEQCSXRMNCMBIMTNQAZPTFBEDDMNMXNXLCENTFXINGSRSSVDTKPRQMDTAMPCFNLBEYZXPKRGFXMVKQYDIMHEVMATGZZXKWIAKPLWEDLAPRMFNDDAGEFPSQWYPDPHYYCBLMFEZHEBTPRMRIDECYGIFMTDRWZCLBCXEIHAXMRETZCMYXBWQVBPMVGXVHHXPGRPDNSEENHINTTVNLQHQXVFKGYNRLTEMXPHILERCEYZYZPENXPVZTNWAKCYFAZCVKHYVLXFYHGKKXRXNTVYSXGERTWHPZVQNIKHABVTFQQZPTFAITSPFHKNGQEWTZNMAZGZTCSCFRPBWISZFNVLGCMATQDTCYLTGXBGAXEAARMVTDLKHMZBKMYBRFRPZYHMGSYZWPNGLFAKYFFRWTAGHKLNBTWFALYPIMSZWBDKCZDPHXLGIRHZHHBNHXGPWPFPYSMLKIKWMIWTGPIKZRHNKYCMGVFWLYZLGLWHSSBNTTCLSRQMPMDNGDIEKBXFWINFYMQDCZFFALMCEVHKCCFNSPELYCYTPYCIGNSKMWFDACNLCNXCHZMYAZRKEAHDXVEHHZADVTVSDNVFPANXCMCMNNRCTCVVWZXWXSWFRLMLTMHHRWIYLZBXCKXXZSIIDWXTRMCXQLEKIEIDDVSQBSEZYDFZBYZLPPPEBIXCBEKSWCTYWAZLZYIEFABPMNKQZPPE";
        //String y = " GERWMFDZPCRLSLPVSKEMZDAPCZAMHYQIXLQWCGCWNFYRCFGFSPHXXQQXNDQDFQCDIGRMLHCSLMGILMTKPIGHYESMPDRMRVISNGXKFFLSXRLTMNWQFSAESBHYIPFVARRDATKMBSMWFDSCIBYSICEDFTDRFAXIKYGCVFIKWGMXDAPZETEZIYZWKQKVLVNBNZQPENCVPPPDFIQLFPRXPWXRDFKCYFTMSXVFZQTXTYBHKAWQXWLTATKBHCCXBRHYGIKATDYNIMWEFGZWAIXKNDSZVHCTYLYRQIKKKGFGRZICBPGAKTMTZMQSTZXXIKEITELBBZKSMCEDFKLCSBPFGMAQSHGLQWMQYRREYFAWQEYBDDADQANABYBTQEGEPKZTZKWKPAVIHBZHMYWKMHIDVBYQBEVRDFFLCRSVIFGZQPEYZGDWLDKLZREILDZGCCCPLGGEIDWMCTKNINQKMARPZMKARXNSYZVTQSHSQLMCDXZSMMCLZSCNEPKDARWWWIPWPKZREIWGIIYYAHVZZISEGZAYFAKEGPRSRNXBBSNVBFNBLBFKZTVLGPCSPSPSYTZXDFFKRPXTVQPGLMXIDYTTEABFQFBTYFGMRHKMMRTPAICLIYTRAPFTXDFRPLEPHVIPLBHTLHKCNQWAIQZMIEPYRNBNESZAKTHTTATMGWCHPQZMZGGHNCISHTTZVSQIYLZLHAFIPVXRYGTQXDWAQGTHCDSPDBLFNZIHDXSVSZXKEQPWEYLSQRNMLXSIWNPELQCEFXKBZKFHSQYLDDZWBXBGSLAHKEHRPAPRNIEXWGKBSTGFHDGCPTMZTVWABKVPQZYTIBEQRBMMRPEFILEVLEXHBCNHHBSRMQRWGBPQVHFGRSALWMBNLYDZCKAYANKVTCLWRVHNBQLKRAIRKPKKWVRWKNSTTNQTPZPGHEFDFGVKCHGHWDRLCIIAFDDIDBAMXIRPKGXQVWWYBMFFHQSSYYDAQIQWSICHPBYZGBHDXQFIPXSK";

        //String x = m;
        //String y = n;

        System.out.println(x);
        System.out.println(y);

        int k = 10;

        int xlength = x.length();
        int ylength = y.length();

        //initialize the matrix

        Cell[] firstCol = new Cell[y.length()];
        Cell[] secondCol = new Cell[y.length()];
        Cell[] switchingCol = firstCol;
        for (int i = 0; i < y.length(); i++){
            firstCol[i] = new Cell();
            firstCol[i].addScore();
            secondCol[i] = new Cell();
        }
        secondCol[0].addScore();

        for (int i = 1; i < xlength; i++){
            for (int j = 1; j < ylength; j++){

                //find if i,j is start point for new path.
                Score localNewPath = null;

                int top = 0;
                int dia = 0;
                int bot = 0;
                ArrayList topPaths = secondCol[j - 1].scores;
                ArrayList diaPaths = firstCol[j - 1].scores;
                ArrayList botPaths = firstCol[j].scores;
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

                        tempTopScore = ((Score) topPaths.get(top)).getScore() + lookUp(x.charAt(i), '*', BLOSUM62, indexTable);
                        if (tempTopScore <= 0){
                            top = topPaths.size();
                        }
                    }
                    else{
                        tempTopScore = -1;
                    }
                    if (dia < diaPaths.size()) {
                        char first = x.charAt(i);
                        char second = y.charAt(j);
                        if (first == second){
                            if (((Score) diaPaths.get(dia)).getScore() != 0){
                                tempDiaScore = ((Score) diaPaths.get(dia)).getScore() + lookUp(x.charAt(i), y.charAt(j), BLOSUM62, indexTable);
                                localNewPath = new Score(1, 1, lookUp(x.charAt(i), y.charAt(j), BLOSUM62, indexTable), lookUp(x.charAt(i), x.charAt(i), BLOSUM62, indexTable), lookUp(y.charAt(j), y.charAt(j), BLOSUM62, indexTable));
                            }
                            else {
                                localNewPath = new Score(1, 1, lookUp(x.charAt(i), y.charAt(j), BLOSUM62, indexTable), lookUp(x.charAt(i), x.charAt(i), BLOSUM62, indexTable), lookUp(y.charAt(j), y.charAt(j), BLOSUM62, indexTable));
                                tempDiaScore = -1;
                                dia = diaPaths.size();
                            }
                        }
                        else {
                            tempDiaScore = ((Score) diaPaths.get(dia)).getScore() + lookUp(x.charAt(i), y.charAt(j), BLOSUM62, indexTable);
                            if (tempDiaScore <= 0){
                                dia = diaPaths.size();
                            }
                        }
                    }
                    else{
                        tempDiaScore = -1;
                    }
                    if (bot < botPaths.size()) {
                        tempBotScore = ((Score) botPaths.get(bot)).getScore() + lookUp(y.charAt(j), '*', BLOSUM62, indexTable);
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

                        tempTop = new Score(((Score) topPaths.get(top)).getX(), ((Score) topPaths.get(top)).getY() + 1, tempTopScore, ((Score) topPaths.get(top)).getXX(), ((Score) topPaths.get(top)).getYY() + lookUp(y.charAt(j), y.charAt(j), BLOSUM62, indexTable));
                        tempDia = new Score(((Score) diaPaths.get(dia)).getX() + 1, ((Score) diaPaths.get(dia)).getY() + 1, tempDiaScore, ((Score) diaPaths.get(dia)).getXX() + lookUp(x.charAt(i), x.charAt(i), BLOSUM62, indexTable), ((Score) diaPaths.get(dia)).getYY() + lookUp(y.charAt(j), y.charAt(j), BLOSUM62, indexTable));
                        tempBot = new Score(((Score) botPaths.get(bot)).getX() + 1, ((Score) botPaths.get(bot)).getY(), tempBotScore,((Score) botPaths.get(bot)).getXX() + lookUp(x.charAt(i), x.charAt(i), BLOSUM62, indexTable), ((Score) botPaths.get(bot)).getYY());

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

                        tempTop = new Score(((Score) topPaths.get(top)).getX(), ((Score) topPaths.get(top)).getY() + 1, tempTopScore, ((Score) topPaths.get(top)).getXX(), ((Score) topPaths.get(top)).getYY() + lookUp(y.charAt(j), y.charAt(j), BLOSUM62, indexTable));
                        tempDia = new Score(((Score) diaPaths.get(dia)).getX() + 1, ((Score) diaPaths.get(dia)).getY() + 1, tempDiaScore, ((Score) diaPaths.get(dia)).getXX() + lookUp(x.charAt(i), x.charAt(i), BLOSUM62, indexTable), ((Score) diaPaths.get(dia)).getYY() + lookUp(y.charAt(j), y.charAt(j), BLOSUM62, indexTable));

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
                        tempTop = new Score(((Score) topPaths.get(top)).getX(), ((Score) topPaths.get(top)).getY() + 1, tempTopScore, ((Score) topPaths.get(top)).getXX(), ((Score) topPaths.get(top)).getYY() + lookUp(y.charAt(j), y.charAt(j), BLOSUM62, indexTable));
                        tempBot = new Score(((Score) botPaths.get(bot)).getX() + 1, ((Score) botPaths.get(bot)).getY(), tempBotScore,((Score) botPaths.get(bot)).getXX() + lookUp(x.charAt(i), x.charAt(i), BLOSUM62, indexTable), ((Score) botPaths.get(bot)).getYY());
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

                        tempDia = new Score(((Score) diaPaths.get(dia)).getX() + 1, ((Score) diaPaths.get(dia)).getY() + 1, tempDiaScore, ((Score) diaPaths.get(dia)).getXX() + lookUp(x.charAt(i), x.charAt(i), BLOSUM62, indexTable), ((Score) diaPaths.get(dia)).getYY() + lookUp(y.charAt(j), y.charAt(j), BLOSUM62, indexTable));
                        tempBot = new Score(((Score) botPaths.get(bot)).getX() + 1, ((Score) botPaths.get(bot)).getY(), tempBotScore,((Score) botPaths.get(bot)).getXX() + lookUp(x.charAt(i), x.charAt(i), BLOSUM62, indexTable), ((Score) botPaths.get(bot)).getYY());

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
                        tempBestPath = new Score(((Score) topPaths.get(top)).getX(), ((Score) topPaths.get(top)).getY() + 1, tempTopScore, ((Score) topPaths.get(top)).getXX(), ((Score) topPaths.get(top)).getYY() + lookUp(y.charAt(j), y.charAt(j), BLOSUM62, indexTable));
                        isTopTempBest = true;
                    }
                    else if (max > 0 && tempDiaScore == max){
                        tempBestPath = new Score(((Score) diaPaths.get(dia)).getX() + 1, ((Score) diaPaths.get(dia)).getY() + 1, tempDiaScore, ((Score) diaPaths.get(dia)).getXX() + lookUp(x.charAt(i), x.charAt(i), BLOSUM62, indexTable), ((Score) diaPaths.get(dia)).getYY() + lookUp(y.charAt(j), y.charAt(j), BLOSUM62, indexTable));
                        isDiaTempBest = true;
                    }
                    else if (max > 0 && tempBotScore == max){
                        tempBestPath = new Score(((Score) botPaths.get(bot)).getX() + 1, ((Score) botPaths.get(bot)).getY(), tempBotScore,((Score) botPaths.get(bot)).getXX() + lookUp(x.charAt(i), x.charAt(i), BLOSUM62, indexTable), ((Score) botPaths.get(bot)).getYY());
                        //isBotTempBest = true;
                    }
                    else{
                        break;
                    }

                    secondCol[j].scores.add(tempBestPath);
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

                if (secondCol[j].scores.size() > 1){
                    ArrayList finalPaths = new ArrayList();
                    Node rt = new Node(secondCol[j].scores.get(0));
                    if (localNewPath == null || rt.getScore().getScore() > localNewPath.getScore()) {
                        Tree sortingTree = new Tree(rt);
                        finalPaths.add(secondCol[j].scores.get(0));
                        for (int index = 1; index < secondCol[j].scores.size(); index++) {
                            if (localNewPath == null) {
                                if (sortingTree.add(new Node(secondCol[j].scores.get(index)))) {
                                    finalPaths.add(secondCol[j].scores.get(index));
                                }
                            } else {
                                if (secondCol[j].scores.get(index).getScore() <= localNewPath.getScore()) {
                                    finalPaths.add(localNewPath);
                                    break;
                                } else if (sortingTree.add(new Node(secondCol[j].scores.get(index)))) {
                                    finalPaths.add(secondCol[j].scores.get(index));
                                }
                            }
                        }
                        secondCol[j].scores = finalPaths;
                    }
                    else{
                        secondCol[j].scores = new ArrayList<>();
                        secondCol[j].scores.add(localNewPath);
                    }
                }
                else if (secondCol[j].scores.size() == 0){
                    if (localNewPath == null) {
                        secondCol[j].scores.add(new Score());
                    }
                    else {
                        secondCol[j].scores.add(localNewPath);
                    }
                }
                else{
                    if (localNewPath != null){
                        if (secondCol[j].scores.get(0).getScore() <= localNewPath.getScore()){
                            secondCol[j].scores = new ArrayList<>();
                            secondCol[j].scores.add(localNewPath);
                        }
                        else{
                            secondCol[j].scores.add(localNewPath);
                        }
                    }
                }


                //sort the paths again by k value and concave functions.

                if (secondCol[j].scores.size() > 0) {
                    for (int index = 0; index < secondCol[j].scores.size(); index++) {
                        Score currentScore = secondCol[j].scores.get(index);
                        int tempXLength = currentScore.getX();
                        int tempYLength = currentScore.getY();
                        double xx = currentScore.getXX();
                        double yy = currentScore.getYY();

                        double[] localScore = new double[3];
                        localScore[0] = formula1(xx, yy, currentScore.getScore(), k);
                        localScore[1] = formula2(xx, yy, currentScore.getScore(), k);
                        localScore[2] = formula3(xx, yy, currentScore.getScore(), k);

                        //add the local best paths.
                        for (int Index = 0; Index < 3; Index++){

                            heaps[Index].add(new BestPath(i, j, tempXLength, tempYLength, localScore[Index]));
                        }
                    }
                }


                //free the memory space from previous row of the matrix.
                firstCol[j - 1].scores.clear();
                if (j == y.length()) {
                    firstCol[j].scores.clear();
                }
                if (i == xlength - 1){
                    secondCol[j - 1].scores.clear();
                    if (j == ylength - 1){
                        secondCol[j].scores.clear();
                    }
                }
                totalPaths += secondCol[j].scores.size();
                if (secondCol[j].scores.size() > maxPathNum){
                    maxPathNum = secondCol[j].scores.size();
                }
            }
            firstCol = secondCol;
            secondCol = switchingCol;
            switchingCol = firstCol;
            Runtime rs =  Runtime.getRuntime();
            rs.gc();
        }


        BestPath[][] sortedAlignments = new BestPath[3][resultSize];
        for (int i = 0; i < 3; i++){
            int realSize = heaps[i].size();
            for (int j = 0; j < realSize; j++){
                sortedAlignments[i][j] = heaps[i].getMin();
            }
            System.out.println("The best local alignment for concave function " + (i + 1) + " is:");
            for (int index = sortedAlignments[i][realSize - 1].getStartxPosition(); index <= sortedAlignments[i][realSize - 1].getxPosition(); index++){
                System.out.print(x.charAt(index));
            }
            System.out.println();
            for (int index = sortedAlignments[i][realSize - 1].getStartyPosition(); index <= sortedAlignments[i][realSize - 1].getyPosition(); index++){
                System.out.print(y.charAt(index));
            }
            System.out.println();
        }

        //sort the alignments by starting and ending points.
        boolean[][] checkStart = new boolean[x.length()][y.length()];
        boolean[][] checkEnd = new boolean[x.length()][y.length()];

        for (int i = 0; i < x.length(); i++){
            for (int j = 0; j < y.length(); j++){
                checkStart[i][j] = false;
                checkEnd[i][j] = false;
            }
        }
        for (int i = 0; i < 3; i++){
            for (int j = resultSize - 1; j >= 0; j--){
                if (sortedAlignments[i][j] != null){
                    if (checkStart[sortedAlignments[i][j].getStartxPosition()][sortedAlignments[i][j].getStartyPosition()] == true){
                        sortedAlignments[i][j] = null;
                    }
                    else{
                        checkStart[sortedAlignments[i][j].getStartxPosition()][sortedAlignments[i][j].getStartyPosition()] = true;
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++){
            for (int j = resultSize - 1; j >= 0; j--){
                if (sortedAlignments[i][j] != null){
                    if (checkEnd[sortedAlignments[i][j].getxPosition()][sortedAlignments[i][j].getyPosition()] == true){
                        sortedAlignments[i][j] = null;
                    }
                    else{
                        checkEnd[sortedAlignments[i][j].getxPosition()][sortedAlignments[i][j].getyPosition()] = true;
                    }
                }
            }
        }

        ArrayList<ArrayList> finalAlignments = new ArrayList<ArrayList>();
        for (int i = 0; i < 3; i++){
            finalAlignments.add(new ArrayList());
            for (int j = resultSize - 1; j >= 0; j--) {
                if (sortedAlignments[i][j] != null) {
                    finalAlignments.get(i).add(sortedAlignments[i][j]);
                }
            }
        }


        System.out.println("avg paths number for each spot is: " +totalPaths/ ((x.length() - 1) * (y.length())));
        System.out.println("the largest arrayList stored: " + maxPathNum + " paths.");
        long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime)/1000000000;
        System.out.println(totalTime + " seconds");
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long free_mem = rt.freeMemory();
        double used_mem = (double)(total_mem - free_mem)/(1024*1024*1024);
        System.out.println("Amount of used memory: " + used_mem);
        System.out.println();
    }

    private static int lookUp(char x, char y, int[][] table, int[] indexTable){
        return table[indexTable[(int)x]][indexTable[(int)y]];
    }

    private static double concave1(double x){
        //double y = Math.pow(x, 0.26);
        double y = Math.pow(x, 0.4);
        return y;
    }

    private static double concave2(double x){
        //double y = Math.pow(x, 0.4);
        double y = Math.pow(Math.log10(x) + 1, 2.6);
        return y;
    }

    private static double concave3(double x){
        double y = Math.sqrt(x);
        return y;
    }

    private static double formula1(double xx, double yy, double xy, int k){
        if (xy == 0){
            return -Infinity;
        }
        else {

            double temp = Math.pow(Math.pow(xx - xy, k) + Math.pow(yy - xy, k), (double)1 / k) + xy;
            double result = xy / concave1(temp);
            return result;
        }
    }

    private static double formula2(double xx, double yy, double xy, int k){
        if (xy == 0){
            return -Infinity;
        }
        else {
            double temp = Math.pow(Math.pow(xx - xy, k) + Math.pow(yy - xy, k), (double)1 / k) + xy;
            double result = xy / concave2(temp);
            return result;
        }
    }

    private static double formula3(double xx, double yy, double xy, int k){
        if (xy == 0){
            return -Infinity;
        }
        else {
            double temp = Math.pow(Math.pow(xx - xy, k) + Math.pow(yy - xy, k), (double)1 / k) + xy;
            double result = xy / concave3(temp);
            return result;
        }
    }
}