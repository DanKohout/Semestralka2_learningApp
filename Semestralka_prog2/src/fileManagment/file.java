/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileManagment;

import java.io.File;
import java.util.ArrayList;

/**
 * class made for creating and managing file that is read from and etc.
 *
 * @author daniel kohout
 */
public class file {

    private File newFile;
    private String name;
    private String folder;
    //vocabulary
    private ArrayList<Word> words;

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
        Word word = new Word(a, b);
        words.add(word);

    }

    /**
     * @param index
     * @return
     */
    public Word getWord(int index) {
        Word word = new Word(words.get(index).getWord(), words.get(index).getTransl());
        return word;
    }

    /**
     * @param index
     */
    public void removeWord(int index) {
        words.remove(index);
    }

    /**
     * @param index
     * @param a
     * @param b
     */
    public void changeWord(int index, String a, String b) {
        words.remove(index);
        words.add(new Word(a, b));
    }

}
