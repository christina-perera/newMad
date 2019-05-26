package com.example.thefeast.BeverageManagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

import com.example.thefeast.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainBActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextIngredient;
    EditText editTextInstructions;
    Button buttonAdd;
    Spinner spinnerGenres;

    DatabaseReference databaseBeverages;

    ListView listViewBeverages;

    List<Beverage> beverageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);

        databaseBeverages = FirebaseDatabase.getInstance().getReference("beverages");

        editTextName = (EditText) findViewById(R.id.editTextNameb);
        editTextIngredient = (EditText) findViewById(R.id.editTextIngredient);
        editTextInstructions = (EditText) findViewById(R.id.editTextInstructions);
        buttonAdd = (Button) findViewById(R.id.buttonAddbeverages);
        spinnerGenres = (Spinner) findViewById(R.id.spinnerbGenres);
        listViewBeverages = (ListView) findViewById(R.id.listViewBeverages);

        beverageList = new ArrayList<>();


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBeverage();

            }
        });


        listViewBeverages.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {

                Beverage beverage = beverageList.get(i);

                showUpdateDialog(beverage.getBeverageId(), beverage.getBeverageName());

                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseBeverages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                beverageList.clear();

                for(DataSnapshot beverageSnapshot : dataSnapshot.getChildren() ){
                    Beverage beverage = beverageSnapshot.getValue(Beverage.class);

                    beverageList.add(beverage);
                }

                BeverageList adapter = new BeverageList(MainBActivity.this, beverageList);
                  listViewBeverages.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    //updating method

    private void showUpdateDialog(final String beverageId, String beverageName){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_beverages, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextNameu = (EditText) dialogView.findViewById(R.id.editTextName1);
        final EditText editTextNameing = (EditText) dialogView.findViewById(R.id.editTextName2);
        final EditText editTextNameins = (EditText) dialogView.findViewById(R.id.editTextName3);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdate);
        final Spinner spinnerGenres = (Spinner) dialogView.findViewById(R.id.spinnerGenres);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDelete);



        dialogBuilder.setTitle("Updating Beverage"+beverageName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editTextNameu.getText().toString().trim();
                String genre = spinnerGenres.getSelectedItem().toString();
                String ingredient = editTextNameing.getText().toString().trim();
                String  instruction = editTextNameins.getText().toString().trim();


                if(TextUtils.isEmpty(name)){
                    editTextNameu.setError("Name required");
                    return;
                }

                updateBeverage(beverageId, name, genre, ingredient, instruction);

                alertDialog.dismiss();


            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteBeverage(beverageId);
                alertDialog.dismiss();

            }
        });





    }

    private void deleteBeverage(String beverageId) {
        DatabaseReference drBeverage = FirebaseDatabase.getInstance().getReference("beverages").child(beverageId);


        drBeverage.removeValue();

        Toast.makeText(this, "Beverage is Deleted", Toast.LENGTH_LONG).show();

    }

    private boolean updateBeverage(String id, String name, String genere, String ingredient, String instruction){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("beverages").child(id);

        Beverage beverage = new Beverage(id, name, genere, ingredient,  instruction);

        databaseReference.setValue(beverage);

        Toast.makeText(this, "Beverage Update Sucessfully", Toast.LENGTH_LONG).show();

        return true;

    }


    private void addBeverage(){
            String name = editTextName.getText().toString().trim();
            String ingredient = editTextIngredient.getText().toString().trim();
            String instructions = editTextInstructions.getText().toString().trim();
            String genre = spinnerGenres.getSelectedItem().toString();

            if(!TextUtils.isEmpty(name)){

                String id = databaseBeverages.push().getKey();

                Beverage beverage = new Beverage(id,name, ingredient, instructions, genre);

                databaseBeverages.child(id).setValue(beverage);

                Toast.makeText(this,"Beverage added", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainBActivity.this, ViewBActivity.class);
                startActivity(intent);


            }else{
                Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
            }

        }



    }


