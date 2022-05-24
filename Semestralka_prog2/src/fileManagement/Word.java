/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileManagement;

/**
 * container
 *
 * @author daniel kohout
 */
public class Word implements Comparable<Word> {

    final private String word;
    final private String translation;
    private boolean knowAlready = false;
//obtiznost slovicka atd.
//ulozit si kde sem skoncil
//urovne anglictiny slovicka pro danou uroven

    public Word(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public String getWord() {
        return word;
    }

    public String getTransl() {
        return translation;
    }

    public void know() {
        knowAlready = true;
    }

    @Override
    public int compareTo(Word s) {
        return s.getWord().compareTo(getWord());
    }

    @Override
    public String toString() {
        return getWord() + " = " + getTransl();
    }
}
