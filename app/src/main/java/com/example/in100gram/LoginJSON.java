package com.example.in100gram;

import java.util.List;

public class LoginJSON {

    private String login;
    private String password;

    public LoginJSON(String login, String password){
        this.login = login;
        this.password = password;
    }
    public String getLogin(){
        return login;
    }
    public String getPassword(){
        return password;
    }
}
