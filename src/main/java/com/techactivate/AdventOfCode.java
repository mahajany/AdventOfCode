package  com.techactivate;

import com.techactivate.utils.PasswordLine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdventOfCode {

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
    public boolean isHex(String s){
        return s.matches("[0-9A-Fa-f]+");
    }

    public boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[+-]?\\d+(\\.\\d+)?");
        return pattern.matcher(s).matches();
    }

    public List<String> readDataSeparatedByABlankLine(String fileName) throws IOException {
        return this.readDataSeparatedByABlankLine(fileName, " ");
    }

    /***
     *
     * @param fileName
     * @return List of Strings
     * @throws IOException
     * Read a list of data separated by a blank like
     */
    public List<String> readDataSeparatedByABlankLine(String fileName, String separator) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
        String currentLine, dataForThisGroupSoFar = "";
        List<String> list = new ArrayList<>();

        while ((currentLine = csvReader.readLine()) != null) {
            if (currentLine.trim().length() == 0) {
                this.addLinetoTheList(list, dataForThisGroupSoFar);
                dataForThisGroupSoFar = "";
            } else {
                dataForThisGroupSoFar = dataForThisGroupSoFar + separator + currentLine;
            }
        }
        //..and the last one!
        this.addLinetoTheList(list, dataForThisGroupSoFar);
        csvReader.close();
        return list;
    }
    private void addLinetoTheList(List<String> list, String line){
        if(!line.isEmpty())
            list.add(line);
    }

}
