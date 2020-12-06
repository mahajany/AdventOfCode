package com.yogesh.advent2020.general;

public class PasswordLine {
    //Day02
    public Integer start;
    public Integer end;
    public char  theChar;
    public String  password;

    public PasswordLine(){
        start=0;
        end=0;
        theChar =' ';
        password="";
    }

    @Override
    public String toString() {
        return "PasswordLine{" +
                "start=" + start +
                ", end=" + end +
                ", theChar='" + theChar + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
