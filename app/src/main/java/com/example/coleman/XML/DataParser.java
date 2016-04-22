package com.example.coleman.xml;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;

import com.example.coleman.app_code.Category;
import com.example.coleman.app_code.Items;
import com.example.coleman.app_code.Todo;

import org.w3c.dom.Element;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sirkellsworth on 4/11/16.
 */
public class DataParser
{
    public static final int CATEGORY = 0;
    public static final int NOTE = 1;

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
            Log.d("TODO","directory is "+dir.exists());
            file = new File(dir, "todo.xml");
            if(!file.exists())
            {
                file.createNewFile();
            }
            Log.d("TODO","file is "+file.exists());
            reader = new XMLReader(file);

            data=new ArrayList<Todo>();
            categories=new ArrayList<Category>();

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
        reader.setTag("category");
        reader.parse();
        for(int i = 0; i < reader.getCapacity(); i++)
        {
            Element tmp = (Element)reader.getNodeAt(i);
            //get the child nodes here...
            String name=tmp.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
            int id=Integer.getInteger(tmp.getElementsByTagName("id").item(0).getFirstChild().getNodeValue());
            int color=Integer.getInteger(tmp.getElementsByTagName("color").item(0).getFirstChild().getNodeValue());

            Category newCat=new Category(id,name,color);
            categories.add(newCat);
        }

        //then todos
        reader.setTag("todo");
        reader.parse();
        for(int i = 0; i < reader.getCapacity(); i++)
        {
            try {
                Element tmp = (Element) reader.getNodeAt(i);
                String name = tmp.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
                String description = tmp.getElementsByTagName("description").item(0).getFirstChild().getNodeValue();
                String raw = tmp.getElementsByTagName("date").item(0).getFirstChild().getNodeValue();
                SimpleDateFormat sdf = new SimpleDateFormat();
                Date date = sdf.parse(raw);
                int categoryId=Integer.getInteger(tmp.getElementsByTagName("categoryId").item(0).getFirstChild().getNodeValue());
                int id=Integer.getInteger(tmp.getElementsByTagName("id").item(0).getFirstChild().getNodeValue());
                //get the child nodes here...

                Category category=null;
                for(Category temp:categories){
                    if(temp.getid()==categoryId){
                        category=temp;
                    }
                }

                Todo todo = new Todo(name, description,date,category,id);
            }catch(Exception e){e.printStackTrace();}
        }
    }

    private void saveData()
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
