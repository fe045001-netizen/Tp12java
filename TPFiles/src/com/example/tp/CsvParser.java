package com.example.tp;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CsvParser {

    public static List<Record> readCsv(String path) throws IOException {
        List<Record> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            String line;
            br.readLine(); // ignore header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double score = Double.parseDouble(parts[2].trim());
                list.add(new Record(id, name, score));
            }
        }
        return list;
    }

    public static void writeCsv(List<Record> records, String path) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            pw.println("id,name,score");
            for (Record r : records) {
                pw.println(r.getId() + "," + r.getName() + "," + r.getScore());
            }
        }
    }
}
