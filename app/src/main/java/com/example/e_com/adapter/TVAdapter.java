package com.example.e_com.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_com.R;
import com.example.e_com.TVPage;
import com.example.e_com.model.TV;

import java.util.List;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.TVViewHolder> {

    Context context;
    List<TV> TVs;

    public TVAdapter(Context context, List<TV> TVs) {
        this.context = context;
        this.TVs = TVs;
    }

    @NonNull
    @Override
    public TVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tvItems = LayoutInflater.from(context).inflate(R.layout.tv_item, parent, false);
        return new TVAdapter.TVViewHolder(tvItems);
    }

    @Override
    public void onBindViewHolder(@NonNull TVViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvBg.setCardBackgroundColor(Color.parseColor(TVs.get(position).getBackground()));

        int imageId = context.getResources().getIdentifier(TVs.get(position).getImg(), "drawable", context.getPackageName());
        holder.tvImage.setImageResource(imageId);

        holder.tvTitle.setText(TVs.get(position).getTitle());
        holder.tvPrice.setText(TVs.get(position).getPrice());
        holder.tvResolution.setText(TVs.get(position).getResolution());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TVPage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) context,
                        new Pair<View,String>(holder.tvImage,"tvImage"));

                intent.putExtra("TVBg", Color.parseColor(TVs.get(position).getBackground()));
                intent.putExtra("TVImage", imageId);
                intent.putExtra("TVTitle", TVs.get(position).getTitle());
                intent.putExtra("TVPrice", TVs.get(position).getPrice());
                intent.putExtra("TVResolution", TVs.get(position).getResolution());
                intent.putExtra("TVText", TVs.get(position).getText());
                intent.putExtra("TVId", TVs.get(position).getId());

                context.startActivity(intent, options.toBundle());
            }
        });
    }

    public void setTVs(List<TV> TVs) {
        this.TVs = TVs;
    }

    @Override
    public int getItemCount() {
        return TVs.size();
    }

    public static final class TVViewHolder extends RecyclerView.ViewHolder
    {
        CardView tvBg;
        ImageView tvImage;
        TextView tvTitle, tvPrice, tvResolution;

        public TVViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBg = itemView.findViewById(R.id.tvBg);
            tvImage = itemView.findViewById(R.id.tvImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvResolution = itemView.findViewById(R.id.tvResolution);
        }
    }
}
