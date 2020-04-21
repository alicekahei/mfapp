package com.first.test.webceshi;

public class BaseRequest {
    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }


    private String nick,password;

    public BaseRequest (String nick,String password){
        this.nick= nick;
        this.password =password;
    }
}
