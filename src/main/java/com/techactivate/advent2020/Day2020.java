package com.techactivate.advent2020;


import com.techactivate.AdventOfCode;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Day2020 extends AdventOfCode {
    String inputFile;

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();

//        new Day01().run();
//        new Day02().run();
//        new Day03().run();
//        new Day04().run();
//        new Day05().run();
//        new Day06().run();
        new Day07().run();

        LocalDateTime end = LocalDateTime.now();
        System.out.println("Advent2020:=========================================================================================");
        System.out.println("Advent2020:Time to run: "+  start.until(end, ChronoUnit.MILLIS) + " ms!");
        System.out.println("Advent2020:=========================================================================================");
    }

    abstract void run();
}
