package com.tectro.mobileapp3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tectro.mobileapp3.R;
import com.tectro.mobileapp3.game_model.GameModel;

import java.lang.reflect.Field;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View v)
    {
        int TotalRounds = 5;
        int MaxCollectedNumbers = 3;

        switch (v.getId())
        {
            case R.id.MainMenu_btn1: GameModel.RebuildInstance(2,TotalRounds,MaxCollectedNumbers); break;
            case R.id.MainMenu_btn2: GameModel.RebuildInstance(3,TotalRounds,MaxCollectedNumbers); break;
            case R.id.MainMenu_btn3: GameModel.RebuildInstance(4,TotalRounds,MaxCollectedNumbers); break;
            case R.id.MainMenu_btn4: GameModel.RebuildInstance(5,TotalRounds,MaxCollectedNumbers); break;
            case R.id.MainMenu_btn5: GameModel.RebuildInstance(6,TotalRounds,MaxCollectedNumbers); break;
        }

        Intent Game = new Intent(this, ActiveRoundActivity.class);
        startActivity(Game);
    }
}