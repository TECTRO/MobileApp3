package com.tectro.mobileapp3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tectro.mobileapp3.adapters.PlayerDataAdapter;
import com.tectro.mobileapp3.R;
import com.tectro.mobileapp3.data_models.Number;
import com.tectro.mobileapp3.data_models.Player;
import com.tectro.mobileapp3.game_model.GameModel;

public class ActiveRoundActivity extends AppCompatActivity {

    private GameModel model;

    PlayerDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_round);

        model = GameModel.GetInstance();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RoundRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this) );
        /*
        {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        }
        * */
        adapter = new PlayerDataAdapter(this, model.getPlayers(), this::SelectNumbers);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();


        ((TextView) findViewById(R.id.all_Numbers_PreView)).setText(TextUtils.join(", ", model.getGeneratedNumbers().stream().map(Number::getRaw).map(Object::toString).toArray()));


        if (!model.isGameFinished()) {

            ((TextView) findViewById(R.id.RoundNumber)).setText(String.valueOf(model.getCurrentRound()));

        } else {
            ((TextView) findViewById(R.id.RoundNumber)).setText("окончен");


            ((Button) findViewById(R.id.NextRoundBtn)).setText("в главное меню");
            ((Button) findViewById(R.id.NextRoundBtn)).setOnClickListener(v->
            {
                this.finish();
            });
            Player winner = model.GetWinner();
            Toast toast = Toast.makeText(this, "Победил игрок " + (model.getPlayers().indexOf(winner) + 1) + " со счетом " + winner.getScore(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void SelectNumbers(Player pl) {
        model.setSelectedPlayer(pl);
        Intent Game = new Intent(this, ChooseNumberActivity.class);
        Game.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(Game);
    }

    public void NextRound(View v) {
        model.EndRound();
        Intent Game = new Intent(this, ActiveRoundActivity.class);
       //Game.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(Game);
        finish();

        //recreate();
    }
}