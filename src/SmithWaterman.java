import java.io.File;
import java.util.*;

import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 * Created by qiangzhou on 2018-06-20.
 */
public class SmithWaterman {

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

        int xPos = 0;
        int yPos = 0;
        int hs = -1;

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

        //String x = " SPZSLHHQDL";
        //String y = " SSQTMTRFTA";

        //String x = " FZLKBEEYYVZLDWRFKLIQHLIELBZQTDXCBZYRPRNVCDZPQYRXNNQHMKFFHSXIBBXGXZBXTNEDLTWSMMCSRYDHKQKLSMXTZXZAIRKENBZGGCWAAIGERYIZRQLYMHYZDVBKQXRDCYKNXYFXXBVBPSFTCNERFZFKCZAWNVPLMBSDQKBLVSYWIITEGNAIMHYICTVEHRETXVLNZCVFVHEAGHNZCABNNHFCEBWYYLDHXXBRSWRTQXSXTWXIEKMCTYVEKIRIVTSRPAHIMACEFNSGGYINCXWZVBFANLVVGFEFGBBGTWLIVKFBRVPNFIMPMYYXAIWGLXBGTHISQSBFLTDWISXYSWZKVXQYPMFWFYYWCVRYSGTFFLKWGPAFEQBLZRZDXGNZFTGKKVTEMXKNMXQVXGTLADGRLTBVAEGHXEDKLXNGGHKTGAKGNQDZDWGFYAGWXHLSIWKGIMKPVDIRIFFECEEBGIYVVEEGSGKWSQNTWVETREBMXYADDYHEEXSMWPXVVZEFWBDSASBEINTITKVVHTVRAGKRWFWIGRBDKITRVFWHDMQBTLTDRHRSWPLMMDETLFXKFHBSMCHILLALZADZSNPEDHIZITTCFSNGPNFBBICSZMCKNSNMMCFXAFRQMXTXRYEHTYRRYZVXPTCFSRDSZQIFVHEYBHGTBWTPVGXSYCHXTLNERZGAVWIRCLCAYVIALDHZIXTQVQFFSSEYEYPSEEIZFYXMEPYKQRFLPCCNTPFQWPNADDVPDGHMFGQLMBGYIKHTWCKFSQFESBCSLGIZLNACBKICPTZXRACHRQFLFRHTYZNCDNSLWIRGRCPGLDFSEKXZCRMPALZQBLGXVIRWZWNCYMHWITGRWDFRTRCRPSFYTGHFSKAFRYMYLINGGRZHLXHYEPPNQLIQCMMMGYFCXRQFCVCGZBQFRPKTVVFFKKPGLRFSIKAYEKMTCCCICPFKLIFASLXCVYLZZTTVCNFKDHXKHYADBKGPPDNBEYKEPCPB";
        //String y = " HTYTQEKMLKAMKSLYHEVSZEEBHZIVZPAFDNHVFAAYZXHGRLVGQRVCRZRGGDYWYVKLXMXDWSPGFBPTLYMMFZHMVKSPMGKAQRGYCCCZITQYSPMSKNTAYNRYEPETBREPMQDMMXHCZQRZGHDDPAVSBFVVIKYAQVDFWRFLTXLMYBWBWTCGDLYHBINIEKIEBFLDTRVCGIHCABRNYPAYMNKZBXKLLWZWYHPYAPTRKDGIVNGVSDEAAASNMZVFKYXNCQHPSQLLYNAFHASVPVQTVBIHESYEDGVCSYWTZXMKFBYLKTXPBRZZPMDXYIAKZMACIBMFQNDXGBBAQYMTNVVZEHYSXLDZFBMCPQWRYWYCRDEHZRCRQKKCLVZZDLIYNLAGCWALHMSLTZYYGRPSPRHVFTBPQVBQCINAVYQRZSDTSLSYIKKNXRVZCPDEZBLXQGMYEVLISXNVMQDBZDEQCBDPGTXCLLPBIYANXMLWKCQQCSPNHDSPEAGFETHTBXBBFLBPADRTRMRAGYSNLAEFZNQDMESAPRZYCVFCBLWEXRLDIWRMXEDDKVYSTTTXQFFQSDWCTFACHPVIHKAFGVPINKEKVMRWXAZEFNKBXZPIAKSIYHGZBEQGWYNIZQAQYRRTNHSVVIPVCPDTAYMSXWINDNYHQHGNVSIRZWLYCYLZTICALLBRTVZFEHEZGGHYAHHCTPTDDBDRBGFWTERWFEMHBDLLIDXKCQGKCCTBPZKRGQZBTCTWHREGDBBQNGMMSSDDGGZYIGECRHVADZMTENMHZLTWPIQALHNPCYYFPXTWDINVAANHCFQYQRNGPRCLVBQEMZWDTFBCNRPZPIWFTQQHZSYLKAECLQVDKAZMTDAAWIDMEDDTHDKYPVRDTTECAXARXTDZNZBSMSLKAMDKBXVPGWTKCGAMZCPVQLEWZCCNMFHINMEPVGKQVQIBFZIDCCLXCLRMVETPDPQICCQWCXDTEZKLCSTGYDSTKSCVVGMKYTALMHBVNWFI";

