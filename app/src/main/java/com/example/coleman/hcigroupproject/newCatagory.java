package com.example.coleman.hcigroupproject;

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

    public newCatagory(Application app, DataParser parser){
        this.app=app;
        this.parser=parser;
    }

    public void showTodo(){
        dialog=new Dialog(app);
        dialog.setContentView(R.layout.new_todo);
        dialog.setTitle("Create New Catagory");

        name=(EditText)dialog.findViewById(R.id.name);
        //fill data from some method passed from the main
        color=(Spinner)dialog.findViewById(R.id.color);
        color.setAdapter(new ColorAdapter(dialog.getOwnerActivity(),new Integer[]{}));



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
