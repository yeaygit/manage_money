package com.example.manage_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CategoryStatic extends AppCompatActivity {

    private ImageButton back;
    private Button food,add,house,desert,hobby,etc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_static);

        //back버튼 누르면 moneystatic페이지로 이동
        back=findViewById(R.id.bt_back7);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 moneystatic클래스로 이동
                Intent intent = new Intent(getApplicationContext(), MoneyStatic.class);
                startActivityForResult(intent,1);
            }
        });
    }
}