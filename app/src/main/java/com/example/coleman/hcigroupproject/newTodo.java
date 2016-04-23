package com.example.coleman.hcigroupproject;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.coleman.adapters.CatagoryAdapter;
import com.example.coleman.xml.DataParser;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sirkellsworth on 4/22/16.
 */
public class newTodo{

    EditText name;
    EditText description;
    Spinner category;
    DatePicker picker;
    Button cancel;
    Button finish;
    Dialog dialog;
    Application app;
    DataParser parser;

    public newTodo(Application app, DataParser parser){
        this.app=app;
        this.parser=parser;
    }

    public void showTodo(){
        dialog=new Dialog(app);
        dialog.setContentView(R.layout.new_todo);
        dialog.setTitle("Create New Note");

        name=(EditText)dialog.findViewById(R.id.name);
        description=(EditText)dialog.findViewById(R.id.description);
        //fill data from some method passed from the main
        category=(Spinner)dialog.findViewById(R.id.category);
        category.setAdapter(new CatagoryAdapter(dialog.getOwnerActivity(),parser.getCategory()));
        //picker=(DatePicker)view.find

        cancel=(Button)dialog.findViewById(R.id.cancel);
        finish=(Button)dialog.findViewById(R.id.submit);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    //check to see if all fields are used
                    String nameText = name.getText().toString();
                    String notes = description.getText().toString();
                    int selected=category.getSelectedItemPosition();
                    int year = picker.getYear();
                    int day = picker.getDayOfMonth();
                    int month = picker.getMonth();

                    SimpleDateFormat sdf = new SimpleDateFormat();
                    Date date = sdf.parse("" + month + "/" + day + "/" + year);

                    parser.addNote(nameText, selected, date, notes);
                }catch(Exception e){e.printStackTrace();}

                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
