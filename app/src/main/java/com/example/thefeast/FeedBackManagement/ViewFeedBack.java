package com.example.thefeast.FeedBackManagement;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
        listViewFeedBack = (ListView) findViewById(R.id.listView2);
        feedBackList = new ArrayList<>();

        listViewFeedBack.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                FeedBack feedBack = feedBackList.get(position);

                showUpdateDialog(feedBack.getId(),feedBack.getTitle(),feedBack.getDescription());

                return false;
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                feedBackList.clear();

                for (DataSnapshot feedbackSnapshot : dataSnapshot.getChildren()){
                    FeedBack feedBack1 = feedbackSnapshot.getValue(FeedBack.class);

                    feedBackList.add(feedBack1);
                }

                FeedBackList adapter = new FeedBackList(ViewFeedBack.this, feedBackList);
                listViewFeedBack.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void showUpdateDialog(final String feedBackId , String title , String Des){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog,null);
        dialogBuilder.setView(dialogView);


        final EditText editTextTitle = (EditText) dialogView.findViewById(R.id.updateText);
        final EditText editTextDes = (EditText) dialogView.findViewById(R.id.updateDes);
        final Button updateButton  = (Button) dialogView.findViewById(R.id.btnUpdate);
        final Button deleteButton = (Button) dialogView.findViewById(R.id.btnDelete);

        dialogBuilder.setTitle("Updating FeedBack " +title);

       final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTextTitle.getText().toString().trim();
                String des = editTextDes.getText().toString().trim();

                if(TextUtils.isEmpty(title)){
                    editTextDes.setError("Title Required");
                    return;
                }

                updateFeedBack(feedBackId,title,des);
                alertDialog.dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFeedback(feedBackId );
                alertDialog.dismiss();
            }

        });



    }

    public void deleteFeedback(String feedBackId){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FeedBack").child(feedBackId);

        databaseReference.removeValue();

        Toast.makeText(this,"FeedBack Deleted SuccessFully",Toast.LENGTH_LONG).show();



    }


    private boolean updateFeedBack(String id , String title , String des){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FeedBack").child(id);

        FeedBack feedBack = new FeedBack(title,des,id);

        databaseReference.setValue(feedBack);

        Toast.makeText(this,"FeedBack Updated Successfully",Toast.LENGTH_LONG).show();

        return true;

    }
}
