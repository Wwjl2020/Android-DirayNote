package com.example.diraynote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView textlist;

    private SQLiteDatabase sqLiteDatabase;

    private  MyAdapter textAdapter;

    private List<textBean> list;

    private Button add_text;

    //长按后点击更新时返回点击的项目和文本ID号

    private int Item_id;

    //单击返回文本

    private textBean Item_click;

    private textBean text_item;





    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textlist=findViewById(R.id.text_list);

        Database text_database=new Database(MainActivity.this);

        //能读

        sqLiteDatabase=text_database.getReadableDatabase();

        //获取数据

        list=text_database.querydata(sqLiteDatabase);

        //将sqlite查询到的数据返回

        textAdapter=new MyAdapter(MainActivity.this,list);

        textlist=findViewById(R.id.text_list);

        textlist.setAdapter(textAdapter);



        registerForContextMenu(textlist);



        //列表项长按监听

        textlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                //实现删除功能





                //得到id号

                Item_id=list.get(i).getId();

                //得到点击第i个item时返回itemBean

                text_item=list.get(i);

                textlist.showContextMenu();

                return true;

            }

        });





        //点击显示整个文本

        textlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item_id=list.get(i).getId();
                text_item = list.get(i);
                Intent intent=new Intent(MainActivity.this,Update_Text.class);

                Bundle bundle=new Bundle();

                String Update_Context=text_item.getContext();

                String Update_Title=text_item.getTitle();
                String Update_Author_=text_item.getAuthor();
                bundle.putInt("id",Item_id);

                //把文本和标题传递

                bundle.putString("context",Update_Context);

                bundle.putString("title",Update_Title);
                bundle.putString("author",Update_Author_);
                intent.putExtras(bundle);

                startActivity(intent);





            }
            });


        //实现添加功能

        add_text=findViewById(R.id.add_text);

        add_text.setOnClickListener(this);



    }







    @Override

    public void onClick(View view) {

        switch (view.getId())

        {



            case R.id.add_text:

                Intent intent=new Intent(this,Add_Text.class);

                startActivity(intent);

                break;

            default:break;

        }



    }



    //重载：改写长按弹出的菜单内容

    @Override

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.add(0,0,0,"删除文本");



        super.onCreateContextMenu(menu, v, menuInfo);

    }











    //对菜单选项关于和退出事件监听








    @Override

    public void onBackPressed() {

        String title="提示";

        new AlertDialog.Builder(MainActivity.this)

                .setTitle(title)

                .setMessage("确定退出吗？")

                .setPositiveButton("确定" ,new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();

                    }

                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {



            }

        }).create().show();

    }







    //长按后弹出的文本点击事件

    @Override

    public boolean onContextItemSelected(MenuItem item) {



                String title="提示";

                new AlertDialog.Builder(MainActivity.this)

                        .setTitle(title)

                        .setMessage("确定删除吗？")

                        .setPositiveButton("确定" ,new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialogInterface, int i) {

                                //删除操作，在什么数据库下删除id号的记录

                                Database text_database=new Database(MainActivity.this);

                                SQLiteDatabase sqLiteDatabase=text_database.getReadableDatabase();

                                text_database.delete(sqLiteDatabase,Item_id);

                                Intent intent=new Intent(MainActivity.this,MainActivity.class);

                                startActivity(intent);

                            }

                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                }).create().show();


return true;
    }

}
