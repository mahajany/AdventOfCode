package com.techactivate.advent2020;

import com.techactivate.advent2020.Day2020;
import com.yogesh.advent2020.general.PasswordLine;

import java.io.IOException;
import java.util.List;

public class Day02 extends Day2020 {

    public void run() {


        List<PasswordLine> input = null;
        try {
            System.out.println("T.E.S.T");
            input = this.readCSV("2020/resources/day02test.txt", " ");
            part1(input);
            part2(input);

            System.out.println("A.C.T.U.A.L");
            input = this.readCSV("2020/resources/day02.txt", " ");
            part1(input);
            part2(input);


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(input.size() + "...elements!");
    }


    public void part1(List<PasswordLine> input ){
        int i =0;
        for (var v: input){
            if(isValid1(v)){
                i++;
            }
        }
        System.out.println("Part-1: valid passwords:"+ i);
    }

    boolean isValid1(PasswordLine line){
        char c = line.theChar;
        long count = line.password.chars().filter(ch -> ch == c).count();
        if(count>=line.start && count<=line.end )
            return true;
        else
            return false;
    }
    public void part2(List<PasswordLine> input ){
        int i =0;
        for (var v: input){
            if(isValid2(v)){
                i++;
            }
        }
        System.out.println("Part-2: valid passwords:"+ i);
    }

    boolean isValid2(PasswordLine line){
        char c = line.theChar;
        long count = line.password.chars().filter(ch -> ch == c).count();
        char first = line.password.charAt(line.start-1);
        char second = line.password.charAt(line.end-1);
        if((c==first && c==second)||(c!=first && c!=second)) {
            return  false;

        } else
            return true;
    }
}
