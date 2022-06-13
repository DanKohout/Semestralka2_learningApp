/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileManagement;

import java.time.LocalDate;

/**
 * container class
 *
 * @author daniel kohout
 */
public class Record {

    final private LocalDate date;
    final private String fileName;
    final private int nWordsLearned;

    /**
     * Constructor of container Record
     *
     * @param date - (LocalDate) day when the Record was made
     * @param fileName - (String) name of the file
     * @param n - (integer) number of learned words
     */
    public Record(LocalDate date, String fileName, int n) {
        this.date = date;
        this.fileName = fileName;
        this.nWordsLearned = n;
    }

    /**
     * getter for date of the day when the Record was made
     *
     * @return LocalDate
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * getter for file name
     *
     * @return String name of the file
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return Integer number of words learned
     */
    public int getNWordsLearned() {
        return nWordsLearned;
    }
}
