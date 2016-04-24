package com.example.coleman.app_code;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by coleman on 4/22/16.
 */
public class Category implements Items
{
    private int id;
    private String name;
    private int color;
    private boolean active;

    public Category(int id, String name, int color)
    {
        this.id = id;
        this.name = name;
        this.color = color;
        this.active = true;
    }

    public Category()
    {
        this.id = 0;
        this.name = "Default";
        this.color = -148594;
        this.active = true;
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

    public String toString(){return name;}

    public boolean getActive()
    {
        return active;
    }

    public void setActive(boolean b)
    {
        this.active = b;
    }

    public static boolean hasDefault(ArrayList<Category> ca)
    {
        Category defalt = new Category();

        for(Category c : ca)
        {
            if(c.getid() == defalt.getid())
            {
                return true;
            }
        }

        return false;
    }

    public static boolean isActive(ArrayList<Category> ca, int i)
    {
        for(Category c : ca)
        {
            if(c.id == i)
            {
                return c.active;
            }
        }

        return false;
    }
}
