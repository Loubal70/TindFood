package com.appli.tindfood.tindfood2;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.appli.tindfood2.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>{

    List<Categories> data;
    Context context;
    int selectedItem = 0;

    OnItemClick onCategoryClick;

    public CategoriesAdapter(List<Categories> data, Context context, OnItemClick onCategoryClick){
        this.data = data;
        this.context = context;
        this.onCategoryClick = onCategoryClick;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.categories_holder, parent, false);
        return new CategoryHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        holder.image.setImageResource(data.get(position).getImage());
        holder.title.setText(data.get(position).getName());
        if(position == selectedItem){
            // Système de sélectionnement de card (catégorie)
            holder.cardView.setOutlineSpotShadowColor(ContextCompat.getColor(context,R.color.red));
            holder.cardView.setOutlineAmbientShadowColor(ContextCompat.getColor(context,R.color.red));
            holder.cardView.setStrokeWidth(2);
            holder.title.setTextColor(ContextCompat.getColor(context,R.color.red));
            holder.image.setColorFilter(ContextCompat.getColor(context,R.color.red), PorterDuff.Mode.SRC_IN);
        } else {
            holder.cardView.setOutlineSpotShadowColor(ContextCompat.getColor(context,R.color.gray1));
            holder.cardView.setOutlineAmbientShadowColor(ContextCompat.getColor(context,R.color.gray));
            holder.title.setTextColor(ContextCompat.getColor(context,R.color.gray));
            holder.image.setColorFilter(ContextCompat.getColor(context,R.color.gray1), PorterDuff.Mode.SRC_IN);
            holder.cardView.setStrokeWidth(0);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;
        MaterialCardView cardView;
        public CategoryHolder(View holder){
            super(holder);
            title = holder.findViewById(R.id.txt_title);
            image = holder.findViewById(R.id.img);
            cardView = holder.findViewById(R.id.card_view);

            cardView.setOnClickListener(View -> {
                selectedItem = getAdapterPosition();
                // Reset items, Couleur change quand je clic
                if (onCategoryClick != null){
                    onCategoryClick.onClick(getAdapterPosition());
                }
                notifyDataSetChanged();
            });
        }
    }
}