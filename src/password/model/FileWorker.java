package org.passgen.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileWorker {

    private static Scanner scanner;

    private static File file;

    private static void validateFile(String fileName) throws FileNotFoundException {
        file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }

    public static String readToString(String fileName) {
        String plainString = "";
        try {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                plainString += scanner.nextLine();
                plainString += "\n";
            }
            scanner.close();
            return plainString;
        } catch (FileNotFoundException e) {
            System.out.println("An error occured. You might not choose any file.");
        }
        return null;
    }

    public static String[][] readToTable(String fileName, String separator, int rows, int cols) {
        try {
            validateFile(fileName);
            scanner = new Scanner(file);
            String[][] data = new String[rows][cols];
            int i = 0;
            while (scanner.hasNextLine()) {
                if (i > rows) {
                    String buf = scanner.nextLine();
                    data[++i] = buf.split(separator);
                }
            }
            scanner.close();
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return null;
    }

    public static List<String> readLinesToList(String fileName) {
        try {
            scanner = new Scanner(new File(fileName));
            ArrayList<String> result = new ArrayList<>();
            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }

            scanner.close();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found!");
        }
        return null;
    }

    public static void write(String fileName, String text) {
        file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedwriter = new BufferedWriter(fileWriter);
            try {
                bufferedwriter.write(text);
            } finally {
                bufferedwriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write(String fileName, String[] text) {
        file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedwriter = new BufferedWriter(fileWriter);
            try {
                StringBuilder stringBuilder = new StringBuilder();
                for (String string :
                        text) {
                    stringBuilder.append(string + "\n");
                }
                bufferedwriter.write(stringBuilder.toString());
            } finally {
                bufferedwriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modifyTextFile(String fileName, String newText) throws FileNotFoundException {
        validateFile(fileName);
        String oldFile = readToString(fileName);
        StringBuilder sb = new StringBuilder();
        sb.append(oldFile);
        sb.append(newText);
        write(fileName, sb.toString());
    }

    public static void delete(String fileName) throws FileNotFoundException {
        validateFile(fileName);
        file.delete();
    }

}
