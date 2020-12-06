package com.techactivate.advent2020;

import com.yogesh.advent2020.general.LaFileReadingThings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.joining;

public class Day06 extends Day2020 {

    public void run() {
//        inputFile = "2020/resources/day06test.txt";
        inputFile = "2020/resources/day06.txt";
        int totalUnique = 0;
        int totalCommon =0;
        try {
            List<String> responses = new LaFileReadingThings().readDataSeparatedByABlankLine(inputFile);
            List<String> uniqueReplies = new ArrayList<>();
            for (String response : responses) {
                String unique = uniqueNonBlankCharsInString(response);
                uniqueReplies.add(unique);
                totalUnique += unique.length();
            }

            List<String> responses2 = this.charPresentInEveryAnswerGroup(inputFile);
            List<String> coomonReplies = new ArrayList<>();
            for (String response : responses2) {
                coomonReplies.add(response);
                totalCommon += response.length();
//                System.out.println(coomonReplies + " --> "+response.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Part-1 ==>" + totalUnique);
        System.out.println("Part-2 ==>" + totalCommon);
    }

    public String uniqueNonBlankCharsInString(String s) {
        s = s.replaceAll(" ", "");
        Set<Character> uniques = new HashSet<>();
        for (char c : s.toCharArray()) {
            uniques.add(c);
        }
        String result = uniques.stream().map(String::valueOf).collect(joining());
        return result;
    }


    public List<String> charPresentInEveryAnswerGroup(String fileName) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
        String currentLine="";
        List<String> list = new ArrayList<>();
        List<String> answerListOfGroup = new ArrayList<>();

        while ((currentLine = csvReader.readLine()) != null) {
            if (currentLine.trim().length() == 0) {
                list.add(commonResponses(answerListOfGroup));
                answerListOfGroup= new ArrayList<>();
            } else {
                answerListOfGroup.add(currentLine);
            }
        }
        //..and the last one!
        list.add(commonResponses(answerListOfGroup));
        csvReader.close();
        return list;
    }
    private String commonResponses(List<String> answers){
        List<String> ls = new ArrayList<>();
        for(String s: answers){
            ls.add(uniqueNonBlankCharsInString(s));
        }
        String common = commonChars(ls);
        return common;
    }
    private String commonChars(List<String> answers){
        String all="abcdefghijklmnopqrstuvwxyz";
            for(Character option: all.toCharArray()){
                for(String ans: answers){
                    if(ans.indexOf(option)==-1){
                        all=all.replace(option.toString(),"");
                    }
                }

            }
        return all;
    }


}
