package com.example.mobileappcska.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappcska.R;
import com.example.mobileappcska.model.entity.Club;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder>{

    private List<Club> clubs;
    private Context context;

    public ClubAdapter(List<Club> clubs){
        this.clubs = clubs;
    }


    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.club_item,parent,false);

       return new ClubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {

        Club currentClub = clubs.get(position);
        holder.textViewPlace.setText(currentClub.getPlace());
        String urlToImage = currentClub.getImage();
        if(urlToImage != null && !urlToImage.isEmpty()){

            Picasso.get().load(urlToImage).into(holder.imageViewClub);
        }

        holder.textViewName.setText(currentClub.getName());
        holder.textViewGames.setText(currentClub.getGames());
        holder.textViewWin.setText(currentClub.getWins());
        holder.textViewDraw.setText(currentClub.getDraws());
        holder.textViewLose.setText(currentClub.getLoses());
        holder.textViewPoints.setText(currentClub.getPoints());

    }

    @Override
    public int getItemCount() {
        return clubs.size();
    }

    class ClubViewHolder extends RecyclerView.ViewHolder{


        private TextView textViewPlace;
        private TextView textViewName;
        private ImageView imageViewClub;
        private TextView textViewGames;
        private TextView textViewWin;
        private TextView textViewDraw;
        private TextView textViewLose;
        private TextView textViewPoints;


        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPlace = itemView.findViewById(R.id.textViewPlace);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageViewClub = itemView.findViewById(R.id.imageViewClub);
            textViewGames = itemView.findViewById(R.id.textViewGames);
            textViewWin = itemView.findViewById(R.id.textViewWin);
            textViewDraw = itemView.findViewById(R.id.textViewDraw);
            textViewLose = itemView.findViewById(R.id.textViewLose);
            textViewPoints = itemView.findViewById(R.id.textViewPoints);

        }
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs){
        this.clubs = clubs;
        notifyDataSetChanged();
    }

}
