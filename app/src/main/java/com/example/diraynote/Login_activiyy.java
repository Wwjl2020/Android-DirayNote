package com.example.diraynote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login_activiyy extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_account, edit_password;
    private Button  btn_signUp;
    private String account, password;
    private CheckBox box_rememberpsw;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        init();
    }
    private void init() {
        edit_account = (EditText) findViewById(R.id.phone_text);

        edit_password = (EditText) findViewById(R.id.password_text);

        box_rememberpsw = (CheckBox) findViewById(R.id.remember_pass);
        btn_signUp = (Button) findViewById(R.id.signUp_btn);

        btn_signUp.setOnClickListener(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = sharedPreferences.getBoolean("remember_password", false);
        if (isRemember) {
            String name = sharedPreferences.getString("Account", "");
            String psw = sharedPreferences.getString("Password", "");
            edit_account.setText(name);
            edit_password.setText(psw);
            box_rememberpsw.setChecked(true);
        }


    }
    @Override
    public void onClick(View view) {
        account = edit_account.getText().toString();
        password = edit_password.getText().toString();
        editor = sharedPreferences.edit();
        if (account.equals("123456")& password.equals("654321")){
            if (box_rememberpsw.isChecked()) {
                editor.putBoolean("remember_password", true);
                editor.putString("Account", account);
                editor.putString("Password", password);
            } else {
                editor.clear();
            }
            editor.apply();
            Toast.makeText(Login_activiyy.this, "登录成功!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login_activiyy.this, MainActivity.class);
            intent.putExtra("UserPhone", account);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(Login_activiyy.this, "手机号或密码错误，请重新输入！", Toast.LENGTH_SHORT).show();
        }
    }


    }


