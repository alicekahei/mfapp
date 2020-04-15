package com.first.test.webceshi;

import androidx.fragment.app.Fragment;

public class Account {

    private String uid;
    private String userName;
    private String password;
    private String telNumber,token,nick,companyName;


    public Account(String uid, String userName, String telNumber) {
        this.uid = uid;
        this.userName = userName;
        this.telNumber = telNumber;
    }
    public Account(String token ,String companyName,String nick,String uid){
        this.uid = uid;
        this.companyName = companyName;
        this.token = token;
        this.nick = nick;

    }

    @Override
    public String toString() {
        return "Account [uid=" + uid + ", userName=" + userName + ", password=" + password + ", telNumber=" + telNumber
                + "]";
    }

    public String getToken(){
        return token;
    }
}

