package com.example.coleman.adapters;

import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.EditText;

import com.example.coleman.app_code.Todo;
import com.example.coleman.hcigroupproject.R;
import com.example.coleman.xml.DataParser;

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
    private final DataParser dp;

    Boolean expanded = false;

    
    public TodoAdapter(Activity context, Todo[] events, DataParser dp)
    {
        super(context, R.layout.todo_item, events);

        this.context = context;
        this.events = events;
        this.dp = dp;
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

        OnSwipeTouchListener ostl = new OnSwipeTouchListener(context, dp, events[position]);

        rowView.setOnTouchListener(ostl);

        final LinearLayout hiddenrow = (LinearLayout) context.findViewById(R.id.hiddenrow);
        final TextView notes = (TextView) context.findViewById(R.id.notes);
        final EditText name = (EditText) context.findViewById(R.id.name);
//        DatePicker date = (DatePicker) context.findViewById(R.id.date);
        final Button dateButton = (Button) context.findViewById(R.id.datebutton);
        final Spinner category = (Spinner) context.findViewById(R.id.category);
        final Switch usedate = (Switch) context.findViewById(R.id.usedate);
        final ImageButton expand = (ImageButton) context.findViewById(R.id.expand);


        hiddenrow.setVisibility(View.INVISIBLE);
        notes.setVisibility(View.INVISIBLE);



        notes.setText(events[position].getNote());
        name.setText(events[position].getName());
//        date.updateDate(d.getYear(), d.getMonth(), d.getDay());
//        dateButton.set


        expand.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (expanded) {
                    expanded = false;
                    //reset button image (pointing up)

                    expand.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_24dp);
                    //set name and date to UNEDITABLE
                    name.setEnabled(false);
                    dateButton.setEnabled(false);
                    dateButton.setClickable(false);

                } else {

                    hiddenrow.setVisibility(View.VISIBLE);
                    notes.setVisibility(View.VISIBLE);
                    //reset button image (pointing down)
                    expand.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_24dp);
                    //set name and date to EDITABLE
                    name.setEnabled(true);
                    dateButton.setEnabled(true);
                    dateButton.setClickable(true);

                }
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                //DatePicker datePicker = new DatePicker();

            }
        });


        return rowView;
    }
}
