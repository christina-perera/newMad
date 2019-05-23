package com.example.thefeast.FeedBackManagement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.thefeast.Model.FeedBack;
import com.example.thefeast.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewFeedBack extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listViewFeedBack;
    List<FeedBack> feedBackList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feed_back);
        databaseReference = FirebaseDatabase.getInstance().getReference("FeedBack");
        listViewFeedBack = (ListView) findViewById(R.id.listViewId);
        feedBackList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                feedBackList.clear();

                for (DataSnapshot feedbackSnapshot : dataSnapshot.getChildren()){
                    FeedBack feedBack = feedbackSnapshot.getValue(FeedBack.class);

                    feedBackList.add(feedBack);
                }

                FeedBackList adapter = new FeedBackList(ViewFeedBack.this, feedBackList);
                listViewFeedBack.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
