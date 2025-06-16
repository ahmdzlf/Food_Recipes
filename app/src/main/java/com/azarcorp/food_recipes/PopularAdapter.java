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

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private final Context context;
    private List<Resep> resepList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Resep resep);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public PopularAdapter(Context context, List<Resep> resepList) {
        this.context = context;
        this.resepList = resepList;
    }

    public void setData(List<Resep> newList) {
        this.resepList = newList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_popular_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Resep resep = resepList.get(position);
        holder.tvPopularTitle.setText(resep.getNama());
        Picasso.get().load(resep.getImageUrl()).placeholder(R.drawable.placeholders).into(holder.imgPopular);

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
        ImageView imgPopular;
        TextView tvPopularTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            imgPopular = itemView.findViewById(R.id.imgPopular);
            tvPopularTitle = itemView.findViewById(R.id.tvPopularTitle);
        }
    }
}
