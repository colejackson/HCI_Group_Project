package com.example.coleman.hcigroupproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by sirkellsworth on 4/22/16.
 */
public class newTodo{

    Context context;
    View parent;
    EditText name;
    EditText description;
    Spinner category;
    DatePicker picker;
    ViewGroup base;
    Button cancel;
    Button finish;

    public newTodo(Context context, View parent){
        this.context=context;
        this.parent=parent;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final View view=mInflater.inflate(R.layout.new_todo,null);
        base=(ViewGroup)parent.getParent();

        name=(EditText)view.findViewById(R.id.name);
        description=(EditText)view.findViewById(R.id.description);
        //fill data from some method passed from the main
        category=(Spinner)view.findViewById(R.id.category);
        //picker=(DatePicker)view.find

        cancel=(Button)view.findViewById(R.id.cancel);
        finish=(Button)view.findViewById(R.id.submit);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                base.removeView(view);
            }
        });
        finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //check to see if all fields are used
                String nameText=name.getText().toString();
                String notes=description.getText().toString();
                String selected=category.getSelectedItem().toString();

                base.removeView(view);
            }
        });

        base.addView(view);
    }
}
