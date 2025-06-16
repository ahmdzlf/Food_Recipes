package com.azarcorp.food_recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.ViewHolder> {

    private final Context context;
    private List<Resep> resepList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Resep resep);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public FeaturedAdapter(Context context, List<Resep> resepList) {
        this.context = context;
        this.resepList = resepList;
    }

    public void setData(List<Resep> newList) {
        this.resepList = newList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_featured_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Resep resep = resepList.get(position);
        holder.tvRecommendation.setText(resep.getNama());
        Picasso.get().load(resep.getImageUrl()).placeholder(R.drawable.placeholders).into(holder.imgFeatured);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(resep);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resepList != null ? resepList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFeatured;
        TextView tvRecommendation;

        public ViewHolder(View itemView) {
            super(itemView);
            imgFeatured = itemView.findViewById(R.id.img_featured);
            tvRecommendation = itemView.findViewById(R.id.tv_recommendation);
        }
    }
}
