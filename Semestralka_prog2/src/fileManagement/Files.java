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
     * creates file if file doesn't exist, if file exists it assignes File to it
     *
     * @param name - is name of the file without .txt, example "file_01"
     * @param folder - is folder of the file, example "data/"
     */
    public void createFile(String name, String folder);

    /**
     * method reads txt file
     */
    public void readFile() throws FileNotFoundException, IOException;

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
     * @return size of the array words
     */
    public int getNumOfWords();

    /**
     * @param index
     * @return
     */
    public Word getWord(int index);

    /**
     * @return true if person knows all words in a file
     */
    public boolean knowAllWords();

    /**
     * @return how many words does person know from the file
     */
    public int nLearnedWords();
}
