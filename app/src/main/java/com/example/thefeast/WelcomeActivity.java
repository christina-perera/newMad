package com.example.thefeast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button btnSignin , btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnSignin = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);


    }

    public void ViewLogin(View view){

        Intent intent = new Intent(WelcomeActivity.this,Login.class);
        startActivity(intent);
        finish();
    }

    public void ViewSignUp(View view){

        Intent intent = new Intent(WelcomeActivity.this,SignUp.class);
        startActivity(intent);
        finish();
    }
}
