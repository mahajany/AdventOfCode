package com.techactivate.advent2019;

import com.techactivate.utils.Coordinates;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day03 extends Day2019 {
	public static final Coordinates ORIGIN = new Coordinates(0, 0);

		@Override
		void run() {
		Day03 obj = new Day03();
		obj.processFileInput();
		return;
	}

	public void process(String line1, String line2) {
		Map<String, Integer> w1 = new HashMap<String, Integer>();

		pathFromOrigin(line1, w1);
		intersections(line2, w1);

	}

	public void pathFromOrigin(String line, Map<String, Integer> w1) {
		String[] list = line.split(",");
		int noSteps = 0;

		Coordinates prev = new Coordinates(0, 0);
		Coordinates step = new Coordinates(0, 0);
		int stepsTaken = 0;
		for (String s : list) {
			char direction = s.charAt(0);
			noSteps = Integer.parseInt(s.substring(1));
			while (noSteps > 0) {
				stepsTaken++;	
				switch (direction) {
				case 'D':
					step = new Coordinates(0, -1);
					break;
				case 'U':
					step = new Coordinates(0, +1);
					break;
				case 'R':
					step = new Coordinates(+1, 0);
					break;
				case 'L':
					step = new Coordinates(-1, 0);
					break;
				}
				prev.add(step);
				if (!w1.containsKey(prev.toString())) {
					w1.put(prev.toString(), stepsTaken);
				}
				--noSteps;
			}
		}
	}


	public void intersections(String line, Map<String, Integer> w1) {
		int noSteps = 0;
		Integer minDistance = 9999;
		int minSteps = 99999999;
		String[] list = line.split(",");

		Coordinates nextStep = new Coordinates(0, 0);
		Coordinates step = new Coordinates(0, 0);
		int stepsBythisWire = 0;
		for (String s : list) {
			char direction = s.charAt(0);
			noSteps = Integer.parseInt(s.substring(1));
			while (noSteps > 0) {
				stepsBythisWire++;
				switch (direction) {
				case 'D':
					step = new Coordinates(0, -1);
					break;
				case 'U':
					step = new Coordinates(0, +1);
					break;
				case 'R':
					step = new Coordinates(+1, 0);
					break;
				case 'L':
					step = new Coordinates(-1, 0);
					break;
				}
				nextStep.add(step);
				if (w1.containsKey(nextStep.toString())) {
					int distance = nextStep.distance(ORIGIN);
					if (distance < minDistance)
						minDistance = distance;
					int stepsByFirstWire = w1.get(nextStep.toString());
					if (minSteps > stepsByFirstWire + stepsBythisWire)
						minSteps = stepsByFirstWire + stepsBythisWire;

//					System.out.println(prev + " at " + distance);
				}
				--noSteps;
			}
		}
		System.out.println("====" + minDistance + "/" + minSteps);
	}

	public void processFileInput() {
		String file = "input/2019/testInput3.txt";
		String line1;
		String line2;

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line1 = reader.readLine()) != null) {
				line2 = reader.readLine();
				process(line1, line2);
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
}
