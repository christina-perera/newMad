package com.example.thefeast.BeverageManagement;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.thefeast.R;

import java.util.List;

public class BeverageList extends ArrayAdapter<Beverage> {

    private Activity context;
    private List<Beverage>  beverageList;

    public BeverageList(Activity context, List<Beverage> beverageList){
        super(context, R.layout.listbeverage_layout, beverageList);
        this.context = context;
        this.beverageList = beverageList;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.listbeverage_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenere);
        TextView textViewIngredient = (TextView) listViewItem.findViewById(R.id.textViewIngredient);
        TextView textViewInstruction = (TextView) listViewItem.findViewById(R.id.textViewInstruction);

        Beverage beverage = beverageList.get(position);
        textViewName.setText(beverage.getBeverageName());
        textViewGenre.setText(beverage.getBeverageGenre());
        textViewIngredient.setText(beverage.getBeverageIngredient());
        textViewInstruction.setText(beverage.getBeverageInstructions());

        return listViewItem;

    }
}
