package com.example.fitstep.ui.recipe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitstep.R;
import com.example.fitstep.models.GlobalData;
import com.example.fitstep.models.Recipe;
import com.squareup.picasso.Picasso;


public class RecipeDetailsFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        EditText edLabel = view.findViewById(R.id.edLabel);
        EditText edCuisine = view.findViewById(R.id.edCuisine);
        EditText edDescription = view.findViewById(R.id.edDesciption);
        EditText edCalories = view.findViewById(R.id.edCalories);
        ImageView imgFood = view.findViewById(R.id.imgFood);
        TextView tvIngredients = view.findViewById(R.id.tvIngredientsFilled);

        Recipe recipe = (Recipe) getArguments().getSerializable("recipe");
        edLabel.setText(recipe.getLabel());
        edCuisine.setText(recipe.getCuisineTypes());
        edDescription.setText(recipe.getDescription());
        edCalories.setText(recipe.getCalories());
        Picasso.with(view.getContext()).load(recipe.getImage()).into(imgFood);
        String ingredients = "";
        for(String ingredient : recipe.getIngredients()){
            ingredients = ingredients + ingredient + "\n";
        }
        tvIngredients.setText(ingredients);

        return view;
    }
}