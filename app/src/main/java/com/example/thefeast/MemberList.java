package com.example.thefeast;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MemberList extends ArrayAdapter<Member> {


    private Activity context;

    private List<Member> memberList;


public MemberList( Activity context,List<Member>memberList){

    super(context,R.layout.listmember_layout,memberList);
    this.context = context;
    this.memberList = memberList;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.listmember_layout,null,true);

        TextView textViewname = (TextView) listViewItem.findViewById(R.id.textViewname);
        TextView textViewAddress = (TextView) listViewItem.findViewById(R.id.textViewAddress);
        TextView textViewPhoonenumber = (TextView) listViewItem.findViewById(R.id.textViewphoneNumber);


        Member member = memberList.get(position);


        textViewname.setText(member.getMemberName());
        textViewAddress.setText(member.getMemberAddress());
        textViewPhoonenumber.setText(member.getMemberPhone());


        return listViewItem;
    }
}
