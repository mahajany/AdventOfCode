package com.techactivate.advent2019;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Day01 extends Day2019{
	@Override
	void run() {
		this.readFile();
		return;
	}

	public void readFile() {
		String file;
		file = "input/2019/day01test.txt";
//		file = "input/2019/day01.txt";

		Integer total1=0, total2 = 0, totalRecursion=0;
		Integer number = 0;
		String line;

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				number = Integer.valueOf(line);

//				total1+=  (number/3)-2;
				total1+=onlyModuelsFule(number);
				total2 = extracted(total2, number);
				totalRecursion+=extractedRecursion(number);
			}

			System.out.println("Part-1: Total:" + total1);
			System.out.println("Part-2: Total:" + total2);
			System.out.println("Part-2-Recursion Total:" + totalRecursion);

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

	private Integer extracted(Integer total, Integer number) {
		Integer fuel=0;
		while (number/3-2 > 0) {
			fuel = number /3 -2;
//			System.out.println(fuel + " ");
			total +=fuel;			
			number= number /3 -2;
		}
		return total;
	}

	private Integer extractedRecursion(Integer number) {
		int rv=0;
		rv += x(number);
		return rv;
	}
	Integer x(Integer number){
		if(number/3-2<=0)
			return number;
		return x(number/3-2);
	}
	private Integer onlyModuelsFule(Integer number) {
		return number / 3 - 2;
	}
}
