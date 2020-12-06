package com.yogesh.advent2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day05 extends Day00 {

    public void run() {
//        inputFile="W:\\AdventOfCode\\AdventOfCode2020\\input\\day05test.txt";
//        inputFile="W:\\AdventOfCode\\AdventOfCode2020\\input\\day05test2.txt";
        inputFile="W:\\AdventOfCode\\AdventOfCode2020\\input\\day05.txt";
        List<Integer> seatIds = new ArrayList<>();

        getIds(inputFile, seatIds);
        Collections.sort(seatIds,  Collections.reverseOrder());
        System.out.println("Part-1:"  + seatIds.get(0));

        int startId=getSeatId("FFFFFFBLLL");
        int endId=getSeatId("BBBBBBFRRR");
        System.out.println(seatIds.size() + " Possible min/max " + endId + "/" + startId + ", but this list:" + seatIds.get(seatIds.size() - 1) + "/" + seatIds.get(0));
        boolean  found=false;
        int current=seatIds.get(0);
        for(int i =seatIds.get((seatIds.size()-1)); i<seatIds.get(0); i++){
            if(!seatIds.contains(i)){
                found=true;
                System.out.println("Part-2:===>"+i);
                break;
            }
        }
        if(!found)
            System.out.println("Part-2:===>" + ( seatIds.get(seatIds.size() - 1) -1)+ " or " + (seatIds.get(0)+1));
    }


    private void getIds(String inputFile, List<Integer> seatIds) {
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            line = reader.readLine();
            while(line!=null){
                seatIds.add(getSeatId(line));
                line=reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer getSeatId(String line) {
        int i = 0;
        int row = getPosition(line.substring(0,7),'B', 'F', 0, 127);
        int column = getPosition(line.substring(7), 'R', 'L', 0,7);
        i = row * 8 + column;
//        System.out.println( line + " row "+ row + ", column "+ column + ", ID:"+ i);
        return  i;
    }

    private int getPosition(String line, char up, char down, int start, int end) {
        for (char c : line.toCharArray()) {
//            System.out.print(c + " - " + start + "-" + end + "====>");

            if (c == up) {
                start = (start + end) / 2;
            } else if (c == down) {
                end = (start + end) / 2;
            }
//            System.out.println(c + " - " + start + "-" + end );
        }
        return end;
    }
}
