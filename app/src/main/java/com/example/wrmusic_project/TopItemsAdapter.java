package com.example.wrmusic_project;

import android.content.Intent;
import android.graphics.Outline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TopItemsAdapter extends RecyclerView.Adapter<TopItemsAdapter.TopItemViewHolder> {

    private final  String[] ranks;
    private final String[] titles;
    private final String[] artists;
    private final String[] descriptions;
    private final String[] genres;
    private final String[] prices;
    private final int[] images;
    private final int[] tracks;

    public TopItemsAdapter(String[] ranks, String[] titles, String[] artists, String[] descriptions, String[] genres, String[] prices, int[] images, int[] tracks) {
        this.ranks = ranks;
        this.titles = titles;
        this.artists = artists;
        this.descriptions = descriptions;
        this.genres = genres;
        this.prices = prices;
        this.images = images;
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public TopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_top, parent, false);
        return new TopItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopItemViewHolder holder, int position) {
        holder.rank.setText(ranks[position]);
        holder.title.setText(titles[position]);
        holder.description.setText(descriptions[position]);
        holder.image.setImageResource(images[position]);

        float radius = 25.0f;
        holder.image.setClipToOutline(true);
        holder.image.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
            }
        });

        // Set OnClickListener to navigate to ItemDetail
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ItemDetail activity
                Intent intent = new Intent(v.getContext(), ItemDetail.class);
                intent.putExtra("title", titles[position]);
                intent.putExtra("artist", artists[position]);
                intent.putExtra("genre", genres[position]);
                intent.putExtra("description", descriptions[position]);
                intent.putExtra("price", prices[position]);
                intent.putExtra("imageResId", images[position]);
                intent.putExtra("trackCount", tracks[position]);

                // Start the ItemDetail activity
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    static class TopItemViewHolder extends RecyclerView.ViewHolder {
        TextView rank;
        TextView title;
        TextView description;
        ImageView image;

        TopItemViewHolder(@NonNull View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.rankTV);
            title = itemView.findViewById(R.id.itemTitle);
            description = itemView.findViewById(R.id.itemDescription);
            image = itemView.findViewById(R.id.itemImage);
        }
    }
}
