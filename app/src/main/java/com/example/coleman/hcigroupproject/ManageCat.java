package com.example.coleman.hcigroupproject;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.coleman.adapters.CategoryManageAdapter;
import com.example.coleman.adapters.ColorAdapter;
import com.example.coleman.app_code.Category;
import com.example.coleman.xml.DataParser;

import java.util.ArrayList;

/**
 * Created by coleman on 4/23/16.
 */
public class ManageCat
{
    EditText name;
    Button cancel;
    Button finish;
    Dialog dialog;
    Spinner color;

    Activity parent;
    DataParser parser;

    public ManageCat(Activity parent)
    {
        this.parser = ((Main)parent).getParser();
        this.parent = parent;
    }

    public void showTodo()
    {
        dialog = new Dialog(parent);
        dialog.setContentView(R.layout.manage_outer);
        dialog.setTitle("Manage Categories");

        ListView list = (ListView) dialog.findViewById(R.id.man_list);
        list.setAdapter(new CategoryManageAdapter(parent, ((Main)parent).getParser().getCategory()));

        cancel = (Button) dialog.findViewById(R.id.cat_man_cancel);
        finish = (Button) dialog.findViewById(R.id.cat_man_ok);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                try
                {
                    for(Category c : ((Main)parent).getParser().getCategory())
                    {
                        parser.updateCat(c);
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }



                dialog.dismiss();

                ((Main)parent).update();
            }
        });

        dialog.show();
    }
}
