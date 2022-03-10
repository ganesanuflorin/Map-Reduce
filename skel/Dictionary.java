import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;

public class Dictionary {
    HashMap<Integer, Integer> sacks;
    ArrayList<String> maxWords;

    public Dictionary() {
        this.sacks = new HashMap<>();
        this.maxWords = new ArrayList<>();
    }


    public HashMap<Integer, Integer> getSacks() {
        return sacks;
    }

    public void setSacks(HashMap<Integer, Integer> sacks) {
        this.sacks = sacks;
    }

    public ArrayList<String> getMaxWords() {
        return maxWords;
    }

    public void setMaxWords(ArrayList<String> maxWords) {
        this.maxWords = maxWords;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "sacks=" + sacks +
                ", maxWords=" + maxWords +
                '}';
    }
}
