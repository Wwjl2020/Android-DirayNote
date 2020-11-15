package com.example.diraynote;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import android.content.Context;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;





import java.util.ArrayList;

import java.util.Date;

import java.util.List;
public class Database extends SQLiteOpenHelper {
    public Database(Context context) {

        super(context, "text_db", null, 2);

    }



    //第一次执行时创建

    @Override

    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "create table text(id integer primary key autoincrement," +

                "title varchar(20),context varchar(100),date Date,author varchar(20))";

        sqLiteDatabase.execSQL(sql);



    }



    @Override

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists text");
        onCreate(sqLiteDatabase);


    }



    //删

    public void delete(SQLiteDatabase sqLiteDatabase, int id) {

        sqLiteDatabase.delete("text", "id=?", new String[]{id + ""});

        sqLiteDatabase.close();

    }



    //查



    public List<textBean> querydata(SQLiteDatabase sqLiteDatabase)

    {

        Cursor cursor=sqLiteDatabase.query("text",null,null,null,null,null,"id ASC");

        List<textBean> list=new ArrayList<textBean>();

        while (cursor.moveToNext())

        {

            int id=cursor.getInt(cursor.getColumnIndex("id"));

            String title=cursor.getString(1);

            String context=cursor.getString(2);

            String date=cursor.getString(3);

            String author=cursor.getString(4);

            list.add(new textBean(id,title,context,date,author));

        }

        cursor.close();

        sqLiteDatabase.close();

        //返回查询的记事本集合

        return list;

    }

    //增

    public void adddata(SQLiteDatabase sqLiteDatabase,String title,String context,String date,String author)

    {

        ContentValues contentValues=new ContentValues();

        contentValues.put("title",title);

        contentValues.put("context",context);

        contentValues.put("date",date);

        contentValues.put("author",author);

        sqLiteDatabase.insert("text",null,contentValues);

        sqLiteDatabase.close();

    }

    //根据ID号更新表

    public boolean Update( SQLiteDatabase sqLiteDatabase,int id,String title,String context,String date,String author)

    {

        ContentValues contentValues=new ContentValues();

        contentValues.put("title",title);

        contentValues.put("context",context);

        contentValues.put("date",date);

        contentValues.put("author",author);

        sqLiteDatabase.update("text",contentValues,"id=?",new String[]{id+""});

        sqLiteDatabase.close();

        return true;

    }
}
