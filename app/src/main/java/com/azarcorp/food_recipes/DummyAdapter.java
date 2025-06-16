package com.azarcorp.food_recipes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DummyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<String> items;
    private final int layoutType;

    // viewType constants
    private static final int TYPE_FEATURED = 1;
    private static final int TYPE_POPULAR = 2;

    // Interface listener klik item
    public interface OnItemClickListener {
        void onItemClick(String resep);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public DummyAdapter(List<String> items, int layoutType) {
        this.items = items;
        this.layoutType = layoutType;
    }

    @Override
    public int getItemViewType(int position) {
        return layoutType == 1 ? TYPE_FEATURED : TYPE_POPULAR;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == TYPE_FEATURED) {
            View view = inflater.inflate(R.layout.item_featured_recipe, parent, false);
            return new FeaturedViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_popular_recipe, parent, false);
            return new PopularViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String title = items.get(position);

        if (holder instanceof FeaturedViewHolder) {
            FeaturedViewHolder featured = (FeaturedViewHolder) holder;
            featured.title.setText(title);
            featured.image.setImageResource(R.drawable.baso);

            featured.itemView.setOnClickListener(v -> {
                if (listener != null) listener.onItemClick(title);
            });

        } else if (holder instanceof PopularViewHolder) {
            PopularViewHolder popular = (PopularViewHolder) holder;
            popular.title.setText(title);
            popular.image.setImageResource(R.drawable.nasi_goreng);

            popular.itemView.setOnClickListener(v -> {
                if (listener != null) listener.onItemClick(title);
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Featured ViewHolder (horizontal)
    static class FeaturedViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;

        FeaturedViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_recommendation);
            image = itemView.findViewById(R.id.img_featured);
        }
    }

    // Popular ViewHolder (grid)
    static class PopularViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;

        PopularViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvPopularTitle);
            image = itemView.findViewById(R.id.imgPopular);
        }
    }
}
