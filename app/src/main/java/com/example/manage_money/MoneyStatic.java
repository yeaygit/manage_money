package com.example.manage_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MoneyStatic extends AppCompatActivity {

    private Button category;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_static);


        //back버튼 누르면 navigator화면으로
        back=findViewById(R.id.bt_back6);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 navigator클래스로 이동
                Intent intent = new Intent(getApplicationContext(), Navigator.class);
                startActivityForResult(intent,1);
            }
        });

        //category버튼 누르면 categorystatic화면으로
        category=findViewById(R.id.bt_category);
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 category-static클래스로 이동
                Intent intent = new Intent(getApplicationContext(),CategoryStatic.class);
                startActivityForResult(intent,1);
            }
        });
    }
}