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
     * creates file in default folder
     *
     * @param name - name of the file (without .txt)
     * @param folder - example: "data/"
     * @throws java.io.IOException
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

    /**
     * @return name of the file
     */
    @Override
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
            if (newFile.getParentFile() != null) {
                newFile.getParentFile().mkdirs();
            } else {
                System.out.println("newFile getparentFile==null !!!");
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
     * @param folder
     * @param name
     */
    /*private void createFile(String name, String folder, boolean b) {
        String a = name + ".txt";
        try {//vytvareni souboru se slozkou data
            newFile = new File(folder + a);
            if (newFile.getParentFile() != null) {
                newFile.getParentFile().mkdirs();
            } else {
                System.out.println("newFile getparentFile==null !!!");
            }

            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());

            } else {
                assignToExistingFile(name, folder);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating file data. " + e.getMessage());
        }
    }*/
    /**
     */
    private void assignToExistingFile(String name, String folder) throws IOException {
        String a = name + ".txt";
        newFile = new File(folder + a);
        if (newFile.exists()) {
            System.out.println("file exists (success)");
            readFile(newFile);
        }
    }

    /**
     */
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

    @Override
    public void overwriteFile() throws FileNotFoundException, IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(newFile)))) {
            //new PrintWriter(new OutputStreamWriter () pouzit, kdyz chci kodovani
            int n = 1;
            for (Word w : words) {
                pw.println(w.getWord() + "=" + w.getTransl());
                n++;
            }
        }
    }

    /**
     * @param a word in first language
     * @param b word in second language
     */
    @Override
    public void addNewWord(String a, String b) {
        Word word = new Word(a, b);
        words.add(word);

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
     * @param index
     */
    @Override
    public void removeWord(int index) {
        words.remove(index);
    }

    /**
     * @param s
     */
    @Override
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
     * @param index
     * @param a
     * @param b
     */
    @Override
    public void changeWord(int index, String a, String b) {
        words.remove(index);
        words.add(new Word(a, b));
    }

    @Override
    public int getNumOfWords() {
        return words.size();
    }

    @Override
    public String getAllWords() {
        StringBuilder sb = new StringBuilder();
        for (Word w : words) {
            sb.append(w);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean knowAllWords() {
        for (Word w : words) {
            if (!w.getKnow()) {
                return false;
            }
        }
        return true;
    }

    public int nLearnedWords() {
        int i = 0;
        for (Word w : words) {
            if (w.getKnow()) {
                i++;
            }
        }
        return i;
    }

}
