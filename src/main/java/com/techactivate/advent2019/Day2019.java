package com.techactivate.advent2019;


import com.techactivate.AdventOfCode;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Day2019 extends AdventOfCode {
    String inputFile;

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();

        new Day01().run();
//        new Day02().run();
//        new Day03().run();
//        new Day04().run();
//        new Day05().run();

        LocalDateTime end = LocalDateTime.now();
        System.out.println("Advent2019:=========================================================================================");
        System.out.println("Advent2019:Time to run: "+  start.until(end, ChronoUnit.MILLIS) + " ms!");
        System.out.println("Advent2019:=========================================================================================");
    }

    abstract void run();
}
