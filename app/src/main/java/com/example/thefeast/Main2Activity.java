package com.example.thefeast;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    EditText editDescription;
    EditText editIngredients;
    EditText editInstructions;

    Button buttonRecipe;

    Spinner spinnerFeast;

    DatabaseReference databaseRecipes;

    ListView listViewRecipe;

    List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        databaseRecipes = FirebaseDatabase.getInstance().getReference("recipes");

        editDescription = (EditText) findViewById(R.id.editDes);
        editIngredients = (EditText) findViewById(R.id.editIngre);
        editInstructions =(EditText) findViewById(R.id.editInstruc);

        buttonRecipe = (Button) findViewById(R.id.buttonAddRec);

        spinnerFeast = (Spinner) findViewById(R.id.spinnerFeast);

        listViewRecipe = (ListView) findViewById(R.id.listViewRecipe);

        recipeList = new ArrayList<>();



        listViewRecipe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long id) {
                Recipe recipe = recipeList.get(i);

                showUpdateRecipe(recipe.getFoodId(), recipe.getFoodDescription(), recipe.getFoodIngredient(), recipe.getFoodInstruction());
                return false;
            }
        });





        listViewRecipe.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                return false;
            }
        });




        buttonRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecipe();

            }
        });


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

                RecipeList adapter = new RecipeList(Main2Activity.this, recipeList);
                listViewRecipe.setAdapter(adapter);

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateRecipe(final String foodId, String foodDescription, String foodIngredients, String foodInstructions){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View recipeView = inflater.inflate(R.layout.update_recipe, null);

        dialogBuilder.setView(recipeView);

        final EditText editTextDes = (EditText) recipeView.findViewById(R.id.editTextDes);
        final EditText editTextIngre = (EditText) recipeView.findViewById(R.id.editTextIngre);
        final EditText editTextInstruc = (EditText) recipeView.findViewById(R.id.editTextInstruc);

        final Button buttonUpdate = (Button) recipeView.findViewById(R.id.buttonUpdate);
        final Button buttonDelete = (Button) recipeView.findViewById(R.id.btnDelete);

        final Spinner spinnerFeast = (Spinner) recipeView.findViewById(R.id.spinnerFeast);

        dialogBuilder.setTitle("Updating Description" + foodDescription);
        //dialogBuilder.setTitle("Updating Ingredients" + foodIngredients);
        //dialogBuilder.setTitle("Updating Instructions" + foodInstructions);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String description = editTextDes.getText().toString().trim();
                String ingredients = editTextIngre.getText().toString().trim();
                String instructions = editTextInstruc.getText().toString().trim();

                String feast = spinnerFeast.getSelectedItem().toString();

                if(TextUtils.isEmpty(description)){
                    editTextDes.setError("Description Required");
                    return;
                }

                updateRecipe(foodId, description, ingredients, instructions, feast);

                alertDialog.dismiss();


            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecepe(foodId);
            }
        });

    }

    private void deleteRecepe(String foodId) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("recipes").child(foodId);
        databaseReference.removeValue();

        Toast.makeText(this,"Recipe Deleted succeessfully",Toast.LENGTH_LONG).show();

    }




    private boolean updateRecipe(String id, String description, String ingredients, String instructions, String feast){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("recipes").child(id);

        Recipe recipe = new Recipe(id, description, ingredients, instructions, feast);

        databaseReference.setValue(recipe);

        Toast.makeText(this, "Recipe updated Successfully", Toast.LENGTH_LONG).show();

        return true;
    }

    private void addRecipe(){
        String description = editDescription.getText().toString().trim();
        String ingredients = editIngredients.getText().toString().trim();
        String instructions = editInstructions.getText().toString().trim();

        String feast = spinnerFeast.getSelectedItem().toString();

        if(!TextUtils.isEmpty(description)){

            String id = databaseRecipes.push().getKey();

            Recipe recipe = new Recipe(id, description, ingredients, instructions, feast);

            databaseRecipes.child(id).setValue(recipe);

            Toast.makeText(this, "Recipe Added", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Main2Activity.this, ViewMain2Activity.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "You Should Enter a description", Toast.LENGTH_LONG).show();
        }




    }



}
