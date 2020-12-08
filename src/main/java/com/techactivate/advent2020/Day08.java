package com.techactivate.advent2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day08 extends Day2020 {

    class Instruction {
        String  code;
        Integer value;

        public Instruction(String code, Integer value) {
            this.code = code;
            this.value = value;
        }

        @Override
        public String toString() {
            return code + " " + value;
        }
    }
    boolean infiniteLoop=false;

    Set<Integer> lineAlreadyExecuted = new HashSet<>();

    List<Instruction> instructions = new ArrayList<>();

    public void run() {
//        inputFile = "input/2020/day08test.txt";
//        inputFile = "input/2020/day08test2.txt";
        inputFile = "input/2020/day08.txt";
        String line = "";
        int finalValue = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            line = reader.readLine();
            while (line != null) {
                instructions.add(getInstruction(line));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int total = executeCode();
        int total2=0;

        infiniteLoop = true;
        for (Instruction i : instructions) {
            String storedName = i.code;
            if (i.code.equals("jmp")) {
                i.code = "nop";
            } else if (i.code.equals("nop")) {
                i.code = "jmp";
            }
            total2 = executeCode();
            if (infiniteLoop)
                i.code = storedName;
            else
                break;
        }
        System.out.println("Part-1: ===> " + total);
        System.out.println("Part-2: ===> " + total2);

    }

    private int executeCode() {
        int i = 0, accumulator = 0;
        String code;
        lineAlreadyExecuted.clear();
        while (true) {
            if(i>=instructions.size()){
                this.infiniteLoop=false;
                return accumulator;
            } else if (lineAlreadyExecuted.contains(i)) {
                this.infiniteLoop=true;
                return accumulator;
            } else {
                lineAlreadyExecuted.add(i);
            }

            if (instructions.get(i).code.equals("nop")) {
                i++;
            } else if (instructions.get(i).code.equals("acc")) {
                accumulator += instructions.get(i).value;
                i++;
            } else if (instructions.get(i).code.equals("jmp")) {
                i += instructions.get(i).value;
            }
        }
    }

    private Instruction getInstruction(String line) {

        String[] s = line.split(" ");
        return new Instruction(s[0], Integer.parseInt(s[1]));
    }
}


