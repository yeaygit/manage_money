package com.example.manage_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Navigator extends AppCompatActivity {

    private FirebaseAuth fireAuth;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Button btinput, btcheck,btsetting;
    private ImageButton back;
    private ImageView pic;
    private TextView nickname,gender,job,old,balance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);

        //back버튼 누르면 MainActivity로 이동
        back=findViewById(R.id.bt_back3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 Main Activity클래스로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent,1);
            }
        });

        //bt_input누르면 Input(지출입력) 페이지로 이동
        btinput=findViewById(R.id.bt_input);
        btinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 Input 클래스로 이동
                Intent intent = new Intent(getApplicationContext(), Input.class);
                startActivityForResult(intent,1);
            }
        });

        //bt_check누르면 MoneyStatic으로 이동
        btcheck=findViewById(R.id.bt_check);
        btcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 MoneyStatic 클래스로 이동
                Intent intent = new Intent(getApplicationContext(), MoneyStatic.class);
                startActivityForResult(intent,1);
            }
        });

        //bt_setting누르면 setting으로 이동
        btsetting=findViewById(R.id.bt_setting);
        btsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 Main MoneyStatic클래스로 이동
                Intent intent = new Intent(getApplicationContext(), MoneyStatic.class);
                startActivityForResult(intent,1);
            }
        });

        //개인정보(textview , imageview)
        nickname=findViewById(R.id.tv_nickname);
        old=findViewById(R.id.tv_old);
        balance=findViewById(R.id.tv_balance);
        job=findViewById(R.id.tv_job);
        gender=findViewById(R.id.tv_gender);
        pic=findViewById(R.id.img_pic);


    }
}