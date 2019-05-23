//package com.example.thefeast;
//
//import android.app.Activity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//
//public class ContatctList extends ArrayAdapter<Contact> {
//
//
//    private Activity context;
//
//    private List<Contact> contacts;
//
//
//    public ContatctList( Activity context,List<Contact>contacts){
//
//        super(context,R.layout.contatct_list,contacts);
//        this.context = context;
//        this.contacts= contacts;
//
//    }
//
//
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        LayoutInflater inflater = context.getLayoutInflater();
//
//        View listViewItem = inflater.inflate(R.layout.contatct_list,null,true);
//
//      //  TextView textViewname = (TextView) listViewItem.findViewById(R.id.textViewname);
//        //TextView textViewAddress = (TextView) listViewItem.findViewById(R.id.textViewAddress);
//        TextView textViewPhoonenumber = (TextView) listViewItem.findViewById(R.id.textViewphoneNumber2);
//
//
//        Contact contact = contacts.get(position);
//
//
//        //textViewname.setText(contact.getContatctmem());
//      //  textViewAddress.setText(contact.getMemberAddress());
//        textViewPhoonenumber.setText(contact.getContatctmem());
//
//
//        return listViewItem;
//
//
//
//
//}}
