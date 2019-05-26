package com.example.thefeast;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RecipeList extends ArrayAdapter<Recipe> {

    private Activity context;
    private List<Recipe> recipeList;

    public RecipeList(Activity context, List<Recipe> recipeList){
        super(context, R.layout.recipe_list_layout, recipeList);
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listRecipe = inflater.inflate(R.layout.recipe_list_layout, null, true);

        TextView textDes = (TextView) listRecipe.findViewById(R.id.textDes);
        TextView textIngr = (TextView) listRecipe.findViewById(R.id.textIngr);
        TextView textInstruc = (TextView) listRecipe.findViewById(R.id.textInstruc);
        TextView textFeast = (TextView) listRecipe.findViewById(R.id.textFeast);

        Recipe recipe = recipeList.get(position);

        textDes.setText(recipe.getFoodDescription());
        textIngr.setText(recipe.getFoodIngredient());
        textInstruc.setText(recipe.getFoodInstruction());
        textFeast.setText(recipe.getFoodFeast());

        return listRecipe;



    }
}
