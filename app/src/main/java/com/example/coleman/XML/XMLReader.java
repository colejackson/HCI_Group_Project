package com.example.coleman.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by sirkellsworth on 3/13/16.
 */
public class XMLReader
{
    private File file;
    private InputStream in;
    private Document document;
    private NodeList list;
    private String tag;
    private static int index=0;
    private boolean empty=true;

    public XMLReader(File file){
        try{
            file=File.createTempFile(("tmp"+index), ".data");
            index++;
            in=new FileInputStream(file);
            FileOutputStream out=new FileOutputStream(file);
            while(in.available()!=0){
                out.write(in.read());
                empty=false;
            }
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            if(!empty) {
                document = db.parse(file);
            }

        }catch(Exception e){e.printStackTrace();}
    }

    public boolean isEmpty(){
        return empty;
    }

    public XMLReader(InputStream tmp){
        try {
            in = tmp;
            file = File.createTempFile(("tmp"+index), ".data");
            index++;
            FileOutputStream out = new FileOutputStream(file);
            while (in.available() != 0) {
                out.write(in.read());
            }
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            document=db.parse(file);
        }catch(Exception e){e.printStackTrace();}
    }

    public void setTag(String tag){
        this.tag=tag;
    }

    public void parse(){
        Element element=document.getDocumentElement();
        list=element.getElementsByTagName(tag);
    }

    public Node getNodeAt(int index){
        return list.item(index);
    }

    public int getCapacity(){
        return list.getLength();
    }
}
