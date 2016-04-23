package com.example.coleman.hcigroupproject;

import android.app.Activity;
import android.app.Dialog;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.coleman.adapters.CategoryManageAdapter;
import com.example.coleman.app_code.Category;
import com.example.coleman.app_code.Orderings;
import com.example.coleman.xml.DataParser;

import java.util.Collections;

/**
 * Created by coleman on 4/23/16.
 */
public class SortTodo
{
    Button cancel;
    Button finish;
    Dialog dialog;

    RadioGroup group;
    RadioButton ca;
    RadioButton cd;
    RadioButton bc;
    RadioButton ran;

    Activity parent;
    DataParser parser;

    public SortTodo(Activity parent)
    {
        this.parser = ((Main)parent).getParser();
        this.parent = parent;
    }

    public void showTodo()
    {
        dialog = new Dialog(parent);
        dialog.setContentView(R.layout.sort_todo);
        dialog.setTitle("Sort Todo");

        group = (RadioGroup) dialog.findViewById(R.id.group);

        ca = (RadioButton) dialog.findViewById(R.id.ca);
        cd = (RadioButton) dialog.findViewById(R.id.cd);
        bc = (RadioButton) dialog.findViewById(R.id.bc);
        ran = (RadioButton) dialog.findViewById(R.id.ran);

        cancel = (Button) dialog.findViewById(R.id.can);
        finish = (Button) dialog.findViewById(R.id.okay);

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
                int id = group.getCheckedRadioButtonId();

                if(id == ca.getId())
                {
                    parser.setOrdering(Orderings.TIME_ASC);
                    parser.sort();
                }
                else if(id == cd.getId())
                {
                    parser.setOrdering(Orderings.TIME_DESC);
                    parser.sort();
                }
                else if(id == bc.getId())
                {
                    parser.setOrdering(Orderings.BY_CAT);
                    parser.sort();
                }
                else if(id == ran.getId())
                {
                    parser.shuffle();
                }

                dialog.dismiss();

                ((Main)parent).update();
            }
        });

        dialog.show();
    }
}
