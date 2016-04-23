package com.example.coleman.hcigroupproject;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.coleman.adapters.CategoryManageAdapter;
import com.example.coleman.app_code.Category;
import com.example.coleman.app_code.Todo;
import com.example.coleman.xml.DataParser;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by coleman on 4/23/16.
 */
public class SetDate
{
    DatePicker dp;
    Button can;
    Button ok;
    Dialog dialog;
    Activity parent;
    DataParser parser;
    Todo target;

    public SetDate(Activity parent, Todo target)
    {
        this.parser = ((Main)parent).getParser();
        this.parent = parent;
        this.target = target;
    }

    public void showTodo()
    {
        dialog = new Dialog(parent);
        dialog.setContentView(R.layout.date_picker);
        dialog.setTitle("Manage Categories");

        dp = (DatePicker) dialog.findViewById(R.id.picker);
        can = (Button) dialog.findViewById(R.id.date_can);
        ok = (Button) dialog.findViewById(R.id.date_ok);

        Calendar cal=Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        dp.updateDate(year, month, day);

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int day = dp.getDayOfMonth();
                    int month = dp.getMonth();
                    int year =  dp.getYear();

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, day);

                    target.setDate(calendar.getTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                dialog.dismiss();

                ((Main) parent).update();
            }
        });

        dialog.show();
    }
}
