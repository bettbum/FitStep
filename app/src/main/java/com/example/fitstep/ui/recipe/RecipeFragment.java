package com.example.fitstep.ui.recipe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.fitstep.R;
import com.example.fitstep.models.HttpRequest;
import com.example.fitstep.models.Recipe;
import com.example.fitstep.models.adapter.BmiAdapter;
import com.example.fitstep.models.adapter.RecipeAdapter;

import java.util.ArrayList;


public class RecipeFragment extends Fragment {

    private View view;
    EditText edSearchFood;
    ListView lvRecipe;
    Button btnSearch;
    RecipeAdapter adapter;
    ArrayList<Recipe> listOfRecipe;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recipe, container, false);
        edSearchFood = view.findViewById(R.id.edSearchFood);
        lvRecipe = view.findViewById(R.id.lvRecipes);
        lvRecipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Recipe recipeClicked = listOfRecipe.get(i);
                RecipeDetailsFragment fragment = new RecipeDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("recipe",recipeClicked);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_content_main,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String food = edSearchFood.getText().toString().toLowerCase();
                listOfRecipe = HttpRequest.getReceipe(food);
                Log.d("listOfRecipe",String.valueOf(listOfRecipe.get(0).getDescription()));
                adapter = new RecipeAdapter(view.getContext(),listOfRecipe);
                lvRecipe.setAdapter(adapter);
            }
        });


        return view;
    }
}