package com.example.coleman.hcigroupproject;

import java.util.Date;

/**
 * Created by sirkellsworth on 4/17/16.
 */
public class TODO {

    String name;
    String description;
    Date dueDate;

    public TODO(){

    }

    public void setName(String name){
        this.name=name;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setDueDate(Date date){
        dueDate=date;
    }
}
