package com.example.coleman.xml;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.example.coleman.app_code.Category;
import com.example.coleman.app_code.Items;
import com.example.coleman.app_code.Orderings;
import com.example.coleman.app_code.Todo;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by sirkellsworth on 4/11/16.
 */
public class DataParser
{
    public static final int CATEGORY = 0;
    public static final int NOTE = 1;

    private Orderings mode;

    private File file;
    private File dir;
    private XMLReader reader;
    private PrintWriter out;

    private ArrayList<Todo> data;
    private ArrayList<Category> categories;

    public DataParser(Context context)
    {
        mode = Orderings.TIME_ASC;

        try
        {
            file = new File(context.getFilesDir(), "todo.xml");
            if(!file.exists())
            {
                file.createNewFile();
            }
            Log.d("TODO","file is "+file.exists());
            reader = new XMLReader(file);

            data=new ArrayList<>();
            Log.d("TODO","data length "+data.size());
            categories=new ArrayList<>();

            parseData();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void parseData()
    {
        //first categories
        reader.setTag("categories");
        reader.parse();
        if(reader.getCapacity() > 0)
        {
            Element tmp = (Element)reader.getNodeAt(0);

            NodeList list = tmp.getChildNodes();

            for(int i = 0; i < list.getLength(); i++)
            {
                Node n = list.item(i);

                NodeList values = n.getChildNodes();


                //get the child nodes here...
                String name = values.item(0).getFirstChild().getNodeValue();
                int id = Integer.getInteger(values.item(1).getFirstChild().getNodeValue());
                int color = Integer.getInteger(values.item(2).getFirstChild().getNodeValue());

                Category newCat=new Category(id,name,color);
                categories.add(newCat);
            }
        }

        //then todos
        reader.setTag("todos");
        reader.parse();
        if(reader.getCapacity() > 0)
        {
            Element tmp = (Element) reader.getNodeAt(0);

            NodeList list = tmp.getChildNodes();

            for (int i = 0; i < list.getLength(); i++)
            {
                Node n = list.item(i);

                NodeList values = n.getChildNodes();

                //get the child nodes here...
                String name = values.item(0).getFirstChild().getNodeValue();
                String description = values.item(1).getFirstChild().getNodeValue();
                String raw = values.item(2).getFirstChild().getNodeValue();
                SimpleDateFormat sdf = new SimpleDateFormat();

                Date date = null;
                try
                {
                    date = sdf.parse(raw);
                }
                catch(ParseException e)
                {
                    System.out.println("PARSE EXCEPTION: DATA PARSER: BAD DATE PARSE");
                }

                String cat = values.item(3).getFirstChild().getNodeValue();
                int catid = ((cat.equals("null")) ? 0 : Integer.parseInt(cat));
                String id_str = values.item(4).getFirstChild().getNodeValue();
                int id = Integer.parseInt(id_str);

                Category category = null;
                for(Category c : categories)
                {
                    if(catid == c.getid())
                    {
                        category = c;
                    }
                }

                if(category == null)
                    category = new Category(0, "DEFAULT", Color.BLUE);

                Todo todo = new Todo(name, description, date, category, id);
                data.add(todo);
            }
        }
    }

    public void saveData()
    {
        this.sort();

        try{
            file.delete();

            out = new PrintWriter(file);
            out.print("<base>");

            out.print("<categories>");

            for(Category cat : categories)
            {
                out.print("<category>");
                    out.print("<name>");
                        out.print(cat.getName());
                    out.print("</name>");
                    out.print("<id>");
                        out.print(cat.getid());
                    out.print("</id>");
                    out.print("<color>");
                        out.print(cat.getColor());
                    out.print("</color>");
                out.print("</category>");
            }

            out.print("</categories>");

            out.print("<todos>");

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
                    out.print("<category>");
                        out.print(todo.getCategory());
                    out.print("</category>");
                    out.print("<id>");
                        out.print(todo.getid());
                    out.print("</id>");
                out.print("</todo>");
            }

            out.print("</todos>");

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

    public Category[] getCategory(){return categories.toArray(new Category[categories.size()]);}

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

        saveData();
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

        saveData();
    }

    public void updateCat(Category c)
    {
        for(int j = 0; j < categories.size(); j++)
        {
            if(categories.get(j).getid() == c.getid())
            {
                categories.set(j, c);
            }
        }

        saveData();
    }

    public void updateNote(Todo t)
    {
        for(int j = 0; j < data.size(); j++)
        {
            if(data.get(j).getid() == t.getid())
            {
                data.set(j, t);
            }
        }

        saveData();
    }

    public void removeCat(Category c)
    {
        categories.remove(c);

        saveData();
    }

    public void removeNote(Todo t)
    {
        data.remove(t);

        saveData();
    }

    public void sort()
    {
        Collections.sort(data, mode);
        saveData();
    }

    public void shuffle()
    {
        Collections.shuffle(data);
    }

    public void setOrdering(Orderings o)
    {
        this.mode = o;
    }

    private boolean checkID(int id, int mode)
    {
        ArrayList<? extends Items> stuff = ((mode == CATEGORY) ? categories : data);

        for(Items i : stuff)
        {
            if(id == i.getid())
                return true;
        }

        return false;
    }
}
