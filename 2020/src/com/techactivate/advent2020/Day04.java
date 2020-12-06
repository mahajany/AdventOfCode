package com.techactivate.advent2020;

import utils.CommonUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day04 extends Day2020 {
    int allFields = 0, missingFields = 0;
    int valid = 0, invalid= 0;
    CommonUtils utils=new CommonUtils();


    public void run() {
//        inputFile="2020/input/day04test.txt";
//        inputFile="2020/input/day04test2.txt";
        inputFile="2020/input/day04.txt";

        List<NorthPolePassport>passports=new ArrayList<>();
        try {
            passports=readPassports(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Part-1: Total "+ passports.size() + ", Valid:" + allFields + ", Invalid:"+ missingFields);
        System.out.println("Part-2: Total "+ passports.size() + ", Valid:" + valid + ", Invalid:"+ invalid);

    }


    public class NorthPolePassport {
        Map<String, String>     data;
        boolean valid;
        public NorthPolePassport(){
            data = new HashMap();
            valid=false;
        }
        @Override
        public String toString() {
            return data.toString()+">"+valid;
        }

    }

    public List<NorthPolePassport> readPassports(String fileName) throws IOException {
        //Passwordline file for Day02
        BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
        String row, line = "";
        List<NorthPolePassport> passports = new ArrayList<>();

        while ((row = csvReader.readLine()) != null) {
            if (row.trim().length() == 0) {
                passports.add(processPassportData(line));
                line = "";
            } else {
                line = line + " " + row;
           }
        }
        //..and the last one!
        passports.add(processPassportData(line));
        csvReader.close();
        return passports;
    }

    public NorthPolePassport processPassportData(String line) {
        NorthPolePassport passport = new NorthPolePassport();
        String[] fields = line.trim().split("[\\n\\s]");
        for (var field : fields) {
            String key = field.split(":")[0];
            String value = field.split(":")[1];
            passport.data.put(key, value);
        }
        if (passport.data.containsKey("byr")
                && passport.data.containsKey("iyr")
                && passport.data.containsKey("eyr")
                && passport.data.containsKey("hgt")
                && passport.data.containsKey("hcl")
                && passport.data.containsKey("ecl")
                && passport.data.containsKey("pid")) {
            passport.valid = true;
            this.allFields++;
        } else {
            passport.valid = false;
            this.missingFields++;
        }
        if(passport.valid)
            validateData(passport);
        if(passport.valid)
            valid++;
        else
            invalid++;
        return passport;
    }
    public void validateData(NorthPolePassport p){
        p.valid = validYear(p.data.get("byr"), 1920, 2002)
                && validYear(p.data.get("iyr"), 2010, 2020)
                && validYear(p.data.get("eyr"), 2020, 2030)
                && validHeight(p.data.get("hgt"))
                && validHairColour(p.data.get("hcl"))
                && validEyeColor(p.data.get("ecl"))
                && validPassportID(p.data.get("pid"));
    }
    boolean validYear(String s, int start, int end){
        int i  = Integer.parseInt(s);
        if(i>=start && i<=end)
            return true;
        else
            return false;
    }
    boolean validHeight(String s){
        String sHt = s.replaceAll("[^0-9]", "");
        Double ht = Double.parseDouble(sHt);
        String units = s.substring(sHt.length());
        if(units.equals("in") && ht>=59 && ht<=76)
            return true;
        else if(units.equals("cm") && ht>=150 && ht<=193)
            return true;
        else
            return false;
    }

    boolean validHairColour(String s){
        if(s.length()==7 && s.charAt(0)=='#' && utils.isHex(s.substring(1)))
            return true;
        else
            return false;
    }

    boolean validEyeColor(String s){
        List<String> validColours = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        if(validColours.contains(s))
            return true;
        else
            return false;
    }
    boolean validPassportID(String s){
        return utils.isNumeric(s) &&
                s.length()==9; //Hmmm...-12345678 too will be marked as 'valid'!
    }

}
