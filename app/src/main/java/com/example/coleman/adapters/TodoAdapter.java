package com.example.coleman.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.coleman.app_code.Todo;
import com.example.coleman.hcigroupproject.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by coleman on 4/22/16.
 */
public class TodoAdapter extends ArrayAdapter<Todo>
{
    private final Activity context;
    private final Todo[] events;

    public TodoAdapter(Activity context, Todo[] events)
    {
        super(context, R.layout.todo_item, events);

        this.context = context;
        this.events = events;
    }

    public View getView(int position,View view, ViewGroup parent)
    {
        Date d = null;
        try
        {
            d = new SimpleDateFormat("dd MMMM, yyyy").parse(events[position].getDate());
        }
        catch(ParseException e)
        {
            System.out.println("Messed Up Date Format!!!!");
        }

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.todo_item, null, true);

        LinearLayout hiddenrow = (LinearLayout) context.findViewById(R.id.hiddenrow);
        TextView notes = (TextView) context.findViewById(R.id.notes);
        TextView name = (TextView) context.findViewById(R.id.name);
        DatePicker date = (DatePicker) context.findViewById(R.id.date);
        Spinner category = (Spinner) context.findViewById(R.id.category);
        Switch usedate = (Switch) context.findViewById(R.id.usedate);

        hiddenrow.setVisibility(View.INVISIBLE);
        notes.setVisibility(View.INVISIBLE);

        notes.setText(events[position].getNote());
        name.setText(events[position].getName());
        date.updateDate(d.getYear(), d.getMonth(), d.getDay());

        return rowView;
    }
}
