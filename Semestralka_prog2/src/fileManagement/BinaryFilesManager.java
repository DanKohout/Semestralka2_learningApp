/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileManagement;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This static class is for reading and saving from binary files. In binary
 * files is kept information about when (date),from where(name of file) and how
 * many words did somebody learned.
 *
 * @author daniel kohout
 */
public class BinaryFilesManager {

    private static ArrayList<LocalDate> when;
    private static ArrayList<String> fromWhere;
    private static ArrayList<Integer> numOfWords;
    private static int helpingNumber = 0;
    private static File f = new File("data/data2.dat");

    /**
     * @param l - LocalDate - date of the record
     * @param fileName
     * @param numOfWordsLearned
     * @return true if the record was added
     */
    public static boolean addRecord(LocalDate l, String fileName, int numOfWordsLearned) {
        try {
            if (f.exists()) {
                readBinaryResults(f);
                when.add(l);
                fromWhere.add(fileName);
                numOfWords.add(numOfWordsLearned);
                saveToBinary(f);
                return true;
            } else {
                f.createNewFile();
                System.out.println("file data/data2.dat vytvořen");
                addRecord(l, fileName, numOfWordsLearned);
                return true;
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found exception!!!!!!!!!!\n" + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    /**
     *
     */
    public static void addRecord(int year, int month, int day, String fileName, int numOfWordsLearned) {
        LocalDate l = LocalDate.parse(formatDate(year, month, day));
        addRecord(l, fileName, numOfWordsLearned);
    }

    public static void startup() {
        when = new ArrayList<>();
        fromWhere = new ArrayList<String>();
        numOfWords = new ArrayList<Integer>();
        try {
            if (f.exists()) {
                readBinaryResults(f);
            } else {
                f.createNewFile();
                System.out.println("file data/data2.dat vytvořen");
                readBinaryResults(f);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found exception!!!!!!!!!!\n" + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * clearing/removing history/all records
     *
     * @return true if there was no problem in clearing history
     */
    public static boolean removeAllRecords() {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(f, false))) {
            readBinaryResults(f);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * writing inside the binary files all the information, for the LocalDate we
     * will use 3 Integers of format yyyy-mm-dd
     *
     * @param results
     * @throws java.io.FileNotFoundException
     */
    private static void saveToBinary(File results) throws FileNotFoundException, IOException {
        FileOutputStream outf;
        try (DataOutputStream out = new DataOutputStream(outf = new FileOutputStream(results, true))) {
            for (int i = helpingNumber; i < when.size(); i++) {

                int yyyy = when.get(i).getYear();
                int mm = when.get(i).getMonthValue();
                int dd = when.get(i).getDayOfMonth();

                String name = fromWhere.get(i);
                int number = numOfWords.get(i);

                out.writeInt(yyyy);
                out.writeInt(mm);
                out.writeInt(dd);
                out.writeUTF(name);
                out.writeInt(number);
            }
        }
    }

    /**
     *
     * @param results
     * @throws java.io.FileNotFoundException
     */
    private static void readBinaryResults(File results) throws FileNotFoundException, IOException {
        try (DataInputStream in = new DataInputStream(new FileInputStream(results))) {
            if (!when.isEmpty()) {
                when.clear();
                fromWhere.clear();
                numOfWords.clear();
            }
            boolean end = false;
            int i = 0;
            while (!end) {
                try {
                    int yyyy = in.readInt();
                    int mm = in.readInt();
                    int dd = in.readInt();
                    String name = in.readUTF();
                    int number = in.readInt();

                    when.add(LocalDate.parse(formatDate(yyyy, mm, dd)));
                    fromWhere.add(name);
                    numOfWords.add(number);
                    i++;
                    helpingNumber = i;
                } catch (EOFException e) {
                    end = true;
                }

            }
        }
    }

    private static String formatDate(int yyyy, int mm, int dd) {
        if (mm < 10 && dd < 10) {
            return (yyyy + "-0" + mm + "-0" + dd);
        } else if (mm < 10) {
            return (yyyy + "-0" + mm + "-" + dd);
        } else if (dd < 10) {
            return (yyyy + "-" + mm + "-0" + dd);
        } else {
            return (yyyy + "-" + mm + "-" + dd);
        }
    }

    public static String tooString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < when.size(); i++) {
            sb.append(String.format("%s %9s    %5d", when.get(i).toString(), fromWhere.get(i), numOfWords.get(i)));
            sb.append("\n");
        }
        return sb.toString();
    }
/*
    public static void main(String[] args) throws IOException {
        LocalDate l = LocalDate.parse("2020-02-01");
        readBinaryResults(f);
        when.add(l);
        fromWhere.add("filename");
        numOfWords.add(5);
        saveToBinary(f);
    }
*/
}
