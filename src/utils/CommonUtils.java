package utils;

import com.yogesh.advent2020.general.PasswordLine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonUtils {

    public List<String> readFileIntoListOfStrings(String fileName) {
        List<String> result=new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Integer> readFileIntoListOfIntegers(String fileName) {
        List<String> result=new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public Integer strToInt(String input){
        return Integer.parseInt(input);
    }

    public List<PasswordLine> readCSV(String fileName, String separator) throws IOException {
        //Passwordline file for Day02
        BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
        String row;
        List<PasswordLine> lines = new ArrayList<>();

        while ((row = csvReader.readLine()) != null) {
            PasswordLine line = new PasswordLine();
            String[] data = row.split(separator);
            String col1=data[0];
            line.start=Integer.parseInt(col1.split("-")[0]);
            line.end=Integer.parseInt(col1.split("-")[1]);

            line.theChar =data[1].charAt(0);
            line.password =data[2];
            lines.add(line);
        }
        csvReader.close();
        return lines;
    }



}