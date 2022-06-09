/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author daniel kohout
 */
public class Library implements Files {

    private File newFile;
    private String name;
    private String folder;

    private ArrayList<Word> words;

    public Library(String name, String folder) {
        words = new ArrayList<>();
        this.name = name;
        this.folder = folder;
        createFile(name, folder);
    }

    /**
     * @return name of the file
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * creates file if file doesn't exist, if file exists it assignes File to it
     *
     * @param name - is name of the file without .txt, example "file_01"
     * @param folder - is folder of the file, example "data/"
     */
    @Override
    public void createFile(String name, String folder) {
        String a = name + ".txt";
        try {
            newFile = new File(folder + a);
            if (newFile.getParentFile() != null) {
                newFile.getParentFile().mkdirs();
            }
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());

            } else {
                assignToExistingFile(name, folder);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating file data. " + e.getMessage());
        }
    }

    /**
     * assigns File to existing file and reads it
     *
     * @param name
     * @param folder
     */
    private void assignToExistingFile(String name, String folder) throws IOException {
        String a = name + ".txt";
        newFile = new File(folder + a);
        if (newFile.exists()) {
            readFile();
        }
    }

    /**
     * method reads txt file
     */
    public void readFile() throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(newFile))) {
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

    /**
     * method for saving changes into file, does nothing for the library
     */
    @Override
    public void overwriteFile() {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(newFile)))) {
            for (Word w : words) {
                pw.println(w.getWord() + "=" + w.getTransl());
            }
        } catch (IOException e) {
            System.out.println("IOException in creating Library " + name);
        }
    }

    /**
     * @return size of the array words
     */
    @Override
    public int getNumOfWords() {
        return words.size();
    }

    /**
     * @param index
     * @return
     */
    @Override
    public Word getWord(int index) {
        return words.get(index);
    }

    /**
     * @return true if person knows all words in a file
     */
    @Override
    public boolean knowAllWords() {
        for (Word w : words) {
            if (!w.getKnow()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return how many words does person know from the file
     */
    @Override
    public int nLearnedWords() {
        int i = 0;
        for (Word w : words) {
            if (w.getKnow()) {
                i++;
            }
        }
        return i;
    }

    /**
     * @param a word in first language
     * @param b word in second language
     */
    public void addNewWord(String a, String b) {
        Word word = new Word(a, b);
        words.add(word);

    }
}
