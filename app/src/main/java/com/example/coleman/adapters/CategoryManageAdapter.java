package com.example.coleman.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.coleman.app_code.Category;
import com.example.coleman.hcigroupproject.Main;
import com.example.coleman.hcigroupproject.R;
import com.example.coleman.xml.DataParser;

/**
 * Created by coleman on 4/23/16.
 */
public class CategoryManageAdapter extends ArrayAdapter<Category>
{
    private final Activity parent;
    private final Category[] cats;
    private final DataParser dp;

    public CategoryManageAdapter(Activity parent, Category[] cats)
    {
        super(parent, R.layout.category_manage, cats);

        this.parent = parent;
        this.cats = cats;
        this.dp = ((Main)parent).getParser();
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.category_manage, parent, false);
        }

        final CheckBox activeBox = (CheckBox) view.findViewById(R.id.active_box);
        final TextView cat_name = (TextView) view.findViewById(R.id.cat_name);
        final Spinner cat_spin = (Spinner) view.findViewById(R.id.color_spin);

        activeBox.setChecked(cats[position].getActive());
        cat_name.setText(cats[position].getName());
        cat_spin.setAdapter(new ColorAdapter(this.parent, new Integer[]{}));

        //activeBox.setOnClickListener();

        return view;
    }
}
