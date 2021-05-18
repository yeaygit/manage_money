package com.example.manage_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Input extends AppCompatActivity {

    private ImageButton back;
    private Button btmoneysave;
    private EditText des,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //뒤로 가기 누르면 Navigator화면으로 이동
        back=findViewById(R.id.bt_back5);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 Navigator 클래스로 이동
                Intent intent = new Intent(getApplicationContext(), Navigator.class);
                startActivityForResult(intent,1);
            }
        });

        //지출 내용 입력하기
        des=findViewById(R.id.et_des);
        price=findViewById(R.id.et_price);


        //버튼누르면 지출 입력 내용 저장 후 Navigator화면으로 이동
        btmoneysave=findViewById(R.id.bt_money_save);
        btmoneysave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Navigator.class);
                startActivityForResult(intent,1);
            }
        });



    }

    Spinner yearSpinner = (Spinner)findViewById(R.id.sp_year);
    Spinner monthSpinner=(Spinner)findViewById(R.id.sp_month);
    Spinner daySpinner=(Spinner)findViewById(R.id.sp_date);
    Spinner cateSpinner=(Spinner)findViewById(R.id.sp_category);

}