package com.example.coleman.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.coleman.app_code.Category;
import com.example.coleman.hcigroupproject.R;

/**
 * Created by sirkellsworth on 4/22/16.
 */
public class ColorAdapter extends ArrayAdapter<Integer>{

    Activity context;
    Integer[] colors;

    public ColorAdapter(Activity context, Integer[] events){
        super(context, R.layout.new_color, events);

        this.context=context;
        this.colors=events;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.catagory_layout, null, true);

        TextView name=(TextView) rowView.findViewById(R.id.category_name);
        name.setBackgroundColor(colors[position]);

        return rowView;
    }
}
