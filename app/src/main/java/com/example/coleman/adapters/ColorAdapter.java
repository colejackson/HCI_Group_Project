package com.example.coleman.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.coleman.app_code.CatColors;
import com.example.coleman.app_code.Category;
import com.example.coleman.hcigroupproject.R;

/**
 * Created by sirkellsworth on 4/22/16.
 */
public class ColorAdapter extends ArrayAdapter<Integer>{

    Activity context;
    Integer[] colors;

    public ColorAdapter(Activity context, Integer[] ia){
        super(context, R.layout.new_color, ia);

        this.context = context;
        this.colors = ia;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.catagory_layout, null, true);

        TextView name = (TextView) rowView.findViewById(R.id.category_name);
        name.setBackgroundColor(colors[position]);
        name.setWidth(30);
        name.setHeight(40);
        name.setText("");

        return rowView;
    }
}
