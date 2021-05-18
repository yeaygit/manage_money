package com.example.manage_money;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth fireAuth;
    private EditText etid, etemail,etpw, etcheckpw;
    private ImageButton back;
    private Button btgosignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //뒤로 가기 버튼을 통해 MainActivity로 가기
        back=findViewById(R.id.bt_back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭스 Main Activity클래스로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent,1);
            }
        });

        //회원가입하려면 입력해야해!
        etid=findViewById(R.id.et_sign_id);
        etemail=findViewById(R.id.et_email);
        etpw=findViewById(R.id.et_sign_pw);
        etcheckpw=findViewById(R.id.et_check_pw);





        //회원 가입 후 Navigator화면으로 이동
        btgosignup=findViewById(R.id.bt_go_singup);
        btgosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입하기
                String id=IngetText(etid);
                String pw=IngetText(etpw);
                String checkpw = IngetText(etcheckpw);
                String email=IngetText(etemail);

                if(!checkpw.equals(pw)){
                    Toast.makeText(getApplicationContext(),"비밀번호롤 다르게 입력 하셨습니다.",Toast.LENGTH_SHORT);
                    return;
                }
                else if (!id.matches("^[a-zA-Z][a-zA-Z0-9-_]{7,19}$")){
                    Toast.makeText(getApplicationContext(),"아이디 형식을 잘 못 입력하셨습니다.", Toast.LENGTH_SHORT);
                    return;
                }
                else if(!pw.matches("^[a-zA-Z0-9!@#$%^&*()\\-_+|`~<>\\[\\];:\'\",./?\\\\=\\s]{8,20}$")){
                    Toast.makeText(getApplicationContext(),"비밀번호 형식을 잘 못 입력하셨습니다.",Toast.LENGTH_SHORT);
                    return;
                }
                else if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")){
                    Toast.makeText(getApplicationContext(),"이메일 형식을 잘 못 입력하셨습니다.",Toast.LENGTH_SHORT);
                    return;
                }
                else{
                    GoSignUP(id,email, pw);
                }


                //버튼 클릭스 Navigator클래스로 이동
                Intent intent = new Intent(getApplicationContext(), Navigator.class);
                startActivityForResult(intent,1);
            }
        });

    }


    private void GoSignUP(String id,String email,String pw) {
        // 진행 상황 알려주기
        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("가입중..잠시만 기다려주세요.");
        mDialog.show();

        //파이어베이스에 신규계정 등록하기
        // Initialize Firebase Auth
        fireAuth = FirebaseAuth.getInstance();
        fireAuth.createUserWithEmailAndPassword(email, pw).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //가입 성공시
                        mDialog.dismiss();
                        if (task.isSuccessful()) {

                            FirebaseUser user = fireAuth.getCurrentUser();
                            String email = user.getEmail();
                            String name = id;

                            //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                            HashMap<String, Object> userInfo = new HashMap<>();
                            // 현재시간을 msec 으로 구한다.
                            long now = System.currentTimeMillis();
                            // 현재시간을 date 변수에 저장한다.
                            Date date = new Date(now);
                            // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                            SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            // nowDate 변수에 값을 저장한다.
                            String formatDate = sdfNow.format(date);

                            userInfo.put("userEmail", email);
                            userInfo.put("userId", id);
                            userInfo.put("userPass", pw);
                            userInfo.put("userStart", formatDate);

                            userInfo.put("userFollowing", 0);
                            userInfo.put("userFollower", 0);
                            userInfo.put("userGrade", 0);


                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("Users");
                            reference.child(email.substring(0, email.indexOf("@"))).setValue(userInfo);


                            //가입이 이루어져을시 가입 화면을 빠져나감.
                            Intent intent = new Intent(getApplicationContext(), Navigator.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "회원가입에 실패하셨습니다." + task.getException().toString(), Toast.LENGTH_SHORT).show();
                            return;  //해당 메소드 진행을 멈추고 빠져나감.
                        }
                    }});


    }

    private String IngetText(EditText et){
        return et.getText().toString();
    }
}