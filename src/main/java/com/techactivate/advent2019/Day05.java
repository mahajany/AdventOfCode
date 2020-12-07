package com.techactivate.advent2019;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day05 extends Day2019 {

	@Override
	void run() {

	        this.processFileInput();
        return;
    }

    public void processFileInput() {
        String file = "input/2019/testInput5.txt";
        String line;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                process(line);
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            System.out.println("Finally...");
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void process(String line) {
        if ("//".equals(line.subSequence(0, 2)))
            return;
        String[] strings = line.split(",");

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (String s : strings)
            list.add(Integer.valueOf(s));
        ArrayList<Integer> copy = new ArrayList<Integer>(list);
        int output = getOutput(list);

//		if (3085697 != output) {
//			System.out.println("ERROR!");
//			return;
//		}
        System.out.println("Part-1>" + output + "< for " + list);

//		int state = 19690720;
//		int last = 99;
//		int start = 0;
//
//		getState(state, copy, start, last);

//		System.out.println(line + "==>" + list);
//		System.out.println("Position-0:"+list.get(0));

    }

    int a = 0, b = 0, c = 0, de = 0;

    private Integer getOutput(List<Integer> list) {
        int i = 0;
        int value = 0;
        int size = list.size();
        int operand1;
        int operand2;
        int operation;
        int loc1, loc2, loc3;
        int location;
        while (i + 4 < list.size()) {
//			System.out.println("iii;"+i);
            operation = list.get(i);
            decodeOperation(operation);
//			System.out.println("Operation:" + a +"/" + b +"/"+ c+"/"+de);

            loc1 = list.get(i + 1);
            loc2 = list.get(i + 2);
            loc3 = list.get(i + 3);

            if (c == 0)
                operand1 = list.get(loc1);
            else
                operand1 = loc1;

            if (b == 0)
                operand2 = list.get(loc2);
            else
                operand2 = loc2;

            location = loc3;

            if (de == 1) {
                value = operand1 + operand2;
            } else if (de == 2) {
                value = operand1 * operand2;
            }
            if (de == 3) {
                list.set(location, value);
            } else if (operation == 4) {
                System.out.print("----" + value);
                ;
            }
            i += 4;
        } /* End of While */
        System.out.println(value);
        return list.size();
    }

    private void decodeOperation(int operation) {
        a = 0;
        b = 0;
        c = 0;
        de = 0;

        a = operation / 10000 % 10;
        b = operation / 1000 % 10;
        c = operation / 100 % 10;
        de = operation % 100;
        System.out.println("Operation:" + a + "/" + b + "/" + c + "/" + de);

    }

    void getState(Integer state, ArrayList<Integer> list, int start, int last) {
        ArrayList<Integer> listCopy = new ArrayList<Integer>(list);

        for (int i = start; i <= last; i++) {
            for (int j = start; j <= last; j++) {
                list = new ArrayList(listCopy);
                list.set(1, i);
                list.set(2, j);
//				System.out.println("Original:   " + listCopy);
                int output = (getOutput(list));
//				System.out.println(output + "=" + list);
                if (output == -1)
                    continue;
                if (output == state) {
                    System.out.println("Desired number " + i + " and " + j + "=>" + (i * 100 + j));
//					return;
                }
            }
//			System.out.println(i + ". ");

        }
        System.out.println("--nothing!");
    }


}
