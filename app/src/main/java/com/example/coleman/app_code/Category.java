package com.example.coleman.app_code;

import android.graphics.Color;

/**
 * Created by coleman on 4/22/16.
 */
public class Category implements Items
{
    private int id;
    private String name;
    private int color;

    public Category(int id, String name, int color)
    {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public int getid()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getColor()
    {
        return this.color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }
}
