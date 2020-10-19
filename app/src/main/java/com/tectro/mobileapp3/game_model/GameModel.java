package com.tectro.mobileapp3.game_model;

import com.tectro.mobileapp3.data_models.Number;
import com.tectro.mobileapp3.data_models.Player;
import com.tectro.mobileapp3.enums.NumberModifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {

    //region Singleton
    private static GameModel current;

    /**
     * @return if instanse isn`t created method returns NULL
     */
    public static GameModel GetInstance() {
        return current;
    }

    public static GameModel CreateInstance(int playersQuantity, int TotalRounds, int maxCollectedNumbers) {
        if (current == null)
            current = new GameModel(playersQuantity, TotalRounds, maxCollectedNumbers);
        return current;
    }

    public static GameModel RebuildInstance(int playersQuantity, int TotalRounds, int maxCollectedNumbers) {
        current = new GameModel(playersQuantity, TotalRounds, maxCollectedNumbers);
        return current;
    }
    //endregion

    //region Constructor
    private GameModel(int playersQuantity, int totalRounds, int maxCollectedNumbers) {
        rand = new Random();
        CurrentRound = 1;
        TotalRounds = totalRounds;
        gameFinished = false;

        Players = new ArrayList<>();
        for (int i = 0; i < playersQuantity; i++)
            Players.add(new Player(maxCollectedNumbers));

        GenerateNumbers(playersQuantity + 4);
    }
    //endregion

    //region Accessors
    public Player getSelectedPlayer() {
        return SelectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        SelectedPlayer = selectedPlayer;
    }

    public ArrayList<Player> getPlayers() {
        return Players;
    }

    public List<Number> getGeneratedNumbers() {
        return GeneratedNumbers;
    }

    public int getCurrentRound() {
        return CurrentRound;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }


    //endregion

    private Random rand;

    private final ArrayList<Player> Players;

    private final int TotalRounds;

    private boolean gameFinished;

    private int CurrentRound;

    private Player SelectedPlayer;

    private List<Number> GeneratedNumbers;

    private void GenerateNumbers(int numbersCount) {
        GeneratedNumbers = new ArrayList<>();
        for (int i = 0; i < numbersCount; i++) {
            GeneratedNumbers.add(new Number(rand.nextInt(100), NumberModifiers.values()[rand.nextInt(NumberModifiers.values().length)]));
        }
    }

    public void EndRound() {
        for (Player cur : Players) {
            boolean hasRepetitions = false;
            for (Player eqw : Players) {
                if (cur != eqw) {
                    for (Number cur_num : cur.getNumbers()) {
                        for (Number eqw_num : eqw.getNumbers()) {
                            if (cur_num == eqw_num) {
                                hasRepetitions = true;
                                break;
                            }
                        }
                        if (hasRepetitions) break;
                    }
                    if (hasRepetitions) break;
                }
            }

            cur.CollectScore(hasRepetitions);
        }

        for (Player cur : Players)
            cur.getNumbers().clear();

        if (CurrentRound == TotalRounds)
            gameFinished = true;
        else
            CurrentRound++;
    }

    public Player GetWinner() {
        Player winner = null;
        for (Player pl : Players) {
            if (winner == null)
                winner = pl;
            else if (winner.getScore() < pl.getScore())
                winner = pl;
        }
        return winner;
    }

}
