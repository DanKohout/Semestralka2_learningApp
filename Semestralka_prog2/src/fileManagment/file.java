/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileManagment;

import java.io.File;
import java.util.ArrayList;

/**
 * class made for creating and managing file that is read from and etc.
 * @author daniel kohout
 */
public class file {

    private File newFile;
    private String name;
    private String folder;
    //words in first language
    private ArrayList<String> language1;
    //words in second language
    private ArrayList<String> language2;

    /**
     * creates file in default folder
     *
     * @param name
     */
    public file(String name) {

    }

    public file(String name, String folder) {
        createFile(name, folder);
    }

    /**
     * @param folder
     * @param name
     */
    private void createFile(String name, String folder) {
        newFile = new File(name + ".txt");
        if (newFile.exists()) {
            System.out.println("file already exists");
        }
    }

    /**
     * @return
     */
    public boolean checkFileExists() {
        return false;
    }

    /**
     * @param a word in first language
     * @param b word in second language
     */
    public void addNewWord(String a, String b) {
        language1.add(a);
        language2.add(b);
    }

    public Word getWord(int index) {
        Word word = new Word(language1.get(index), language2.get(index));
        return word;
    }

}
