package com.example.diraynote;
import java.util.Date;



 class textBean {

    private int id;

    private String title;

    private String Context;

    private String date;
     private String author;


    public int getId() {

        return id;

    }



    public void setId(int id) {

        this.id = id;

    }



    public String getDate() {

        return date;

    }



    public void setDate(String date) {

        this.date = date;

    }





    public String getTitle() {

        return title;

    }



    public void setTitle(String title) {

        this.title = title;

    }



    public String getContext() {

        return Context;

    }



    public void setContext(String context) {

        Context = context;

    }



    public textBean(int id, String title, String context, String date,String author) {

        this.id = id;

        this.title = title;

        Context = context;

        this.date = date;
        this.author=author;
    }

     public String getAuthor() {
         return author;
     }

     public void setAuthor(String author) {
         this.author = author;
     }
 }