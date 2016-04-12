package com.example.coleman.hcigroupproject.XML;

import android.content.Context;

import org.w3c.dom.Element;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by sirkellsworth on 4/11/16.
 */
public class DataParser {

    File file;
    XMLReader reader;
    PrintWriter out;

    ArrayList<String> data;

    public DataParser(Context context){
        try {
            file = new File(context.getFilesDir(),"todo.xml");
            if (!file.exists()) {
                file.createNewFile();
            }
            reader=new XMLReader(file);

            parseData();
        }catch(Exception e){e.printStackTrace();}
    }

    public void parseData(){
        reader.setTag("todo");
        reader.parse();
        for(int i=0;i<reader.getCapacity();i++){
            Element tmp=(Element)reader.getNodeAt(i);
            //get the child nodes here...
        }
    }

    public void saveData(){
        try{
            file.delete();
            out=new PrintWriter(file);
            out.print("<base>");
                out.print("<todo>");
                    //figure out what the other tags I will need like the name and details and such
                out.print("</todo>");
            out.println("</base>");
            out.flush();
            out.close();
        }catch(Exception e){e.printStackTrace();}
    }

    /*
    for now use String[] maybe later change to some kind of class like TODO[]
     */
    public String[] getData(){
        return data.toArray(new String[data.size()]);
    }
}
