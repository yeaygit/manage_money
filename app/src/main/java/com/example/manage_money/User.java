package com.example.manage_money;

import android.content.Intent;

public class User {
    private String nick;
    private String old;
    private String gender;
    private String job;
    private int balance;

    public User(){

    }


    public User( String nick, String old, String gender,String job,int balance){

        this.nick=nick;
        this.old=old;
        this.gender=gender;
        this.job=job;
        this.balance=balance;
    }



    //setter

    public void setValue( String nick, String old, String gender,String job,int balance){
        this.nick=nick;
        this.old=old;
        this.gender=gender;
        this.job=job;
        this.balance=balance;
    }

    public Intent setIntentValue(Intent intent){
        intent.putExtra("usernick",nick);
        intent.putExtra("userold",old);
        intent.putExtra("usergender",gender);
        intent.putExtra("userjob",job);
        intent.putExtra("userbalance",balance);
        return intent;
    }

    //getter
    public void getIntentValue(Intent intent){
        this.setValue(
                intent.getExtras().getString("usernick"),
                intent.getExtras().getString("userold"),
                intent.getExtras().getString("usergender"),
                intent.getExtras().getString("userjob"),
                intent.getExtras().getInt("userbalance")
        );
    }




}
