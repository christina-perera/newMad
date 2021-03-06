package com.example.thefeast;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;

import com.example.thefeast.BeverageManagement.MainBActivity;
import com.example.thefeast.FeedBackManagement.AddFeedBack;
import com.example.thefeast.FeedBackManagement.MainFeedBack;

public class AdminPanel extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btn1 , btn2 , btn3, btn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        btn1 = (Button) findViewById(R.id.viewAddMember);
        btn2 = (Button) findViewById(R.id.ViewAddBeverages);
        btn3 = (Button)findViewById(R.id.ViewaddRecipe);
        btn4 = (Button)findViewById(R.id.ViewAddFeedback);

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
        getMenuInflater().inflate(R.menu.admin_panel, menu);
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

            Intent intent = new Intent(AdminPanel.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

            Intent intent = new Intent(AdminPanel.this, Main2Activity.class);
            startActivity(intent);

        } else if (id == R.id.nav_tools) {

            Intent intent = new Intent(AdminPanel.this, MainBActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

            Intent intent = new Intent(AdminPanel.this, MainFeedBack.class);
            startActivity(intent);


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void ViewAddMember(View v){

        Intent intent = new Intent(AdminPanel.this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    public void ViewAddBeverage(View v){

        Intent intent = new Intent(AdminPanel.this,MainBActivity.class);
        startActivity(intent);
        finish();

    }

    public void ViewAddRecipe(View v){

        Intent intent = new Intent(AdminPanel.this,Main2Activity.class);
        startActivity(intent);
        finish();

    }
    public void ViewFeedback(View v){

        Intent intent = new Intent(AdminPanel.this, AddFeedBack.class);
        startActivity(intent);
        finish();

    }


}
