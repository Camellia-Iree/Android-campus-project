package com.example.test.auxiliary;

import android.content.Context;
import android.service.autofill.Dataset;
import android.widget.ArrayAdapter;
import com.example.test.auxiliary.Testdata;
import com.example.test.auxiliary.TestdataSet;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Testdata> {

    private int id;
    public MyAdapter(Context context, int Item, List<Testdata>objects){
        super(context, Item, objects);
        id = Item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Testdata testdata = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(id, null);
        ImageView head = view.findViewById(R.id.head);
        TextView name = view.findViewById(R.id.name);
        TextView talk = view.findViewById(R.id.talk);
        name.setText(testdata.name);
        talk.setText(testdata.talk);
        head.setImageResource(testdata.image);
        return view;
    }
}
