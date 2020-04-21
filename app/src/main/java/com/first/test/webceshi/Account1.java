package com.first.test.webceshi;

import androidx.fragment.app.Fragment;

public class Account1 {



    private String uid;
    private String token;
    private String nick;
    private String companyName;


    public Account1(String token , String companyName, String nick, String uid){
        this.uid = uid;
        this.companyName = companyName;
        this.token = token;
        this.nick = nick;

    }

    public String getCompanyName(){
        return companyName;
    }
    public String getNick() {
        return nick;
    }
    public String getToken(){
        return token;
    }
    public String getUid() {
        return uid;
    }
}

