package com.example.manage_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    private Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //login
        login=findViewById(R.id.bt_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼 클릭스 login클래스로 이동
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivityForResult(intent,1);
            }
        });

        //signup
        login=findViewById(R.id.bt_signup);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼 클릭스 signup클래스로 이동
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivityForResult(intent,1);
            }
        });



    }
}