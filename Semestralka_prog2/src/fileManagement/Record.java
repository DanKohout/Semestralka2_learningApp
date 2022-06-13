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

    public Record(LocalDate date, String fileName, int n) {
        this.date = date;
        this.fileName = fileName;
        this.nWordsLearned = n;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFileName() {
        return fileName;
    }

    public int getNWordsLearned() {
        return nWordsLearned;
    }
}
