import java.util.ArrayList;

public class MinHeap {

    private ArrayList items;
    private int maxSize;

    public MinHeap(int size){
        items = new ArrayList<BestPath>(size);
        maxSize = size;
    }

    public void add(BestPath bp){
        if (items.size() == 0){
            items.add(bp);
        }
        else if(items.size() < maxSize && bp.getScore() >= ((BestPath)items.get(0)).getScore()){
            items.add(bp);
            this.heapify(items.size() - 1);
        }
        else if(items.size() == maxSize && bp.getScore() > ((BestPath)items.get(0)).getScore()){
            items.set(0, bp);
            this.pushDown();
        }
    }

    private void heapify(int index){
        while (index != 0) {
            if (((BestPath) items.get(index)).getScore() < ((BestPath) items.get((index - 1) / 2)).getScore()) {
                BestPath temp = (BestPath) items.get(index);
                items.set(index, ((BestPath) items.get(index)));
                items.set((index - 1) / 2, temp);
                index = (index - 1) / 2;
            }
            else{
                break;
            }
        }
    }

    private void pushDown(){
        int current = 0;
        int left = current * 2 + 1;
        int right = current * 2 + 2;
        while (left <= items.size() - 1){
            if (right <= items.size() - 1){
                if (((BestPath)items.get(left)).getScore() > ((BestPath)items.get(right)).getScore()){
                    if (((BestPath)items.get(current)).getScore() > ((BestPath)items.get(right)).getScore()){
                        BestPath temp = (BestPath)items.get(current);
                        items.set(current, items.get(right));
                        items.set(right, temp);
                        current = right;
                        left = current * 2 + 1;
                        right = current * 2 + 2;
                    }
                    else{
                        break;
                    }
                }
                else{
                    if (((BestPath)items.get(current)).getScore() > ((BestPath)items.get(left)).getScore()){
                        BestPath temp = (BestPath)items.get(current);
                        items.set(current, items.get(left));
                        items.set(left, temp);
                        current = left;
                        left = current * 2 + 1;
                        right = current * 2 + 2;
                    }
                    else{
                        break;
                    }
                }
            }
            else{
                if (((BestPath)items.get(current)).getScore() > ((BestPath)items.get(left)).getScore()){
                    BestPath temp = (BestPath)items.get(current);
                    items.set(current, items.get(left));
                    items.set(left, temp);
                    current = left;
                    left = current * 2 + 1;
                    right = current * 2 + 2;
                }
                else{
                    break;
                }
            }
        }
    }

    public BestPath getMin(){
        if (items.size() == 0){
            return null;
        }
        BestPath min = (BestPath)items.get(0);
        items.set(0, items.get(items.size() - 1));
        items.remove(items.size() - 1);
        this.pushDown();
        return min;
    }

    public int size(){
        return items.size();
    }
}