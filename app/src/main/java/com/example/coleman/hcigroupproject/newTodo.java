package com.example.coleman.hcigroupproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * Created by sirkellsworth on 4/22/16.
 */
public class newTodo{

    Context context;
    View parent;
    EditText name;
    EditText description;
    DatePicker picker;
    ViewGroup base;
    Button cancel;
    Button finish;

    public newTodo(Context context, View parent){
        this.context=context;
        this.parent=parent;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view=mInflater.inflate(R.layout.new_todo,null);
        base=(ViewGroup)parent.getParent();

        name=(EditText)view.findViewById(R.id.name);
        //description(EditText)view.findViewById(R.id.description);
        //picker=(DatePicker)view.find


        base.addView(view);
    }
}
