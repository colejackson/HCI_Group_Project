package com.example.coleman.adapters;

import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.EditText;

import com.example.coleman.app_code.Category;
import com.example.coleman.app_code.Todo;
import com.example.coleman.hcigroupproject.Main;
import com.example.coleman.hcigroupproject.R;
import com.example.coleman.hcigroupproject.SetDate;
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
    Boolean isOn = false;
    
    public TodoAdapter(Activity context, Todo[] events, DataParser dp)
    {
        super(context, R.layout.todo_item, events);

        this.context = context;
        this.events = events;
        this.dp = dp;
    }

    public View getView(int position, View view, final ViewGroup parent)
    {
        final Todo t = events[position];

        Date d = null;
        try
        {
            d = new SimpleDateFormat("dd MMMM, yyyy").parse(events[position].getDate());
        }
        catch(ParseException e)
        {
            System.out.println("Messed Up Date Format!!!!");
        }

        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
        }

        OnSwipeTouchListener ostl = new OnSwipeTouchListener(context, dp, events[position]);

        view.setOnTouchListener(ostl);

        final LinearLayout hiddenrow = (LinearLayout) view.findViewById(R.id.hiddenrow);
        final TextView notes = (TextView) view.findViewById(R.id.notes);
        final EditText name = (EditText) view.findViewById(R.id.name);
        final Button dateButton = (Button) view.findViewById(R.id.datebutton);
        final Spinner category = (Spinner) view.findViewById(R.id.category);
        final Switch usedate = (Switch) view.findViewById(R.id.usedate);
        final ImageButton expand = (ImageButton) view.findViewById(R.id.expand);
        
        hiddenrow.setVisibility(View.GONE);
        notes.setVisibility(View.GONE);

        category.setAdapter(new CatagoryAdapter(this.context, dp.getCategory()));

        dateButton.setText(t.getDate());

        notes.setText(events[position].getNote());
        name.setText(events[position].getName());

        expand.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_24dp);

        expand.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (expanded) {
                    expanded = false;
                    //reset button image (pointing up)

                    hiddenrow.setVisibility(View.GONE);
                    notes.setVisibility(View.GONE);

                    expand.setImageResource(android.R.color.transparent);
                    expand.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_24dp);
                    //set name and date to UNEDITABLE
                    name.setEnabled(false);
                    dateButton.setEnabled(false);
                    dateButton.setClickable(false);

                    //SAVE ALL INPUT

                }
                else
                {
                    expanded = true;

                    t.setName(name.getText().toString());
                    t.setCategory((Category) category.getSelectedItem());
                    t.setNote(notes.getText().toString());

                    dp.updateNote(t);

                    hiddenrow.setVisibility(View.VISIBLE);
                    notes.setVisibility(View.VISIBLE);
                    //reset button image (pointing down)
                    expand.setImageResource(android.R.color.transparent);
                    expand.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_24dp);
                    //set name and date to EDITABLE
                    name.setEnabled(true);
                    dateButton.setEnabled(true);
                    dateButton.setClickable(true);

                }
                ((View)((View)((View)v.getParent()).getParent()).getParent()).invalidate();
                ((View)((View)((View)v.getParent()).getParent()).getParent()).refreshDrawableState();
            }
        });

        usedate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //We should test this... *not sure if default is "off" or "on"

                if (isOn) {
                    isOn = false;
                } else {
                    isOn = true;
                }

            }
        });
        

        dateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                //DatePicker datePicker = new DatePicker();

                //TODO for this action listener...
                //only execute if switch is "on"
                if (isOn)
                {
                    SetDate creator = new SetDate(context, t);
                    creator.showTodo();
                }
            }
        });


        return view;
    }
}
