package com.example.coleman.hcigroupproject;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.coleman.adapters.CatagoryAdapter;
import com.example.coleman.adapters.ColorAdapter;
import com.example.coleman.xml.DataParser;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sirkellsworth on 4/22/16.
 */
public class newCatagory {

    EditText name;
    Spinner color;
    Button cancel;
    Button finish;
    Dialog dialog;
    Application app;
    DataParser parser;
    Activity activity;

    public newCatagory(Application app, DataParser parser, Activity activity){
        this.app=app;
        this.parser=parser;
        this.activity=activity;
    }

    public void showCat(){
        dialog=new Dialog(activity);
        dialog.setContentView(R.layout.new_catagory);
        dialog.setTitle("Create New Catagory");

        name=(EditText)dialog.findViewById(R.id.name);
        //fill data from some method passed from the main
        color=(Spinner)dialog.findViewById(R.id.color);
        color.setAdapter(new ColorAdapter(activity,new Integer[]{app.getResources().getColor(R.color.blue),
                app.getResources().getColor(R.color.red),
                app.getResources().getColor(R.color.green),
                app.getResources().getColor(R.color.purple),
                app.getResources().getColor(R.color.yellow)}));



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
                    int selected=color.getSelectedItemPosition();

                    parser.addCat(nameText);
                }catch(Exception e){e.printStackTrace();}

                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
