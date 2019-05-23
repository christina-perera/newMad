package com.example.thefeast.FeedBackManagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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

public class MainFeedBack extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseReference databaseReference;
    ListView listViewFeedBack;
    List<FeedBack> feedBackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed_back);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        databaseReference = FirebaseDatabase.getInstance().getReference("FeedBack");
        listViewFeedBack = (ListView) findViewById(R.id.listView2);
        feedBackList = new ArrayList<>();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       listViewFeedBack.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

               FeedBack feedBack = feedBackList.get(position);

               showUpdateDialog(feedBack.getId(),feedBack.getTitle(),feedBack.getDescription());

               return false;
           }
       });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);




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

                FeedBackList adapter = new FeedBackList(MainFeedBack.this, feedBackList);
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

        AlertDialog alertDialog = dialogBuilder.create();
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

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFeedback(feedBackId );
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




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_feed_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            Intent intent = new Intent(MainFeedBack.this, AddFeedBack.class);
            startActivity(intent);


        } else if (id == R.id.nav_slideshow) {


            Intent intent = new Intent(MainFeedBack.this, ViewFeedBack.class);
            startActivity(intent);


        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
