package com.example.thefeast.FeedBackManagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thefeast.Model.FeedBack;
import com.example.thefeast.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFeedBack extends AppCompatActivity {

    Button btnSubmit;
    EditText editTitle , editDescription;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed_back);

        databaseReference = FirebaseDatabase.getInstance().getReference("FeedBack");

        editTitle = (EditText) findViewById(R.id.editTitle);
        editDescription = (EditText) findViewById(R.id.editDescription);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFeedBack();
            }
        });

    }

    public void addFeedBack(){

        String title = editTitle.getText().toString().trim();
        String description = editDescription.getText().toString().trim();

        if(!TextUtils.isEmpty(title)){

            String id = databaseReference.push().getKey();

            FeedBack feedBack = new FeedBack(title,description,id);
            //add data to database
            databaseReference.child(id).setValue(feedBack);

            Toast.makeText(this,"FeedBack Added .. Thank You", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(AddFeedBack.this,MainFeedBack.class);
            startActivity(intent);

        }else{
            Toast.makeText(this,"You should Enter a Title", Toast.LENGTH_LONG).show();
        }


    }

}
