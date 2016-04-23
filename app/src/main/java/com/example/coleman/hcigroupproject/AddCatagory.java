package com.example.coleman.hcigroupproject;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.coleman.adapters.ColorAdapter;
import com.example.coleman.app_code.Category;
import com.example.coleman.xml.DataParser;

/**
 * Created by sirkellsworth on 4/22/16.
 */
public class AddCatagory {

    EditText name;
    Button cancel;
    Button finish;
    Dialog dialog;
    Spinner color;

    Activity parent;
    DataParser parser;

    public AddCatagory(Activity parent)
    {
        this.parser = ((Main)parent).getParser();
        this.parent = parent;
    }

    public void showTodo()
    {
        dialog = new Dialog(parent);
        dialog.setContentView(R.layout.new_catagory);
        dialog.setTitle("   Create New Category");

        name = (EditText) dialog.findViewById(R.id.catName);
        //fill data from some method passed from the main
        color = (Spinner) dialog.findViewById(R.id.colorSpinner);
        Category[] cats=parser.getCategory();
        if(cats.length==0){
            cats = new Category[1];
            cats[0] =  new Category(0, "Default", Color.BLUE);
        }

        Integer[] colors=new Integer[cats.length];
        for(int i=0;i<cats.length;i++){
            colors[i]=cats[i].getColor();
        }
        color.setAdapter(new ColorAdapter(parent, colors));

        cancel = (Button) dialog.findViewById(R.id.catCancel);
        finish = (Button) dialog.findViewById(R.id.catOK);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String nameText = "";
                try {
                    //check to see if all fields are used
                    nameText = name.getText().toString();
                    //int selected=color.getSelectedItemPosition();

                    parser.addCat(nameText);
                }catch(Exception e){e.printStackTrace();}

                dialog.dismiss();

                ((Main)parent).update();
            }
        });

        dialog.show();
    }
}
