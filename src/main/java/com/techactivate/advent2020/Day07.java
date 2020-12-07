package com.techactivate.advent2020;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day07 extends Day2020 {

    List<HoldingBag> hBags = new ArrayList<>();
    String           name  = "shiny gold bag";
    HoldingBag       shiny = new HoldingBag(name, new ArrayList<>());
    List<HoldingBag> children = new ArrayList<>();
    Set<String> parents = new HashSet<>();

    public void run() {
        inputFile = "input/2020/day07test.txt";
        inputFile = "input/2020/day07test2.txt";
        inputFile = "input/2020/day07.txt";

        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            line = reader.readLine();
            while (line != null) {
                hBags.add(getBags(line));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<String> results = new HashSet<>();
        getParentsOfBag(shiny.name);
        int total = findCostOfSubTree(shiny.name)-1; //-1 because it is counting the 1 shiny bag itself as well!

        System.out.println("Part-1: Parents of " + shiny.name + " ===> " + parents.size());
        System.out.println("Part-2:===> " + total);

    }

    private int findCostOfSubTree(String name) {
        HoldingBag hb = getBh(name);
        int sum = 1;
        if (hb == null) {
            return 0; //Will it ever reach?
        } else {
            for (Bag bag : hb.bags) {
                int subTreeCost = findCostOfSubTree(bag.name);
                sum += bag.number * subTreeCost;
            }
        }
        return sum;
    }

    private HoldingBag getBh(String name) {
        for (HoldingBag hb : hBags)
            if (hb.name.equals(name))
                return hb;
        return null;
    }


    /***My magic recusive function **/
    private void getParentsOfBag(String name) {
        Set<String> newParents = new HashSet<>();
        newParents = addParentsList(name);
        parents.addAll(newParents);

        if (newParents.size() > 0) {
            for (String s : newParents) {
                getParentsOfBag(s);
            }
        }
    }


    Set<String> addParentsList(String name) {
        Set<String> parents = new HashSet<>();
        for (HoldingBag hb : hBags) {
            int searchResults = hb.searchForAChild(name);
            if (searchResults > 0) {
                parents.add(hb.name);
            }
        }
        return parents;
    }

    private HoldingBag getBags(String line) {
        line = line.replace("contain ", ",");
        line = line.replace(".", "");
        line = line.replace("bags", "bag");

        String[] lineParts = StringUtils.split(line, ",");
        HoldingBag hb = new HoldingBag();
        hb.name = lineParts[0].trim();
        for (int i = 1; i < lineParts.length; i++) {
            String s = lineParts[i].trim();
            if (s.equals("no other bag"))
                break;
            int firstSpace = s.indexOf(" ");

            Bag b = new Bag(s.substring(firstSpace).trim(), Integer.parseInt(s.substring(0, firstSpace)));
            hb.bags.add(b);
        }
        if (shiny.name.equals(hb.name)) {
            shiny = hb;
        }
        return hb;
    }

    class Bag {
        String  name;
        Integer number;

        public Bag(String name, int i) {
            this.name = name;
            this.number = i;
        }

        @Override
        public String toString() {
            return "Bag:" + name + ":" + number;
        }
    }

    class HoldingBag {
        String    name;
        List<Bag> bags;

        public HoldingBag() {
            this.name = "";
            this.bags = new ArrayList<>();
        }

        public HoldingBag(String name, ArrayList<Bag> bags) {
            this.name = name;
            this.bags = bags;
        }

        public Integer searchForAChild(String name) {
            for (int i = 0; i < this.bags.size(); i++) {
                if (this.bags.get(i).name.equals(name))
                    return this.bags.get(i).number;
            }
            return 0;
        }

        @Override
        public String toString() {
//            return name.equals("shiny gold bag")?
//                    "\nHB:" + name + ":" + bags.stream().map(String::valueOf).collect(Collectors.toList())
//                    :"";
            return "HB:" + name + ":"
                    + bags.stream().map(String::valueOf).collect(Collectors.toList());
        }
    }


}
