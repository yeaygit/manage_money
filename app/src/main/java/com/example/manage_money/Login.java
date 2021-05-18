package com.example.manage_money;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Login extends AppCompatActivity {

    private FirebaseAuth fireAuth;
    private Button btgologin;
    private EditText etid,etpw;
    private ImageButton back;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //뒤로 가기 버튼
        back=findViewById(R.id.bt_back1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭스 Main Activity클래스로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent,1);
            }
        });

        //아이디와 패스워드 입력 받기
        etid=findViewById(R.id.et_id);
        etpw=findViewById(R.id.et_pw);

        //로그인 하고 navigator화면으로 이동
        btgologin=findViewById(R.id.bt_go_login);

        btgologin.setOnClickListener(new View.OnClickListener() {
            String id= IngetText(etid);
            String pw= IngetText(etpw);

            @Override
            public void onClick(View v) {
                if(!id.matches("^[a-zA-Z][a-zA-Z0-9-_]{7,19}$")){
                    Toast.makeText(getApplicationContext(),"아이디 형식을 잘 못 입력하셨습니다.",Toast.LENGTH_SHORT);
                    return;
                }
                else{
                    login(id,pw);
                }

            }
        });




    }

    private void login(String id, String pw) {
        fireAuth = FirebaseAuth.getInstance();
        fireAuth.signInWithEmailAndPassword(id, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //로그인 정보 저장
                            save_login_info(id, pw);

                            // 로그인 성공 메세지 출력
                            Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_SHORT).show();

                            // 유저 정보 전달 객체 생성
                            Intent intent = new Intent(getApplicationContext(), Navigator.class);
                            intent.putExtra("userEmail", "");
                            intent.putExtra("userId", id);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"로그인 오류" + task.getException().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private String IngetText(EditText et){

        return et.getText().toString();
    }
    private void save_login_info(String id, String pass)
    {
        //기본 SharedPreferences 환경과 관련된 객체를 얻어옵니다.
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // SharedPreferences 수정을 위한 Editor 객체를 얻어옵니다.
        editor = preferences.edit();
        editor.putString("userId", id);
        editor.putString("userPass", pass);
        editor.commit();
    }

    private String[] load_login_info()
    {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String id = preferences.getString("userId", "");
        String pass = preferences.getString("userPass","");
        String[] id_and_pass = {id, pass};
        return id_and_pass;
    }

}

