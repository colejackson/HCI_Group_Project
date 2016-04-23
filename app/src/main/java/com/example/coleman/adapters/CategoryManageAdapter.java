package com.example.coleman.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.coleman.app_code.CatColors;
import com.example.coleman.app_code.Category;
import com.example.coleman.hcigroupproject.Main;
import com.example.coleman.hcigroupproject.R;
import com.example.coleman.xml.DataParser;

import java.util.ArrayList;

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
        final Category target = cats[position];

        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.category_manage, parent, false);
        }

        final CheckBox activeBox = (CheckBox) view.findViewById(R.id.active_box);
        final TextView cat_name = (TextView) view.findViewById(R.id.cat_name);
        final Spinner cat_spin = (Spinner) view.findViewById(R.id.color_spin);

        activeBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                target.setActive(isChecked);
            }
        });

        cat_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                target.setColor(Integer.parseInt(cat_spin.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        cat_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                target.setName(v.getText().toString());

                return true;
            }
        });

        activeBox.setChecked(cats[position].getActive());
        cat_name.setText(cats[position].getName());

        ArrayList<Integer> colors = new ArrayList();
        for(CatColors c : CatColors.values())
            colors.add(c.id);

        cat_spin.setAdapter(new ColorAdapter(this.parent, colors.toArray(new Integer[colors.size()])));

        for(int i = 0; i < cat_spin.getCount(); i++)
        {
            cat_spin.setSelection(i);

            int j = Integer.parseInt(cat_spin.getSelectedItem().toString());
            int k = target.getColor();

            if(j == k)
            {
                break;
            }
        }

        return view;
    }
}
