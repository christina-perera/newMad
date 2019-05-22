package com.example.thefeast;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editname ;
    EditText editAddress;
    EditText editPhonenumber;
    Button  buttonaddMember;


     DatabaseReference databaseMember;

    ListView listViewMember;

    List<Member> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseMember = FirebaseDatabase.getInstance().getReference("member");

        editname = (EditText)findViewById(R.id.editTextname);
        editAddress = (EditText)findViewById(R.id.editTextaddress);
        editPhonenumber = (EditText) findViewById(R.id.editText3ohoneNumber);
        buttonaddMember = (Button)findViewById(R.id.buttonAdd);


        listViewMember = (ListView)findViewById(R.id.listViewMember);

        memberList = new ArrayList<>();

        buttonaddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 AddMember();
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();


        databaseMember.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                memberList.clear();
                for(DataSnapshot memberSnapshot : dataSnapshot.getChildren()){

                    Member member = memberSnapshot.getValue(Member.class);


                    memberList.add(member);

                }

                MemberList adapter = new MemberList(MainActivity.this,memberList);

                listViewMember.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {




            }
        });


    }

    private void AddMember(){

        String name = editname.getText().toString().trim();
        String address = editAddress.getText().toString().trim();
        String phoneNumber = editPhonenumber.getText().toString().trim();

        if(!TextUtils.isEmpty(name)){

       String id = databaseMember.push().getKey();


       Member member = new Member(id,name,address,phoneNumber);

        databaseMember.child(id).setValue(member);


        Toast.makeText(this,"Member Added",Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(this,"You Should Enter Name",Toast.LENGTH_LONG).show();
        }


    }

}
