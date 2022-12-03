package com.example.fitstep.models.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitstep.R;
import com.example.fitstep.models.GlobalData;
import com.example.fitstep.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Recipe> listOfRecipes;

    public RecipeAdapter(Context context, ArrayList<Recipe> listOfRecipes) {
        this.context = context;
        this.listOfRecipes = listOfRecipes;
    }

    @Override
    public int getCount() {
        return listOfRecipes.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfRecipes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View recipeIntem;
        LayoutInflater inflater = LayoutInflater.from(context);
        recipeIntem = inflater.inflate(R.layout.recipe_item,viewGroup,false);
        ImageView imgFood;
        TextView tvLabel, tvCalory;
        imgFood = recipeIntem.findViewById(R.id.imgFood);
        tvLabel = recipeIntem.findViewById(R.id.tvLabel);
        tvCalory = recipeIntem.findViewById(R.id.tvCalory);
        Recipe recipe = (Recipe) getItem(i);
        tvLabel.setText(recipe.getLabel());
        tvCalory.setText("Calory: "+recipe.getCalories());
        if(recipe.getImage()!= null){
            Picasso.with(context).load(recipe.getImage()).placeholder(R.drawable.rounding_image).into(imgFood);
        }
        return recipeIntem;
    }
}
