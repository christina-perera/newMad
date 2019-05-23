package com.example.thefeast;

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



        ////////////update

        listViewMember.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long id) {

               Member member = memberList.get(i);

               showUpdateMemberDialog(member.getMemberId(),member.getMemberName(),member.getMemberAddress(),member.getMemberPhone());

                return false;
            }
        });




        //////////////////////////




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


    ///////////update
    private void showUpdateMemberDialog(final String memberID, String memberName, String memberAddress, String meberPhonenumber){


        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView =  inflater.inflate(R.layout.updatemember,null);

        dialogbuilder.setView(dialogView);


        final EditText editTextname = (EditText) dialogView.findViewById(R.id.editTextupdateName);

        final EditText editTextAddress = (EditText) dialogView.findViewById(R.id.editTextupdateAddress);

        final EditText editTextPhonenumber = (EditText) dialogView.findViewById(R.id.editTextupdatePhonenumber);

        final Button buttonUpdateMember = (Button) dialogView.findViewById(R.id.buttonupdateMember);

        final Button buttonDeleteMember = (Button)dialogView.findViewById(R.id.buttonDeleteMember);

        dialogbuilder.setTitle("Update Member name : "+memberName+" Address : "+memberAddress+" TP : "+meberPhonenumber);

        final AlertDialog alertDialog = dialogbuilder.create();
        alertDialog.show();





        buttonUpdateMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



        String name = editTextname.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phoneNumber = editTextPhonenumber.getText().toString().trim();


        if(TextUtils.isEmpty(name)){

            editTextname.setError("Name required");
            return ;
        }



        updateMember(memberID,name,address,phoneNumber);


        alertDialog.dismiss();


            }
        });

        ///////////////delete

        buttonDeleteMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteMember(memberID);

            }
        });

        ////////////////////////




    }


    ////////////delete

    private void deleteMember(String memberID){

    DatabaseReference databaseReferenceMember = FirebaseDatabase.getInstance().getReference("member").child(memberID);

        databaseReferenceMember.removeValue();


        Toast.makeText(this,"Member Deleted",Toast.LENGTH_LONG).show();
    }


    //////////////



    private  boolean updateMember(String id, String name , String address, String phoneNumber){


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("member").child(id);


        Member member =  new Member(id,name,address,phoneNumber);

        databaseReference.setValue(member);

        Toast.makeText(this,"Member Updated",Toast.LENGTH_LONG).show();


        return true;
    }









/////////////////////////////////
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