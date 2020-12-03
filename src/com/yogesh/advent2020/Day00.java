package com.yogesh.advent2020;

import utils.CommonUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Day00 extends CommonUtils {
    String inputFile;

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();

        new Day03().run();

        LocalDateTime end = LocalDateTime.now();
        System.out.println("=========================================================================================");
        System.out.println("Time to run: "+  start.until(end, ChronoUnit.MILLIS) + " ms!");
        System.out.println("=========================================================================================");
    }

    abstract void run();
}
//Day01- 266ms
//Day02-129 ms
//Day03-
