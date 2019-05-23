package com.example.thefeast.FeedBackManagement;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.thefeast.Model.FeedBack;
import com.example.thefeast.R;

import java.util.List;

public class FeedBackList extends ArrayAdapter<FeedBack> {

    private Activity context;
    private List<FeedBack> feedBackList;


    public FeedBackList(Activity context , List<FeedBack> feedBackList){
        super(context, R.layout.list_all_feddback,feedBackList);
        this.context = context;
        this.feedBackList = feedBackList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_all_feddback,null,true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.viewTitle);
        TextView textViewDes = (TextView) listViewItem.findViewById(R.id.viewDes);

        FeedBack feedBack = feedBackList.get(position);

        textViewName.setText(feedBack.getTitle());
        textViewDes.setText(feedBack.getDescription());

        return listViewItem;

    }
}
