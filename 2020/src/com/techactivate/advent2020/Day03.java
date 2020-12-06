package com.techactivate.advent2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day03 extends Day2020 {

    public void run() {

//        this.inputFile = "2020/input/day03test.txt";
//        this.inputFile = "2020/input/day03test2.txt";
        this.inputFile = "2020/input/day03.txt";

        Point2D direction = new Point2D(3, 1);
        Point2D result = traverseNFind(inputFile, direction);
        System.out.println("Part-1:"+ result);

        List<Point2D> list = new ArrayList<>();
        list.add(new Point2D(1,1));
        list.add(new Point2D(3,1));
        list.add(new Point2D(5,1));
        list.add(new Point2D(7,1));
        list.add(new Point2D(1,2));

        List<Point2D> results = new ArrayList<>();

        list.forEach(l->{
               results.add(traverseNFind(inputFile, l));
                });
        Integer product=1;

        for (var r : results){ //Can't use Lamda here?
//            System.out.print(r);
            product*=r.x;
        };
        System.out.println("\nPart-2:"+ product);

    }

    public Point2D traverseNFind(String inputFile, Point2D direction) {
        Point2D count = new Point2D(0,0);
        Point2D location = new Point2D(1, 1);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(this.inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        int skipY = 1;
        try {
            line = reader.readLine(); //Purge first line
            line = reader.readLine();
            while (line != null) {
                location.y++;
                skipY++;
                if (skipY > direction.y) {
                    skipY = 1;
                    if (location.x + direction.x > line.length()) {
                        location.x = location.x + direction.x - line.length();
                    } else {
                        location.x += direction.x;
                    }
                    if (line.charAt(location.x - 1) == '#') {
                        count.x++;
                    } else if (line.charAt(location.x - 1) == '.') {
                        count.y++;
                    } else {
                        System.out.println("E.R.R.O.R.");
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    class Point2D {
        int x;
        int y;

        public Point2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}
