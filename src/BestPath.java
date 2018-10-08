public class BestPath {

    private int xPosition;
    private int yPosition;
    private int xLength;
    private int yLength;
    private double score;

    public BestPath(int x, int y, int xLength, int yLength, double score){
        xPosition = x;
        yPosition = y;
        this.xLength = xLength;
        this.yLength = yLength;
        this.score = score;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getxLength() {
        return xLength;
    }

    public int getyLength() {
        return yLength;
    }

    public double getScore() {
        return score;
    }

    public int getStartxPosition(){
        return xPosition - xLength + 1;
    }

    public int getStartyPosition(){
        return yPosition - yLength + 1;
    }
}