package com.tectro.mobileapp3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tectro.mobileapp3.R;
import com.tectro.mobileapp3.data_models.Number;
import com.tectro.mobileapp3.data_models.Player;

import java.util.List;
import java.util.function.Consumer;

public class PlayerDataAdapter extends RecyclerView.Adapter<PlayerDataAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Player> players;
    private  Consumer<Player> callable;

    public PlayerDataAdapter(Context context, List<Player> players, Consumer<Player> callable) {
        this.players = players;
        this.inflater = LayoutInflater.from(context);
        this.callable = callable;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.player_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player player = players.get(position);
        holder.PlayerIndex.setText(String.valueOf(position+1));
        try {
            holder.PlayerAddNumbersBtn.setOnClickListener(r->callable.accept(player));
            holder.PlayerScore.setText(String.valueOf(player.getNumbers().stream().map(Number::getValue).mapToInt(Integer::intValue).sum()));
        }catch (Exception ignored){}
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView PlayerIndex, PlayerScore;
        final Button PlayerAddNumbersBtn;
        ViewHolder(View view) {
            super(view);
            PlayerIndex = view.findViewById(R.id.player_index_info_view);
            PlayerScore = view.findViewById(R.id.player_expected_score_view);
            PlayerAddNumbersBtn = view.findViewById(R.id.player_select_numbers_btn);
        }
    }
}
