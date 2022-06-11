/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileManagement;

/**
 * container class
 *
 * @author daniel kohout
 */
public class Word implements Comparable<Word> {

    final private String word;
    final private String translation;
    private boolean knowAlready = false;

    /**
     * constructor of class Word
     *
     * @param word - String for word
     * @param translation - String for translation
     */
    public Word(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    /**
     * @return word
     */
    public String getWord() {
        return word;
    }

    /**
     * @return translation
     */
    public String getTransl() {
        return translation;
    }

    /**
     * method sets knowAlready to true
     */
    public void know() {
        this.knowAlready = true;
    }

    /**
     * @return boolean knowAlready
     */
    public boolean getKnow() {
        return knowAlready;
    }

    /**
     * default comparator by word
     *
     * @param s
     */
    @Override
    public int compareTo(Word s) {
        return s.getWord().compareTo(getWord());
    }

    /**
     * method returns formatted string of word and translation
     */
    @Override
    public String toString() {
        return String.format("%s = %s ", getWord(), getTransl());
    }

}
