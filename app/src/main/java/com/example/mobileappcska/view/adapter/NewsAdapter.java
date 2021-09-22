package com.example.mobileappcska.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappcska.R;
import com.example.mobileappcska.model.entity.News;
import com.squareup.picasso.Picasso;

import java.time.Month;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> list;
    private Context context;
    private onNewsListener onNewsListener;


    public NewsAdapter(List<News> list, Context context, onNewsListener onNewsListener){
        this.list = list;
        this.context = context;
        this.onNewsListener = onNewsListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_news,parent,false);

        return new NewsViewHolder(view, onNewsListener);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        News currentNews = list.get(position);
        String text = currentNews.getHeading();

        holder.textViewNewsHeader.setText(currentNews.getHeading());
        String tempDate = currentNews.getDate();
        int month = Integer.parseInt(tempDate.substring(3,5));
        String day = tempDate.substring(6,8);
        holder.textViewTime.setText(day + " " + Month.of(month));

        String urlToImage = currentNews.getImageURL();
        if(urlToImage != null && !urlToImage.isEmpty()){
            Picasso.get().load(urlToImage).into(holder.imageViewNews);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewsListener.onNewsClick(currentNews);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageViewNews;
        private TextView textViewNewsHeader;
        private TextView textViewTime;
        onNewsListener onNewsListener;


        public NewsViewHolder(@NonNull View itemView, onNewsListener onNewsListener) {
            super(itemView);
            imageViewNews = itemView.findViewById(R.id.news_image);
            textViewNewsHeader = itemView.findViewById(R.id.textViewNews);
            textViewTime = itemView.findViewById(R.id.timeNews);
            this.onNewsListener = onNewsListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public interface onNewsListener{
        void onNewsClick(News news);
    }

    public List<News> getNews() {
        return list;
    }

    public void setNews(List<News> news){
        this.list = news;
        notifyDataSetChanged();
    }

}
