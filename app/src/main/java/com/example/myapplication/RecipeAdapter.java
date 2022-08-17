package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{

    public RecipeAdapter(List<Recipe> mListRecipe) {
        this.mListRecipe = mListRecipe;
    }

    private List<Recipe> mListRecipe;


    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe,parent,false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = mListRecipe.get(position);
        if(recipe == null){
            return;
        }
        holder.recipeName.setText(recipe.getNameRecipe());

    }

    @Override
    public int getItemCount() {
        if(mListRecipe != null){
            return mListRecipe.size();
        }
        return 0;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        private TextView recipeName;

        public RecipeViewHolder(@NonNull View itemView){
            super(itemView);
            recipeName = itemView.findViewById(R.id.name_recipe);


        }
    }
}
