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
 * Class is for reading and saving from binary files. In binary files is kept
 * information about when (date),from where(name of file) and how many words did
 * somebody learned.
 *
 * @author daniel kohout
 */
public class BinaryFilesManager {

    private ArrayList<LocalDate> when;
    private ArrayList<String> fromWhere;
    private ArrayList<Integer> numOfWords;

    /**
     * writing inside the binary files all the information, for the LocalDate we
     * will use 3 Integers of format yyyy-mm-dd
     *
     * @param results
     * @throws java.io.FileNotFoundException
     */
    public void saveToBinary(File results) throws FileNotFoundException, IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(results, true))) {

            for (int i = 0; i < when.size(); i++) {

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
            //sortByRunTime();
            /*out.writeInt(Files.size());
            for (Files runner : Files) {
                out.writeUTF(runner.getName());
                out.writeInt(runner.getSurName().length());
                for (int i = 0; i < runner.getSurName().length(); i++) {
                    out.writeChar(runner.getSurName().charAt(i));
                }
                out.writeInt(runner.getRunTime());
            }*/
        }
    }

    /**
     *
     * @param results
     * @return
     * @throws java.io.FileNotFoundException
     */
    public String readBinaryResults(File results) throws FileNotFoundException, IOException {
        //StringBuilder sb = new StringBuilder();
        try (DataInputStream in = new DataInputStream(new FileInputStream(results))) {
            boolean end = false;
            int i = 0;
            while (!end) {
                try {
                    int yyyy = in.readInt();
                    int mm = in.readInt();
                    int dd = in.readInt();
                    String name = in.readUTF();
                    int number = in.readInt();

                    if (mm < 10 && dd < 10) {
                        when.add(LocalDate.parse(yyyy + "-0" + mm + "-0" + dd));
                    } else if (mm < 10) {
                        when.add(LocalDate.parse(yyyy + "-0" + mm + "-" + dd));
                    } else if (dd < 10) {
                        when.add(LocalDate.parse(yyyy + "-" + mm + "-0" + dd));
                    } else {
                        when.add(LocalDate.parse(yyyy + "-" + mm + "-" + dd));
                    }
                    fromWhere.add(name);
                    numOfWords.add(number);
                    i++;
                } catch (EOFException e) {
                    end = true;
                }
            }
            /* boolean end = false;
            int nRunners, nLetters, time = 0;
            int rank = 1;
            String name = "", surname = "";
            while (!end) {
                try {
                    nRunners = in.readInt();
                    for (int i = 0; i < nRunners; i++) {
                        name = in.readUTF();
                        nLetters = in.readInt();
                        surname = "";
                        for (int j = 0; j < nLetters; j++) {
                            surname += in.readChar();
                        }
                        time = in.readInt();
                        sb.append(String.format("%2d. %10s%10s%10s%n", rank, name, surname, TimeTools.secondsToStringTime(time)));
                        rank++;
                    }
                    rank = 1;
                    sb.append("\n");
                } catch (EOFException e) {
                    end = true;
                }
            }*/
        }
        return "";//sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < when.size(); i++) {
            sb.append(String.format(" %s  %s  %d", when.get(i).toString(), fromWhere.get(i), numOfWords.get(i)));
            sb.append("\n");
        }
        return sb.toString();
    }
}
