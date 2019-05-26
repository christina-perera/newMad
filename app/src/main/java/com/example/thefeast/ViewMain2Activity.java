package com.example.thefeast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewMain2Activity extends AppCompatActivity {

    DatabaseReference databaseRecipes;

    ListView listViewRecipe;

    List<Recipe> recipeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_main2);

        databaseRecipes = FirebaseDatabase.getInstance().getReference("recipes");

        listViewRecipe = (ListView) findViewById(R.id.listViewRecipe);

        recipeList = new ArrayList<>();


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseRecipes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                recipeList.clear();

                for(DataSnapshot recipeSnapShot: dataSnapshot.getChildren()){
                    Recipe recipe = recipeSnapShot.getValue(Recipe.class);

                    recipeList.add(recipe);

                }

                RecipeList adapter = new RecipeList(ViewMain2Activity.this, recipeList);
                listViewRecipe.setAdapter(adapter);

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }
}
