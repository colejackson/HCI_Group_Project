package com.example.coleman.xml;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;

import com.example.coleman.app_code.Category;
import com.example.coleman.app_code.Items;
import com.example.coleman.app_code.Todo;

import org.w3c.dom.Element;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sirkellsworth on 4/11/16.
 */
public class DataParser
{
    private static final int CATEGORY = 0;
    private static final int NOTE = 1;

    private File file;
    private File dir;
    private XMLReader reader;
    private PrintWriter out;

    private ArrayList<Todo> data;
    private ArrayList<Category> categories;

    public DataParser(Context context)
    {
        boolean makeDir = false;

        try
        {
            data = new ArrayList();
            dir = new File(Environment.getExternalStorageDirectory() + "/HCI/data");
            if(!dir.exists() || !dir.isDirectory())
            {
                makeDir = dir.mkdir();
            }
            file = new File(dir, "todo.xml");
            if(!file.exists() && makeDir)
            {
                file.createNewFile();
            }
            reader = new XMLReader(file);

            parseData();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void parseData()
    {
        reader.setTag("todo");
        reader.parse();
        for(int i = 0; i < reader.getCapacity(); i++)
        {
            Element tmp = (Element)reader.getNodeAt(i);
            //get the child nodes here...
        }
    }

    public void saveData()
    {
        try{
            file.delete();
            out=new PrintWriter(file);
            out.print("<base>");

            out.print("<categories>");

            for(Category cat : categories)
            {
                out.print("<category>");
                    out.print("<name>");
                        out.print(cat.getid());
                    out.print("</name>");
                    out.print("</id>");
                        out.print(cat.getName());
                    out.print("<id>");
                out.print("</category>");
            }

            out.print("</categories>");

            out.print("<events>");

            for(Todo todo:data)
            {
                out.print("<todo>");
                //figure out what the other tags I will need like the name and details and such
                    out.print("<name>");
                        out.print(todo.getName());
                    out.print("</name>");
                    out.print("<description>");
                        out.print(todo.getNote());
                    out.print("</description>");
                    out.print("<date>");
                        out.print(todo.getDate());
                    out.print("</date>");
                out.print("</todo>");
            }

            out.print("</events>");

            out.println("</base>");
            out.flush();
            out.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /*
    for now use String[] maybe later change to some kind of class like Todo[]
     */
    public Todo[] getData()
    {
        return data.toArray(new Todo[data.size()]);
    }

    public void remove(String name)
    {
        for(Todo todo : data)
        {
            if(todo.getName().equals(name))
            {
                data.remove(todo);
            }
        }
    }

    public void addNote(String name, int category, Date date, String note)
    {
        int id;
        do
        {
            id = (int)(Math.random()*10000);
        }
        while(checkID(id, NOTE));

        Category cat = null;
        for(Category c : categories)
        {
            if(c.getid() == category)
                cat = c;
        }

        data.add(new Todo(name, note, date, cat, id));
    }

    public void addCat(String name)
    {
        int id;
        int color;
        do
        {
            id = (int)(Math.random()*10000);
            color = Color.argb(255, 44, 44, 240);
        }
        while(checkID(id, CATEGORY));

        categories.add(new Category(id, name, color));
    }

    private boolean checkID(int id, int mode)
    {
        ArrayList<? extends Items> stuff = ((mode == CATEGORY) ? categories : data);

        for(Items i : stuff)
        {
            if(id == i.getid())
                return false;
        }

        return true;
    }
}
