package  com.techactivate.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LaFileReadingThings {


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
