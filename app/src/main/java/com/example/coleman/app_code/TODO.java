package com.example.coleman.app_code;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sirkellsworth on 4/17/16.
 */
public class Todo implements Items
{

    String name;
    String note;
    Date dueDate;
    Category cat;
    int eventid;

    public Todo(String name, String note, Date date, Category cat, int id)
    {
        this.name = name;
        this.note = note;
        this.dueDate = date;
        Log.d("TODO","THE CATEGORY IS "+cat);
        this.cat = cat;
        this.eventid = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public String getNote()
    {
        return note;
    }

    public void setDate(Date date)
    {
        this.dueDate = date;
    }

    public String getDate()
    {
        DateFormat formatter = new SimpleDateFormat();

        return formatter.format(dueDate);
    }

    public void setCategory(Category cat)
    {
        this.cat = cat;
    }

    public Category getCategory()
    {
        return cat;
    }

    public int getid()
    {
        return eventid;
    }

    public String toString()
    {
        return name;
    }
}
