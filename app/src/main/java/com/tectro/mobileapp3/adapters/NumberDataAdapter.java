package com.tectro.mobileapp3.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tectro.mobileapp3.R;
import com.tectro.mobileapp3.data_models.Number;
import com.tectro.mobileapp3.data_models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class NumberDataAdapter extends RecyclerView.Adapter<NumberDataAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Player player;
    private List<Number> numbers;

    public NumberDataAdapter(Context context, List<Number> numbers, Player player) {
        this.numbers = numbers;
        this.inflater = LayoutInflater.from(context);
        this.player = player;
    }

    @NonNull
    @Override
    public NumberDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.number_item, parent, false);
        return new NumberDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Number number = numbers.get(position);
        holder.NumberBtn.setText(number.toString());
        switch (number.getModifier()) {
            case multi2:
                holder.NumberBtn.setBackgroundColor(Color.rgb(90, 170, 235));
                break;
            case plus3://30 218 197
                holder.NumberBtn.setBackgroundColor(Color.rgb(30, 218, 197));
                break;
            default:
                holder.NumberBtn.setBackgroundColor(Color.LTGRAY);
                break;
        }


            if (player.getNumbers().contains(number))
                holder.NumberBtn.setBackgroundColor(Color.rgb(158,129,255));


        holder.NumberBtn.setOnClickListener(r ->
        {
            if (player.UpdateNumber(number))
                this.notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final Button NumberBtn;

        ViewHolder(View view) {
            super(view);
            NumberBtn = view.findViewById(R.id.collection_number_btn);
        }
    }

}
