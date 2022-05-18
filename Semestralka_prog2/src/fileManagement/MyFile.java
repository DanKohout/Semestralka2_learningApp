/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * class made for creating and managing file that is read from and etc.
 *
 * @author daniel kohout
 */
public class MyFile {

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
    /* public MyFile(String name) {

    }*/
    public MyFile(String name, String folder) throws IOException {
        words = new ArrayList<>();
        this.name = name;
        this.folder = folder;
        createFile(name, folder);
        //assignToExistingFile(name, folder);
    }

    public String getName() {
        return name;
    }

    /**
     * @param folder
     * @param name
     */
    private void createFile(String name, String folder) {
        String a = /*folder + */ name + ".txt";
        try {//vytvareni souboru se slozkou data
            newFile = new File(folder + a);
            newFile.getParentFile().mkdirs();
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                assignToExistingFile(name, folder);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating file data. " + e.getMessage());
        }
    }

    private void assignToExistingFile(String name, String folder) throws IOException {
        String a = name + ".txt";
        newFile = new File(folder + a);
        if (newFile.exists()) {
            System.out.println("file exists (success)");
            readFile(newFile);
        }
    }

    private void readFile(File file) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int radek = 0;
            while (((line = br.readLine()) != null)) {
                radek++;
                String[] splitString = line.split("=");
                if (splitString.length == 2) {
                    addNewWord(splitString[0], splitString[1]);
                } else {
                    System.out.println("chyba na radku " + radek);
                }
            }
        }
    }

    /* 
    public boolean checkFileExists() {
        return false;
    }*/
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

    public int getNumOfWords() {
        return words.size();
    }

    public void writeInFile() {

    }

}
