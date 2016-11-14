package de.ast.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ReadLinesFromFile {

    public ReadLinesFromFile() {
    }

    public List<String> readFromFile(String filename) {

        List<String> lines = new ArrayList<String>(0);

        String line;
        try {
            InputStream fis = new FileInputStream("filename");
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException fEx) {

        } catch (IOException ioex) {

        }

        return lines;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        ReadLinesFromFile readLinesFromFile = new ReadLinesFromFile();
        List<String> lines = readLinesFromFile.readFromFile("C:/Workspaces/Tools/src/de/gecko/tools/lines.txt");

        int counter = 0;
        for (String line : lines) {
            System.out.println(++counter + " : " + line);
        }

    }

}
