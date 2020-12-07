package com.techactivate.advent2020;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 extends Day2020 {

    @Override
    public void run() {

        List<Integer> input = new ArrayList<>();
//        input = this.readFileIntoListOfIntegers(input/2020/day01test.txt");
        input = this.readFileIntoListOfIntegers("input/2020/day01.txt");
        Collections.sort(input);

        this.twoNumbers(input);
        this.threeNumbers(input);

    }

    public void twoNumbers(List<Integer> input) {
        System.out.println("..2 numbers");
        int size = input.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int sum  = input.get(i) + input.get(j);
                if (sum == 2020) {
                    System.out.println(input.get(i) + " and " + input.get(j) + "->" + input.get(i) * input.get(j));
                    break;
                } else if (sum>2020){
                    break;
                }
            }
        }
    }

    public static void threeNumbers(List<Integer> input) {
        System.out.println("..now 3 numbers");
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                for (int k = j + 1; k < input.size(); k++) {
                    if (input.get(i) + input.get(j) + input.get(k) == 2020) {
                        System.out.println(input.get(i) + " and " + input.get(j) + " and " + input.get(k) + "->"
                                + input.get(i) * input.get(j) * input.get(k));
                    }
                }
            }
        }
    }
    }
