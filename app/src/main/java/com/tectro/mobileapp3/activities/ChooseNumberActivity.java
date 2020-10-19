package com.tectro.mobileapp3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.tectro.mobileapp3.R;
import com.tectro.mobileapp3.adapters.NumberDataAdapter;
import com.tectro.mobileapp3.game_model.GameModel;

public class ChooseNumberActivity extends AppCompatActivity {

    GameModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_number);

        RecyclerView recyclerView = findViewById(R.id.SelectNumberRecyclerView);

        FlexboxLayoutManager f = new FlexboxLayoutManager();
        f.setFlexDirection(FlexDirection.ROW);
        f.setFlexWrap(FlexWrap.WRAP);
         f.setAlignItems(AlignItems.BASELINE);
        recyclerView.setLayoutManager(f);

        model = GameModel.GetInstance();

        recyclerView.setAdapter(new NumberDataAdapter(this, model.getGeneratedNumbers(), model.getSelectedPlayer()));
    }

    public void Back(View v)
    {
        this.finish();
    }

}