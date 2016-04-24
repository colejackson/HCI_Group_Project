package com.example.coleman.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.coleman.app_code.Category;
import com.example.coleman.app_code.Todo;
import com.example.coleman.hcigroupproject.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sirkellsworth on 4/22/16.
 */
public class CatagoryAdapter extends ArrayAdapter<Category>{

    Activity context;
    Category[] events;

    public CatagoryAdapter(Activity context, Category[] events){
        super(context, R.layout.catagory_layout, events);

        this.context=context;
        this.events=events;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.catagory_layout, null, true);

        TextView name=(TextView) rowView.findViewById(R.id.category_name);
        name.setText(events[position].getName());

        return rowView;
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent){
        View correctFile=LayoutInflater.from(getContext()).inflate(R.layout.catagory_layout, parent, false);
        TextView view=(TextView)correctFile.findViewById(R.id.category_name);

        view.setTextColor(Color.BLACK);
        view.setText(events[position].getName());
        view.setBackgroundColor(events[position].getColor());

        return view;
    }
}
