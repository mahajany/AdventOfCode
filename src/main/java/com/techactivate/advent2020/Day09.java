package com.techactivate.advent2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day09 extends Day2020 {
List<BigInteger> list =new ArrayList<>();
public int SIZE=25;

    public void run() {
//        inputFile = "input/2020/day09test.txt"; SIZE = 5;
        inputFile = "input/2020/day09.txt";  SIZE = 25;

        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            line = reader.readLine();
            while (line != null) {
                list.add(new BigInteger(line.strip()));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i=0;
        BigInteger notInN = new BigInteger(String.valueOf(-999));
        for(i = SIZE; i<list.size(); i++){
            if(!checkLastN(list.subList(i-SIZE, i), list.get(i), i)){
                notInN=list.get(i);
                System.out.println("FOUND at "+ i);
                break;
            }
        }
        BigInteger weekness = findWeekness(notInN, list.subList(0,i));

        int total=0;
        System.out.println("Part-1: ===> " + notInN.toString());
        System.out.println("Part-2: ===> " + weekness.toString());

    }

    private boolean checkLastN(List<BigInteger> subList, BigInteger numberToSearch, int current) {
        boolean found=false;
        for(int i=0; i<subList.size(); i++){
            for(int j=i; j<subList.size();j++){
                if((subList.get(i).add(subList.get(j)).compareTo(( numberToSearch)))==0){
                    found=true;
                }
            }
        }
        return found;
    }

    BigInteger findWeekness(BigInteger n, List<BigInteger> list){
        BigInteger sum;
        for(int i = 0; i<list.size(); i++){
            for(int j=i+1; j<list.size(); j++) {
                BigInteger s = sum(list.subList(i,j));
                if (s.compareTo(n) == 0) {
                    List<BigInteger> subList = list.subList(i,j);;
                    return min(subList).add(max(subList));
                }
            }
        }
        return new BigInteger(String.valueOf(-9999));
    }

    BigInteger sum(List<BigInteger> list){
        BigInteger s = BigInteger.ZERO;
        for(BigInteger l: list){
            s=s.add(l);
        }
        return s;
    }
    BigInteger min(List<BigInteger> list){
        BigInteger m = list.get(0);
        for(BigInteger l: list){
            if(l.compareTo(m)<0)
                m=l;
        }
        return m;
    }
    BigInteger max(List<BigInteger> list){

        BigInteger m = list.get(0);
        for(BigInteger l: list){
            if(l.compareTo(m)>0)
                m=l;
        }
        return m;
    }

}
