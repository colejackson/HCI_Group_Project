package com.example.coleman.hcigroupproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sirkellsworth on 4/17/16.
 */
public class TODO {

    String name;
    String description;
    Date dueDate;

    public TODO()
    {

    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){return name;}

    public String getDescription(){return description;}

    public String getDate(){
        DateFormat formater=new SimpleDateFormat();

        return formater.format(dueDate);
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setDueDate(Date date){
        dueDate=date;
    }
}
