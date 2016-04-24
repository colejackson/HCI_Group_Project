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
public class ColorAdapter extends ArrayAdapter<CatColors>{

    Activity context;
    Integer[] colors;

    public ColorAdapter(Activity context){
        super(context, R.layout.new_color, CatColors.values());

        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.catagory_layout, parent, false);
        }

        TextView name = (TextView) view.findViewById(R.id.category_name);
        name.setBackgroundColor(CatColors.values()[position].id);
        name.setWidth(30);
        name.setHeight(40);
        name.setText("");

        return view;
    }
}