        //String x = m;
        //String y = n;

        String x = " ";
        String y = " ";
        try {
            Scanner fasta1 = new Scanner(new File("Q92794.fasta.txt"));
            Scanner fasta2 = new Scanner(new File("Q99250.fasta.txt"));
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

        System.out.println(x);
        System.out.println(y);

        int k = 10;



        int xlength = x.length();
        int ylength = y.length();

        int[][] swPath = new int[xlength][ylength];
        for (int i = 0; i < xlength; i++){
            for (int j = 0; j < xlength; j++){
                swPath[i][j] = -1;
            }
        }


        //initialize the matrix

        Cell[][] cells = new Cell[xlength][ylength];
        for (int i = 0; i < xlength; i++){
            for (int j = 0; j < ylength; j++){
                cells[i][j] = new Cell();
            }
        }

        for (int i = 0; i < xlength; i++){
            cells[i][0].addScore(0, 0, 0, 0, 0);
        }

        for (int j = 1; j < ylength; j++){
            cells[0][j].addScore(0, 0, 0, 0, 0);
        }

        for (int i = 1; i < xlength; i++){
            for (int j = 1; j < ylength; j++){
                int top = cells[i][j - 1].scores.get(0).getScore() + lookUp(y.charAt(j), '*', BLOSUM62, indexTable);
                int bot = cells[i - 1][j].scores.get(0).getScore() + lookUp(x.charAt(i), '*', BLOSUM62, indexTable);
                int dia = cells[i - 1][j - 1].scores.get(0).getScore() + lookUp(x.charAt(i), y.charAt(j), BLOSUM62, indexTable);
                int max = Math.max(Math.max(top, Math.max(bot, dia)), 0);
                cells[i][j].scores.add(new Score(0, 0, max, 0, 0));

                if (max > hs){
                    xPos = i;
                    yPos = j;
                    hs = max;
                }
                if (max == 0){
                    swPath[i][j] = 0;
                }
                else if (max == dia){
                    swPath[i][j] = 2;
                }
                else if (max == top){
                    swPath[i][j] = 1;
                }
                else {
                    swPath[i][j] = 3;
                }
                cells[i - 1][j - 1] = null;
                if (j == y.length()) {
                    cells[i - 1][j] = null;
                }
            }
        }

        String bestx = "";
        String besty = "";
        int i = xPos;
        int j = yPos;
        while (swPath[i][j] != 0){
            if (swPath[i][j] == 1){
                besty = y.charAt(j) + besty;
                j--;
            }
            else if (swPath[i][j] == 2){
                bestx = x.charAt(i) + bestx;
                besty = y.charAt(j) + besty;
                i--;
                j--;
            }
            else {
                bestx = x.charAt(i) + bestx;
                i--;
            }
        }
        System.out.println("the best alignments is as the following: ");
        System.out.println(bestx);
        System.out.println(besty);
        System.out.println(bestx.length());
        System.out.println(besty.length());


        System.out.println("the highest score is: " + hs + " at: x = " + xPos + " y = " + yPos);
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
}