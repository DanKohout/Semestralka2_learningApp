/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fileManagement;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author daniel kohout
 */
public interface Files {

    /**
     * @return name of the file
     */
    public String getName();

    /**
     * method for saving changes into file, first it deletes content and then
     * writes words from "words" (Arraylist< Word>) which should every
     * implementation have
     *
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public void overwriteFile() throws FileNotFoundException, IOException;

    /**
     * adding new word to ArrayList "words"
     *
     * @param a word in first language
     * @param b word in second language
     */
    public void addNewWord(String a, String b);

    /**
     * @return Word of desired index from ArrayList "words"
     */
    public Word getWord(int index);

    /**
     * removes word by its index
     */
    public void removeWord(int index);

    /**
     * removes word by its "name"
     */
    public void removeWord(String s);

    /**
     * overwrites Word in ArrayList "words" of index "index"
     */
    public void changeWord(int index, String a, String b);

    /**
     * @return number of words in ArrayList "words"
     */
    public int getNumOfWords();

    /**
     * @return formatted string of all words in ArrayList "words"
     */
    public String getAllWords();

    /**
     * @return true if all words in ArrayList "words" have property knowAlready
     * as true
     */
    public boolean knowAllWords();

}
