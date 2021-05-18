package com.example.manage_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Setting extends AppCompatActivity {


    private FirebaseAuth fireAuth;
    private User user;
    private ImageView pic;
    private ImageButton back;
    private EditText etnickname,etgender,etjob,etold;
    private Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //back하면 navigator로!
        back=findViewById(R.id.bt_back4);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 navigator 클래스로 이동
                Intent intent = new Intent(getApplicationContext(), Navigator.class);
                startActivityForResult(intent,1);
            }
        });

        // 이전 액티비티의 데이터 수신
        Intent intent =getIntent();
        user = new User();
        user.getIntentValue(intent);


        //입력해서 저장하기!
        etnickname=findViewById(R.id.et_nickname);
        etgender=findViewById(R.id.et_gender);
        etjob=findViewById(R.id.et_job);
        etold=findViewById(R.id.et_old);

        pic=findViewById(R.id.img_pic1);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이미지를 선택
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), 0);
            }
        });

        //개인 정보 저장하기!
        String nickname=IngetText(etnickname);
        String job=IngetText(etjob);
        String old=IngetText(etold);
        String gender=IngetText(etgender);



        save=findViewById(R.id.bt_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private String IngetText(EditText et){

        return et.getText().toString();
    }

    public Setting() {
    }



}