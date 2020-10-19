package com.tectro.mobileapp3.data_models;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Player {
    private ArrayList<Number> Numbers;
    private final int MaxCollectedNumbers;

    private int Score;

    public void CollectScore(Boolean hasRepetitions) {
        try {
            if (!hasRepetitions)
                for (Number num : Numbers)
                    Score += num.getValue();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean UpdateNumber(Number num) {
        if (!Numbers.contains(num)) {
            if (Numbers.size() < MaxCollectedNumbers) {
                Numbers.add(num);
                return true;
            }
            return false;
        } else {
            Numbers.remove(num);
            return true;
        }
    }

    //region Accessors
    public ArrayList<Number> getNumbers() {
        return Numbers;
    }

    public int getScore() {
        return Score;
    }

    public Player(int maxCollectedNumbers) {
        MaxCollectedNumbers = maxCollectedNumbers;
        Score = 0;
        Numbers = new ArrayList<>();
    }
    //endregion
}
