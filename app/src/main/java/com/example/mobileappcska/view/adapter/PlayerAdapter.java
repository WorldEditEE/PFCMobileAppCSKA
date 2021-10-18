package com.example.mobileappcska.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappcska.R;
import com.example.mobileappcska.model.API.entity.Result;

import java.util.Calendar;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<Result> matchesList;
    private Activity activity;

    public PlayerAdapter(Activity activity){
        this.activity = activity;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item,parent,false);

        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {

        Result match = matchesList.get(position);

        holder.textViewName.setText(match.getEventAwayTeam());
        holder.textViewClub.setText(match.getEventHomeTeam());
        holder.textViewGoals.setText(match.getEventStadium());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                String[] dateValues = match.getEventDate().split("-");
                String[] timeValues = match.getEventTime().split(":");
                calendar.set(Integer.parseInt(dateValues[0]),Integer.parseInt(dateValues[1])-1,
                        Integer.parseInt(dateValues[2]), Integer.parseInt(timeValues[0]),
                        Integer.parseInt(timeValues[1]));

                long time = calendar.getTimeInMillis();

                Log.d("CheckTime", timeValues[0]+timeValues[1]);

                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE, match.getEventHomeTeam() + " - " +
                        match.getEventAwayTeam());
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION,match.getEventStadium());
                intent.putExtra(CalendarContract.Events.DESCRIPTION,"Матч " + match.getLeagueName());
                intent.putExtra(CalendarContract.Events.EVENT_TIMEZONE,"Europe/Moscow");
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,time+ 120*60*1000);
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,time);

                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }

    public List<Result> getPlayerList() {
        return matchesList;
    }

    public void setPlayerList(List<Result> playerList) {
        this.matchesList = playerList;
        notifyDataSetChanged();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewName;
        private TextView textViewClub;
        private TextView textViewGoals;
        private Button button;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewPlayerName);
            textViewClub = itemView.findViewById(R.id.textViewPlayerClub);
            textViewGoals = itemView.findViewById(R.id.textViewPlayerGoals);
            button = itemView.findViewById(R.id.buttonAddCalendarMatch);
        }
    }

}
