/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileManagement;

import Utils.SortEnum;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * class made for creating and managing file that is read from and etc.
 *
 * @author daniel kohout
 */
public class MyFile implements Files {

    private File newFile;
    private String name;
    private String folder;

    private ArrayList<Word> words;//vocabulary

    /**
     * constructor for MyFile
     *
     * @param name - name of the file (without .txt)
     * @param folder - example: "data/"
     * @throws java.io.IOException
     */
    public MyFile(String name, String folder) throws IOException {
        words = new ArrayList<>();
        this.name = name;
        this.folder = folder;
        //createFile(name, folder);
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
     * @param folder
     * @param name
     */
    @Override
    public void createFile() {
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
     * method reads txt file and adds all words to Arraylist words
     *
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    @Override
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
     * method for saving changes into file, first it deletes content and then
     * writes words from "words" (Arraylist< Word>) which should every
     * implementation have
     *
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    @Override
    public void overwriteFile() throws FileNotFoundException, IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(newFile)))) {
            for (Word w : words) {
                pw.println(w.getWord() + "=" + w.getTransl());
            }
        }
    }

    /**
     * adds new word to memory
     *
     * @param a word in first language
     * @param b word in second language
     */
    public void addNewWord(String a, String b) {
        Word word = new Word(a, b);
        words.add(word);
    }

    /**
     * @param index
     * @return Word of desired index
     */
    @Override
    public Word getWord(int index) {
        return words.get(index);
    }

    /**
     * removes word by its index
     *
     * @param index
     */
    public void removeWord(int index) {
        words.remove(index);
    }

    /**
     * method removes word by its name
     *
     * @param s is its name
     */
    public void removeWord(String s) {
        int index = -1;
        for (Word w : words) {
            if (s.equals(w.getWord())) {
                index = words.indexOf(w);
                break;
            }
        }
        if (index == -1) {
            System.out.println("word not found");
        }
        words.remove(index);
    }

    /**
     * method removes word and adds another
     *
     * @param index
     * @param a
     * @param b
     */
    public void changeWord(int index, String a, String b) {
        words.remove(index);
        words.add(new Word(a, b));
    }

    /**
     * @return size of the array words
     */
    @Override
    public int getNumOfWords() {
        return words.size();
    }

    /**
     * @return String of formatted all words, similar to method toString
     */
    public String getAllWords() {
        StringBuilder sb = new StringBuilder();
        for (Word w : words) {
            sb.append(w);
            sb.append("\n");
        }
        return sb.toString();
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
     * Metoda pro seřazení kolekce podle slova či překladu
     *
     * @param method
     */
    public void sort(SortEnum method) {
        switch (method) {
            case word:
                Collections.sort(words);
                break;
            case translation:
                Collections.sort(words, SORT_BY_TRANSLATION);
                break;
        }
    }
    /**
     * new Comparator by translation
     */
    private static final Comparator<Word> SORT_BY_TRANSLATION = (Word t1, Word t2) -> {
        String transl = t1.getTransl();
        String transl2 = t2.getTransl();
        int value;
        if (transl == null) {
            transl = " ";
        }
        if (transl2 == null) {
            transl2 = " ";
        }
        value = transl.compareTo(transl2);
        return value;
    };

}
