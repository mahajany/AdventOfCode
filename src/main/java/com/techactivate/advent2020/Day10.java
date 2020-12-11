package com.techactivate.advent2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10 extends Day2020 {
    List<Integer> list  = new ArrayList<>();
    Steps         steps = new Steps();
    List<String>  stack = new ArrayList<>();
    Integer       max   = 0;

    public void run() {

//        inputFile = "input/2020/day10test3.txt";
//        inputFile = "input/2020/day10test2.txt";
//        inputFile = "input/2020/day10test.txt";
        inputFile = "input/2020/day10.txt";

        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            line = reader.readLine();
            while (line != null) {
                list.add(Integer.parseInt(line));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(list);
        max = Collections.max(list);
        int deviceRating = max + 3;
        System.out.println("Reach from 0 to " + deviceRating);
        getStepsCount(list, steps);
        System.out.println("Part-1: ===> " + steps.one * steps.three + " (rating is " + deviceRating + " and + " + list.size() + " adapters)");

        list.add(0);
        list.add(deviceRating);
        Collections.sort(list);
        BigInteger pathCount = possiblePaths(list);
        System.out.println("Part-2: ===> " + pathCount);
    }

    private void getStepsCount(List<Integer> list, Steps steps) {
        for (int i = 1; i < list.size(); i++) {
            Integer prev = list.get(i - 1);
            Integer current = list.get(i);
            Integer diff = current - prev;
            if (diff == 1) {
                steps.one++;
            } else if (diff == 2) {
                steps.two++;
            } else if (diff == 3) {
                steps.three++;
            }
        }
    }

    BigInteger possiblePaths(List<Integer> list) {
        BigInteger pathCount = BigInteger.ZERO;

        if (list.get(0) == max) {
            System.out.println(showList(stack));
            return BigInteger.ONE;
        }
        int x = 666;
        for (int i = 1; i <= 3; i++) {
            if (i < list.size()) {
                x = list.get(i) - list.get(0);
                if (x == 1 || x == 2 || x == 3) {
                stack.add(list.get(0)+"");
                pathCount = pathCount.add(possiblePaths(list.subList(i, list.size())));
//                System.out.println( list.get(0) + "x" + i + ":" + pathCount);
                stack.remove(stack.size()-1);
                }
            }
        }
        return pathCount;
    }



    private String showList(List<String> list) {
        String s = "";
        for (var l : list)
            s += l + ", ";
        return s;
    }

    class Steps {
        int one;
        int two;
        int three;

        public Steps() {
            //Initalize to 1 and not 0 - to use the "built-in" adapter of the device!
            one = 1;
            two = 1;
            three = 1;
        }

        @Override
        public String toString() {
            return "Steps{" +
                    "one=" + one +
                    ", two=" + two +
                    ", three=" + three +
                    '}';
        }
    }
}
