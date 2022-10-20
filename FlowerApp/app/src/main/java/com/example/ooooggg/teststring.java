package com.example.ooooggg;

public class teststring {
    private String name;
    private String email;
    private String password;
    public  teststring() {}
    public  teststring (String text_1, String text_2,String text_3) {
        this.name=text_1;
        this.email=text_2;
        this.password=text_3;
    }
    public String getname() {
        return name;
    }
    public String getemail(){
        return email;
    }
    public String getpassword(){
        return password;
    }
}
