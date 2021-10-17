package com.example.mobileappcska.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappcska.R;
import com.example.mobileappcska.model.entity.Player;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<Player> playerList;

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item,parent,false);

        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {

        Player player = playerList.get(position);

        holder.textViewName.setText(player.getPlayerName());
        holder.textViewClub.setText(player.getTeamName());
        holder.textViewGoals.setText(player.getGoals());

    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
        notifyDataSetChanged();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewName;
        private TextView textViewClub;
        private TextView textViewGoals;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewPlayerName);
            textViewClub = itemView.findViewById(R.id.textViewPlayerClub);
            textViewGoals = itemView.findViewById(R.id.textViewPlayerGoals);
        }
    }

}
