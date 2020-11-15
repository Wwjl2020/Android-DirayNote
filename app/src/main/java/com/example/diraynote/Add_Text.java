package com.example.diraynote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;





import android.os.Bundle;




import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.TextView;



import java.text.SimpleDateFormat;

import java.util.Date;
public class Add_Text extends AppCompatActivity implements View.OnClickListener {

    private TextView showTime;

    private EditText add_title;

    private EditText add_context;
    private EditText add_author;
    private Button save_text,return_text;



    @Override

    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

        String Date = simpleDateFormat.format(new Date());

        //可以放在init()中

        showTime=findViewById(R.id.showTime);

        showTime.setText(Date);

        add_context=findViewById(R.id.add_context);

        add_title=findViewById(R.id.add_title);

        add_author=findViewById(R.id.add_author);

        save_text=findViewById(R.id.save_text);

        return_text=findViewById(R.id.return_text);

        save_text.setOnClickListener(this);

        return_text.setOnClickListener(this);



    }



    @Override

    public void onClick(View view) {

        switch (view.getId())

        {

            //保存操作

            case R.id.save_text:

                new AlertDialog.Builder(Add_Text.this)

                        .setTitle("系统提示")

                        .setMessage("确定保存吗？")

                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialogInterface, int i) {
                                String author=add_author.getText().toString();

                                String title=add_title.getText().toString();

                                String context=add_context.getText().toString();

                                //返回时间

                                String time=showTime.getText().toString();

                                //理解：相当于是给工厂，工厂有原件和方法，text_database.getReadableDatabase()获得原价

//                                text_database.adddata 执行方法，将数据库原件给他

                                Database text_database=new Database(Add_Text.this);

                                //返回一个 SQLiteDatabase对象

                                SQLiteDatabase sqLiteDatabase=text_database.getReadableDatabase();

                                text_database.adddata(sqLiteDatabase,title,context,time,author);

                                Intent intent1=new Intent(Add_Text.this,MainActivity.class);

                                startActivity(intent1);

                            }

                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                }).create().show();

                break;



            case R.id.return_text:

                Intent intent=new Intent(Add_Text.this,MainActivity.class);

                startActivity(intent);

                break;

            default: break;

        }



    }











}